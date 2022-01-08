package com.xx.study.offer.stackqueueheap;

import java.util.Stack;

/**
 * @Author: XX
 * @Description: 31. 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * <p>
 * 例如序列 1,2,3,4,5 是某栈的压入顺序，序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列，但 4,3,5,1,2 就不可能是该压栈序列的弹出序列。
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/31.%20%E6%A0%88%E7%9A%84%E5%8E%8B%E5%85%A5%E3%80%81%E5%BC%B9%E5%87%BA%E5%BA%8F%E5%88%97.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 11:04 2022/1/8
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer31 {

    public static void main(String[] args) {
        int[] pushSequence = {1,2,3,4,5};
        int[] popSequence1 = {4,5,3,2,1};
        int[] popSequence2 = {4,3,5,1,2};
        System.out.println(isPopOrder(pushSequence,popSequence1));
        System.out.println(isPopOrder(pushSequence,popSequence2));
    }

    public static boolean isPopOrder(int[] pushSequence, int[] popSequence) {
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushSequence[pushIndex]);
            while (popIndex < n && !stack.isEmpty() && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }
}
