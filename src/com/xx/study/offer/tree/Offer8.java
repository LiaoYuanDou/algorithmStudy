package com.xx.study.offer.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的下一个结点
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/8.%20%E4%BA%8C%E5%8F%89%E6%A0%91%E7%9A%84%E4%B8%8B%E4%B8%80%E4%B8%AA%E7%BB%93%E7%82%B9.md
 *
 * @Author XX
 * @Date 2022/11/1 21:51
 * @Version 1.0
 */
public class Offer8 {
    public static void main(String[] args) {

    }

    /**
     * step 1：首先先根据当前给出的结点找到根节点
     * step 2：然后根节点调用中序遍历
     * step 3：将中序遍历结果存储下来
     * step 4：最终拿当前结点匹配是否有符合要求的下一个结点
     */
    List<TreeLinkNode> nodes = new ArrayList<TreeLinkNode>();

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        TreeLinkNode root = pNode;
        while (root.next != null) {
            root = root.next;
        }

        inOrder(root);

        for (int i = 0; i < nodes.size() - 1; i++) {
            TreeLinkNode cur = nodes.get(i);
            if (cur == pNode) {
                return nodes.get(i + 1);
            }
        }
        return null;
    }

    private void inOrder(TreeLinkNode root) {
        if (root != null) {
            inOrder(root.left);
            nodes.add(root);
            inOrder(root.right);
        }
    }

    /**
     * 如果给出的结点有右子节点，则最终要返回的下一个结点即右子树的最左下的结点
     * 如果给出的结点无右子节点，且当前结点是其父节点的左子节点，则返回其父节点
     * 如果给出的结点无右子节点，且当前结点是其父节点的右子节点，
     * 则先要沿着左上方父节点爬树，一直爬到当前结点是其父节点的左子节点为止，返回的就是这个父节点；或者没有满足上述情况的则返回为NULL
     *
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext2(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode rchild = pNode.right;
            while (rchild.left != null) {
                rchild = rchild.left;
            }
            return rchild;
        }

        if (pNode.next != null && pNode.next.left == pNode) {
            return pNode.next;
        }

        if (pNode.next != null) {
            TreeLinkNode ppar = pNode.next;
            while (ppar.next != null && ppar.next.right == ppar) {
                ppar = ppar.next;
            }
            return ppar.next;
        }

        return null;
    }


    public TreeLinkNode GetNext3(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode rchild = pNode.right;
            while (rchild.left != null) {
                rchild = rchild.left;
            }
            return rchild;
        } else {
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode) {
                    return parent;
                }
                pNode = pNode.next;
            }
        }
        return null;
    }
}
