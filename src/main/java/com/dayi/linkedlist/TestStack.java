package com.dayi.linkedlist;

import java.util.Stack;

/**
 * 演示栈Stack的基本使用
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-12-02 17:54
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        // 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}
