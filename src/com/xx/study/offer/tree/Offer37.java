package com.xx.study.offer.tree;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * <p>
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/37.%20%E5%BA%8F%E5%88%97%E5%8C%96%E4%BA%8C%E5%8F%89%E6%A0%91.md
 *
 * @Author XX
 * @Date 2022/11/7 10:18
 * @Version 1.0
 */
public class Offer37 {

    public String Serialize(TreeNode root) {
        if (root == null) return "#";
        return root.val + " " + Serialize(root.left) + " " + Serialize(root.right);
    }

    private String deserializeStr = null;

    public TreeNode Deserialize(String str) {
        if (str == null) {
            return null;
        }
        deserializeStr = str;
        return DeserializeFunction();
    }

    private TreeNode DeserializeFunction() {
        if (deserializeStr.length() == 0) {
            return null;
        }
        int index = deserializeStr.indexOf(" ");
        String valStr = index == -1 ? deserializeStr : deserializeStr.substring(0, index);
        deserializeStr = index == -1 ? "" : deserializeStr.substring(index + 1);
        if (valStr.equals("#")) {
            return null;
        }
        Integer val = Integer.valueOf(valStr);
        TreeNode root = new TreeNode(val);
        root.left = DeserializeFunction();
        root.right = DeserializeFunction();
        return root;
    }

    /**
     * step 1：优先处理序列化，首先空树直接返回“#”，然后调用SerializeFunction函数前序递归遍历二叉树。
     * step 2：SerializeFunction函数负责前序递归，根据“根左右”的访问次序，优先访问根节点，
     * 遇到空节点在字符串中添加‘#’，遇到非空节点，添加相应节点数字和‘ ’，然后依次递归进入左子树，右子树。
     * step 3：创建全局变量index表示序列中的下标（C++中直接指针完成）。
     * step 4：再处理反序列化，读入字符串，如果字符串直接为"#"，就是空树，否则还是调用DeserializeFunction函数前序递归建树。
     * step 5：DeserializeFunction函数负责前序递归构建树，遇到‘#’则是空节点，遇到数字则根据感叹号分割，
     * 将字符串转换为数字后加入新创建的节点中，依据“根左右”，创建完根节点，然后依次递归进入左子树、右子树创建新节点。
     */

    public String Serialize2(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder res = new StringBuilder();
        Serialize2Function(root, res);
        return res.toString();
    }

    private void Serialize2Function(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("#");
            return;
        }
        str.append(root.val).append(" ");
        Serialize2Function(root.left, str);
        Serialize2Function(root.right, str);
    }

    private int index = 0;

    public TreeNode Deserialize2(String str) {
        if (str == null) {
            return null;
        }
        TreeNode node = Deserialize2Function(str);
        return node;
    }

    private TreeNode Deserialize2Function(String str) {
        if (str.charAt(index) == '#') {
            index++;
            return null;
        }
        int val = 0;
        while (str.charAt(index) != ' ' && index < str.length()) {
            val = val * 10 + ((str.charAt(index)) - '0');
            index++;
        }
        TreeNode treeNode = new TreeNode(val);
        if (index == str.length()) {
            return treeNode;
        } else {
            index++;
        }
        treeNode.left = Deserialize2Function(str);
        treeNode.right = Deserialize2Function(str);
        return treeNode;
    }

}
