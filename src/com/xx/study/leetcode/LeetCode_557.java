package com.xx.study.leetcode;

/**
 * @Author: XX
 * @Description:
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:39 2021/11/26
 * @ModifiedDate:
 * @Copyright: ©
 * <p>
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 * <p>
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *  
 * <p>
 * 提示：
 * <p>
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 * 相关标签  双指针 字符串
 */
public class LeetCode_557 {

    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        System.out.println(reverseWords(str));
        System.out.println();
        System.out.println(reverseWordsStudy(str));
    }

    public static String reverseWords(String s) {
        String[] strS = s.split(" ");
        StringBuilder stb = new StringBuilder();
        for (int index = 0; index < strS.length; index++) {
            String str = strS[index];
            char[] chars = str.toCharArray();
            int i = 0, j = chars.length - 1;
            while (i < j) {
                char temp = chars[i];
                chars[i++] = chars[j];
                chars[j--] = temp;
            }
            stb.append(chars);
            if (index < strS.length - 1) {
                stb.append(" ");
            }
        }
        return stb.toString();
    }

    public static String reverseWordsStudy(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i <= j) {
            int start = i;
            while (i <= j && chars[i] != ' ') {
                i++;
            }
            int end = i - 1;
            while (start < end) {
                char temp = chars[start];
                chars[start++] = chars[end];
                chars[end--] = temp;
            }
            i++;
        }
        return new String(chars);
    }
}
