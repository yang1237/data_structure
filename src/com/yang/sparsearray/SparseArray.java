package com.yang.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        // 初始化棋盘
        int[][] chessArr =new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        chessArr[4][5]=2;
        //打印棋盘
        for(int[] row:chessArr) {
            for(int data: row) {
                System.out.printf("%d\t",data);
            }
            System.out.print("\n");
        }
        //找出chessArr中值不为0的个数
        int sum=0;
        for(int i=0;i<11;i++) {
            for(int j=0;j<11;j++) {
                if(chessArr[i][j]!=0) {
                    sum++;
                }
            }
        }
        //初始化稀疏数组
        int[][] sparseArr =new int[sum+1][3];
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;

        //将棋盘保存到稀疏数组
        int index=0;
        for(int i=0;i<11;i++) {
            for(int j=0;j<11;j++) {
                if(chessArr[i][j]!=0) {
                    index++;
                    sparseArr[index][0]=i;
                    sparseArr[index][1]=j;
                    sparseArr[index][2]=chessArr[i][j];
                }
            }
        }
        //打印稀疏数组
        for(int[]row :sparseArr) {
            for(int data:row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }



        /*
         * 从稀疏数组还原棋盘
         */
        int row=sparseArr[0][0];
        int col=sparseArr[0][1];
        //初始化棋盘数组
        int[][] chessArr2=new int[row][col];
        //还原棋盘
        for(int i=1;i<sparseArr.length;i++) {
            int x=sparseArr[i][0];
            int y=sparseArr[i][1];
            int v=sparseArr[i][2];
            chessArr2[x][y]=v;
        }

        //打印还原后的棋盘
        for(int[] r:chessArr2) {
            for(int data: r) {
                System.out.printf("%d\t",data);
            }
            System.out.print("\n");
        }

    }
}
