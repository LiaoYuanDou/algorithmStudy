package com.xx.study.offer.linked;

import java.util.HashMap;
import java.util.Map;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/35.%20%E5%A4%8D%E6%9D%82%E9%93%BE%E8%A1%A8%E7%9A%84%E5%A4%8D%E5%88%B6.md
 * <p>
 *
 * <p>
 * 复杂链表的复制
 *
 * @Author XX
 * @Date 2022/10/26 21:35
 * @Version 1.0
 */
public class offer35 {

    public static void main(String[] args) {

    }

    /**
     * 链表拼接、拆分
     * 第一步，在每个节点的后面插入复制的节点。
     * 第二步，对复制节点的 random 链接进行赋值。
     * 第三步，拆分。
     *
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode cur = pHead;
        // 插入新节点
        while (cur != null) {
            RandomListNode cloneNode = new RandomListNode(cur.label);
            cloneNode.next = cur.next;
            cur.next = cloneNode;
            cur = cur.next.next;
        }
        cur = pHead;
        // 建立 random 链接
        while (cur != null) {
            RandomListNode next = cur.next;
            if (cur.random != null) {
                next.random = cur.random.next;
            }
            cur = next.next;
        }
        cur = pHead;
        RandomListNode cloneHeadNode = cur.next;
        // 拆分
        while (cur.next != null) {
            RandomListNode next = cur.next;
            cur.next = next.next;
            cur = next;
        }
        return cloneHeadNode;
    }


    /**
     * （哈希表）
     * （1）初始化哈希表dict，节点cur指向头节点
     * （2）复制链表；建立新节点，循环遍历链表，并向 dict 添加键值对 (原 cur 节点, 新 cur 节点）
     * （3）构建新链表的引用指向；cur遍历原链表，构建新节点的next、random引用指向
     * （4）返回新链表的头节点 dict[pHead]
     *
     * @param pHead
     * @return
     */
    public static RandomListNode Clone2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode cur = pHead;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = pHead;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(pHead);
    }
}
