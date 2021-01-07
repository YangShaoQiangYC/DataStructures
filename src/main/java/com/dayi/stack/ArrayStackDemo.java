package com.dayi.stack;

import java.util.Scanner;

/**
 * 用数组实现一个栈的demo
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-12-31 15:00
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        /** 用户操作标识 */
        String key = "";
        /** 控制是否退出菜单 */
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);

        while (loop) {
            System.out.println("show：表示显示栈");
            System.out.println("push：表示添加数据到栈（入栈）");
            System.out.println("pop：表示从栈取出数据（出栈）");
            System.out.println("exit：退出程序");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int result = stack.pop();
                        System.out.printf("出栈的数据是%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    System.out.println("请输入正确的操作指令");
                    break;
            }
        }
        System.out.println("程序已退出");
    }
}

/**
 * 用数组实现一个栈
 */
class ArrayStack {
    /** 栈的大小 */
    private int maxSize;
    /** 数组，模拟栈用于存储数据 */
    private int[] stack;
    /** 栈顶 */
    private int top = -1;

    /** 构造器 */
    public ArrayStack(int maxSize) {
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

}
