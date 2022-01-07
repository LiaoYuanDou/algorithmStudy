package com.xx.study.simple;

import java.util.*;

/**
 * @Author: XX
 * @Description: 二分查找
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 16:14 2021/8/26
 * @ModifiedDate:
 * @Copyright: ©
 */
public class BinarySearch {


    public static void main(String[] args) {
        System.err.println("\nresult=" + getMult("12", "11"));
        int[][] ints = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        /*rotate(ints);*/
        /*int[][] ints = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}};
        setZeroes(ints);
        findDiagonalOrder(ints);
        int[] test = {2, 7, 11, 15};
        twoSum(test, 9);*/
        /*int[] test1 = {2, 3, 1, 2, 4, 3};
        minSubArrayLen(7, test1);*/

        System.out.println(generate(5));
    }

    public int doBinarySearch(int searchKey, List<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (searchKey == list.get(mid)) {
                return mid + 1;
            } else if (searchKey > list.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static String getMult(String bigIntA, String bigIntB) {
        int maxLength = bigIntA.length() + bigIntB.length();
//      if (maxLength < 10) { // Integer.MAX_VALUE = 2147483647;
//          return String.valueOf(Integer.valueOf(bigIntA) * Integer.valueOf(bigIntB));
//      }
        int[] result = new int[maxLength];
        int[] aInts = new int[bigIntA.length()];
        int[] bInts = new int[bigIntB.length()];

        for (int i = 0; i < bigIntA.length(); i++) {
            aInts[i] = bigIntA.charAt(i) - '0';// bigIntA字符转化为数字存到数组
        }
        for (int i = 0; i < bigIntB.length(); i++) {
            bInts[i] = bigIntB.charAt(i) - '0';// bigIntB字符转化为数字存到数组
        }

        int curr; // 记录当前正在计算的位置，倒序
        int x, y, z; // 记录个位十位

        for (int i = bigIntB.length() - 1; i >= 0; i--) {
            curr = bigIntA.length() + i; // 实际为 maxLength - (bigIntB.length() - i) 简化得到
            for (int j = bigIntA.length() - 1; j >= 0; j--) {
                z = bInts[i] * aInts[j] + result[curr]; // 乘积 并加上 上一次计算的进位
                x = z % 10;// 个位
                y = z / 10;// 十位
                result[curr] = x; // 计算值存到数组c中
                result[curr - 1] += y; // curr-1表示前一位，这里是进位的意思
                curr--;
            }
        }

        int t = 0;
        for (; t < maxLength; t++) {
            if (result[t] != 0) {
                break; // 最前面的0都不输出
            }
        }
        StringBuilder builder = new StringBuilder();
        if (t == maxLength) { // 结果为0时
            builder.append('0');
        } else { // 结果不为0
            for (int i = t; i < maxLength; i++) {
                builder.append(result[i]);
            }
        }
        return builder.toString();
    }

    public static void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = i; j < matrix.length - (i + 1); j++) {
                // 相邻交换一次
                matrix[i][j] = matrix[i][j] ^ matrix[j][matrix.length - 1 - i];
                matrix[j][matrix.length - 1 - i] = matrix[i][j] ^ matrix[j][matrix.length - 1 - i];
                matrix[i][j] = matrix[i][j] ^ matrix[j][matrix.length - 1 - i];
                // 对角交换一次
                matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                matrix[matrix.length - 1 - i][matrix.length - 1 - j]
                        = matrix[i][j] ^ matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - 1 - i][matrix.length - 1 - j];
                // 相邻交换一次
                matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - 1 - j][i];
                matrix[matrix.length - 1 - j][i] = matrix[i][j] ^ matrix[matrix.length - 1 - j][i];
                matrix[i][j] = matrix[i][j] ^ matrix[matrix.length - 1 - j][i];
            }
        }

    }

    public static void setZeroes(int[][] matrix) {
        int len = matrix.length;
        int innerLen = matrix[0].length;
        boolean[] row = new boolean[len];
        boolean[] column = new boolean[innerLen];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < innerLen; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    column[j] = true;
                }
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < innerLen; j++) {
                if (row[i] || column[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }


    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0 || mat[0].length == 0) { //空矩阵判定
            return new int[0];
        }
        int m = mat.length; //行
        int n = mat[0].length; //列
        int[] list = new int[m * n]; //存放元素的新数组
        int count = 0; //计数
        int i = 0;
        int j = 0;
        boolean direct = true; //方向值（向上为true,向下为false）
        while (count < (m * n)) {
            if (direct) {
                //向上
                while (i >= 0 && j < n) { //到达边界则跳出循环
                    list[count] = mat[i][j]; //将遍历到的元素放入新数组
                    i--; //行减少
                    j++; //列增加
                    count++;
                }
                if (j == n) { //到达边界后进行两种情况判定（上边界还是右边界）
                    j--;
                    i = i + 2;
                } else { //右边界
                    i++;
                }
            } else {
                //向下
                while (i < m && j >= 0) {
                    list[count] = mat[i][j];
                    i++; //行增加
                    j--; //列减少
                    count++;
                }
                if (i == m) { //同上进行判定（下边界还是左边界）
                    i = i - 1;
                    j = j + 2;
                } else {
                    j++;
                }
            }
            direct = !direct; //到达边界后调整方向
        }
        return list;

    }


    public int strStr(String haystack, String needle) {
        if (haystack == null || haystack.length() == 0 || needle == null || needle.length() == 0) {
            return 0;
        }
        int m = haystack.length(), n = 0;
        int i = needle.length(), j = 0;
        int[] next = nextBuilder(needle);
        while (n < m && j < i) {
            if (j < 0 || haystack.charAt(n) == needle.charAt(j)) {
                n++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == i) {
            return n - j;
        } else {
            return -1;
        }

    }

    private int[] nextBuilder(String needle) {
        int m = needle.length();
        int[] next = new int[m];
        next[0] = -1;
        int t = -1, j = 0;
        while (j < m - 1) {
            if (t < 0 || needle.charAt(t) == needle.charAt(j)) {
                t++;
                j++;
                next[j] = t;
            } else {
                t = next[t];
            }
        }
        return next;
    }


    public void reverseString(char[] s) {
        if (s == null || s.length == 0) {
            return;
        }
        int i = 0, j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i++] = s[j];
            s[j--] = temp;
        }
    }

    public int arrayPairSum(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length % 2 != 0) {
            return 0;
        }
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            sum += nums[i];
        }
        return sum;
    }

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        if (numbers == null || numbers.length < 2) {
            return result;
        }
        Arrays.sort(numbers);

        out:
        for (int i = 0; i < numbers.length; i++) {
            int j = numbers.length - 1;
            while (j >= 0) {
                int sum = numbers[i] + numbers[j];
                if (sum > target) {
                    j--;
                } else if (sum < target) {
                    j = numbers.length - 1;
                    continue out;
                } else if (sum == target) {
                    result[0] = i + 1;
                    result[1] = j + 1;
                    break out;
                }
            }
        }
        return result;
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int sum = 0;
        int len = 0;
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            sum += nums[fast];
            while (sum >= target) {
                len = len == 0 ? (fast - slow + 1) : Math.min(len, fast - slow + 1);
                sum -= nums[slow++];
            }
        }
        return len;
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>(i+1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    temp.add(1);
                } else {
                    temp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(temp);
        }
        return res;
    }
}
