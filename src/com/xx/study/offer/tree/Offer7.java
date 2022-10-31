package com.xx.study.offer.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 重建二叉树
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/7.%20%E9%87%8D%E5%BB%BA%E4%BA%8C%E5%8F%89%E6%A0%91.md
 * <p>
 * 前序遍历的第一个值为根节点的值，使用这个值将中序遍历结果分成两部分，左部分为树的左子树中序遍历结果，右部分为树的右子树中序遍历的结果。
 * 然后分别对左右子树递归地求解。
 *
 * @Author XX
 * @Date 2022/10/31 22:03
 * @Version 1.0
 */
public class Offer7 {
    public static void main(String[] args) {
        int[] preTest = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] vinTest = {4, 7, 2, 1, 5, 3, 8, 6};
        System.out.println(reConstructBinaryTree(preTest, vinTest));
        System.out.println(reConstructBinaryTree2(preTest, vinTest));
        System.out.println(reConstructBinaryTree3(preTest, vinTest));
    }

    private static Map<Integer, Integer> indexForMap = new HashMap<>();

    public static TreeNode reConstructBinaryTree(int[] pre, int[] vin) {
        for (int idx = 0; idx < vin.length; idx++) {
            indexForMap.put(vin[idx], idx);
        }
        return reConstructBinaryTree(pre, 0, pre.length - 1, 0);
    }

    private static TreeNode reConstructBinaryTree(int[] pre, int preL, int preR, int inL) {
        if (preL > preR) {
            return null;
        }
        TreeNode treeNode = new TreeNode(pre[preL]);
        int treeNodeIdx = indexForMap.get(treeNode.val);
        int lastLeftSize = treeNodeIdx - inL;
        treeNode.left = reConstructBinaryTree(pre, preL + 1, preL + lastLeftSize, inL);
        treeNode.right = reConstructBinaryTree(pre, preL + lastLeftSize + 1, preR, inL + lastLeftSize + 1);
        return treeNode;
    }

    /**
     * step 1：先根据前序遍历第一个点建立根节点。
     * step 2：然后遍历中序遍历找到根节点在数组中的位置。
     * step 3：再按照子树的节点数将两个遍历的序列分割成子数组，将子数组送入函数建立子树。
     * step 4：直到子树的序列长度为0，结束递归。
     *
     * @param pre
     * @param vin
     * @return
     */
    public static TreeNode reConstructBinaryTree2(int[] pre, int[] vin) {
        if (pre.length == 0 || vin.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int idx = 0; idx < vin.length; idx++) {
            if (root.val == vin[idx]) {
                root.left = reConstructBinaryTree2(
                        Arrays.copyOfRange(pre, 1, idx + 1),
                        Arrays.copyOfRange(vin, 0, idx));
                root.right = reConstructBinaryTree2(
                        Arrays.copyOfRange(pre, idx + 1, pre.length),
                        Arrays.copyOfRange(vin, idx + 1, vin.length));
                break;
            }
        }
        return root;
    }

    /**
     * step 1：首先前序遍历第一个数字依然是根节点，并建立栈辅助遍历。
     * step 2：然后我们就开始判断，在前序遍历中相邻的两个数字必定是只有两种情况：要么前序后一个是前一个的左节点；要么前序后一个是前一个的右节点或者其祖先的右节点。
     * step 3：我们可以同时顺序遍历pre和vin两个序列，判断是否是左节点，如果是左节点则不断向左深入，用栈记录祖先，如果不是需要弹出栈回到相应的祖先，然后进入右子树，整个过程类似非递归前序遍历。
     * @param pre
     * @param vin
     * @return
     */
    public static TreeNode reConstructBinaryTree3(int [] pre, int [] vin) {
        int n = pre.length;
        int m = vin.length;
        if (n == 0 || m == 0) {
            return null;
        }
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode root = new TreeNode(pre[0])   ;
        TreeNode cur = root;
        for(int i = 1,j=0;i<n;i++){
            if(cur.val != vin[j]){
                cur.left = new TreeNode(pre[i]);
                s.push(cur);
                cur = cur.left;
            }else{
                j++;
                while(!s.isEmpty()&&s.peek().val==vin[j]){
                    cur = s.pop();
                    j++;
                }
                cur.right = new TreeNode(pre[i]);
                cur =cur.right;
            }
        }
        return root;
    }
}
