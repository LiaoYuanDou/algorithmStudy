package com.xx.study.offer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（我们约定空树不是任意一个树的子结构）
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/26.%20%E6%A0%91%E7%9A%84%E5%AD%90%E7%BB%93%E6%9E%84.md
 *
 * @Author XX
 * @Date 2022/11/1 22:52
 * @Version 1.0
 */
public class Offer26 {
    public static void main(String[] args) {

    }

    /**
     * step 1：因为空树不是任何树的子树，所以要先判断B树是否为空树。
     * step 2：当A树为空节点，但是B树还有节点的时候，不为子树，但是A树不为空节点，B树为空节点时可以是子树。
     * step 3：每次递归比较A树从当前节点开始，是否与B树完全一致，同步前序遍历。
     * step 4：A树自己再前序遍历进入子节点，当作子树起点再与B树同步遍历。
     * step 5：以上情况任意只要有一种即可。
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }

        boolean flag1 = recursion(root1, root2);
        boolean flag2 = HasSubtree(root1.left, root2);
        boolean flag3 = HasSubtree(root2.right, root2);
        return flag1 || flag2 || flag3;
    }

    private boolean recursion(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 != null) {
            return false;
        }
        if (root1 == null || root2 == null) {
            return true;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return recursion(root1.left, root2.left) && recursion(root1.right, root2.right);
    }

    /**
     * step 1：先判断空树，空树不为子结构。
     * step 2：利用队列辅助，层次遍历第一棵树，每次检查遍历到的节点是否和第二棵树的根节点相同。
     * step 3：若是相同，可以以该节点为子树根节点，再次借助队列辅助，同步层次遍历这个子树与第二棵树，
     * 这个时候以第二棵树为基，只要找到第二棵树的全部节点，就算找到了子结构。
     *
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree2(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root1);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.val == root2.val) {
                if (helper(node, root2)) {
                    return true;
                }
            }
            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
        return false;
    }

    private boolean helper(TreeNode root1, TreeNode root2) {
        Queue<TreeNode> q1 = new LinkedList<>();
        q1.offer(root1);
        Queue<TreeNode> q2 = new LinkedList<>();
        q2.offer(root1);
        while (!q2.isEmpty()) {
            TreeNode node1 = q1.poll();
            TreeNode node2 = q2.poll();
            if (node1 == null || node1.val != node2.val) {
                return false;
            }
            if (node2.left != null) {
                q1.offer(node1.left);
                q2.offer(node2.left);
            }
            if (node2.right != null) {
                q1.offer(node1.right);
                q2.offer(node2.right);
            }
        }
        return true;
    }
}
