package com.xx.study.offer.stackqueueheap;

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
    public static void main(String[] args) {
        for (int i = 20; i >= 0; i = i - 2) {
            insert(i);
        }
        System.out.println(N);
        System.out.println(getMedian());
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
}
