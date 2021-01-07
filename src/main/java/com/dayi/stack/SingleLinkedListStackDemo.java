package com.dayi.stack;

import java.util.Scanner;

/**
 * 单链表实现栈
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-07 9:48
 */
public class SingleLinkedListStackDemo {
    public static void main(String[] args) {
        SingleLinkedListStack stack = new SingleLinkedListStack(4);
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
                    Node node = new Node(value);
                    stack.push(node);
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

class SingleLinkedListStack {
    /** 栈的大小 */
    private int maxSize;
    /** 栈顶 */
    private Node top = null;
    /** 当前链表大小 */
    private int curSize = 0;

    /** 初始化构造方法 */
    public SingleLinkedListStack(int maxSize) {
        this.maxSize = maxSize;
    }

    /**
     * 栈满
     * @return
     */
    public boolean isFull() {
        return curSize == maxSize;
    }

    /**
     * 栈空
     * @return
     */
    public boolean isEmpty() {
        return curSize == 0;
    }

    /**
     * 入栈-push
     * @param node
     */
    public void push(Node node) {
        if (isFull()) {
            System.out.println("栈满~~~");
            return;
        }
        if (curSize == 0) {
            top = node;
        } else {
            node.next = top;
            top = node;
        }
        curSize++;
    }

    /**
     * 出栈-pop
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据了~~~");
        }
        int value = top.value;
        top = top.next;
        curSize--;
        return value;
    }

    /**
     * 显示栈的情况【遍历栈】，从栈顶开始遍历
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据了~~~");
        }
        Node curNode = top;
        // 需要从栈顶开始显示数据
        for (int i = 0; i < curSize; i++) {
            System.out.printf("stack[%d]=%d\n", i, curNode.value);
            curNode = curNode.next;
        }
    }

}

/**
 * 义HeroNode，每个HeroNode就是一个节点
 */
class Node {
    /**
     * 节点值
     */
    public int value;
    /**
     * 指向下一个节点
     */
    public Node next;

    /** 构造器 */
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", next=" + next +
                '}';
    }
}
