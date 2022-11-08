package com.xx.study.offer.greedy;

/**
 * 股票的最大利润
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/63.%20%E8%82%A1%E7%A5%A8%E7%9A%84%E6%9C%80%E5%A4%A7%E5%88%A9%E6%B6%A6.md
 *
 * @Author XX
 * @Date 2022/11/8 18:16
 * @Version 1.0
 */
public class Offer63 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int min = prices[0];
        int maxPrices = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            maxPrices = Math.max(maxPrices, prices[i] - min);
        }
        return maxPrices;
    }
}
