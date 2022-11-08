package com.xx.study.offer.binarysearch;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/53.%20%E6%95%B0%E5%AD%97%E5%9C%A8%E6%8E%92%E5%BA%8F%E6%95%B0%E7%BB%84%E4%B8%AD%E5%87%BA%E7%8E%B0%E7%9A%84%E6%AC%A1%E6%95%B0.md
 * <p>
 * 数字在排序数组中出现的次数
 *
 * @Author XX
 * @Date 2022/11/8 22:41
 * @Version 1.0
 */
public class Offer53 {

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 3, 3, 3, 4, 5};
        int[] test1 = {3};
        System.out.println(GetNumberOfK(test, 3));
        System.out.println(GetNumberOfK(test1, 3));
    }

    /**
     * step 1：写一个二分查找的函数在数组中找到某个元素出现的位置。每次检查区间中点值，根据与中点的大小比较，确定下一次的区间。
     * step 2：分别使用二分查找，找到k+0.5和k-0.5应该出现的位置，中间的部分就全是k，相减计算次数就可以了。
     *
     * @param array
     * @param k
     * @return
     */
    public static int GetNumberOfK(int[] array, int k) {
        return binarySearch(array, k + 0.5) - binarySearch(array, k - 0.5);
    }

    private static int binarySearch(int[] array, double k) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > k) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
