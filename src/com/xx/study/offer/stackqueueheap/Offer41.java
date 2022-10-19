package com.xx.study.offer.stackqueueheap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: XX
 * @Description: 41.1 数据流中的中位数
 * 如何得到一个数据流中的中位数？
 * 如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
 * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/41.1%20%E6%95%B0%E6%8D%AE%E6%B5%81%E4%B8%AD%E7%9A%84%E4%B8%AD%E4%BD%8D%E6%95%B0.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 15:29 2022/1/8
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer41 {
    /**
     * step 1：我们可以维护两个堆，分别是大顶堆min，用于存储较小的值，其中顶部最大；小顶堆max，用于存储较大的值，其中顶部最小，则中位数只会在两个堆的堆顶出现。
     * step 2：我们可以约定奇数个元素时取大顶堆的顶部值，偶数个元素时取两堆顶的平均值，则可以发现两个堆的数据长度要么是相等的，要么奇数时大顶堆会多一个。
     * step 3：每次输入的数据流先进入大顶堆排序，然后将小顶堆的最大值弹入大顶堆中，完成整个的排序。
     * step 4：但是因为大顶堆的数据不可能会比小顶堆少一个，因此需要再比较二者的长度，若是小顶堆长度小于大顶堆，需要从大顶堆中弹出最小值到大顶堆中进行平衡。
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 20; i >= 0; i = i - 2) {
            insert2(i);
            insert3(i);
        }
        //System.out.println(N);
        System.out.println(getMedian2());
        System.out.println(getMedian3());

    }

    /* 大顶堆，存储左半边元素 */
    private static PriorityQueue<Integer> left = new PriorityQueue<Integer>((o1, o2) -> o2 - o1);
    /* 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
    private static PriorityQueue<Integer> right = new PriorityQueue<Integer>();
    /* 当前数据流读入的元素个数 */
    private static int N = 0;

    public static void insert(int val) {
        /* 插入要保证两个堆存于平衡状态 */
        if (N % 2 == 0) {
            /* N 为偶数的情况下插入到右半边。
             * 因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大，
             * 因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边 */
            left.add(val);
            right.add(left.poll());
        } else {
            right.add(val);
            left.add(right.poll());
        }
        N++;
    }

    public static Double getMedian() {
        if (N % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return Double.valueOf(right.peek());
        }
    }

    public static void insert2(int val) {
        left.offer(val);
        right.offer(left.poll());
        if (left.size() < right.size()) {
            left.offer(right.poll());
        }
    }

    public static Double getMedian2() {
        if (left.size() == right.size()) {
            return (left.peek() + right.peek()) / 2.0;
        } else {
            return Double.valueOf(left.peek());
        }
    }

    /**
     * step 1：用一数组存储输入的数据流。
     * step 2：Insert函数在插入的同时，遍历之前存储在数组中的数据，按照递增顺序依次插入，如此一来，加入的数据流便是有序的。
     * step 3：GetMedian函数可以根据下标直接访问中位数，分为数组为奇数个元素和偶数个元素两种情况。记得需要类型转换为double。
     */

    private static List<Integer> inputs = new ArrayList<>();

    public static void insert3(Integer num) {
        if (inputs.isEmpty()) {
            inputs.add(num);
        } else {
            int i = 0 ;
            for (; i < inputs.size(); i++) {
                if (num <= inputs.get(i)) {
                    break;
                }
            }
            inputs.add(i, num);
        }
    }

    public static Double getMedian3() {
        int size = inputs.size();
        int mid = size / 2;
        if ((size & 1) == 0) {
            int left = inputs.get(mid - 1);
            int right = inputs.get(mid);
            return (left + right) / 2.0;
        } else {
            return (double)inputs.get(mid);

        }
    }
}
