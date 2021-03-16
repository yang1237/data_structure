package com.yang.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        for (int i = 0; i < 10; i++) {
            Node heroNode = new Node(i + 1, " ", " ");
            doubleLinkedList.addNode(heroNode);
        }
        Node node = new Node(8, "", "");
        Node node1 = new Node(11, "", "");
        doubleLinkedList.insertNode(node,node1);
        doubleLinkedList.list();
    }
}
class DoubleLinkedList{
    private final Node head=new Node(0,"","");

    public void list(){
        if (head.next==null){
            System.out.println("空链表");
            return;
        }
        Node cur=head.next;
        while (cur!=null){
            System.out.println(cur);
            cur=cur.next;
        }
    }
    public void addNode(Node node){
        Node cur=head;
        while (cur.next!=null){
            cur=cur.next;
        }
        //此时cur为当前链表最后一个节点
        //把要添加的节点信息保存到cur中
        cur.next=node;
        //把cur的信息保存到node中
        node.pre=cur;
    }
    public void updateNode(Node node){
        if (head.next==null){
            System.out.println("空链表");
        }
        boolean flag=false;
        Node cur=head.next;
        while (cur!=null){
            if (cur.no==node.no){
                flag=true;
                break;
            }
            cur=cur.next;
        }
        if (flag){
            cur.name=node.name;
            cur.nickname=node.nickname;
        }else {
            System.out.println("要修改的节点不存在");
        }
    }
    public void removeNode(Node node){
        if (head.next==null){
            System.out.println("空节点");
        }
        Node cur=head.next;
        boolean flag=false;
        while (cur!=null){
            if(cur.no==node.no) {
                flag = true;
                break;
            }
            cur=cur.next;
        }
        if (flag){
            cur.pre.next=cur.next;
            if(cur.next!=null){
                cur.next.pre=cur.pre;
            }
        }else {
            System.out.println("要删除的节点不存在");
        }
    }
    public void insertNode(Node preNode,Node node){
        if (head.next==null){
            System.out.println("空链表");
            return;
        }
        Node cur=head.next;
        boolean flag=false;
        while (cur!=null){
            if (cur.no== preNode.no){
                flag=true;
                break;
            }
            cur=cur.next;
        }
        if (flag){
            //初始化node
           node.next=cur.next;
           node.pre=cur;
           //把node加入链表
            cur.next=node;
        }else{
            System.out.println("找不到插入位置");
        }
    }

}
class Node{
    public int no;
    public String name;
    public String nickname;
    public Node pre;
    public Node next;

    public Node(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}