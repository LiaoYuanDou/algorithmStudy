package com.xx.study.offer.arraysandmatrices;

import com.sun.imageio.plugins.wbmp.WBMPImageReader;

/**
 * @Author: XX
 * @Description: 5. 替换空格
 * 将一个字符串中的空格替换成 "%20"。
 * Input:
 * "A B"
 * Output:
 * "A%20B"
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/5.%20%E6%9B%BF%E6%8D%A2%E7%A9%BA%E6%A0%BC.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:51 2022/1/7
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer5 {
    public static void main(String[] args) {
        StringBuilder testSb = new StringBuilder("A B C ");
        System.out.println(replaceSpace(testSb));
        String s = testSb.toString();
        System.out.println(replaceSpace(s));

        System.out.println(replaceSpace2(s));

        System.out.println(replaceSpace3(s));

    }

    /**
     * 传入StringBuilder 先第一循环加长长度
     * 第二次循环使用从旧长度位置开始循环往后插入字符
     *
     * @param sb 传入StringBuilder
     * @return
     */
    public static String replaceSpace(StringBuilder sb) {
        if (sb == null || sb.length() == 0) {
            return "";
        }
        int idx1 = sb.length();
        for (int i = 0; i < idx1; i++) {
            if (sb.charAt(i) == ' ') {
                sb.append("  ");
            }
        }
        int idx2 = sb.length();
        while (idx1 > 0 && idx2 > idx1) {
            char c = sb.charAt(--idx1);
            if (c == ' ') {
                sb.setCharAt(--idx2, '0');
                sb.setCharAt(--idx2, '2');
                sb.setCharAt(--idx2, '%');
            } else {
                sb.setCharAt(--idx2, c);
            }
        }
        return sb.toString();
    }

    /**
     * 利用字符串先转换成字符数组
     * 循环得出新字符数组长度
     *
     * @param s 字符串
     * @return
     */

    public static String replaceSpace(String s) {
        // write code here
        if (s == null || s == "") {
            return s;
        }
        char[] cs = s.toCharArray();
        int length = s.length();
        for (char c : cs) {
            if (c == ' ') {
                length = length + 2;
            }
        }
        char[] newCs = new char[length];
        for (int idx = s.length() - 1; idx >= 0; idx--) {
            char c = cs[idx];
            if (c == ' ') {
                newCs[--length] = '0';
                newCs[--length] = '2';
                newCs[--length] = '%';
            } else {
                newCs[--length] = c;
            }
        }
        return new String(newCs);
    }

    /**
     * 直接新建立StringBuilder 循环一次旧字符串拼接
     *
     * @param s 字符串
     * @return
     */
    public static String replaceSpace2(String s) {
        // write code here
        if (s == null || s.length() == 0) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < s.length(); idx++) {
            char c = s.charAt(idx);
            if (c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 直接假设所有的字符串都是空格 length*3 最后再截取
     *
     * @param s 字符串
     * @return
     */
    public static String replaceSpace3(String s) {
        // write code here
        if (s == null || s.length() == 0) {
            return s;
        }
        int length = s.length();
        char[] ch = new char[length * 3];
        int idx = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                ch[idx++] = '%';
                ch[idx++] = '2';
                ch[idx++] = '0';
            } else {
                ch[idx++] = c;
            }
        }
        return new String(ch, 0, idx);
    }
}
