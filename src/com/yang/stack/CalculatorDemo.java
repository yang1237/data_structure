package com.yang.stack;

public class CalculatorDemo {
    public static void main(String[] args) {
        Calculator numCal = new Calculator(10);
        Calculator opeCal = new Calculator(10);
        String str="3+20*6-20";
        int index=0;
        String temp="";
        while (index<str.length()){
            char ch=str.charAt(index);
            if (opeCal.isOpe(ch)){
                if (opeCal.isEmpty()){
                    opeCal.push(ch);
                }else if (opeCal.priority(ch)>opeCal.priority(opeCal.peek())){
                    opeCal.push(ch);
                }else {
                    int c = opeCal.pop();
                    int num1 = numCal.pop();
                    int num2 = numCal.pop();
                    int cal = opeCal.cal(num1, num2,c );
                    numCal.push(cal);
                    opeCal.push(ch);
                }
            }else {
                temp+=ch;
                if (index==str.length()-1){
                    numCal.push(Integer.parseInt(temp));
                }else{
                    if (opeCal.isOpe(str.charAt(index+1))){
                        numCal.push(Integer.parseInt(temp));
                        temp="";
                    }
                }
            }
            index++;
        }
        while (numCal.getTop()!=0){
            int i2 = numCal.pop();
            int i1 = numCal.pop();
            int pop = opeCal.pop();
            int cal = opeCal.cal(i2, i1, pop);
            numCal.push(cal);
        }
        System.out.println(numCal.peek());
    }
}
