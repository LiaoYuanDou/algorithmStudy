package com.xx.study.offer.tree;

/**
 * @Author XX
 * @Date 2022/11/1 21:51
 * @Version 1.0
 */
public class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null; // 指向父结点的指针

    TreeLinkNode(int val) {
        this.val = val;
    }
}
