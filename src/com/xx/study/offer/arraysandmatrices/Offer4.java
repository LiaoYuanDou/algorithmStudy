package com.xx.study.offer.arraysandmatrices;

/**
 * @Author: XX
 * @Description: 4. 二维数组中的查找
 * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
 * Consider the following matrix:
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/4.%20%E4%BA%8C%E7%BB%B4%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E6%9F%A5%E6%89%BE.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:39 2022/1/7
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer4 {
    public static void main(String[] args) {
        int[][] testMatrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        System.out.println(find(5, testMatrix));
        System.out.println(find(20, testMatrix));
    }

    public static boolean find(int target, int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int high = matrix.length - 1;
        int width = matrix[0].length - 1;
        int highIdx = 0;
        int widthIdx = width;
        while (highIdx <= high && widthIdx >= 0) {
            if (target > matrix[highIdx][widthIdx]) {
                highIdx++;
            } else if (target < matrix[highIdx][widthIdx]) {
                widthIdx--;
            } else {
                return true;
            }
        }
        return false;
    }
}
