package com.xx.study.offer.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 把二叉树打印成多行
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/32.2%20%E6%8A%8A%E4%BA%8C%E5%8F%89%E6%A0%91%E6%89%93%E5%8D%B0%E6%88%90%E5%A4%9A%E8%A1%8C.md
 *
 * @Author XX
 * @Date 2022/11/2 21:28
 * @Version 1.0
 */
public class Offer78 {
    /**
     * step 1：如果树为空，则返回空数组，没有任何打印结果。
     * step 2：使用队列辅助层次遍历，优先加入二叉树的根节点。
     * step 3：从根节点开始，每次进入一层的时候，记录队列的长度即本层的节点数，然后访问相应节点数全在同一个数组中，子节点加入队列中继续排队。
     * step 4：每次访问完一层将数组加入二维数组中再进入下一层。
     *
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(pRoot);
        while (!q.isEmpty()) {
            int cnt = q.size();
            ArrayList<Integer> rows = new ArrayList<>();
            while (cnt-- > 0) {
                TreeNode node = q.poll();
                rows.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(rows);
        }
        return res;
    }

    /**
     * step 1：记录输出的二维数组初始化为空，每到一层里面填出一个一维数组。
     * step 2：从根节点开始，深度为1开始进行递归，当前节点有值递归内容才继续进行，否则返回。
     * step 3：如果记录输出的二维数组长度小于当前层数，说明要新到了一层，我们新开辟一个一维数组加到最后。
     * step 4：因为“根左右”的顺序，同一层左边必定先访问，只需要根据层数在二维数组中找到相应的行号，添加在该行末尾就一定是层次遍历的次序。
     *
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        traverse(pRoot, res, 1);
        return res;
    }

    void traverse(TreeNode root, ArrayList<ArrayList<Integer>> res, int deptoh) {
        if (root == null) {
            return;
        } else {
            if (res.size() < deptoh) {
                ArrayList<Integer> rows = new ArrayList<>();
                res.add(rows);
                rows.add(root.val);
            } else {
                ArrayList<Integer> rows = res.get(deptoh - 1);
                rows.add(root.val);
            }
            traverse(root.left, res, deptoh + 1);
            traverse(root.right, res, deptoh + 1);
        }
    }
}
