package com.xx.study.offer.linked;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author XX
 * @Date 2022/10/23 21:20
 * @Version 1.0
 */
public class Offer6 {

    public static void main(String[] args) {
        //{1,2,3}
        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(3);
        listNode1.next = listNode2;
        listNode.next = listNode1;
        System.out.println(printListFromTailToHead(listNode));
        System.out.println(printListFromTailToHead3(listNode));
        System.out.println(printListFromTailToHead4(listNode));
        System.out.println(printListFromTailToHead2(listNode));


    }

    /**
     * 栈具有后进先出的特点，在遍历链表时将值按顺序放入栈中，最后出栈的顺序即为逆序。
     *
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }
        return res;
    }


    /**
     * 头插法顾名思义是将节点插入到头部：在遍历原始链表时，将当前节点插入新链表的头部，使其成为第一个节点。
     * <p>
     * 链表的操作需要维护后继关系，例如在某个节点 node1 之后插入一个节点 node2，我们可以通过修改后继关系来实现：
     *
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead2(ListNode listNode) {
        ListNode head = new ListNode(-1);
        while (listNode != null) {
            ListNode temp = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = temp;
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        head = head.next;
        while (head != null) {
            res.add(head.val);
            head = head.next;
        }
        return res;
    }

    public static ArrayList<Integer> printListFromTailToHead3(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<>();
        if (listNode != null) {
            res.addAll(printListFromTailToHead3(listNode.next));
            res.add(listNode.val);
        }
        return res;
    }

    /**
     * listNode 是链表，只能从头遍历到尾，但是输出却要求从尾到头，这是典型的"先进后出"，我们可以想到栈！
     * ArrayList 中有个方法是 add(index,value)，可以指定 index 位置插入 value 值
     * 所以我们在遍历 listNode 的同时将每个遇到的值插入到 list 的 0 位置，最后输出 listNode 即可得到逆序链表
     * @param listNode
     * @return
     */
    public static ArrayList<Integer> printListFromTailToHead4(ListNode listNode) {
        ArrayList<Integer>  res = new ArrayList<Integer>();
        while(listNode!=null){
            res.add(0,listNode.val);
            listNode = listNode.next;
        }
        return res;
    }
}
