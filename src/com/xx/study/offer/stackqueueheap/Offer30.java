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

    /**
     * step 1: 使用一个数据栈记录每次push的数据，进行正常的push pop top操作
     * step 2: 使用一个最小栈记录每次push的最小数据
     * step 3： 每次push数据的时候要与最小栈的栈顶元素比较，若是push元素小于最小栈的栈顶元素 就 push进入最小栈的栈顶
     * 若是push元素大于最小栈的栈顶元素 就 重复push最小栈的栈顶元素 因为这次push还是这个元素最小
     * step 4： pop的时候两个栈一起pop
     *
     * @param node
     */
    public void push1(int node) {
        dataStack.push(node);
        if (minStack.isEmpty() || minStack.peek() > node) {
            minStack.push(node);
        } else {
            minStack.push(minStack.peek());
        }
    }

}
