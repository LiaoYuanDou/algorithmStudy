package com.xx.study.offer.greedy;

/**
 * @Author XX
 * @Date 2022/11/8 14:24
 * @Version 1.0
 */
public class Offer14 {

    public int cutRope(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int timeOf3 = n / 3;
        if (n - timeOf3 * 3 == 1) timeOf3--;
        int timeOf2 = (n - timeOf3 * 3) / 2;
        return (int) Math.pow(3, timeOf3) * (int) Math.pow(2, timeOf2);
    }

    public int cutRope2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), dp[j] * (i - j)));
            }
        }
        return dp[n];
    }

    /**
     * 动态规划
     *
     * step 1：检查当number不超过3的时候直接计算。
     * step 2：用dp数组表示长度为i的绳子可以被剪出来的最大乘积，初始化前面4个容易推断的。
     * step 3：遍历每个长度，对于每个长度的最大乘积，可以遍历从1到i的每个固定一段，按照上述公式求的最大值。
     * step 4：最后数组最后一位就是答案。
     * @param n
     * @return
     */
    public int cutRope3(int n) {
        if (n < 3) return n - 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        dp[4] = 4;
        for (int i = 5; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j));
            }
        }
        return dp[n];
    }

    /**
     *
     *
     * step 1：不超过3的直接计算
     * step 2：超过3的不断累乘3，然后number不断减去3，直到最后不超过4。
     * step 3：最后乘上剩余的数字。
     * @param n
     * @return
     */
    public int cutRope4(int n) {
        if (n <= 3) return n - 1;
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
}
