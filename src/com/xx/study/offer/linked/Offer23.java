package com.xx.study.offer.linked;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/23.%20%E9%93%BE%E8%A1%A8%E4%B8%AD%E7%8E%AF%E7%9A%84%E5%85%A5%E5%8F%A3%E7%BB%93%E7%82%B9.md
 * <p>
 * 链表中环的入口结点
 *
 * 在慢指针进入链表环之前，快指针已经进入了环，且在里面循环，这才能在慢指针进入环之后，快指针追到了慢指针，
 * 不妨假设快指针在环中走了n圈，慢指针在环中走了m圈，它们才相遇，而进入环之前的距离为x，环入口到相遇点的距离为y，
 * 相遇点到环入口的距离为zzz。快指针一共走了x+n(y+z)+y步，
 * 慢指针一共走了x+m(y+z)+y，这个时候快指针走的倍数是慢指针的两倍，则x+n(y+z)+y=2(x+m(y+z)+y)，
 * 这时候x+y=(n−2m)(y+z)，因为环的大小是y+z
 * 说明从链表头经过环入口到达相遇地方经过的距离等于整数倍环的大小：那我们从头开始遍历到相遇位置，
 * 和从相遇位置开始在环中遍历，会使用相同的步数，而双方最后都会经过入口到相遇位置这y个节点，
 * 那说明这y个节点它们就是重叠遍历的，那它们从入口位置就相遇了，这我们不就找到了吗？
 *
 * 具体做法：
 *
 * step 1：使用BM6.判断链表中是否有环中的方法判断链表是否有环，并找到相遇的节点。
 * step 2：慢指针继续在相遇节点，快指针回到链表头，两个指针同步逐个元素逐个元素开始遍历链表。
 * step 3：再次相遇的地方就是环的入口。
 *
 * @Author XX
 * @Date 2022/10/26 19:53
 * @Version 1.0
 */
public class Offer23 {

    public static void main(String[] args) {

    }

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode slow = findCycle(pHead);
        if (slow == null) {
            return null;
        }
        ListNode fast = pHead;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public ListNode findCycle(ListNode pHead) {
        if (pHead == null && pHead.next == null) {
            return null;
        }
        ListNode slow = pHead;
        ListNode fast = pHead;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return slow;
            }
        }
        return null;
    }
}
