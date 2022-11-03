package com.xx.study.offer.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author XX
 * @Date 2022/11/3 21:59
 * @Version 1.0
 */
public class Offer34 {

    ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int expectNumber) {
        dfs(root, expectNumber);
        return ret;
    }

    /**
     * 知识点：深度优先搜索（dfs）
     * <p>
     * 深度优先搜索一般用于树或者图的遍历，其他有分支的（如二维矩阵）也适用。它的原理是从初始点开始，
     * 一直沿着同一个分支遍历，直到该分支结束，然后回溯到上一级继续沿着一个分支走到底，如此往复，直到所有的节点都有被访问到。
     * <p>
     * 思路：
     * <p>
     * 我们从根节点开始向左右子树进行递归，递归函数中需要处理的是：
     * <p>
     * 当前的路径path要更新
     * 当前的目标值expectNumber要迭代，减去当前节点的值
     * 若当前节点是叶子节点，考虑是否满足路径的期待值，并考虑是否将路径添加到返回列表中
     * 具体做法：
     * <p>
     * step 1：维护两个向量ret和path
     * step 2：编写递归函数dfs
     * step 3：递归函数内部要处理更新path，更新expectNumber，判断是否为叶子节点和判断是否要将path追加到ret末尾
     *
     * @param node
     * @param expectNumber
     */
    private void dfs(TreeNode node, int expectNumber) {
        if (node == null) return;
        path.add(node.val);
        expectNumber -= node.val;
        if (expectNumber == 0 && node.left == null && node.right == null) {
            ret.add(new ArrayList<>(path));
        }
        dfs(node.left, expectNumber);
        dfs(node.right, expectNumber);
        path.removeLast();
    }

    /**
     * 广度优先遍历（扩展思路）
     * 知识点：队列
     * <p>
     * 队列是一种仅支持在表尾进行插入操作、在表头进行删除操作的线性表，插入端称为队尾，删除端称为队首，因整体类似排队的队伍而得名。
     * 它满足先进先出的性质，元素入队即将新元素加在队列的尾，元素出队即将队首元素取出，它后一个作为新的队首。
     * <p>
     * 思路：
     * <p>
     * 深度优先搜索是优先一条路径寻到底，而广度优先搜索是按照二叉树的层进行搜索，因此路径需要逐层进行记录，我们引入队列来维护这个逐层路径的信息。
     * <p>
     * 具体做法：
     * <p>
     * step 1：通过维护队列来进行广度优先搜索
     * step 2：当我们遍历到叶子节点的层时，就进行与目标值的匹配
     * step 3：最终返回目标结果
     *
     * @param root
     * @param expectNumber
     * @return
     */
    public ArrayList<ArrayList<Integer>> FindPath2(TreeNode root, int expectNumber) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Queue<ArrayList<Integer>> pathQ = new LinkedList<>();
        Queue<TreeNode> nodeQ = new LinkedList<>();
        pathQ.offer(new ArrayList<>(Arrays.asList(root.val)));
        nodeQ.offer(root);
        while (!nodeQ.isEmpty()) {
            ArrayList<Integer> curPath = pathQ.poll();
            TreeNode node = nodeQ.poll();
            if (node.left != null) {
                ArrayList<Integer> left = new ArrayList<>(curPath);
                left.add(node.left.val);
                pathQ.offer(left);
                nodeQ.offer(node.left);
            }
            if (node.right != null) {
                ArrayList<Integer> right = new ArrayList<>(curPath);
                right.add(node.right.val);
                pathQ.offer(right);
                nodeQ.offer(node.right);
            }
            if (node.left == null && node.right == null) {
                ArrayList<Integer> list = curPath;
                int sum = 0;
                for (Integer i : list) {
                    sum += i;
                }
                if (sum == expectNumber) res.add(list);
            }
        }
        return res;
    }
}
