package com.xx.study.offer.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/52.%20%E4%B8%A4%E4%B8%AA%E9%93%BE%E8%A1%A8%E7%9A%84%E7%AC%AC%E4%B8%80%E4%B8%AA%E5%85%AC%E5%85%B1%E7%BB%93%E7%82%B9.md
 * <p>
 * 两个链表的第一个公共结点
 *
 * @Author XX
 * @Date 2022/10/31 21:38
 * @Version 1.0
 */
public class Offer52 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode6 = new ListNode(6);
        ListNode listNode7 = new ListNode(7);

        listNode6.next = listNode7;
        listNode5.next = listNode6;
        listNode4.next = listNode5;
        listNode3.next = listNode6;
        listNode2.next = listNode3;
        listNode1.next = listNode2;

        System.out.println(FindFirstCommonNode(listNode1, listNode4));
        System.out.println(FindFirstCommonNode2(listNode1, listNode4));

    }

    /**
     * 使用两个指针N1,N2，一个从链表1的头节点开始遍历，我们记为N1，一个从链表2的头节点开始遍历，我们记为N2。
     * <p>
     * 让N1和N2一起遍历，当N1先走完链表1的尽头（为null）的时候，则从链表2的头节点继续遍历，同样，如果N2先走完了链表2的尽头，则从链表1的头节点继续遍历，也就是说，N1和N2都会遍历链表1和链表2。
     * <p>
     * 因为两个指针，同样的速度，走完同样长度（链表1+链表2），不管两条链表有无相同节点，都能够到达同时到达终点。
     * <p>
     * （N1最后肯定能到达链表2的终点，N2肯定能到达链表1的终点）。
     * <p>
     * 有公共节点的时候，N1和N2必会相遇，因为长度一样嘛，速度也一定，必会走到相同的地方的，所以当两者相等的时候，则会第一个公共的节点
     * 无公共节点的时候，此时N1和N2则都会走到终点，那么他们此时都是null，所以也算是相等了。
     *
     * @param pHead1
     * @param pHead2
     * @return
     */
    public static ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {
            l1 = (l1 == null) ? pHead2 : l1.next;
            l2 = (l2 == null) ? pHead1 : l2.next;
        }
        return l1;
    }

    public static ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        Map<ListNode, Integer> dataMap = new HashMap<>();
        while (pHead1 != null) {
            dataMap.put(pHead1, 1);
            pHead1 = pHead1.next;
        }
        while (pHead2 != null) {
            if (dataMap.containsKey(pHead2)) {
                return pHead2;
            } else {
                pHead2 = pHead2.next;
            }
        }
        return null;
    }
}
