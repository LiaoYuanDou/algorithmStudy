package com.xx.study.offer.doublepointer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/57.1%20%E5%92%8C%E4%B8%BA%20S%20%E7%9A%84%E4%B8%A4%E4%B8%AA%E6%95%B0%E5%AD%97.md
 * <p>
 * 输入一个升序数组 array 和一个数字S，在数组中查找两个数，使得他们的和正好是S，
 * 如果有多对数字的和等于S，返回任意一组即可，如果无法找出这样的数字，返回一个空数组即可。
 *
 * @Author XX
 * @Date 2022/10/21 11:06
 * @Version 1.0
 */
public class Offer57 {
    public static void main(String[] args) {
        int[] test = {1, 2, 4, 7, 11, 15};
        int sum = 15;
        System.out.println(FindNumbersWithSum(test, sum));
        System.out.println(FindNumbersWithSum2(test, sum));

    }

    /**
     * 如果两个指针指向元素的和 sum == target，那么这两个元素即为所求。
     * 如果 sum > target，移动较大的元素，使 sum 变小一些；
     * 如果 sum < target，移动较小的元素，使 sum 变大一些。
     *
     * @param array
     * @param sum
     * @return
     */
    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (array == null || array.length == 0) {
            return res;
        }
        for (int a = 0, b = array.length - 1; a < b; ) {
            if ((array[a] + array[b]) == sum) {
                res.add(array[a]);
                res.add(array[b]);
                return res;
            } else if ((array[a] + array[b]) > sum) {
                b--;
            } else {
                a++;
            }
        }
        return res;
    }

    public static ArrayList<Integer> FindNumbersWithSum2(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (array == null || array.length == 0) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int idx = 0; idx < array.length; idx++) {
            int temp = sum - array[idx];
            if (map.containsKey(temp)) {
                res.add(temp);
                res.add(array[idx]);
                return res;
            } else {
                map.put(array[idx], idx);
            }
        }
        return res;
    }
}
