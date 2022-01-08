package com.xx.study.offer.stackqueueheap;

import java.util.Stack;

/**
 * @Author: XX
 * @Description: 30. 包含 min 函数的栈
 * 实现一个包含 min() 函数的栈，该方法返回当前栈中最小的值。
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/30.%20%E5%8C%85%E5%90%AB%20min%20%E5%87%BD%E6%95%B0%E7%9A%84%E6%A0%88.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:56 2022/1/8
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer30 {
    public static void main(String[] args) {

    }

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public void push(int node) {
        dataStack.push(node);
        minStack.push(minStack.isEmpty() ? node : Math.min(node, minStack.peek()));
    }

    public void pop() {
        dataStack.pop();
        minStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
