package com.xx.study.offer.linked;

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
}
