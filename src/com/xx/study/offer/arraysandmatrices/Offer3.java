package com.xx.study.offer.arraysandmatrices;

/**
 * @Author: XX
 * @Description: 3. 数组中重复的数字
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字。
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/3.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E9%87%8D%E5%A4%8D%E7%9A%84%E6%95%B0%E5%AD%97.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:22 2022/1/7
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer3 {

    public static void main(String[] args) {
        int[] testNums = {3, 0, 2, 1, 2};
        System.out.println(duplicate(testNums));
    }

    public static int duplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i);
            }
        }

        return -1;
    }

    public static void swap(int[] nums, int i) {
        int temp = nums[i];
        nums[i] = nums[temp];
        nums[temp] = temp;
    }
}
