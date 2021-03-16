package com.yang.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] ints = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            ints[i]=(int)(Math.random()*8000000);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        long time = new Date().getTime();

        quickSort.sort(ints, 0, ints.length-1);
        long time1 = new Date().getTime();
        System.out.println(time1-time);

    }

    private void sort(int[] arr,int left,int right){
        if (left<right){
            int partition = partition(arr, left, right);
            sort(arr,left,partition-1);
            sort(arr,partition+1,right);
        }
    }

    private int partition(int[] arr,int left,int right){
        int index;
        index = left +1;
        for (int i = index ; i <=right; i++) {
            if (arr[i]<arr[left]){
                swap(arr,index,i);
                index++;
            }
        }
        swap(arr, left,index-1);
        return index-1;
    }

    private void swap(int[] arr,int left,int right){
        int temp=arr[left];
        arr[left]=arr[right];
        arr[right]=temp;
    }
}
