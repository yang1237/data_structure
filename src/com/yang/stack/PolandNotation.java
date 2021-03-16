package com.yang.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //String str="1+((2+3)*4)-5";
     String str = "5*5+2*(2+5*4+2-(3+4*1)+6)+6*6";
        List<String> list = getStringList(str);
        List<String> stringList = getList(list);
        System.out.println(stringList);
        int calculate = calculate(stringList);
        System.out.println(calculate);
    }

    private static List<String> getList(String suffixExpression) {
        String[] strings = suffixExpression.split(" ");
        return Arrays.asList(strings);
    }

    private static List<String> getList(List<String> ls) {
        Stack<String> temp = new Stack<>(); //用于临时存放括号和操作符
        List<String> result = new ArrayList<>(); //返回值
        for (String s : ls) {
            if (s.matches("\\d+")) {
                result.add(s);
            } else if ("(".equals(s)) {
                temp.push(s);
            } else if (temp.size() > 0 && "(".equals(temp.peek())) {
                temp.push(s);
            } else if (")".equals(s)) {
                while (!"(".equals(temp.peek())) {
                    result.add(temp.pop());
                }
                temp.pop();
            } else {
                while (temp.size() > 0 && !("(".equals(temp.peek())) && Operation.getValue(s) <= Operation.getValue(temp.peek())) {
                    result.add(temp.pop());
                }
                temp.push(s);
            }
        }
        while (temp.size()!=0){
            result.add(temp.pop());
        }
        return result;

    }

    private static int calculate(List<String> list) {
        Stack<String> stringStack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")) {
                stringStack.push(s);
            } else {
                //弹出两个数
                int num2 = Integer.parseInt(stringStack.pop());
                int num1 = Integer.parseInt(stringStack.pop());
                int res = switch (s) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    case "/" -> num1 / num2;
                    default -> throw new RuntimeException("运算符有误");
                };
                stringStack.push("" + res);
            }
        }
        return Integer.parseInt(stringStack.pop());
    }

    private static List<String> getStringList(String str) {
        List<String> ls = new ArrayList<>();
        int index = 0;
        char ch;
        StringBuilder temp;
        do {
            if ((ch = str.charAt(index)) < 48 || (ch = str.charAt(index)) > 57) {
                ls.add("" + ch);
                index++;
            } else {
                temp = new StringBuilder();
                while (index < str.length() && (ch = str.charAt(index)) >= 48 && (ch = str.charAt(index)) <= 57) {
                    temp.append(ch);
                    index++;
                }
                ls.add(temp.toString());
            }
        } while (index < str.length());
        return ls;
    }
}

class Operation {
    private final static int ADD = 1;
    private final static int SUB = 1;
    private final static int MUL = 2;
    private final static int DIV = 2;

    public static int getValue(String operator) {
        int result = switch (operator) {
            case "+" -> ADD;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            default -> throw new RuntimeException("操作符有误");
        };
        return result;
    }
}
