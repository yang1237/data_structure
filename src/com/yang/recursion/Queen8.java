package com.yang.recursion;

public class Queen8 {

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println(count);
    }

    int max=8;
    int[] arr=new int[max];
    static int count=0;

    /**
     *
     * @param n 第n行的皇后
     * @return true 表示arr[n]是该n行皇后可以放的列；
     */
    private boolean judge(int n){
        for (int i = 0; i < n; i++) {
            //判断第i个皇后和第n个皇后是否在同一列,
            // 或者第i个皇后，和第n个皇后恰好在某个正方形的对角线上
            if (arr[i]==arr[n]||Math.abs(n-i)==Math.abs(arr[n]-arr[i])){
                return false;
            }
        }
        return true;
    }

    private void print(){
        count++;
        //i表示行，arr[i]表示列
        for (int i = 0; i < max; i++) {
            System.out.printf("%d\t",arr[i]);
        }
        System.out.println();
    }

    /**
     *
     * @param n 代表第n+1个皇后，即已经放完了n行，尝试放第n+1行
     *          （当然n+1行不存在）只是为了强调索引是从0开始的
     */
    private void check(int n){
        if (n==max){
            print();
            return;
        }
        //i表示第n+1个皇后放在第i+1列
        for (int i = 0; i < max; i++) {
            //把第n+1个皇后，放在n+1行，i+1列的位置
            arr[n]=i;
            if (judge(n)){//该位置是否是不冲突的位置
                check(n+1);
            }
        }
    }
}
