package com.yang.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    三数之和为0
 */
public class SumOfThree {

    public List<List<Integer>> threeSum(int[] ints){
        ArrayList<List<Integer>> result = new ArrayList<>();
        Arrays.sort(ints);
        for (int i = 0; i < ints.length; i++) {
            if (ints[i]>0){
                break;
            }
            if (ints[i]+ints[ints.length-1]+ints[ints.length-2]<0){
                continue;
            }
            if (i>0&&ints[i]==ints[i-1]){
                continue;
            }
            int cur=ints[i];
            int m=i+1;
            int n=ints.length-1;
            while (m<n){
                if (cur+ints[m]+ints[n]==0){
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(cur);
                    integers.add(ints[m]);
                    integers.add(ints[n]);
                    result.add(integers);
                    while (m+1<ints.length-1&&ints[m+1]==ints[m])++m;
                    while (n-1>i+1&&ints[n-1]==ints[n])--n;
                    m++;
                    n--;
                }else if (cur+ints[m]+ints[n]<0){
                    m++;
                }else {
                    n--;
                }
            }
        }
        return result;
    }
}
