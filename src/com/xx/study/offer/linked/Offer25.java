package com.xx.study.offer.linked;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/25.%20%E5%90%88%E5%B9%B6%E4%B8%A4%E4%B8%AA%E6%8E%92%E5%BA%8F%E7%9A%84%E9%93%BE%E8%A1%A8.md
 * <p>
 * 合并两个排序的链表
 *
 * @Author XX
 * @Date 2022/10/26 21:24
 * @Version 1.0
 */
public class Offer25 {
    public static void main(String[] args) {
        int[] test1 = {1, 3, 5};
        int[] test2 = {2, 4, 6};
        System.out.println(Merge(ListNode.array2ListNode(test1), ListNode.array2ListNode(test2)));
        System.out.println(Merge2(ListNode.array2ListNode(test1), ListNode.array2ListNode(test2)));

    }

    /**
     * 迭代
     * 设置result为哑结点，放置于新链表之前，最后返回的就是result.next；设置cur为当前节点，从result开始
     * 当两个链表都非空时进入循环，令新链表的下一个节点cur.next为val更小的节点，相应的链表节点后移一位，每次循环记得cur也要后移一位
     * 如果循环结束后还有链表非空，cur指向非空链表，返回result.next
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        while (list1 != null) {
            cur.next = list1;
            list1 = list1.next;
            cur = cur.next;
        }
        while (list2 != null) {
            cur.next = list2;
            list2 = list2.next;
            cur = cur.next;
        }
        return head.next;
    }

    /**
     * 递归
     * <p>
     * 如果有一个链表为空，返回另一个链表
     * 如果pHead1 节点值比小pHead2，下一个节点应该是 pHead1，应该return pHead1，在return之前，指定pHead1的下一个节点应该是pHead1.next和pHead2俩链表的合并后的头结点
     * 如果pHead1 节点值比pHead2大，下一个节点应该是pHead2，应该return pHead2，在return之前，指定pHead2的下一个节点应该是pHead1和pHead2.next俩链表的合并后的头结点
     *
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode Merge2(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 != null ? list1 : list2;
        }
        if (list1.val <= list2.val) {
            list1.next = Merge2(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge2(list1, list2.next);
            return list2;
        }
    }
}
