package com.dayi.queue;

import java.util.Scanner;

/**
 * 数组队列
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-11-26 17:10
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        // 创建一个数组队列
        ArrayQueue queue = new ArrayQueue(3);
        // 接收用户输入的数据
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        Boolean loop = true;
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's' :
                    queue.showQueue();
                    break;
                case 'a' :
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'e' :
                    scanner.close();
                    loop = false;
                    break;
                case 'g' :
                    try {
                        int result = queue.getQueue();
                        System.out.printf("取出的数据是：%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h' :
                    try {
                        int result = queue.headQueue();
                        System.out.printf("队列头的数据是：%d\n", result);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出...");
    }

}

/**
 * 使用数组模拟队列
 */
class ArrayQueue {
    /** 队列的最大容量 */
    private int maxSize;
    /** 队列头 */
    private int front;
    /** 队列尾 */
    private int rear;
    /** 存储数据 */
    private int[] arr;

    /** 队列构造器 */
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    /** 判断队列是否已满 */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /** 判断队列是否为空 */
    public boolean isEmpty() {
        return rear == front;
    }

    /** 添加数据到队列 */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据了...");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    /** 获取队列的数据，出队列 */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;
        return arr[front];
    }

    /** 显示队列的所有数据 */
    public void showQueue() {
        //遍历数组输出
        if (isEmpty()) {
            System.out.println("队列空的，没有数据...");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d] = %d\n", i, arr[i]);
        }
    }

    /** 显示队列的头数据 */
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return arr[front + 1];
    }

}