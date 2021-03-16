package com.yang.stack;
public class ArrayStack {
    private int maxSize;//栈的大小
    private int top = -1;//栈顶的位置
    private int[] stack;

    public ArrayStack(){};

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int[] getStack() {
        return stack;
    }

    public void setStack(int[] stack) {
        this.stack = stack;
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[this.maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public void push(int value) {
        if (isFull()) {
            System.out.println("栈溢出");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("空栈");
        }
        int value = stack[top];
        top--;
        return value;

    }

    public void list(){
        if (isEmpty()){
            System.out.println("空栈");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

    public int peek(){
        return this.stack[top];
    }

}

