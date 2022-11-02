package com.xx.study.offer.tree;

import java.util.*;

/**
 * 按之字形顺序打印二叉树
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/32.3%20%E6%8C%89%E4%B9%8B%E5%AD%97%E5%BD%A2%E9%A1%BA%E5%BA%8F%E6%89%93%E5%8D%B0%E4%BA%8C%E5%8F%89%E6%A0%91.md
 *
 * @Author XX
 * @Date 2022/11/2 23:16
 * @Version 1.0
 */
public class Offer77 {

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        traverse(pRoot, res, 1);
        boolean flag = false;
        for (ArrayList<Integer> temp : res) {
            if (flag) {
                Collections.reverse(temp);
            }
            flag = !flag;
        }
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
        }
        traverse(root.left, res, deptoh + 1);
        traverse(root.right, res, deptoh + 1);
    }

    /**
     * step 1：首先判断二叉树是否为空，空树没有打印结果。
     * step 2：建立辅助队列，根节点首先进入队列。不管层次怎么访问，根节点一定是第一个，那它肯定排在队伍的最前面，初始化flag变量。
     * step 3：每次进入一层，统计队列中元素的个数，更改flag变量的值。因为每当访问完一层，下一层作为这一层的子节点，一定都加入队列，而再下一层还没有加入，因此此时队列中的元素个数就是这一层的元素个数。
     * step 4：每次遍历这一层这么多的节点数，将其依次从队列中弹出，然后加入这一行的一维数组中，如果它们有子节点，依次加入队列排队等待访问。
     * step 5：访问完这一层的元素后，根据flag变量决定将这个一维数组直接加入二维数组中还是反转后再加入，然后再访问下一层。
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print2(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(pRoot);
        boolean flag = false;
        while (!q.isEmpty()) {
            int size = q.size();
            ArrayList<Integer> rows = new ArrayList<>();
            while (size-- > 0) {
                TreeNode node = q.poll();
                rows.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            if (flag) Collections.reverse(rows);
            flag = !flag;
            res.add(rows);
        }
        return res;
    }

    /**
     * step 1：首先判断二叉树是否为空，空树没有打印结果。
     * step 2：建立两个辅助栈，每次依次访问第一个栈s1与第二个栈s2，根节点先进入s1.
     * step 3：依据依次访问的次序，s1必定记录的是奇数层，访问节点后，将它的子节点（如果有）依据先左后右的顺序加入s2，
     * 这样s2在访问的时候根据栈的先进后出原理就是右节点先访问，正好是偶数层需要的从右到左访问次序。
     * 偶数层则正好相反，要将子节点（如果有）依据先右后左的顺序加入s1，这样在s1访问的时候根据栈的先进后出原理就是左节点先访问，
     * 正好是奇数层需要的从左到右访问次序。
     * step 4：每次访问完一层，即一个栈为空，则将一维数组加入二维数组中，并清空以便下一层用来记录。
     *
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print3(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (pRoot == null) return res;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(pRoot);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            while (!s1.isEmpty()) {
                TreeNode node = s1.pop();
                temp.add(node.val);
                if (node.left != null) s2.push(node.left);
                if (node.right != null) s2.push(node.right);
            }
            if (temp.size() != 0) res.add(new ArrayList<Integer>(temp));
            temp.clear();
            while (!s2.isEmpty()) {
                TreeNode node = s2.pop();
                temp.add(node.val);
                if (node.right != null) s1.push(node.right);
                if (node.left != null) s1.push(node.left);
            }
            if (temp.size() != 0) res.add(new ArrayList<Integer>(temp));
            temp.clear();
        }
        return res;
    }
}
