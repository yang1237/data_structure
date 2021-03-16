package com.yang.tree;

/**
 * 数组化二叉树为一个完全二叉树
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrayBinaryTree binaryTree = new ArrayBinaryTree(arr);
        binaryTree.preOrder();
    }
}
class ArrayBinaryTree{
    private final int[] arr;
    public ArrayBinaryTree(int[] arr){
        this.arr=arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    private void preOrder(int index){
        if (arr!=null && arr.length>0){
            System.out.println(arr[index]);
            if (2*index+1<arr.length){
               preOrder(2*index+1);
            }
            if (2*index+2<arr.length){
                preOrder(2*index+2);
            }
        }
    }
}
