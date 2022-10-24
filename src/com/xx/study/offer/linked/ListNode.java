package com.xx.study.offer.linked;

import java.util.List;

/**
 * @Author XX
 * @Date 2022/10/23 22:13
 * @Version 1.0
 */
public class ListNode {

    public Integer val;
    public ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }

    public static ListNode list2ListNode(List<Integer> sourceList){
        ListNode targetListNode = new ListNode(-1);
        ListNode curr = targetListNode;
        for (Integer source : sourceList) {
            ListNode sourceListNode = new ListNode(source);
            curr.next = sourceListNode;
            curr = sourceListNode;
        }
        return targetListNode.next;
    }

    public static ListNode array2ListNode(int[] sourceList){
        ListNode targetListNode = new ListNode(-1);
        ListNode curr = targetListNode;
        for (Integer source : sourceList) {
            ListNode sourceListNode = new ListNode(source);
            curr.next = sourceListNode;
            curr = sourceListNode;
        }
        return targetListNode.next;
    }

    public static void main(String[] args) {
        int[] test = {1,2,3,3,4,4,5};
        System.out.println(array2ListNode(test));
    }
}
