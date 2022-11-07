package com.xx.study.offer.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的深度
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/55.1%20%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E6%B7%B1%E5%BA%A6.md
 *
 * @Author XX
 * @Date 2022/11/7 15:59
 * @Version 1.0
 */
public class Offer55 {

    /**
     * step 1：对于每个节点，若是不为空才能累计一次深度，若是为空，返回深度为0.
     * step 2：递归分别计算左子树与右子树的深度。
     * step 3：当前深度为两个子树深度较大值再加1。
     *
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(TreeDepth(root.left), TreeDepth(root.right)));
    }

    /**
     * step 1：既然是层次遍历，我们遍历完一层要怎么进入下一层，可以用队列记录这一层中节点的子节点。
     * 队列类似栈，只不过是一个先进先出的数据结构，可以理解为我们平时的食堂打饭的排队。因为每层都是按照从左到右开始访问的，
     * 那自然记录的子节点也是从左到右，那我们从队列出来的时候也是从左到右，完美契合。
     * step 2：在刚刚进入某一层的时候，队列中的元素个数就是当前层的节点数。
     * 比如第一层，根节点先入队，队列中只有一个节点，对应第一层只有一个节点，第一层访问结束后，它的子节点刚好都加入了队列，
     * 此时队列中的元素个数就是下一层的节点数。因此遍历的时候，每层开始统计该层个数，然后遍历相应节点数，精准进入下一层。
     * step 3：遍历完一层就可以节点深度就可以加1，直到遍历结束，即可得到最大深度。
     *
     * @param root
     * @return
     */
    public int TreeDepth2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int curSize = q.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            depth++;
        }
        return depth;
    }
}
