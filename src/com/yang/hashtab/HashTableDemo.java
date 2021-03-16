package com.yang.hashtab;

import java.util.ArrayList;
import java.util.Scanner;

public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> integers = new ArrayList<>();
        while (true) {
            System.out.println("add:添加");
            System.out.println("list:显示");
            System.out.println("find:查找");
            System.out.println("remove:删除节点");
            System.out.println("exit:退出");
            String next = scanner.next();
            switch (next) {
                case "add":
                    System.out.println("请输入id");
                    int id = scanner.nextInt();
                    System.out.println("请输入姓名");
                    String name = scanner.next();
                    Node node = new Node(id, name);
                    hashTable.add(node);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTable.findNodeById(id);
                    break;
                case "remove":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTable.remove(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class HashTable {
    private final LinkedList[] linkedLists;
    private final int size;

    public HashTable(int size) {
        this.size = size;
        this.linkedLists = new LinkedList[size];

        for (int i = 0; i < linkedLists.length; i++) {
            linkedLists[i] = new LinkedList();
        }
    }

    public void add(Node node) {
        int hash = hashFun(node.id);
        linkedLists[hash].add(node);
    }

    public void findNodeById(int id) {
        int hashFun = hashFun(id);
        Node nodeById = linkedLists[hashFun].findNodeById(id);
        if (nodeById != null) {
            System.out.printf("在第%d条链表中找到id=%d的node\n", hashFun + 1, id);
        }else {
            System.out.println("未找到该节点");
        }
    }

    public void remove(int id){
        int i = hashFun(id);
        linkedLists[i].remove(id);
    }

    public void list() {
        for (int i = 0; i < linkedLists.length; i++) {
            linkedLists[i].list(i + 1);
        }
    }

    private int hashFun(int no) {
        return no % size;
    }
}

class Node {
    public int id;
    public String name;
    public Node next;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class LinkedList {
    private Node head;

    public void add(Node node) {
        if (head == null) {
            head = node;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public void list(int no) {
        if (head == null) {
            System.out.println("第" + no + "条链表为空");
            return;
        }
        System.out.print("第" + no + "条链表信息:");
        Node curNode = head;
        while (curNode != null) {
            System.out.println(curNode);
            curNode = curNode.next;
        }
    }

    public Node findNodeById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Node curNode = head;
        while (true) {
            if (curNode.id == id) {
                break;
            }
            curNode = curNode.next;
            if (curNode.next == null) {
                curNode = null;
                break;
            }
        }
        return curNode;
    }

    public void remove(int id) {
        //判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        //判断是否删除头节点
        if (head.id==id){
            if (head.next==null){
                head=null;
            }else {
                head=head.next;
            }
            System.out.println("删除成功！");
            return;
        }
        //从头节点的下一个节点开始查找要删除的节点的上一个节点，将其保存到curNode;
        Node curNode = head.next;
        while (true) {
            if (curNode.next == null) {
                System.out.println("不存在id为" + id + "的node");
                break;
            }
            if (curNode.next.id == id) {
                curNode.next = curNode.next.next;
                System.out.println("删除成功！");
                break;
            }
            curNode = curNode.next;
        }
    }
}
