package com.xx.study.offer.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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
}
