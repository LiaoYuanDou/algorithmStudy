package com.xx.study.offer.doublepointer;

import java.util.Stack;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/58.1%20%E7%BF%BB%E8%BD%AC%E5%8D%95%E8%AF%8D%E9%A1%BA%E5%BA%8F%E5%88%97.md
 * <p>
 * 翻转单词顺序列
 * <p>
 * Input:
 * "I am a student."
 * <p>
 * Output:
 * "student. a am I"
 *
 * @Author XX
 * @Date 2022/10/22 20:54
 * @Version 1.0
 */
public class Offer73 {
    public static void main(String[] args) {
        System.out.println(ReverseSentence("nowcoder. a am I"));
        System.out.println(ReverseSentence2("nowcoder. a am I"));

    }

    /**
     * 先翻转每个单词，再翻转整个字符串。
     */

    public static String ReverseSentence(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        int length = str.length();
        int left = 0, right = 0;
        char[] chars = str.toCharArray();
        while (right <= length) {
            if (right == length || chars[right] == ' ') {
                reserve(chars, left, right - 1);
                left = right + 1;
            }
            right++;
        }
        reserve(chars, 0, length - 1);
        return new String(chars);
    }

    private static void reserve(char[] chars, int left, int right) {
        while (left < right) {
            swap(chars, left++, right--);
        }
    }

    private static void swap(char[] chars, int left, int right) {
        char temp = chars[right];
        chars[right] = chars[left];
        chars[left] = temp;
    }

    /**
     * step 1：遍历字符串，将整个字符串按照空格分割然后入栈。
     * step 2：遍历栈，将栈中内容弹出拼接成字符串。
     *
     * @param str
     * @return
     */
    public static String ReverseSentence2(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        Stack<String> stack = new Stack<String>();
        String[] ss = str.split(" ");
        for (String s : ss) {
            stack.push(s);
            stack.push(" ");
        }
        if (!stack.isEmpty()) {
            stack.pop();
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
