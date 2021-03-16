package com.yang.tree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedNode root = new ThreadedNode(1);
        ThreadedNode node2 = new ThreadedNode(3);
        ThreadedNode node3 = new ThreadedNode(6);
        ThreadedNode node4 = new ThreadedNode(8);
        ThreadedNode node5 = new ThreadedNode(10);
        ThreadedNode node6 = new ThreadedNode(14);

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);

        threadedBinaryTree.threadedNodes();

        ThreadedNode left = node5.getLeft();
        ThreadedNode right = node5.getRight();
        threadedBinaryTree.threadedTreeList();

    }
}

class ThreadedBinaryTree {

    private ThreadedNode root;

    private ThreadedNode pre=null;

    public void setRoot(ThreadedNode root){
        this.root=root;
    }

    public void threadedNodes(){
        this.threadedNodes(root);
    }

    public void threadedTreeList(){
        ThreadedNode node=root;
        while (node!=null){
            //找出最左边的node
            while (node.getLeftType()!=1){
                node=node.getLeft();
            }
            //打印最左边的node
            System.out.println(node);

            //打印node的后驱节点
            while (node.getRightType()!=0){
                node=node.getRight();
                System.out.println(node);
            }
            //节点存在右子树,因为当前节点已经被打印，可以开始打印它的右子树（按照中序遍历，开始打印右子节点）
            node=node.getRight();
        }
    }

    public void threadedNodes(ThreadedNode node){

        if (node==null){
            return;
        }

        threadedNodes(node.getLeft());

        if (node.getLeft()==null){
            //设置前驱节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre!=null && pre.getRight()==null){
            /*
            递归出栈时携带了this即当前node的前驱节点的引用pre，
               即可以看成pre的后驱节点为node;
               即pre.right指向node;
             */
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre=node;

        threadedNodes(node.getRight());
    }

}

class ThreadedNode extends Node {

    /*
       leftType 0表示指向左子树，1表示指向前驱节点
       rightType 0表示指向右子树，1表示指向后驱节点
     */
    private int leftType;
    private int rightType;

    private ThreadedNode left;
    private ThreadedNode right;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public ThreadedNode getLeft() {
        return left;
    }

    public void setLeft(ThreadedNode left) {
        this.left = left;
    }

    @Override
    public ThreadedNode getRight() {
        return right;
    }

    public void setRight(ThreadedNode right) {
        this.right = right;
    }

    public ThreadedNode(int no) {
        super(no);
    }
    public ThreadedNode(){
        super();
    }

}