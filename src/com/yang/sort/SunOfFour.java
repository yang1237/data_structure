package com.yang.sort;

import java.util.*;

public class SunOfFour {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        //判空
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        //先排序
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) {
            //取第一个数,如果重新取的数等于上一个取的数，再次重新取数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //连续取四个数，和大于target,后面的数不存在满足四个数的和=target的情况
            if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            //取出来的数与数组最大的三个数之和<target,重新取第一个数
            if (nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {
                continue;
            }
            //从第一个数往后取三个数
            for (int j = i + 1; j < length - 2; j++) {
                //取第二个数,如果重新取得数，等于上一次取得数，再次重新取数
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                //从第二个数，往后取两个数,凑成四个数，如果和>target,后面的情况不再满足=target
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                //找出数组中最大的两个数,凑成四个数，如果和<target,重新取数
                if (nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                //还要再取两个数,作为第三和第四个数，第三数从索引j往后取，第四个数，从索引length-1往前取
                int left = j + 1, right = length - 1;
                //当left=right时，第三和第四取得是同一个位置上的数，不满足取出四个数的规则，退出循环，当left>right时，在与上一个取数同样的两个位置上取数,属于重复的四元组，同样退出循环，即只要left不再小于right,退出循环
                while (left < right) {
                    //四数之和
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    //（终于凑齐四个数）
                    if (sum == target) {
                        //满足条件的四元组，放到list中
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        /*第三和第四个数，重新取数，while循环找出所有满足的情况*/

                        //好像叫剪枝？
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        //第三个数重新取数
                        left++;
                        //剪枝
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        //第四个数重新取数
                        right--;
                    } else if (sum < target) {
                        //凑齐四个数发现和小于target，第三个数往后取,使和变大
                        left++;
                    } else {
                        //emmm,第四个数取大了，也要修正，第四个数往前取，使和变小
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : chars) {
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for (int i = 0; i <chars.length; i++) {
            if (map.get(chars[i])==1){
                return i;
            }
        }
        return -1;
    }

    /*
        分发糖果，正确理解题意比较重要，根据题意可以理解为
        看到一队小朋友，按照糖果分发的规则，老师最少需要准备多少糖果
     */
    public static int candy(int[] ratings){
         //先满足后面评分高的小朋友比前面评分低的小朋友分的糖果多
        //(即先给前面的朋友分配糖果，根据前面的朋友的糖果数，决定我拿几颗糖果)
        int[] left =new int[ratings.length];
        int candyCount=0;
        for(int i=0;i< ratings.length;i++){
            if (i>0&&ratings[i]>ratings[i-1]){
                /*
                    我的评分比前一位同学高
                 */
                left[i]=left[i-1]+1;
            }else {
                //如果我的评分没有排在我前面的小朋友评分高，我就只能拿1个糖果,
                //但是如果我的评分比排在我后面的小朋友评分高的时候，我就不应该拿只1个糖果,（此时，排在后面的同学小声BB，
                // 白拿比我高的分数，糖果居然和我一样多，hh。为了保证规则被公平地遵守，这个问题下一步解决）
                left[i]=1;
            }
        }
        //解决前面评分高的朋友和后面评分低的朋友拿到的糖果一样多的问题
        // (即先给后面的朋友分配糖果，根据后面的朋友的糖果数目，决定我拿几颗糖果)
        int[] right =new int[ratings.length];
        for (int i = ratings.length-1; i>=0; i--) {
            if (i<ratings.length-1&&ratings[i]>ratings[i+1]){
                right[i]=right[i+1]+1;
            }else {
                right[i]=1;
            }
            /*
            * 最后进行修正，以最后一个同学为例，最后一个同学在该轮分发糖果中，拿到了一个糖果，
            * 但如果我的评分比我前一位同学的评分高，我在上一轮分发糖果是，拿到的糖果数目大于1，
            * 为了保证上轮分发糖果的结果被尊重（即你可以不给我增加糖果，但你不能从我手里把我已经得到的糖果再拿走），
            *
            * */
            //那么老师发出去的糖果数目为
            candyCount+=Math.max(left[i],right[i]);
        }
        return candyCount;
    }
    public static int candy2(int[] ratings){
        //每人至少一颗
        int candy=ratings.length;
        //从左到右补发
        for (int i = 0; i < ratings.length; i++) {
            int add=1;
            while (i>0 && i< ratings.length && ratings[i]>ratings[i-1]){
                //每碰到一次再发出去一颗
                candy+=add;
                add++;
                i++;
            }

        }
        //从右到左补发
        for (int i = ratings.length-1; i >=0; i--) {
            int add=1;
            while (i<ratings.length-1 && i>=1 && ratings[i]>ratings[i+1] && ratings[i]>=ratings[i-1]){
                candy+=add;
                add++;
                i--;
            }
        }
        return candy;
    }

    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count=0;
        int childIndex=0;
        for (int j : s) {
            if (childIndex < g.length && j >= g[childIndex]) {
                count++;
            }
            childIndex++;
        }
        return count;
    }

    public static int MaxRectangle(char[][] matrix){
        //先记录从每个位置往左数，得到的连续的'1'的数目
        int row=matrix.length;
        if (row==0){
            return 0;
        }
        int col=matrix[0].length;
        int[][] left=new int[row][col]; //用来记录该位置往左数，连续的'1'的数目
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                if (matrix[i][j]=='1'){
                    //该位置往左数连续'1'的数目，决定于该位置前一个位置往左数，连续'1'的数目
                    left[i][j]=(j==0?0:left[i][j-1])+1;
                }
            }
        }
        //返回值，即结果
        int res=0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j]=='0'){
                    continue;
                }
                //碰到'1'时
                int width=left[i][j];//该位置上能数到的连续'1'的数目为矩形的宽
                //该位置所在行在该位置上由'1'所组成的矩形的面积
                int area=width;
                for (int k = i-1; k >=0 ; k--) {
                    //比较矩阵只有i行时，在该位置能得到的矩形的面积
                    //该位置上，由i行所组成的矩形的宽为
                    width=Math.min(width,left[k][j]);
                    //比较两个矩形的面积，然后取最大值，即为矩阵有i行时,能得到的最大矩形
                    area=Math.max(area,width*(i-k+1));
                }
                //记录矩阵有i行时，该位置得到的最大矩形，循环比较
                res=Math.max(res,area);
            }

        }
        return res;

    }

    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        int len=s.length();
        for (int i = 0; i < len; i++) {
            char x = s.charAt(i), y=t.charAt(i);
            if (!map.containsKey(x)){
                if (map.containsValue(y)){
                    return false;
                }
                map.put(x,y);
            }else {
                if (map.get(x)!=y){
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {
        int[] g={1,2,};
        int[] s={1,2,3};
        System.out.println(findContentChildren(g,s));
    }

}
