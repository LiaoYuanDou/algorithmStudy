package com.xx.study.offer.linked;

import static com.xx.study.offer.linked.ListNode.array2ListNode;

/**
 * @Author XX
 * @Date 2022/10/24 22:44
 * @Version 1.0
 */
public class Offer76 {

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 3, 4, 4, 5};
        System.out.println(deleteDuplication(array2ListNode(test)));
        System.out.println(deleteDuplication2(array2ListNode(test)));
        System.out.println(deleteDuplication3(array2ListNode(test)));

    }

    /**
     * step 1：遍历一次链表用哈希表记录每个节点值出现的次数。
     * step 2：在链表前加一个节点值为0的表头，方便可能的话删除表头元素。
     * step 3：再次遍历该链表，对于每个节点值检查哈希表中的计数，只留下计数为1的，其他情况都删除。
     * step 4：返回时去掉增加的表头。
     *
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication(ListNode pHead) {
        int[] dataSet = new int[1000];
        ListNode p = new ListNode(0);
        ListNode pre = p;
        p.next = pHead;
        ListNode curr = pHead;
        ListNode temp = pHead;
        while (temp != null) {
            dataSet[temp.val]++;
            temp = temp.next;
        }
        while (curr != null) {
            if (dataSet[curr.val] >= 2) {
                pre.next = curr.next;
            } else {
                pre = curr;
            }
            curr = curr.next;
        }
        return p.next;
    }

    /**
     * step 1：给链表前加上表头，方便可能的话删除第一个节点。
     * step 2：遍历链表，每次比较相邻两个节点，如果遇到了两个相邻节点相同，则新开内循环将这一段所有的相同都遍历过去。
     * step 3：在step 2中这一连串相同的节点前的节点直接连上后续第一个不相同值的节点。
     * step 4：返回时去掉添加的表头。
     *
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication2(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode res = new ListNode(0);
        res.next = pHead;
        ListNode curr = res;
        while (curr.next != null && curr.next.next != null) {
            if (curr.next.val == curr.next.next.val) {
                int temp = curr.next.val;
                while (curr.next != null && curr.next.val == temp) {
                    curr.next = curr.next.next;
                }
            } else {
                curr = curr.next;
            }
        }
        return res.next;
    }

    /**
     * 递归调用
     * @param pHead
     * @return
     */
    public static ListNode deleteDuplication3(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode next = pHead.next;
        if (pHead.val == next.val) {
            while (next != null && pHead.val == next.val) {
                next = next.next;
            }
            return deleteDuplication3(next);
        } else {
            pHead.next = deleteDuplication3(pHead.next);
            return pHead;
        }
    }
}
