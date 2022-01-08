package com.xx.study.offer.stackqueueheap;

import java.util.Stack;

/**
 * @Author: XX
 * @Description: 9. 用两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/9.%20%E7%94%A8%E4%B8%A4%E4%B8%AA%E6%A0%88%E5%AE%9E%E7%8E%B0%E9%98%9F%E5%88%97.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:51 2022/1/8
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer9 {

    public static void main(String[] args) {


    }

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public void push(int node) {
        in.push(node);
    }

    public Integer pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        if (out.isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return out.pop();
    }
}
