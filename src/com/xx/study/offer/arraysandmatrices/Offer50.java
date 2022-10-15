package com.xx.study.offer.arraysandmatrices;

import java.util.*;

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
        String testStr = "abdacc";
        System.out.println(firstNotRepeatingChar(testStr));
        System.out.println(firstNotRepeatingChar2(testStr));
        System.out.println(FirstNotRepeatingChar(testStr));
        System.out.println(FirstNotRepeatingChar2("google"));
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

    /**
     * 使用两个比特位就能存储这些信息 可以 使空间复杂度 简单。
     *
     * @param str
     * @return
     */
    public static int firstNotRepeatingChar2(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        char[] ch = str.toCharArray();
        BitSet bs1 = new BitSet(128);
        BitSet bs2 = new BitSet(128);
        for (char c : ch) {
            if (!bs1.get(c) && !bs2.get(c)) {
                bs1.set(c);
            } else if (bs1.get(c) && !bs2.get(c)) {
                bs2.set(c);
            }
        }
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (bs1.get(c) && !bs2.get(c)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 字符串只包含 ASCII 码字符 就可以使用长度为 128 的整型数组来存储每个字符出现的次数。
     *
     * @param str
     * @return
     */
    public static int FirstNotRepeatingChar(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        int[] chrs = new int[128];
        char[] ch = str.toCharArray();
        for (char c : ch) {
            chrs[c]++;
        }
        for (int i = 0; i < ch.length; i++) {
            if (chrs[ch[i]] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * step 1：利用哈希表记录字符串中出现过的字符的位置，利用两个队列分别记录字符与下标位置
     * step 2：遍历字符串，如果是没有遇到过的字符，就加入哈希表记录位置，同时字符与下标分别入队。
     * step 3：遇到出现过的字符，就将其哈希表中的下标置为-1，然后弹出队列首部所有重复的字符，即位置为-1的字符。
     * step 4：最后队列中剩余的队首就是第一个只出现一次的字符，因为其他的重复字符都被弹出了，队列为空就代表没有不重复的字符。
     * @param str
     * @return
     */
    public static int FirstNotRepeatingChar2(String str) {
        if (str == null || str.length() == 0) {
            return -1;
        }
        char[] ch = str.toCharArray();
        int length = ch.length;
        Map<Character, Integer> map = new HashMap<>(length);
        Queue<Character> qc = new LinkedList<>();
        Queue<Integer> qi = new LinkedList<>();
        for (int i = 0; i< length; i++) {
            char c = ch[i];
            if (map.containsKey(c)) {
                map.put(c, -1);
                while (!qc.isEmpty() && map.get(qc.peek()) == -1) {
                    qc.poll();
                    qi.poll();
                }
            } else {
                map.put(c, 1);
                qc.offer(c);
                qi.offer(i);
            }
        }
        return qc.isEmpty() ? -1 : qi.poll();
    }

}
