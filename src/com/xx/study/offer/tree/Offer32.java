package com.xx.study.offer.tree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

/**
 * @Author XX
 * @Date 2022/11/2 20:59
 * @Version 1.0
 */
public class Offer32 {
    public static void main(String[] args) {

    }

    /**
     * step 1：首先判断二叉树是否为空，空树没有遍历结果。
     * step 2：建立辅助队列，根节点首先进入队列。不管层次怎么访问，根节点一定是第一个，那它肯定排在队伍的最前面。
     * step 3：每次遍历队首节点，如果它们有子节点，依次加入队列排队等待访问。
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        //Queue<TreeNode> q = new ArrayDeque<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            res.add(node.val);
            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }
        return res;
    }

    /**
     * step 1：首先判断二叉树是否为空，空树没有遍历结果。
     * step 2：使用递归进行层次遍历输出，每次递归记录当前二叉树的深度，每当遍历到一个节点，如果为空直接返回。
     * step 3：如果遍历的节点不为空，输出二维数组中一维数组的个数（即代表了输出的行数）小于深度，说明这个节点应该是新的一层，
     * 我们在二维数组中增加一个一维数组，然后再加入二叉树元素。
     * step 4：如果不是step 3的情况说明这个深度我们已经有了数组，直接根据深度作为下标取出数组，将元素加在最后就可以了。
     * step 5：处理完这个节点，再依次递归进入左右节点，同时深度增加。因为我们进入递归的时候是先左后右，那么遍历的时候也是先左后右，正好是层次遍历的顺序。
     * step 6：最后将二维数组中的结果依次送入一维数组。
     *
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        ArrayList<ArrayList<Integer>> tempArrayList = new ArrayList<>();
        traverse(root, tempArrayList, 1);
        for (ArrayList<Integer> tempList : tempArrayList) {
            for (Integer temp : tempList) {
                res.add(temp);
            }
        }
        return res;
    }

    private void traverse(TreeNode root, ArrayList<ArrayList<Integer>> tempArrayList, int depth) {
        if (root == null) {
            return;
        } else {
            if (tempArrayList.size() < depth) {
                ArrayList<Integer> rows = new ArrayList<>();
                tempArrayList.add(rows);
                rows.add(root.val);
            } else {
                ArrayList<Integer> rows = tempArrayList.get(depth - 1);
                rows.add(root.val);
            }
            traverse(root.left, tempArrayList, depth + 1);
            traverse(root.right, tempArrayList, depth + 1);
        }
    }
}
