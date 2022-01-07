package com.xx.study.offer.arraysandmatrices;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XX
 * @Description: 29. 顺时针打印矩阵
 * 按顺时针的方向，从外到里打印矩阵的值。下图的矩阵打印结果为：1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
 * [
 * [1,2,3,4],
 * [5,6,7,8],
 * [9,10,11,12],
 * [13,14,15,16]
 * ]
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/29.%20%E9%A1%BA%E6%97%B6%E9%92%88%E6%89%93%E5%8D%B0%E7%9F%A9%E9%98%B5.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:52 2022/1/7
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer29 {
    public static void main(String[] args) {
        int[][] testMatrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(testMatrix).forEach(System.out::println);
    }

    public static List<Integer> printMatrix(int[][] matrix) {
        List<Integer> resultList = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return resultList;
        }
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++) {
                resultList.add(matrix[r1][i]);
            }
            for (int i = r1 + 1; i <= r2; i++) {
                resultList.add(matrix[i][c2]);
            }
            if (r1 != r2) {
                for (int i = c2 - 1; i >= c1; i--) {
                    resultList.add(matrix[r2][i]);
                }
            }
            if (c1 != c2) {
                for (int i = r2 - 1; i > r1; i--) {
                    resultList.add(matrix[i][c1]);
                }
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return resultList;
    }
}
