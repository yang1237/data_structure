package com.yang.avl;

import java.util.Objects;

/*
* 二叉平衡树添加节点
* 如果添加节点后，右子树高度-左子树高度>1,进行左旋
* 新建一个节点node，val为根节点的val;
* node.left=根节点的左子树
* node.right=根节点的右子树的左子树
* 然后
* root.val=root.right.val
* root.left=node;
* root.right=root.right.right(原先的root.right没有被引用，相当于从树上删除，实现了树高度的平衡)
* */
public class AvlTreeDemo {
    public static void main(String[] args) {
        int[] arr={10,11,7,6,8,9};
        AvlTree avlTree = new AvlTree();
        for (int j : arr) {
            avlTree.add(new Node(j));
        }
        avlTree.delete(6);
        System.out.println("根节点："+avlTree.getRoot().right.right);
        avlTree.infixOrder();
    }
}

class AvlTree {

   Node root;

    public Node getRoot() {
        return root;
    }

    /**
     * 完全是自己写的平衡二叉树(右树高度大于左树高度时)
     * (未看老师代码)(未考虑结点添加顺序不同，生成的二叉排序树结构也可能并不相同)
     * 实现过程，新添加一个节点，作为root的左子树
     * 后删除root.right,实现了左右子树高度一样
     * @param node 添加节点
     */
    public void myBalance(Node node){
        add(node);
        if (root.rightTreeHeight()-root.leftTreeHeight()>1){
            Node temp = new Node(root.val);
            temp.left=root.left;
            temp.right=root.right.left;
            root.val=root.right.val;
            root.left=temp;
            root.right=root.right.right;
        }
    }

    public void delete(int val) {
        if (root == null) {
            System.out.println("空树");
            return;
        }
        Node targetNode = search(val);
        if (targetNode == null) {
            return;
        }
        /*
            找到要删除的节点后，
            先判断二叉树是不是只有一个根节点
         */
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        /*
            二叉树存在子节点
         */
        Node parent = searchParent(val); //parent为要删除节点的父节点

        if (targetNode.left == null && targetNode.right == null) {
            /*
                要删除的节点是叶子节点（没有子节点），其parent不为空
             */
            if (parent.left != null && parent.left.val == val) {
                parent.left = null;
            } else if (parent.right != null && parent.right.val == val) {
                parent.right = null;
            }
        } else if (targetNode.left != null && targetNode.right != null) {
            /*
                要删除的节点存在两个子节点
             */
            targetNode.val = delRightTreeMin(targetNode.right);
        } else {//要删除的节点只有一个子节点,该节点可能为根节点，考虑其没有父节点的情况
            if (parent == null) {
                //此语法相当于三目运算符
                root = Objects.requireNonNullElseGet(targetNode.left, () -> targetNode.right);
            } else {
                if (parent.left != null && parent.left.val == val) {
                    parent.left = (targetNode.left != null ? targetNode.left : targetNode.right);
                } else if (parent.right != null && parent.right.val == val) {
                    parent.right = Objects.requireNonNullElseGet(targetNode.left, () -> targetNode.right);
                }
            }
        }
    }

    /**
     * 找到要删除节点右子节点树上的最小值节点，并将其从树上删除
     *
     * @param rightTree 要删除的节点的右树
     * @return 被用来替换要删除节点的最小值节点的值
     */
    private int delRightTreeMin(Node rightTree) {
        while (rightTree.left != null) {
            rightTree = rightTree.left;
        }
        delete(rightTree.val);
        return rightTree.val;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public Node search(int val) {
        if (root != null) {
            return root.search(val);
        } else {
            System.out.println("空树");
            return null;
        }
    }

    public Node searchParent(int val) {
        if (root != null) {
            return root.searchParent(val);
        } else {
            System.out.println("空树");
            return null;
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("空树");
        } else {
            root.infixOrder();
        }
    }

}


class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
    }

    public void leftRotate(){
        Node newNode = new Node(val);
        //挂载val小于this.val的节点
        newNode.left=left;
        //挂载val大于this.val小于this.right.val的节点
        newNode.right=right.left;
        //this.val等于this.right.val,
        val=right.val;
        //此时newNode的val重新小于this.val，挂到左边
        left=newNode;
        //把val大于被重新设置了val的this的val的节点，挂到右边
        right=right.right;
    }

    public void rightRotate(){
        Node node = new Node(val);
        node.left=left.right;
        node.right=right;
        val=left.val;
        left=left.left;
        right=node;
    }

    public int leftTreeHeight(){
        if (left==null){
            return 0;
        }
        return left.height();
    }

    public int rightTreeHeight(){
        if (right==null){
            return 0;
        }
        return right.height();
    }

    public int height(){
        return Math.max((left==null?0:left.height()),(right==null?0:right.height()))+1;
    }

    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.val < this.val) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
        //旋转
        if (rightTreeHeight()-leftTreeHeight()>1){
            /*
                右子树的高度大于左子树的高度,左旋
             */
           if (right!=null && right.leftTreeHeight()>right.rightTreeHeight()){
               /*
                右子树的左子树太高了，右子树右旋
                */
               right.rightRotate();
           }
           leftRotate();

        }else if (leftTreeHeight()-rightTreeHeight()>1){
            /*
                left高度大于right高度，右旋(把left.right旋到this.right成为this右边数的左子树(this.right.left))
             */
            if (left!=null && left.rightTreeHeight()>left.leftTreeHeight()){
                /*
                   left.right太高了(left.left高度不够)，left左旋
                 */
                left.leftRotate();
            }
            rightRotate();
        }
    }

    /**
     * 根据值查找结点
     *
     * @param val 要找的节点的值
     * @return 值为val的节点
     */
    public Node search(int val) {
        if (val == this.val) {
            return this;
        } else if (val < this.val) {
            if (this.left == null) {
                return null;
            } else {
                return this.left.search(val);
            }
        } else {
            if (this.right == null) {
                return null;
            } else {
                return this.right.search(val);
            }
        }
    }

    public Node searchParent(int val) {
        if ((this.left != null && this.left.val == val) || (this.right != null && this.right.val == val)) {
            return this;
        } else {
            if (val < this.val && this.left != null) {
                return this.left.searchParent(val);
            } else if (val >= this.val && this.right != null) {
                return this.right.searchParent(val);
            } else {
                return null;
            }
        }
    }

    /**
     * 打印结点，先打印该节点的左子节点，然后打印该节点，最后打印该节点的右子节点
     */
    public void infixOrder() {
        if (this.left != null) {
            //打印左子节点
            this.left.infixOrder();
        }
        //打印该结点
        System.out.println(this);
        //打印右子节点
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}

