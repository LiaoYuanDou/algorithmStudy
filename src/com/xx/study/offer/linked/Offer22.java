package com.xx.study.offer.linked;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/22.%20%E9%93%BE%E8%A1%A8%E4%B8%AD%E5%80%92%E6%95%B0%E7%AC%AC%20K%20%E4%B8%AA%E7%BB%93%E7%82%B9.md
 * <p>
 * 输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
 *
 * @Author XX
 * @Date 2022/10/26 18:59
 * @Version 1.0
 */
public class Offer22 {

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5};
        System.out.println(FindKthToTail(ListNode.array2ListNode(test), 2));
        System.out.println(FindKthToTail2(ListNode.array2ListNode(test), 2));

    }

    /**
     * step 1：准备一个快指针，从链表头开始，在链表上先走kkk步。
     * step 2：准备慢指针指向原始链表头，代表当前元素，则慢指针与快指针之间的距离一直都是kkk。
     * step 3：快慢指针同步移动，当快指针到达链表尾部的时候，慢指针正好到了倒数kkk个元素的位置。
     *
     * @param pHead
     * @param k
     * @return
     */
    public static ListNode FindKthToTail(ListNode pHead, int k) {
        if (pHead == null) {
            return null;
        }
        ListNode front = pHead;
        ListNode behind = pHead;
        for (int i = 0; i < k; i++) {
            if (front == null) {
                return null;
            } else {
                front = front.next;
            }
        }

        while (front != null) {
            front = front.next;
            behind = behind.next;
        }
        return behind;
    }

    /**
     * step 1：可以先遍历一次链表找到链表的长度。
     * step 2：然后比较链表长度是否比kkk小，如果比kkk小返回一个空节点。
     * step 3：如果链表足够长，则我们从头节点往后遍历n−kn-kn−k次即可找到所求。
     *
     * @param pHead
     * @param k
     * @return
     */
    public static ListNode FindKthToTail2(ListNode pHead, int k) {
        // write code here
        if (pHead == null) {
            return null;
        }
        int length = 0;
        ListNode temp = pHead;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        if (length < k) {
            return null;
        }
        temp = pHead;
        for (int i = 0; i < length - k; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
