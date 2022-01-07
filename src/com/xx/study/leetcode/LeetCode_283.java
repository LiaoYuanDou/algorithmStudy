package com.xx.study.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @Author: XX
 * @Description:
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 15:02 2021/11/26
 * @ModifiedDate:
 * @Copyright: ©
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 */
public class LeetCode_283 {
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroesStudy(nums);
        for (int num : nums) {
            System.out.print(num + "  ");
        }
    }

    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length - 1;
        int slow = 0, fast = 0;
        for (; fast <= length; fast++) {
            if (nums[fast] != 0) {
                nums[slow++] = nums[fast];
            }
        }
        for (; slow <= length; slow++) {
            nums[slow] = 0;
        }
    }

    public static void moveZeroesStudy(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        int length = nums.length - 1;
        int index = 0;
        for (int i = 0; i <= length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }
        while (index <= length) {
            nums[index++] = 0;
        }
    }
}
