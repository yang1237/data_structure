package com.yang.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(3);
        Node node3 = new Node(6);
        Node node4 = new Node(8);
        Node node5 = new Node(10);
        Node node6 = new Node(14);

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.setRoot(root);

        System.out.println("删除前");
//        binaryTree.preOrder();
//        binaryTree.delete(3);
        System.out.println("删除后");
        binaryTree.infixOrder();

    }
}


class BinaryTree{
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void delete(int no){
        if (root==null){
            System.out.println("空数");
            return;
        }
        if (root.getNo()==no){
            root=null;
            return;
        }
        root.delete(no);
    }

    public void preOrder(){
        if (this.root!=null){
            root.preOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder(){
        if (this.root!=null){
            root.infixOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder(){
        if (this.root!=null){
            root.postOrder();
        }else {
            System.out.println("二叉树为空");
        }
    }

    public Node preOrderSearch(int id){
        if (this.root!=null){
            return root.preOrderSearch(id);
        }else {
            System.out.println("二叉树为空");
        }
        return null;
    }

    public Node infixOrderSearch(int id) {
        if (this.root!=null){
            return root.infixOrderSearch(id);
        }else {
            System.out.println("二叉树为空");
        }
        return null;
    }

    public Node postOrderSearch(int id) {
        if (this.root!=null){
            return root.postOrderSearch(id);
        }else {
            System.out.println("二叉树为空");
        }
        return null;
    }
}

class Node{
    private int no;
    private Node left;
    private Node right;

    public Node(){}

    public Node(int no){
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }

    /**
     * 删除节点
     * 如果是叶子节点，直接删除该节点
     * 如果是非叶子节点，删除该子树
     * 即把对该节点的引用置(赋值)为null
     * 所以删除节点的操作，需要找到对待删除节点的引用
     * @param no 节点id
     */
    public void delete(int no){

        if (left!=null && left.no==no){
            left=null;
            return;
        }
        if (right!=null && right.no==no){
            right=null;
            return;
        }

        if (left!=null){
            left.delete(no);
        }
        if (right!=null){
            right.delete(no);
        }
    }

    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    public void infixOrder(){
        if (this.left!=null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    public void postOrder(){
        if (this.left!=null){
            this.left.postOrder();
        }
        if (this.right!=null){
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public Node preOrderSearch(int no){
        System.out.println("次数");
        if (this.no==no){
            return this;
        }
        Node node=null;
        if (this.left!=null){
            node = this.left.preOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        if(this.right!=null){
            node= this.right.preOrderSearch(no);
        }
        return node;
    }

    public Node infixOrderSearch(int no){
        Node node=null;
        if (left!=null){
            node=left.infixOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        System.out.println("次数");
        if (this.no==no){
            return this;
        }
        if (right!=null){
            node=right.infixOrderSearch(no);
        }
        return node;
    }

    public Node postOrderSearch(int no){
        Node node=null;
        if (left!=null){
            node=left.postOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        if (right!=null){
            node=right.postOrderSearch(no);
        }
        if (node!=null){
            return node;
        }
        System.out.println("次数");
        if (this.no==no){
            return this;
        }
        return null;
    }
}
