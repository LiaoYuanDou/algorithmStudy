package com.xx.study.offer.doublepointer;

import java.util.ArrayList;

/**
 * @Author XX
 * @Date 2022/10/21 11:06
 * @Version 1.0
 */
public class Offer74 {
    public static void main(String[] args) {
        System.out.println(FindContinuousSequence(9));
        System.out.println(FindContinuousSequence(100));
        System.out.println(FindContinuousSequence2(9));
        System.out.println(FindContinuousSequence2(100));
        System.out.println(FindContinuousSequence3(9));
        System.out.println(FindContinuousSequence3(100));
    }

    public static ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int start = 1, end = 2;
        int curSum = 3;
        while (end < sum) {
            if (curSum < sum) {
                end++;
                curSum += end;
            } else if (curSum > sum) {
                curSum -= start;
                start++;
            } else {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                res.add(list);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return res;
    }

    /**
     * 滑动窗口
     * 两个指针l、r指向区间首和区间尾，公式(start + end) * (end - start + 1) / 2计算区间内部的序列和，
     * 如果这个和刚好等于目标数，说明以该区间首开始的序列找到了，记录下区间内的序列，
     * 同时以左边开始的起点就没有序列了，于是左区间收缩；
     * 如果区间和大于目标数，说明该区间过长需要收缩，只能收缩左边；
     * 如果该区间和小于目标数，说明该区间过短需要扩大，只能向右扩大，移动区间尾。
     *
     * @param sum
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence2(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for (int start = 1, end = 2; start < end; ) {
            int total = (start + end) * (end - start + 1) / 2;
            if (total == sum) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                for (int i = start; i <= end; i++) {
                    list.add(i);
                }
                res.add(list);
                start++;
            } else if (total < sum) {
                end++;
            } else {
                start++;
            }
        }
        return res;
    }

    /**
     * 枚举
     * 从数字1开始枚举连续的数字，将其累加判断其是否等于目标，如果小于目标数则继续往后累加，如果大于目标数说明会超过，跳出，
     * 继续枚举下一个数字开始的情况（比如2，比如3），这样每次都取连续的序列，只有刚好累加和等于目标数才可以记录从开始到结束这一串数字，代表是一个符合的序列。
     * <p>
     * step 1：从1到目标值一半向下取整作为枚举的左区间，即每次序列开始的位置。
     * step 2：从每个区间首部开始，依次往后累加，如果大于目标值说明这一串序列找不到，换下一个起点。
     * step 3：如果加到某个数字刚好等于目标值，则记录从区间首到区间尾的数字。
     *
     * @param sum
     * @return
     */
    public static ArrayList<ArrayList<Integer>> FindContinuousSequence3(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int mid = (sum - 1) / 2;
        for (int i = 1; i <= mid; i++) {
            int total = 0;
            for (int j = i; ; j++) {
                total += j;
                if (total > sum) {
                    break;
                } else if (total == sum) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    for (int k = i; k <= j; k++) {
                        list.add(k);
                    }
                    res.add(list);
                    break;
                }
            }
        }
        return res;
    }
}
