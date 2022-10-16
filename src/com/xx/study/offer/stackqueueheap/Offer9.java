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

    /**
     * step 1：push操作就正常push到第一个栈末尾。
     *
     * @param node
     */
    public void push(int node) {
        in.push(node);
    }

    /**
     * step 2：pop操作时， 判断第二个栈 是否为空 不为空 优先将第二个栈的元素弹出。
     * step 3：为空 第一个栈的元素 弹出并依次加入第二个栈中
     * step 4：判断第二个栈是否为空 为空  抛出 队列为空的异常 不为空 弹出第二个栈的第一个元素
     *
     * @return
     */
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

    /**
     * step 1：先将第二个栈的剩余元素依次push到第一个栈。
     * step 2：然后正常push到第一个栈末尾。
     *
     * @param node
     */
    public void push1(int node) {
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
        in.push(node);
    }

    /**
     * step 3：先将第一个栈的剩余元素依次push到第二个栈。
     * step 4：然后第二个栈pop第一个元素。
     *
     * @return
     */
    public int pop2() {
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        if (out.isEmpty()) {
            throw new RuntimeException("Queue is Empty");
        }
        return out.pop();
    }

}
