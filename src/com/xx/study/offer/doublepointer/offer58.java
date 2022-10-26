package com.xx.study.offer.doublepointer;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/58.2%20%E5%B7%A6%E6%97%8B%E8%BD%AC%E5%AD%97%E7%AC%A6%E4%B8%B2.md
 * 左旋转字符串
 * 将字符串 S 从第 K 位置分隔成两个子字符串，并交换这两个子字符串的位置。
 *
 * @Author XX
 * @Date 2022/10/23 20:33
 * @Version 1.0
 */
public class offer58 {
    public static void main(String[] args) {
        System.out.println(LeftRotateString("abcXYZdef", 3));
        System.out.println(LeftRotateString2("abcXYZdef", 3));
    }

    /**
     * 翻转3次
     * step 1：因为n可能大于字符串长度，因此需要对长度length取余，因为每次长度为length的旋转相当于没有变化。
     * step 2：第一次就将左边的 0 ～（n-1）个元素单独翻转，因为它虽然移到了左边，但是逆序了。
     * step 3：第二次就将右边的 n ～length-1）个元素单独翻转，因此这部分也逆序了。
     * step 4：第三次将整个字符串翻转，得到字符串的逆序，它已经满足了左移的整体出现在了右边。
     *
     * @param str
     * @param n
     * @return
     */
    public static String LeftRotateString(String str, int n) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int length = str.length();
        n = n % length;
        char[] chars = str.toCharArray();
        reserve(chars, 0, n - 1);
        reserve(chars, n, length - 1);
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
     * step 1：因为n可能大于字符串长度，因此需要对长度length取余，因为每次长度为length的旋转相当于没有变化。
     * step 2：先遍历后 n ~ (length-1) 个字符，依次加入待返回的字符串中。
     * step 3：再遍历前 0 ~ (n-1) 个字符，依次加入待返回的字符串中。
     *
     * @param str
     * @param n
     * @return
     */
    public static String LeftRotateString2(String str, int n) {
        if (str == null || str.length() == 0) {
            return "";
        }
        int length = str.length();
        n %= length;
        StringBuilder res = new StringBuilder();
        for (int idx = n; idx < length; idx++) {
            res.append(str.charAt(idx));
        }
        for (int idx = 0; idx < n; idx++) {
            res.append(str.charAt(idx));
        }
        return res.toString();
    }
}
