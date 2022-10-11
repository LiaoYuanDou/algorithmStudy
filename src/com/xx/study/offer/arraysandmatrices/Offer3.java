package com.xx.study.offer.arraysandmatrices;

import java.util.HashSet;

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
        int[] testNumS = {2,3,1,0,2,5,3};
        System.out.println(duplicate(testNumS));
        System.out.println(duplicate1(testNumS));
        System.out.println(duplicate2(testNumS));
        System.out.println(duplicate3(testNumS));
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


    /**
     * 数组重排序思想
     * @param numbers
     * @return
     */
    public static int duplicate1 (int[] numbers) {
        // write code here
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        int length = numbers.length ;
        int idx = 0;
        while (idx<length){
            if(numbers[idx]==idx){
                idx ++;
            }else {
                if(numbers[idx]==numbers[numbers[idx]]){
                    return numbers[idx];
                }
                swap(numbers,idx);
            }
        }
        return -1;
    }

    /**
     * 使用另外一个数组来记录出现次数
     * @param numbers
     * @return
     */
    public static int duplicate2 (int[] numbers) {
        // write code here
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        int length = numbers.length ;
        int[] calNums = new int[length];
        for (int number : numbers) {
            calNums[number]++;
            if (calNums[number] == 2) {
                return number;
            }
        }
        return -1;
    }

    /**
     * 使用set、map contains-key 思想
     * @param numbers
     * @return
     */
    public static int duplicate3 (int[] numbers) {
        // write code here
        if (numbers == null || numbers.length == 0) {
            return -1;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int number : numbers) {
          if(set.contains(number)){
              return number;
          }
          set.add(number);
        }
        return -1;
    }

    public static void swap(int[] numbers, int idx) {
        int temp = numbers[numbers[idx]];
        numbers[numbers[idx]] = numbers[idx];
        numbers[idx] = temp;
    }
}
