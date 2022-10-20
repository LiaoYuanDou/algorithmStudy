package com.xx.study.offer.stackqueueheap;

import java.util.*;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/59.%20%E6%BB%91%E5%8A%A8%E7%AA%97%E5%8F%A3%E7%9A%84%E6%9C%80%E5%A4%A7%E5%80%BC.md
 * <p>
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
 * <p>
 * 例如，如果输入数组 {2, 3, 4, 2, 6, 2, 5, 1} 及滑动窗口的大小 3，那么一共存在 6 个滑动窗口，他们的最大值分别为 {4, 4, 6, 6, 6, 5}。
 *
 * @Author XX
 * @Date 2022/10/20 20:00
 * @Version 1.0
 */
public class Offer59 {

    public static void main(String[] args) {
        int[] test = {2, 3, 4, 2, 6, 2, 5, 1};
        int size = 3;
        System.out.println(maxInWindows(test, 3));
        System.out.println(maxInWindows2(test, 3));
        System.out.println(maxInWindows3(test, 3));
    }

    public static ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        if (num == null || num.length == 0 || size == 0 || num.length < size) {
            return res;
        }
        int max = num[0];
        for (int idx = 0; idx < size; idx++) {
            max = max >= num[idx] ? max : num[idx];
            queue.add(num[idx]);
        }
        res.add(max);
        for (int idx = size; idx < num.length; idx++) {
            queue.add(num[idx]);
            int pollNum = queue.poll();
            if (max == pollNum) {
                max = queue.peek();
                for (int i : queue) {
                    max = max >= i ? max : i;
                }
            } else {
                max = max >= num[idx] ? max : num[idx];
            }
            res.add(max);
        }
        return res;
    }

    /**
     * 维护一个大小为窗口大小的大顶堆，顶堆元素则为当前窗口的最大值。
     * <p>
     * 假设窗口的大小为 M，数组的长度为 N。在窗口向右移动时，需要先在堆中删除离开窗口的元素，并将新到达的元素添加到堆中，
     * 这两个操作的时间复杂度都为 log2M，因此算法的时间复杂度为 O(Nlog2M)，空间复杂度为 O(M)。
     *
     * @param num
     * @param size
     * @return
     */

    public static ArrayList<Integer> maxInWindows2(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length == 0 || size == 0 || num.length < size) {
            return res;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int idx = 0; idx < size; idx++) {
            heap.offer(num[idx]);
        }
        res.add(heap.peek());
        for (int idx1 = 0, idx2 = size; idx2 < num.length; idx2++, idx1++) {
            heap.remove(num[idx1]);
            heap.offer(num[idx2]);
            res.add(heap.peek());
        }
        return res;
    }

    /**
     * step 1：维护一个双向队列，用来存储数列的下标。
     * step 2：首先检查窗口大小与数组大小。
     * step 3：先遍历第一个窗口，如果即将进入队列的下标的值大于队列后方的值，依次将小于的值拿出来去掉，再加入，保证队列是递增序。
     * step 4：遍历后续窗口，每次取出队首就是最大值，如果某个下标已经过了窗口，则从队列前方将其弹出。
     * step 5：对于之后的窗口，重复step 3，直到数组结束。
     *
     * @param num
     * @param size
     * @return
     */
    public static ArrayList<Integer> maxInWindows3(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num == null || num.length == 0 || size == 0 || num.length < size) {
            return res;
        }
        ArrayDeque<Integer> dp = new ArrayDeque<Integer>();
        for (int idx = 0; idx < size; idx++) {
            while (!dp.isEmpty() && num[dp.peekLast()] < num[idx]) {
                dp.pollLast();
            }
            dp.add(idx);
        }
        for (int idx = size; idx < num.length; idx++) {
            res.add(num[dp.peekFirst()]);
            while (!dp.isEmpty() && dp.peekFirst() < (idx - size + 1)) {
                dp.pollFirst();
            }
            while (!dp.isEmpty() && num[dp.peekLast()] < num[idx]) {
                dp.pollLast();
            }
            dp.add(idx);
        }
        res.add(num[dp.pollFirst()]);
        return res;
    }
}
