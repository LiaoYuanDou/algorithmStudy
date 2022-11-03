package com.xx.study.offer.tree;

import java.util.Stack;

/**
 * 二叉搜索树的后序遍历序列
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/33.%20%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E7%9A%84%E5%90%8E%E5%BA%8F%E9%81%8D%E5%8E%86%E5%BA%8F%E5%88%97.md
 *
 * @Author XX
 * @Date 2022/11/3 21:27
 * @Version 1.0
 */
public class Offer33 {

    public static void main(String[] args) {
        int[] test = {1, 3, 2};
        int[] test1 = {3, 1, 2};
        System.out.println(VerifySquenceOfBST(test));
        System.out.println(VerifySquenceOfBST(test1));
        System.out.println(VerifySquenceOfBST2(test));
        System.out.println(VerifySquenceOfBST2(test1));
    }

    /**
     * step 1：首先对于给定列表长度为0的特殊情况返回false
     * step 2：递归函数中返回条件为 last - first <= 1，返回true
     * step 3：递归函数中确定根节点为sequence[last]，然后从后往前遍历找到左右子树分割点，进行继续递归
     *
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        return verify(sequence, 0, sequence.length - 1);
    }

    private static boolean verify(int[] sequence, int first, int last) {
        if (last - first <= 1) return true;
        int rootVal = sequence[last];
        int curIndex = first;
        while (curIndex < last && sequence[curIndex] < rootVal) {
            curIndex++;
        }
        for (int idx = curIndex; idx < last; idx++) {
            if (sequence[idx] < rootVal) {
                return false;
            }
        }
        return verify(sequence, first, curIndex - 1) && verify(sequence, curIndex, last - 1);
    }

    /**
     * step 1：首先处理特殊情况，sequence为空的情况，返回false
     * step 2：维护一个单调栈s，不断迭代一个根节点root（初始化为INT_MAX）
     * step 3：通过循环反向遍历给定的列表，模拟逆序操作
     * step 4：循环内，如果当前遍历节点大于root，则返回false
     * step 5：如果当前节点小于栈顶节点且栈非空，则内循环更新root，并不断退栈，这一过程在找到当前节点的父节点
     * step 6：内循环结束后，将数字进入单调栈
     * @param sequence
     * @return
     */
    public static boolean VerifySquenceOfBST2(int[] sequence) {
        if (sequence == null || sequence.length == 0) return false;
        Stack<Integer> s = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int idx = sequence.length - 1; idx >= 0; idx--) {
            if (sequence[idx] > root) {
                return false;
            }
            while (!s.isEmpty() && s.peek() > sequence[idx]) {
                root = s.pop();
            }
            s.add(sequence[idx]);
        }
        return true;
    }
}
