package com.xx.study.offer.tree;

/**
 * 二叉查找树的第 K 个结点
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/54.%20%E4%BA%8C%E5%8F%89%E6%9F%A5%E6%89%BE%E6%A0%91%E7%9A%84%E7%AC%AC%20K%20%E4%B8%AA%E7%BB%93%E7%82%B9.md
 *
 * @Author XX
 * @Date 2022/11/7 15:48
 * @Version 1.0
 */
public class Offer54 {

    private TreeNode ret = null;
    private int cnt = 0;

    public TreeNode KthNode(TreeNode pRoot, int k) {
        inOrder(pRoot, k);
        return ret;
    }

    /**
     * 利用二叉查找树中序遍历有序的特点。
     *
     * @param root
     * @param k
     */
    private void inOrder(TreeNode root, int k) {
        if (root == null || cnt > k) {
            return;
        }
        inOrder(root.left, k);
        cnt++;
        if (cnt == k) {
            ret = root;
        }
        inOrder(root.right, k);
    }
}
