package com.xx.study.offer.arraysandmatrices;

import java.util.BitSet;

/**
 * @Author: XX
 * @Description: 50. 第一个只出现一次的字符位置
 * 在一个字符串中找到第一个只出现一次的字符，并返回它的位置。字符串只包含 ASCII 码字符。
 * Input: abacc
 * Output: b
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/50.%20%E7%AC%AC%E4%B8%80%E4%B8%AA%E5%8F%AA%E5%87%BA%E7%8E%B0%E4%B8%80%E6%AC%A1%E7%9A%84%E5%AD%97%E7%AC%A6%E4%BD%8D%E7%BD%AE.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:52 2022/1/7
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer50 {
    public static void main(String[] args) {
        String testStr = "abacc";
        System.out.println(firstNotRepeatingChar(testStr));
        System.out.println(firstNotRepeatingChar2(testStr));
    }

    public static int firstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] cus = new int[128];
        for (int i = 0; i < str.length(); i++) {
            cus[str.charAt(i)]++;
        }
        for (int i = 0; i < str.length(); i++) {
            if (cus[str.charAt(i)] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int firstNotRepeatingChar2(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        BitSet bs1 = new BitSet(128);
        BitSet bs2 = new BitSet(128);
        for (char c : str.toCharArray()) {
            if (!bs1.get(c) && !bs2.get(c)) {
                bs1.set(c);
            } else if (bs1.get(c) && !bs2.get(c)) {
                bs2.set(c);
            }
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c)) {
                return i;
            }
        }
        return -1;
    }

}
