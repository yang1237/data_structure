package com.yang.linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点用于加入链表
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode3 = new HeroNode(5, "林冲", "豹子头");
        HeroNode heroNode4 = new HeroNode(2, "鲁智深", "花和尚");
        //创建链表
        SingleLikedList singleLikedList = new SingleLikedList();
        //向链表添加节点
//        singleLikedList.addNode(heroNode1);
//        singleLikedList.addNode(heroNode2);
//        singleLikedList.addNode(heroNode3);
//        singleLikedList.addNode(heroNode4);


        for (int i = 0; i < 10; i++) {
            HeroNode heroNode = new HeroNode(i + 1, " ", " ");
            singleLikedList.addNode(heroNode);
        }
        singleLikedList.delete(new HeroNode(3,"",""));
        singleLikedList.list();
//        singleLikedList.reverse();
//        singleLikedList.printReverse();
//        singleLikedList.list();


    }
}
class SingleLikedList{

    //头节点
    private final HeroNode headNode=new HeroNode(0,"",""); //头结点不存放数据，只存放一个指向下一个节点的指针；

    /**
     * 添加节点
     * @param node 要添加的节点
     */
    public void addNode(HeroNode node){
        //遍历找到最后一个节点，用temp保存
        HeroNode temp = headNode;
        while (temp.next != null) {
            temp= temp.next;
        }
        temp.next=node;
    }

    /**
     * 遍历链表
     */
    public void list(){
        if (headNode.next==null){
            System.out.println("空链表");
            return;
        }
        //因为头结点不存放数据，所以从头节点的下一个节点开始遍历
        HeroNode currentNode=headNode.next;
        while (currentNode!=null){
            System.out.println(currentNode);
            currentNode=currentNode.next;
        }
    }

    /**
     * 插入节点到指定位置，该规则是按no的大小插入
     * @param node 要插入的节点
     */
    public void addByOrder(HeroNode node){
        HeroNode temp=headNode;
        boolean isExist=false;
        while (temp.next!=null){
            if (temp.next.no>node.no){
                break;
            }else if(temp.next.no==node.no) {
                isExist=true;
                break;
            }
            temp=temp.next;
        }
        if (isExist){
            System.out.printf("准备加入的英雄编号%d已存在，不能加入\n",node.no);
        }else {
            node.next=temp.next;
            temp.next=node;
        }
    }

    /**
     * 修改节点信息
     * @param node 携带新的节点信息
     */
    public void update(HeroNode node){
        if(headNode.next==null){
            System.out.println("空链表，不存在数据需要修改");
            return;
        }
        boolean isExist=false;
        HeroNode temp=headNode.next;
        while (true){
            if(temp.no==node.no){//找到了要修改的节点
                isExist=true;
                break;
            }else if(temp.next==null){
                System.out.printf("未找到编号%d的英雄",node.no);
                break;
            }
            temp=temp.next;
        }
        if (isExist){
            temp.name=node.name;
            temp.nickname=node.nickname;
        }
    }

    /**
     * 删除节点 node要删除的节点
     * @param node
     */
    public void delete(HeroNode node){
        HeroNode temp=headNode;
        boolean isExist=false;
        //找出要删除节点的前一个节点
        while (temp.next!=null){
            if (temp.next.no== node.no){
                isExist=true;
                break;
            }
            temp=temp.next;
        }
        if (isExist){
            temp.next=temp.next.next;
        }else {//未找到要删除节点的前一个节点，即要删除的节点不在链表中
            System.out.println("要删除的节点不存在");
        }
    }

    /**
     * 计算链表大小
     * @return 链表节点个数
     */
    public int size(){
        HeroNode temp=headNode.next;
        int size=0;
        while (temp!=null){
            size++;
            temp=temp.next;
        }
        return size;
    }

    /**
     * 返回倒数第k个节点 ，k从q1开始计数，例如倒数第一个，倒数第二个
     * @param k 倒数索引从1计数
     * @return 位于k的节点
     */
    public HeroNode getNode(int k){
        int size = this.size();
        int flag;
        if (size>0){
            if (size>=k){
                flag=size-k+1;
            }else {
                throw new RuntimeException("k值超出边界");
            }
        }else {
            throw new RuntimeException("空链表，查不到数据");
        }
        int pos=0;
        HeroNode currentNode=headNode.next;
        while (currentNode!=null){
            pos++;
            if (pos==flag){
                break;
            }
            currentNode=currentNode.next;
        }
        return currentNode;
//        HeroNode currentNode=headNode.next;
//
//        for (int i = 1; i < size-k+1; i++) {
//            currentNode=currentNode.next;
//        }
//        return currentNode;
    }

    /**
     * 反转链表
     */
    public void reverse(){

        if (headNode.next==null||headNode.next.next==null){
            return;
        }

        HeroNode reverseHeadNode = new HeroNode(0, "", "");
        HeroNode currentNode=headNode.next;
        HeroNode nextNode=null;
        while (currentNode!=null){
           nextNode=currentNode.next;//保存指向原链表下一个节点的指针,即保存原链表下一个节点的地址
           currentNode.next=reverseHeadNode.next;//处理现在的节点，即修改了现在节点的指针，使其指向新节点的下一个节点；
            //用新建节点保存当前处理节点的信息
           reverseHeadNode.next=currentNode; //改变新建节点的指针，让他指向正在处理的节点。
           currentNode=nextNode;//拿到原链表现在节点的下一个节点的地址，处理下一个节点
        }
        headNode.next=reverseHeadNode.next; //改变原链表头节点的指针，让它指向新建节点的下一个节点
    }

    public void printReverse(){
        if (headNode.next==null){
            return;
        }
        Stack<HeroNode> heroNodes = new Stack<>();
        HeroNode currentNode=headNode.next;
        while (currentNode!=null){
            heroNodes.push(currentNode);
            currentNode=currentNode.next;
        }
        while (heroNodes.size()>0){
            HeroNode pop = heroNodes.pop();
            System.out.println(pop);
        }
    }
}
class HeroNode{
    public final int no;
    public String name;
    public String nickname;
    public HeroNode next;

//    public HeroNode(){}

    public HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}


