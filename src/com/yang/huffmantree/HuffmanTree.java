package com.yang.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

public class HuffmanTree {

    public static void main(String[] args) {
        int[] arr={13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("空树");
        }
    }

    public static Node createHuffmanTree(int[] arr) {

        ArrayList<Node> nodes = new ArrayList<>();
        for (int val : arr) {
            nodes.add(new Node(val));
        }

        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(leftNode.val + rightNode.val);
            parentNode.left = leftNode;
            parentNode.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }
}

class Node implements Comparable<Node> {

    int val;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
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

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}
