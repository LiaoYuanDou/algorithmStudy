package com.xx.study.offer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 对称的二叉树
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/28.%20%E5%AF%B9%E7%A7%B0%E7%9A%84%E4%BA%8C%E5%8F%89%E6%A0%91.md
 *
 * @Author XX
 * @Date 2022/11/2 20:18
 * @Version 1.0
 */
public class Offer28 {

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null) return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }

    /**
     * step 1：两种方向的前序遍历，同步过程中的当前两个节点，同为空，属于对称的范畴。
     * step 2：当前两个节点只有一个为空或者节点值不相等，已经不是对称的二叉树了。
     * step 3：第一个节点的左子树与第二个节点的右子树同步递归对比，第一个节点的右子树与第二个节点的左子树同步递归比较。
     *
     * @param left
     * @param right
     * @return
     */
    boolean isSymmetrical(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

    /**
     * step 1：首先判断链表是否为空，空链表直接就是对称。
     * step 2：准备两个队列，分别作为从左往右层次遍历和从右往左层次遍历的辅助容器，初始第一个队列加入左节点，第二个队列加入右节点。
     * step 3：循环中每次从队列分别取出一个节点，如果都为空，暂时可以说是对称的，进入下一轮检查；如果某一个为空或是两个节点值不同，那必定不对称。
     * 其他情况暂时对称，可以依次从左往右加入子节点到第一个队列，从右往左加入子节点到第二个队列。（这里包括空节点）
     * step 4：遍历结束也没有检查到不匹配，说明就是对称的。
     *
     * @param pRoot
     * @return
     */
    boolean isSymmetrical2(TreeNode pRoot) {
        if (pRoot == null) return true;
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(pRoot);
        q2.offer(pRoot);
        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode left = q1.poll();
            TreeNode right = q2.poll();
            if (left == null && right == null) {
                continue;
            }
            if (left == null || right == null || left.val != right.val) {
                return false;
            }
            q1.offer(left.left);
            q1.offer(left.right);
            q2.offer(right.right);
            q2.offer(right.left);
        }
        return true;
    }
}
