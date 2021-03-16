package com.yang.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(3);
        char key; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取数据");
            System.out.println("p(peek):查看队列头的数据");
            System.out.println("e(exit):退出");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int data = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", data);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    queue.showQueue();
                    break;
                case 'p':
                    try {
                        int peek = queue.peek();
                        System.out.printf("队列中即将要出队列的数是%d\n", peek);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
class CircleArrayQueue{
    private final int maxSize;
    private final int[] arr;
    private int rear; //总是指向队列的后一个位置
    private int front;//总是指向队列的第一个位置;

    public CircleArrayQueue(int maxSize){
        this.maxSize=maxSize;
        this.arr=new int[maxSize];
    }

    public boolean isFull(){
        //队列内的元素个数等于队列长度
        return rear-front==maxSize;
    }
    public void addQueue(int data){
        if (isFull()){
            System.out.println("队列已满，不能加入数据");
            return;
        }
        arr[rear%maxSize]=data;
        //rear后移
        rear=rear+1;
    }
    public boolean isEmpty(){
        //恰好需要从出队列的位置入队列
        return front==rear;
    }
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front%maxSize];
        front=front+1;
        return value;
    }
    public void showQueue(){
        if (isEmpty()){
            System.out.println("空队列，没有数据~~");
            return;
        }
        for (int i = front; i <rear ; i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    public int peek(){
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[front%maxSize];
    }
}
