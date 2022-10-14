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
        System.out.println(printMatrix(testMatrix));
        int[][] testMatrix1 = {{1, 2, 3, 4,5}};
        System.out.println(printMatrix1(testMatrix1));
        System.out.println(printMatrix2(testMatrix));
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

    public static ArrayList<Integer> printMatrix1(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int col1 = 0, col2 = matrix.length - 1;
        int row1 = 0, row2 = matrix[0].length - 1;
        while (col1 <= col2 && row1 <= row2) {
            for (int row = row1; row <= row2; row++) {
                result.add(matrix[col1][row]);
            }
            for (int col = col1 + 1; col <= col2; col++) {
                result.add(matrix[col][row2]);
            }
            if (col1 != col2) {
                for (int row = row2 - 1; row >= row1; row--) {
                    result.add(matrix[col2][row]);
                }
            }
            if (row1 != row2) {
                for (int col = col2 - 1; col > col1; col--) {
                    result.add(matrix[col][row1]);
                }
            }
            row1++;
            row2--;
            col1++;
            col2--;
        }
        return result;
    }

    /**
     * step 1：首先排除特殊情况
     * step 2：取矩阵四个边界值，顺时针螺旋循环矩阵，遍历的截止点是左右边界或者上下边界重合
     * step 3：首先对最上面的一排数据从左到右打印，到最右后 上边界+1 判断上边界是否大于下边界 大于跳出
     * step 4：然后输出到了右边，对最右边一列从上到下输出，到最底后 右边界-1，判断左边界是否大于右边界 大于跳出
     * step 5：然后对最下面一排从右到左进行遍历输出，到最左后 下边界-1，判断上边界是否大于下边界 大于跳出
     * step 6：然后输出到了左边，对最左边一列从下到上输出，到顶后 左边界+1，判断左边界是否大于右边界 大于跳出
     * step 7：重复上述3-6步骤直到循环结束。
     * @param matrix 输入数组
     * @return
     */
    public static ArrayList<Integer> printMatrix2(int [][] matrix) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        int up = 0, down = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;

        while (left <= right && up <= down) {
            for (int row = left; row <= right; row++) {
                result.add(matrix[up][row]);
            }
            up++;
            if (up > down) {
                break;
            }
            for (int col = up; col <= down; col++) {
                result.add(matrix[col][right]);
            }
            right--;
            if (left > right) {
                break;
            }
            for (int row = right; row >= left; row--) {
                result.add(matrix[down][row]);
            }
            down--;
            if (up > down) {
                break;
            }
            for (int col = down; col >= up; col--) {
                result.add(matrix[col][left]);
            }
            left++;
            if (left > right) {
                break;
            }
        }
        return result;
    }
}
