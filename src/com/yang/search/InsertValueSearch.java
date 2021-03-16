package com.yang.search;

public class InsertValueSearch {

    public static void main(String[] args) {
        int[] ints = new int[100];
        for (int i = 0; i <ints.length ; i++) {
            ints[i]=i+1;
        }
        int search = search(ints, 0, ints.length - 1, 2);
        System.out.println(search);
    }

    private static int search(int[] arr,int left,int right,int val){

        //确保（val-arr[left])/arr[right]-arr[left] 大于等于0小于等于1;
        if (left>right||val<arr[0]||val>arr[arr.length-1]){
            return -1;
        }
        //此时的mid可能大于arr.length-1；因为数组的索引一般情况下不大于left+right-left；
        int mid=left+(right-left)*(val-arr[left])/(arr[right]-arr[left]);
        if (val<arr[mid]){
            return search(arr,left,mid-1,val);
        }else if(val>arr[mid]){
            return search(arr,mid+1,right,val);
        }else {
            return mid;
        }
    }
}
