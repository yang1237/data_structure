package com.yang.search;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr={1,3,3,3,3,5,7,9,9,9};
        int search = search(arr, 0, arr.length - 1, 1);
        List<Integer> integers = searchAll(arr, 0, arr.length - 1, 3);
        System.out.println(integers);
    }

    /**
     * 二分查找
     * @param arr   数组
     * @param left  左边界
     * @param right 右边界
     * @param val   要查的值
     * @return val在arr的索引
     */
    private static int search(int[] arr, int left, int right, int val) {
        int mid = left + (right - left) / 2;
        if (left > right) {
            return -1;
        }
        if (val < arr[mid]) {
            return search(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return search(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找
     * @param arr   数组
     * @param left  左边界
     * @param right 右边界
     * @param val   要查的值
     * @return val在arr的索引
     */
    private static List<Integer> searchAll(int[] arr, int left, int right, int val){
        ArrayList<Integer> integers = new ArrayList<>();

        int mid = left + (right - left) / 2;
        if (left > right) {
            return integers;
        }
        if (val < arr[mid]) {
            return searchAll(arr, left, mid - 1, val);
        } else if (val > arr[mid]) {
            return searchAll(arr, mid + 1, right, val);
        } else {
            int temp=mid-1;
            while (temp>=0&&arr[temp]==val){
                integers.add(temp);
                temp--;
            }
            integers.add(mid);
            temp=mid+1;
            while (temp<=arr.length-1&&arr[temp]==val){
                integers.add(temp);
                temp++;
            }
            return integers;
        }
    }
}
