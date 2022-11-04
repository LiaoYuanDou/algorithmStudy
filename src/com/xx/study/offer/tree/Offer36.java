package com.xx.study.offer.tree;

import java.util.Stack;

/**
 * 二叉搜索树与双向链表
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/36.%20%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%8E%E5%8F%8C%E5%90%91%E9%93%BE%E8%A1%A8.md
 * <p>
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * @Author XX
 * @Date 2022/11/4 21:59
 * @Version 1.0
 */
public class Offer36 {
    private TreeNode pre = null;
    private TreeNode head = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        inOrder(pRootOfTree);
        return head;
    }

    /**
     * step 1：创建两个指针，一个指向题目中要求的链表头（head），一个指向当前遍历的前一节点（pre)。
     * step 2：首先递归到最左，初始化head与pre。
     * step 3：然后处理中间根节点，依次连接pre与当前节点，连接后更新pre为当前节点。
     * step 4：最后递归进入右子树，继续处理。
     * step 5：递归出口即是节点为空则返回。
     *
     * @param node
     */
    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        pre = node;
        if (head == null) {
            head = node;
        }
        inOrder(node.right);
    }

    /**
     * step 1：创建两个指针，一个指向题目中要求的链表头（head），一个指向当前遍历的前一节点（pre)，创建一个布尔型变量，
     * 标记是否是第一次到最左，因为第一次到最左就是链表头。
     * step 2：判断空树不能连接。
     * step 3：初始化一个栈辅助中序遍历。
     * step 4：依次将父节点加入栈中，直接进入二叉树最左端。
     * step 5：第一次进入最左，初始化head与pre，然后进入它的根节点开始连接。
     * step 6：最后将右子树加入栈中，栈中依次就弹出“左中右”的节点顺序，直到栈为空。
     *
     * @param pRootOfTree
     * @return
     */
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;
        Stack<TreeNode> s = new Stack<>();
        TreeNode head = null;
        TreeNode pre = null;
        while (pRootOfTree != null || !s.isEmpty()) {
            while (pRootOfTree != null) {
                s.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            pRootOfTree = s.pop();
            if (head == null) {
                head = pRootOfTree;
                pre = head;
            } else {
                pre.right = pRootOfTree;
                pRootOfTree.left = pre;
                pre = pRootOfTree;
            }
            pRootOfTree = pRootOfTree.right;
        }
        return head;
    }
}
