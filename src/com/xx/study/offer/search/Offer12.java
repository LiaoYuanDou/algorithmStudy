package com.xx.study.offer.search;

/**
 * 矩阵中的路径
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/12.%20%E7%9F%A9%E9%98%B5%E4%B8%AD%E7%9A%84%E8%B7%AF%E5%BE%84.md
 * <p>
 * 判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向上下左右移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 *
 * @Author XX
 * @Date 2022/11/9 21:28
 * @Version 1.0
 */
public class Offer12 {

    public static void main(String[] args) {
        Offer12 offer12 = new Offer12();
        String val = "ABCESFCSADEE";
        int rows = 3;
        int cols = 4;
        String path = "ABCCED";
        boolean res = offer12.hasPath(val, rows, cols, path);
        System.out.println(res);
    }

    /**
     * 代使用回溯法（backtracking）进行求解，它是一种暴力搜索方法，通过搜索所有可能的结果来求解问题。
     * 回溯法在一次搜索结束时需要进行回溯（回退），将这一次搜索过程中设置的状态进行清除，从而开始一次新的搜索过程。例如下图示例中，
     * 从 f 开始，下一步有 4 种搜索可能，如果先搜索 b，需要将 b 标记为已经使用，防止重复使用。在这一次搜索结束之后，
     * 需要将 b 的已经使用状态清除，并搜索 c。
     *
     * @param matrix string字符串
     * @param rows   int整型
     * @param cols   int整型
     * @param str    string字符串
     * @return bool布尔型
     */
    private int rows;
    private int cols;

    /**
     * 0.根据给定数组，初始化一个标志位数组，初始化为false，表示未走过，true表示已经走过，不能走第二次
     * 1.根据行数和列数，遍历数组，先找到一个与str字符串的第一个元素相匹配的矩阵元素，进入judge
     * <p>
     * 2.根据i和j先确定一维数组的位置，因为给定的matrix是一个一维数组
     * <p>
     * 3.确定递归终止条件：越界，当前找到的矩阵值不等于数组对应位置的值，已经走过的，这三类情况，都直接false，说明这条路不通
     * <p>
     * 4.若k，就是待判定的字符串str的索引已经判断到了最后一位，此时说明是匹配成功的
     * <p>
     * 5.下面就是本题的精髓，递归不断地寻找周围四个格子是否符合条件，只要有一个格子符合条件，就继续再找这个符合条件的格子的四周是否存在符合条件的格子，直到k到达末尾或者不满足递归条件就停止。
     * <p>
     * 6.走到这一步，说明本次是不成功的，我们要还原一下标志位数组index处的标志位，进入下一轮的判断。
     *
     * @param matrix
     * @param rows
     * @param cols
     * @param str
     * @return
     */
    public boolean hasPath(String matrix, int rows, int cols, String str) {
        if (rows == 0 || cols == 0) return false;
        char[] charArray = matrix.toCharArray();
        this.rows = rows;
        this.cols = cols;
        char[][] matrixChar = buildMatrix(charArray);
        char[] pathArray = str.toCharArray();
        boolean[][] marked = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtracking(matrixChar, pathArray, marked, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final static int[][] next = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private boolean backtracking(char[][] matrixChar, char[] pathArray, boolean[][] marked,
                                 int pathLen, int r, int c) {
        if (pathLen == pathArray.length) {
            return true;
        }
        if (r < 0 || r >= rows || c < 0 || c >= cols
                || matrixChar[r][c] != pathArray[pathLen] || marked[r][c]) {
            return false;
        }
        marked[r][c] = true;
        for (int[] n : next) {
            if (backtracking(matrixChar, pathArray, marked, pathLen + 1, r + n[0], c + n[1])) {
                return true;
            }
        }
        marked[r][c] = false;
        return false;
    }

    private char[][] buildMatrix(char[] charArray) {
        char[][] chars = new char[rows][cols];
        for (int r = 0, idx = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                chars[r][c] = charArray[idx++];
            }
        }
        return chars;
    }
}
