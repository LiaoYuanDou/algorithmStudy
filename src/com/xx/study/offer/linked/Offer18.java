package com.xx.study.offer.linked;

/**
 * @Author XX
 * @Date 2022/10/23 22:30
 * @Version 1.0
 */
public class Offer18 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(2);
        ListNode listNode1 = new ListNode(5);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(9);
        listNode2.next = listNode3;
        listNode1.next = listNode2;
        listNode.next = listNode1;
        //System.out.println(deleteNode(listNode, 5));
        System.out.println(deleteNode(listNode, 5));
    }

    /**
     * step 1：首先我们加入一个头部节点，方便于如果可能的话删除掉第一个元素。
     * step 2：准备两个指针遍历链表，一个指针指向当前要遍历的元素，另一个指针指向该元素的前序节点，便于获取它的指针。
     * step 3：遍历链表，找到目标节点，则断开连接，指向后一个。
     * step 4：返回时去掉我们加入的头节点。
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode temp = null;
        ListNode change = head;
        while (change != null) {
            if (change.val == val) {
                if (temp == null) {
                    head = head.next;
                    break;
                } else {
                    ListNode next = change.next;
                    temp.next = next;
                    change.next = null;
                    break;
                }
            }
            temp = change;
            change = change.next;
        }
        return head;
    }

    public static ListNode deleteNode2(ListNode head, int val) {
        // write code here
        if (head == null) {
            return head;
        }
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode curr = head;
        ListNode pre = res;
        while (curr != null) {
            if (curr.val == val) {
                pre.next = curr.next;
                break;
            }
            pre = curr;
            curr = curr.next;
        }
        return res.next;
    }
}
