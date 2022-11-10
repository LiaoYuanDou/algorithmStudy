package com.xx.study.offer.search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author XX
 * @Date 2022/11/10 23:20
 * @Version 1.0
 */
public class Offer38 {

    public static void main(String[] args) {
        Offer38 offer38 = new Offer38();
        System.out.println(offer38.Permutation("aab"));
    }

    /**
     * step 1：先对字符串按照字典序排序，获取第一个排列情况。
     * step 2：准备一个空串暂存递归过程中组装的排列情况。使用额外的vis数组用于记录哪些位置的字符被加入了。
     * step 3：每次递归从头遍历字符串，获取字符加入：首先根据vis数组，已经加入的元素不能再次加入了；
     * 同时，如果当前的元素str[i]与同一层的前一个元素str[i-1]相同且str[i-1]已经用，也不需要将其纳入。
     * step 4：进入下一层递归前将vis数组当前位置标记为使用过。
     * step 5：回溯的时候需要修改vis数组当前位置标记，同时去掉刚刚加入字符串的元素，
     * step 6：临时字符串长度到达原串长度就是一种排列情况。
     * @param str
     * @return
     */
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return res;
        }

        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        boolean[] vis = new boolean[str.length()];
        //标记每个位置的字符是否被使用过
        Arrays.fill(vis, false);
        StringBuilder temp = new StringBuilder();
        recursion(res, charArray, vis, temp);
        return res;
    }

    private void recursion(ArrayList<String> res, char[] charArray, boolean[] vis, StringBuilder temp) {
        if (temp.length() == charArray.length) {
            res.add(new String(temp));
            return;
        }
        for (int idx = 0; idx < charArray.length; idx++) {
            if (vis[idx]) continue;
            //当前的元素charArray[i]与同一层的前一个元素str[i-1]相同且str[i-1]已经用过了
            if (idx > 0 && charArray[idx - 1] == charArray[idx] && !vis[idx - 1]) continue;
            vis[idx] = true;
            temp.append(charArray[idx]);
            recursion(res, charArray, vis, temp);
            //回溯
            vis[idx] = false;
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
