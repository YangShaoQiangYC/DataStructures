package com.dayi.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 逆波兰表达式计数器（后缀表达式计算器）
 *  说明：逆波兰表达式（后缀表达式）是数值在前，计算符号在后的计算表达式
 *
 * 中缀表达式转后缀表达式的思路：
 * 1.初始化两个栈，运算符栈s1和存储中间结果的栈s2;
 * 2.从左至右扫描中缀表达式;
 * 3.遇到操作数时，将其压入s2;
 * 4.遇到运算符时，比较其与s1栈顶运算符的优先级：
 *  1）如果s1为空，或栈顶运算符为“（”,则直接将此运算符入s1栈；
 *  2）否则，若优先级比栈顶运算符高，也将此运算符入栈；
 *  3）否则，将s1栈顶的运算符弹出，并压入到s2操作数栈中，再次转到4.1与s1中新的栈顶运算符相比较
 * 5.遇到括号时：
 *  1）如果是左括号“（”，则直接压入s1
 *  2）如果是有括号“）”，则依次弹出s1栈顶的运算符，并压入s2中，直到遇到左括号为止，此时将这一对括号丢弃
 * 6.重复步骤2至5，直到表达式的最右边
 * 7.将s1中剩余的运算符依次弹出并压入s2中
 * 8.依次弹出s2中的元素并输出，结果的逆序即为【中缀表达式对应的后缀表达式】
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-09 16:24
 */
public class PolandNotation2 {

    /**
     * 匹配 + - * / ( ) 运算符
     */
    private static Pattern SYMBOL_PATTERN = Pattern.compile("\\+|-|\\*|/|\\(|\\)");

    public static void main(String[] args) {
        String infixExpression = "1  +((2.5+   3) *   4) -5";
        // 去除所有空格
        infixExpression = replaceAllBlank(infixExpression);
        System.out.println("去除空格后的中缀表达式：" + infixExpression);
        List<String> InfixExpressionList = toInfixExpressionList(infixExpression);
        System.out.println("中缀表达式list：" + InfixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpression(InfixExpressionList);
        System.out.println("后缀表法式list：" + suffixExpressionList);
        // 中缀表达式转后缀表法式，并计算
        Double result = caculate(suffixExpressionList);
        System.out.printf("%s=%f", infixExpression, result);
    }

    /**
     * 将中缀表达式的list 转为 后缀表达式的list
     * @param infixExpression 中缀表达式的list
     * @return
     */
    public static List<String> parseSuffixExpression(List<String> infixExpression) {
        // 初始化栈，s1是存放运算符的栈，s2是存放中间结果的栈
        Stack<String> s1 = new Stack<>();
        // 因为s2没有进行pop操作，且后面也需要进行逆序输出，因此我们不使用Stack，直接使用list存放
        List<String> s2 = new ArrayList<>();
        // 编辑前缀表达式
        for (String item : infixExpression) {
            if (!isOper(item)) {
                // 遇到操作数时，将其加入s2
                s2.add(item);
            } else if (item.equals("(")) {
                // 如果是左括号“（”，则直接压入s1
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号“）”，则依次弹出s1栈顶的运算符，并加入s2中，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                // 将左括号丢弃
                s1.pop();
            } else {
                if (s1.size() == 0 || s1.peek().equals("(")) {
                    // 如果s1为空，或栈顶运算符为“（”,则直接将此运算符入s1栈
                    s1.push(item);
                } else {
                    // 当s1不为空，且item的优先级小于s1栈顶运算符，需将s1栈顶的运算符弹出添加到s2中，再次转到4.1与s1中新的栈顶运算符相比较
                    while (s1.size() != 0 && Operation.getValue(item) <= Operation.getValue(s1.peek())) {
                        s2.add(s1.pop());
                    }
                    s1.push(item);
                }
            }
        }
        // 将s1中剩余的运算符依次弹出并加入到s2中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        // 将中缀表达式list 转换为 后缀表达式list的结果返回
        return s2;
    }

    /**
     * 将中缀表达式转成对应的list
     * @param infixExpression 中缀表达式
     * @return
     */
    public static List<String> toInfixExpressionList(String infixExpression) {
        List<String> list = new ArrayList<>();
        // 扫描运算符的标识和保存扫描结果变量
        int index = 0;
        char ch = ' ';
        // 数值变量字符串，用于拼接多位数值
        String keepNum = "";
        do {
            // 如果ch是一个字符，则直接加入到list
            if (isOper(String.valueOf(ch=infixExpression.charAt(index)))) {
                list.add("" + ch);
                index++;
            } else {
                keepNum = "";
                // 拼接多位数值，数字符号小数点的ASCII编码是46
                while (index < infixExpression.length() && ((ch=infixExpression.charAt(index)) >= 48
                        && (ch=infixExpression.charAt(index)) <= 57 || ((ch=infixExpression.charAt(index)) == 46))) {
                    keepNum += ch;
                    index++;
                }
                list.add(keepNum);
            }
        } while (index < infixExpression.length());
        // 返回list
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
    public static Double caculate(List<String> list) {
        // 创建栈
        Stack<String> stack = new Stack<>();
        for (String item : list) {
            // 这里使用正则表达式来取出数
            if (!isOper(item)) {
                // 当匹配到是单位或者是多位数值时，则直接入栈
                stack.push(item);
            } else {
                // 当是运算符时，则pop出两个数进行运算，并将运算结果存入栈中
                Double num1 = Double.parseDouble(stack.pop());
                Double num2 = Double.parseDouble(stack.pop());
                Double result = 0.00;
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
        return Double.parseDouble(stack.pop());
    }

    /**
     * 取出所有空白符
     * @param infixExpression
     */
    public static String replaceAllBlank(String infixExpression) {
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return infixExpression.replaceAll("\\s+","");
    }

    /**
     * 判断是不是一个运算符
     * @param value
     * @return
     */
    public static boolean isOper(String value) {
        return SYMBOL_PATTERN.matcher(value).matches();
    }

}

/**
 * 返回运算符对应的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}