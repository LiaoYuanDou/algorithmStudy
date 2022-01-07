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
    }

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
}
