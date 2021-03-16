package com.yang.stack;

public class Calculator extends ArrayStack{

    public Calculator(int maxSize){
        super(maxSize);
    }

    public Calculator() {

    }

    /**
     * 判断运算符优先级
     * @param ope 运算符
     * @return 优先级
     */
    public int priority(int ope){
        if (ope=='+'||ope=='-'){
            return 0;
        }else if (ope=='*'||ope=='/'){
            return 1;
        }else {
            return -1;
        }
    }
    /**
     * 判断是否是运算符
     */
    public boolean isOpe(char val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }
    /**
     * 执行计算
     */
    public int cal(int num1,int num2,int ope){
        int res=0;
        switch (ope){
            case '+':
                res=num2+num1;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num2*num1;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }

}
