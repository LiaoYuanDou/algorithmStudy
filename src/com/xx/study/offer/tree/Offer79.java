package com.xx.study.offer.tree;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/55.2%20%E5%B9%B3%E8%A1%A1%E4%BA%8C%E5%8F%89%E6%A0%91.md
 * <p>
 * 判断是否是平衡二叉树
 * <p>
 * 平衡二叉树左右子树高度差不超过 1。
 *
 * @Author XX
 * @Date 2022/11/7 20:58
 * @Version 1.0
 */
public class Offer79 {
    private boolean isBalanced = true;

    public boolean IsBalanced_Solution(TreeNode root) {
        depth(root);
        return isBalanced;
    }

    private int depth(TreeNode root) {
        if (root == null || !isBalanced) {
            return 0;
        }
        int left = depth(root.left);
        int right = depth(root.right);
        if (Math.abs(left - right) > 1) {
            isBalanced = false;
        }
        return 1 + Math.max(left, right);
    }


    /**
     * step 1：第一个函数递归遍历二叉树所有节点。
     * step 2：对于每个节点判断，调用第二个函数获取子树深度。
     * step 3：第二个函数递归获取子树深度，只需要不断往子节点深度遍历，累加左右深度的较大值。
     * step 4：根据深度判断该节点下的子树是否为平衡二叉树。
     *
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution2(TreeNode root) {
        if (root == null) return true;
        int left = depth2(root.left);
        int right = depth2(root.right);
        if (left - right > 1 || right - left > 1) {
            return false;
        }
        return IsBalanced_Solution2(root.left) && IsBalanced_Solution2(root.right);
    }

    private int depth2(TreeNode root) {
        if (root == null) return 0;
        int left = depth2(root.left);
        int right = depth2(root.right);
        return left > right ? left + 1 : right + 1;
    }

    /**
     * step 1：先判断空树，直接为平衡二叉树。
     * step 2：递归进行边计算深度边判断是否平衡二叉树。
     * step 3：递归计算当前节点左右子树的深度差，然后比较深度差是否符合平衡二叉树。
     * step 4：每次递归都将深度结果往上传，遇到不符合平衡二叉树的，返回-1，而深度差我们取绝对值保证返回正数，
     * 因此最后的负数一定就是 不满足条件，这样就能做到边判断边计算深度了。
     *
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution3(TreeNode root) {
        if (root == null) return true;
        return depth3(root) != -1;
    }

    private int depth3(TreeNode root) {
        if (root == null) return 0;
        int left = depth3(root.left);
        if (left < 0) return -1;
        int right = depth3(root.right);
        if (right < 0) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);
    }
}
