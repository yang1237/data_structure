package com.yang.tree;

import com.yang.sort.QuickSort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HeapSort {

    public static void main(String[] args) {

        QuickSort quickSort = new QuickSort();
        int[] ints = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            ints[i]=(int)(Math.random()*8000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        long time = new Date().getTime();

        headSort(ints);
        long time1 = new Date().getTime();
        System.out.println(time1-time);

    }

    public static void headSort(int[] arr){

        //从数组倒序的第一个非叶子节点开始，调整所有的非叶子节点
        for (int i = arr.length/2-1; i >=0 ; i--) {
            adjustHead(arr,i,arr.length);
        }

        //交换节点，调整位置
        int temp;
        for (int i = arr.length-1; i >0 ; i--) {

            temp=arr[0];
            arr[0]=arr[i];
            arr[i]=temp;

            adjustHead(arr,0,i);
        }
    }

    /**
     * 实现树节点不小于其子节点
     * @param arr 要调整的数组
     * @param index 调整的节点在数组的索引
     * @param size 要调整多少个节点
     */
    public static void adjustHead(int[] arr,int index,int size){
        int temp=arr[index];
        /*
        * for循环调整堆结构
        * 分两种情况：
        * 1.调整到叶子节点，循环执行一次退出
        * 2.调整到非叶子节点，碰到break退出
        * */
        for (int i = 2*index+1 ; i <size; i=2*i+1) {
            if (i+1<size && arr[i]<arr[i+1]){
                i++;
            }
            if (arr[i]>temp){
                //把arr[i]移动到上一层
                arr[index]=arr[i];
                index=i;
            }else {
                break;
            }
        }
        arr[index]=temp;
    }
}
