package com.yang.tree;

import java.util.HashMap;
import java.util.Map;

/*
* 剑指offer题，从前序和中序遍历序列构造二叉树
* */
public class TreeNodeDemo{
    public static void main(String[] args) {
        int[] preorder={3,9,20,15,7};
        int[] inorder={9,3,15,20,7};

        Solution solution = new Solution();
        /*
        * 下面函数的调用顺序
        * 先创建一个val=3的根节点,（创建根树，根节点val=3）
        * 然后创建该根节点的左树,该函数调用该函数创建早已建好的根节点的左树的根节点left
        * 建好左树（节点数目被计算为1）的跟节点left之后，函数被再次调用去创建left的左树的根节点left.left;
        * 调用函数执行后发现，preorderLeft>preorderRight,函数返回null；即用于创建left.left的函数返回null；即left的left值为null
        * 用于创建left的函数继续向下执行，调用函数，创建left左子树的根节点left.right
        * 由于left的节点数被计算为1，不存在右子树，显然用来创建left.right的函数返回null
        * 用于创建left的函数继续向下执行，返回new好的left的根节点，用于创建left的函数执行完毕
        * 此时，会继续向下执行，调用函数，创建根树的右子树right的根节点
        * */
        TreeNode treeNode = solution.buildTree(preorder, inorder);
        System.out.println(treeNode);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}

class Solution {
    private Map<Integer, Integer> indexMap;

    /**
     *
     * @param preorder 前序输出
     * @param preorder_left  子树在前序输出的数组范围的起始索引，该索引所在的元素总可以作为根节点。(先序遍历规则)
     * @param preorder_right 子树在前序输出数组范围的结尾索引
     * @param inorder_left  子树按照中序输出的数组在范围内的起始索引
     * @return 根节点
     */

    public TreeNode myBuildTree(int[] preorder,
                                int preorder_left, int preorder_right,
                                int inorder_left) {
        if (preorder_left > preorder_right) {
            return null;
        }
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_left]);

        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_left]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, preorder_left + 1,
                preorder_left + size_left_subtree,
                inorder_left);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder,
                preorder_left + size_left_subtree + 1,
                preorder_right, inorder_root + 1);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, 0, n - 1, 0);
    }
}
