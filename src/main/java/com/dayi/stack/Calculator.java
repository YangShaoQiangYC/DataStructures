package com.dayi.stack;

/**
 * 计算器（中缀表达式计算器）
 * 说明：通过栈实现一个简单的加减乘除的计算器
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-07 16:53
 */
public class Calculator {
    public static void main(String[] args) {
        // 计算的表达式
        String expression = "30+2*6-3";
        // 创建数值栈和运算符栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 扫描运算符的标识和保存扫描结果变量
        int index = 0;
        char ch = ' ';
        // 数值变量字符串，用于拼接多位数值
        String keepNum = "";
        // 运算相关的变量
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        // 循环扫描表达式
        while (true) {
            // 依次得到表达式expression表达式的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么，依次处理
            if (operStack.isOper(ch)) {
                // 判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    // 如果符号栈已有操作符，就进行比较
                    // --如果当前的操作符的优先级小于或者等于栈中的的操作符，就从数值栈中pop出两个数，运算符栈中pop出
                    //   一个操作符进行运算，将得到的结果入数值栈，再将单前运算符入操作符栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        result = numStack.cal(num1, num2, oper);
                        // 把运算的结果入数值栈
                        numStack.push(result);
                        // 把当前操作符入运算符栈
                        operStack.push(ch);
                    } else {
                        // 如果当前操作符的优先级大于运算符栈栈顶的操作符，则直接入运算符栈
                        operStack.push(ch);
                    }
                } else {
                    // 如果运算符栈为空，则直接入运算符栈
                    operStack.push(ch);
                }
            } else {
                /**
                 * 1.(数值只有单位数的情况)
                 *  说明：如果是数值，则直接入数值栈；注意'1' -> 49, '2'-> 50, 故字符的数值需要减去48
                 * numStack.push(ch - 48);
                 * 2.（数值有多位数的情况）
                 *  说明：如果是多位数值时，不能发现是一个数就立即入栈，因为可能是多位数，因此我们需要向expression表达式的index
                 *  后再多看一位，如果是是数字，则拼接到我们定义的数值变量字符串中，如果是操作符，则将数值变量字符串入栈
                 */
                // 处理多位数
                keepNum += ch;
                // 如果ch已经是expression的最后一位，则直接入栈
                if (index == expression.length()-1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个字符是不是数字，如果是数字，则继续扫描，如果是运算符，则入栈
                    if (operStack.isOper(expression.substring(index+1, index+2).charAt(0))) {
                        // 如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));
                        // 注意，入栈之后，用于拼接数值的数值变量字符串要清空
                        keepNum = "";
                    }
                }
            }
            // 让index+1, 并判断是否已经扫描到expression表达式的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从数值栈和符号栈中pop出响应的数值和运算符，并运算
        while (true) {
            // 如果运算符栈为空，则已计算到最后结果，数值栈中也只有一个数值，即运算结果
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        // 将结果输出
        result = numStack.pop();
        System.out.printf("表达式：%s=%d", expression, result);
    }
}

/**
 * 用数组实现一个栈
 */
class ArrayStack2 {
    /** 栈的大小 */
    private int maxSize;
    /** 数组，模拟栈用于存储数据 */
    private int[] stack;
    /** 栈顶 */
    private int top = -1;

    /** 构造器 */
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈-push
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满~~~");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈-pop
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据了~~~");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈的情况【遍历栈】，从栈顶开始遍历
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据了~~~");
        }
        // 需要从栈顶开始显示数据
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 当前栈顶的值
     * @return
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 运算符优先级
     * 说明：
     *  1.返回运算符的优先级，优先级使用数字表示，数字越大，则优先级越高
     *  2.目前支持的运算符有 +，-，*，/
     * @param oper 运算符
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是不是一个运算符
     * @param value
     * @return
     */
    public boolean isOper(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    /**
     * 计算方法
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        // result用于存放计算的结果
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

}