package com.xx.study.offer.divide;

/**
 * 数值的整数次方
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/16.%20%E6%95%B0%E5%80%BC%E7%9A%84%E6%95%B4%E6%95%B0%E6%AC%A1%E6%96%B9.md
 *
 * @Author XX
 * @Date 2022/11/9 21:02
 * @Version 1.0
 */
public class Offer16 {
    public static void main(String[] args) {
        System.out.println(Power(2.0, 5));
        System.out.println(Power2(2.0, 5));
    }

    public static double Power(double base, int exponent) {
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double res = pow(base, exponent);
        return isNegative ? 1 / res : res;
    }

    public static double pow(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;
        double res = pow(base, exponent >> 1);
        res *= res;
        if ((exponent & 1) == 1) res *= base;
        return res;
    }

    /**
     * step 1：先处理次方数为负数的情况，将底数化为分数解决。
     * step 2：使用快速幂计算次方：将已乘出来的部分求次方，可以每次缩小一半要求的次方数。
     *
     * @param base
     * @param exponent
     * @return
     */
    public static double Power2(double base, int exponent) {
        //处理负数次方
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }
        return pow2(base, exponent);
    }

    public static double pow2(double base, int exponent) {
        double res = 1;
        while (exponent != 0) {
            if ((exponent & 1) == 1) {
                res *= base;
            }
            base *= base;
            exponent = exponent >> 1;
        }
        return res;
    }
}
