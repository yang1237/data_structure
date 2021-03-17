package com.yang.stack;

public class LinkedStack<T> {

    private static class Node<U>{
        U item;
        Node<U> next;

        Node(){
            this.item=null;
            this.next=null;
        }

        Node(U item,Node<U> next){
            this.item=item;
            this.next=next;
        }

        boolean end(){
            return this.item==null && this.next==null;
        }
    }

    private Node<T> top=new Node<>(); //栈顶


    public void push(T item){
        this.top=new Node<>(item,top);
    }

    public T pop(){
        T result = top.item;
        if (!top.end()){
            top=top.next;
        }
        return result;
    }


}
