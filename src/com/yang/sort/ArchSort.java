package com.yang.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ArchSort {

    public static void main(String[] args) {
        int[] arr = new int[15];
        for (int i = 0; i < 15; i++) arr[i] = (int) (Math.random() * 80);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(Arrays.toString(arr));
        System.out.println(format);
        selectSort(arr);
        String format1 = simpleDateFormat.format(new Date());
        System.out.println(format1);
    }

    /**
     * 冒泡排序
     * @param arr 数组
     */
    private static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            //找出arr.length-i个数中的最大值
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                break;
            }
        }
    }

    /**
     * 选择排序
     * @param arr 数组
     */
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i, min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    minIndex = j;
                }
            }

            arr[minIndex] = arr[i];
            arr[i] = min;

        }
    }

}
