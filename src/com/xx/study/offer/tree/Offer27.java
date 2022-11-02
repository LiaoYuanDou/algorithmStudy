package com.xx.study.offer.tree;

import java.util.Stack;

/**
 * 二叉树的镜像
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/27.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E9%95%9C%E5%83%8F.md
 *
 * @Author XX
 * @Date 2022/11/2 19:50
 * @Version 1.0
 */
public class Offer27 {
    public static void main(String[] args) {

    }

    /**
     * step 1：先深度最左端的节点，遇到空树返回，处理最左端的两个子节点交换位置。
     * step 2：然后进入右子树，继续按照先左后右再回中的方式访问。
     * step 3：再返回到父问题，交换父问题两个子节点的值。
     *
     * @param pRoot
     * @return
     */
    public TreeNode Mirror(TreeNode pRoot) {
        if (pRoot == null) return null;
        swapNode(pRoot);
        Mirror(pRoot.left);
        Mirror(pRoot.right);
        return pRoot;
    }

    private void swapNode(TreeNode pRoot) {
        TreeNode node = pRoot.left;
        pRoot.left = pRoot.right;
        pRoot.right = node;
    }

    /**
     * step 1：优先检查空树的情况。
     * step 2：使用栈辅助遍历二叉树，根节点先进栈。
     * step 3：遍历过程中每次弹出栈中一个元素，然后该节点左右节点分别入栈。
     * step 4：同时我们交换入栈两个子节点的值，因为子节点已经入栈了再交换，就不怕后续没有交换。
     *
     * @param pRoot
     * @return
     */
    public TreeNode Mirror2(TreeNode pRoot) {
        if (pRoot == null) return null;
        Stack<TreeNode> s = new Stack<>();
        s.push(pRoot);
        while (!s.isEmpty()) {
            TreeNode node = s.pop();
            if (node.left != null) {
                s.push(node.left);
            }
            if (node.right != null) {
                s.push(node.right);
            }
            swapNode(node);
        }
        return pRoot;
    }
}
