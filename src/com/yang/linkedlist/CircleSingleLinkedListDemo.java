package com.yang.linkedlist;

public class CircleSingleLinkedListDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(5);
        circleSingleLinkedList.remove(1,2,5);
        circleSingleLinkedList.list();
    }
}

class CircleSingleLinkedList {
    private CircleNode head;

    /**
     * 添加节点
     *
     * @param node 要加入的节点
     */
    public void addNode(CircleNode node) {
        if (node == null) {
            System.out.println("要添加的节点为空，请检查");
        } else {
            CircleNode cur = head;
            if (head == null) {
                head = node;
                head.next = head;
                return;
            }
            while (true) {
                if (cur.next == head) {
                    node.next = head;
                    cur.next = node;
                    break;
                }
                cur = cur.next;
            }
        }

    }

    /**
     * 批量添加节点
     *
     * @param num 要添加的节点个数
     */
    public void addNode(int num) {
        if (num < 1) {
            System.out.println("没有节点要添加");
        }
        CircleNode cur = null;
        for (int i = 1; i <= num; i++) {
            CircleNode circleNode = new CircleNode(i);
            if (i == 1) {
                head = circleNode;
                head.next = head;
                cur = head;
            } else {
                //初始化节点,使其指向头节点
                circleNode.next = head;
                /*
                * 用辅助指针连接链表,向链表加入新的节点
                * 辅助指针始终保存着当前链表最后加入的一个节点的地址
                */
                cur.next = circleNode;
                //重置辅助指针，使它始终为当前新加入的节点的引用
                cur = circleNode;
            }
        }
    }

    public void remove(CircleNode node) {
        //先考虑只有头节点一个节点的情况
        if (head.next == head) {
            if (node.no == head.no) {
                head = null;
                return;
            }
        }
        CircleNode cur = head;
        boolean flag = false;
        while (cur.next != head) {
            if (cur.next.no == node.no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag || cur.no == node.no) {
            cur.next = cur.next.next;
        } else if (head.no == node.no) {//此时要删除的节点为头节点
            //指定新的头节点
            head = cur.next.next;
            //使最后加入的节点指向头节点
            cur.next = head;
        }

    }

    public void list() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        CircleNode cur = head.next;
        if (cur == null) {
            System.out.println(head);
            return;
        }
        System.out.println(head);
        while (cur != head) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    public void remove(int first,int count,int size){

        //定义辅助节点，使它保存指向头节点的指针;
        CircleNode alive=head;
        //使alive.next保存head的地址
        while (alive.next!=head){
            alive=alive.next;
        }
        //头节点为开始计数的节点;
        for (int i = 0; i < first-1; i++) {
            head=head.next;
            alive=alive.next;
        }
        //开始约瑟夫死亡游戏
        int killedNum=0;
        while (alive != head) {
            //开始计数，利用头节点计算计数到哪个节点
            for (int i = 0; i < count - 1; i++) {
                head = head.next; //head.next为数到的节点
                alive = alive.next;
            }
            killedNum++;
            //计数结束，被数到的节点出列
            alive.next = head.next;
            //打印出列的节点
            System.out.println(head.no);
            //指定新的头节点，让游戏继续
            head = head.next;
        }
        System.out.printf("幸存的节点是%d号节点\n",alive.no);
    }


}

class CircleNode {
    public int no;
    public CircleNode next;

    public CircleNode(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "CircleNode{" +
                "no=" + no +
                '}';
    }
}
