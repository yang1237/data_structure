package com.yang.recursion;

public class MiGong {

    public static void main(String[] args) {
        int[][] map = new int[8][7];
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1]=1;
        map[3][2]=1;

        setWay(map,1,1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf("%d\t",map[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            //终点
            return true;
        } else {
            if (map[i][j] == 0) {//表示可以从该位置出发=0，该位置可以走人!=1，还未走过该位置!=2,
                map[i][j] = 2;//把人放到该位置
                //尝试走下一步，尝试顺序为下，右，上，左
                //当走到map[6,5]这个位置的时候，走下一步时会碰到递归调用终止条件，
                //例如setWay(map,7,5)执行结果为true；
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {//说明把人放到该位置是走不出迷宫的
                    map[i][j] = 3;//记录一下，人不要处于该位置
                    return false; //走不出迷宫,return false
                }
            } else {
                return false; //该位置有墙（或有障碍）或已被证明走不通
            }
        }
    }
}
