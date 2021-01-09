package com.dayi.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式计数器（后缀表达式计算器）
 *  说明：逆波兰表达式（后缀表达式）是数值在前，计算符号在后的计算表达式
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-09 16:24
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 先定义逆波兰表达式（后缀表达式），为了方便说明，逆波兰表达式的数字和符号使用空格分隔
        // (3+4)*5-6 -> 3 4 + 5 * 6 -
        String suffixExpression = "30 4 + 5 * 6 -";
        List<String> listStr = getListStr(suffixExpression);
        System.out.println(listStr);
        int result = caculate(listStr);
        System.out.println("计算结果是：" + result);
    }

    /**
     * 将逆波兰表达式，依次将数值和操作符存放到ArrayList中
     * @param suffixExpression
     * @return
     */
    public static List<String> getListStr(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>(split.length);
        for (String str : split) {
            list.add(str);
        }
        return list;
    }

    /**
     * 计算逆波兰表达式
     * 说明：
     * 1.从左至右扫描,即遍历【存放逆波兰表达式数值和运算符的list】
     * 2.遇到运算符，则从栈中pop出两个数值，用运算符进行计算，并将计算结果存放入栈中
     * 3.当【存放逆波兰表达式数值和运算符的list】遍历完毕，栈中的数值就是结果
     * @param list 存放逆波兰表达式数值和运算符的list
     * @return
     */
    public static int caculate(List<String> list) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            // 这里使用正则表达式来取出数
            if (item.matches("\\d+")) {
                // 当匹配到是单位或者是多位数值时，则直接入栈
                stack.push(item);
            } else {
                // 当是运算符时，则pop出两个数进行运算，并将运算结果存入栈中
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")) {
                    result = num1 + num2;
                } else if (item.equals("-")) {
                    result = num2 - num1;
                } else if (item.equals("*")) {
                    result = num1 * num2;
                } else if (item.equals("/")) {
                    result = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push("" + result);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
