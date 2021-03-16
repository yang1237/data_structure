package com.yang.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
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


class ArrayQueue {

    private int rear; //尾部索引
    private int front; //front指向队列头的前一个位置
    private final int maxSize;
    private final int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        rear = -1;
        front = -1;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否已满
     *
     * @return true表示队列已满
     */
    public boolean isFull() {
        return rear-front==maxSize;
    }

    /**
     * 判断队列是否为空
     *
     * @return true表示队列为空
     */
    public boolean isEmpty() {
        return rear == front;
    }

    /**
     * 添加数据到队列
     *
     * @param data 要加入队列的数据
     */
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满，不能加入数据");
            return;
        }
        rear++;
        arr[rear-front-1] = data;

    }

    /**
     * 获取队列的数据，出队列
     *
     * @return 出队列的数据
     */
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        int data=arr[0]; //保存要出列队的数据

        for (int i = 0; i < arr.length; i++) {
            if (i < rear - front) {
                arr[i] = arr[i + 1];
            } else {
                arr[i] = 0;
            }
        }
        return data;
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("空队列，没有数据~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /**
     * 显示队列的头数据
     *
     * @return 即将要出队列的数据
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[0];
    }

}

