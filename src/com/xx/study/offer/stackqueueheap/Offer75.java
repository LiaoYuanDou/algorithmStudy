package com.xx.study.offer.stackqueueheap;

import java.util.*;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/41.2%20%E5%AD%97%E7%AC%A6%E6%B5%81%E4%B8%AD%E7%AC%AC%E4%B8%80%E4%B8%AA%E4%B8%8D%E9%87%8D%E5%A4%8D%E7%9A%84%E5%AD%97%E7%AC%A6.md
 * <p>
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符 "go" 时，第一个只出现一次的字符是 "g"。
 * 当从该字符流中读出前六个字符“google" 时，第一个只出现一次的字符是 "l"。
 *
 * @Author XX
 * @Date 2022/10/20 19:56
 * @Version 1.0
 */
public class Offer75 {

    public static void main(String[] args) {
        StringBuilder stringBuilder1= new StringBuilder();
        StringBuilder stringBuilder2= new StringBuilder();
        StringBuilder stringBuilder3= new StringBuilder();

        for (char c : "google".toCharArray()) {
            Insert(c);
            Insert1(c);
            Insert2(c);
            stringBuilder1.append(FirstAppearingOnce());
            stringBuilder2.append(FirstAppearingOnce());
            stringBuilder3.append(FirstAppearingOnce());
        }
        System.out.println(stringBuilder1);
        System.out.println(stringBuilder2);
        System.out.println(stringBuilder3);
    }

    static List<Character> charList = new ArrayList<Character>();
    static HashMap<Character, Integer> charMap = new HashMap<Character, Integer>(128);
    static Map<Character, Integer> charLinkedMap = new LinkedHashMap<>(128);

    /**
     * 使用统计数组来统计每个字符出现的次数，本题涉及到的字符为都为 ASCII 码，因此使用一个大小为 128 的整型数组就能完成次数统计任务。
     *
     * 使用队列来存储到达的字符，并在每次有新的字符从字符流到达时移除队列头部那些出现次数不再是一次的元素。
     * 因为队列是先进先出顺序，因此队列头部的元素为第一次只出现一次的字符。
     */
    static int[] chs = new int[128];
    static Queue<Character> queue = new LinkedList<>();
    public static void Insert(char ch) {
        chs[ch]++;
        queue.offer(ch);
        while (!queue.isEmpty() && chs[queue.peek()] > 1) {
            queue.poll();
        }
    }

    public static char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }

    /**
     * step 1：准备一个List,LinkedHashMap来记录输入的字符流，用哈希表统计每个字符的次数，二者都是全局变量。
     * step 2：在Insert函数中对输入的字符，加入List，然后统计出现次数。
     * step 3：在FirstAppearingOnce函数遍历该List，对于每个字符查找哈希表，返回第一个计数为1的字符，如果遍历完字符串以后都没，则返回#。
     * @param ch
     */

    public static void Insert2(char ch) {
        charLinkedMap.put(ch, (charLinkedMap.getOrDefault(ch, 0) + 1));
    }

    public static char FirstAppearingOnce2() {
        for (Map.Entry<Character, Integer> entry : charLinkedMap.entrySet()) {
            char key = entry.getKey();
            Integer count = entry.getValue();
            if (count == 1) {
                return key;
            }
        }
        return '#';
    }

    public static void Insert1(char ch) {
        charList.add(ch);
        charMap.put(ch, (charMap.getOrDefault(ch, 0) + 1));
    }

    public static char FirstAppearingOnce1() {
        for (char c : charList) {
            if (charMap.get(c) == 1) {
                return c;
            }
        }
        return '#';
    }
}
