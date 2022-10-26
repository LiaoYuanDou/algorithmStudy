package com.xx.study.offer.linked;

import java.util.List;
import java.util.Stack;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/24.%20%E5%8F%8D%E8%BD%AC%E9%93%BE%E8%A1%A8.md
 * <p>
 * 反转链表
 *
 * @Author XX
 * @Date 2022/10/26 20:45
 * @Version 1.0
 */
public class Offer24 {

    public static void main(String[] args) {
        int[] test = {1,2,3};
        System.out.println(ReverseList(ListNode.array2ListNode(test)));
        System.out.println(ReverseList2(ListNode.array2ListNode(test)));
        System.out.println(ReverseList3(ListNode.array2ListNode(test)));
        System.out.println(ReverseList4(ListNode.array2ListNode(test)));

    }

    /**
     * 双链表求解是把原链表的结点一个个摘掉，每次摘掉的链表都让他成为新的链表的头结点，然后更新新链表。
     * <p>
     * 头插法
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList(ListNode head) {
        ListNode p = new ListNode(-1);
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            temp.next = p.next;
            p.next = temp;
        }
        return p.next;
    }

    public static ListNode ReverseList2(ListNode head) {
        ListNode newHead = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    /**
     * 简单的一种方式就是使用栈，因为栈是先进后出的。实现原理就是把链表节点一个个入栈，当全部入栈完之后再一个个出栈，出栈的时候在把出栈的结点串成一个新的链表
     *
     * @param head
     * @return
     */
    public static ListNode ReverseList3(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) {
            return null;
        }
        ListNode node = stack.pop();
        ListNode res = node;
        while (!stack.isEmpty()) {
            ListNode temp = stack.pop();
            node.next = temp;
            node = node.next;
        }
        node.next = null;
        return res;
    }

    public static ListNode ReverseList4(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        ListNode res = ReverseList4(next);
        next.next = head;
        head.next = null;
        return res;
    }
}
