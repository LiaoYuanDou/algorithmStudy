package com.xx.study.offer.binarysearch;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/11.%20%E6%97%8B%E8%BD%AC%E6%95%B0%E7%BB%84%E7%9A%84%E6%9C%80%E5%B0%8F%E6%95%B0%E5%AD%97.md
 * <p>
 * 旋转数组的最小数字
 *
 * @Author XX
 * @Date 2022/11/8 22:48
 * @Version 1.0
 */
public class Offer11 {
    public static void main(String[] args) {
        int[] test = {3, 4, 5, 1, 2};
        System.out.println(minNumberInRotateArray(test));
        System.out.println(minNumberInRotateArray2(test));
    }

    /**
     * step 1：双指针指向旋转后数组的首尾，作为区间端点。
     * step 2：若是区间中点值大于区间右界值，则最小的数字一定在中点右边。
     * step 3：若是区间中点值等于区间右界值，则是不容易分辨最小数字在哪半个区间，比如[1,1,1,0,1]，应该逐个缩减右界。
     * step 4：若是区间中点值小于区间右界值，则最小的数字一定在中点左边。
     * step 5：通过调整区间最后即可锁定最小值所在。
     * @param array
     * @return
     */
    public static int minNumberInRotateArray(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else if (array[mid] == array[right]) {
                right--;
            } else {
                right = mid;
            }
        }
        return array[left];
    }

    /**
     * step 1：因为数组长度不小于1，因此最初的最小值可以设置为数组首元素或者int型最大值。
     * step 2：从左到右遍历整个数组，依次检查当前元素与记录元素的大小，若是当前元素更小，则记录最小的元素更新。
     * step 3：遍历结束，最终记录的最小值即是所求。
     * @param array
     * @return
     */
    public static int minNumberInRotateArray2(int [] array) {
        int res = array[0];
        for (int i : array) {
            res = Math.min(res,i);
        }
        return res;
    }
}
