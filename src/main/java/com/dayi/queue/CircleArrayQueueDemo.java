package com.dayi.queue;

import java.util.Scanner;

/**
 * 环形数组队列
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-11-27 9:31
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // 创建一个环形数组队列（因为本环形队列算法有一个预留空间，所以设置值为4，队列的有效数据的最大个数为3）
        CircleArray queue = new CircleArray(4);
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
 * 使用数组模拟环形队列
 * 说明：
 * 1.front变量的含义做一个调整：front指向队列的第一个元素，也就是arr[front]就是队列的第一个元素；front的初始值为0
 * 2.rear变量的含义做一个调整：rear指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定；rear的初始值为0
 * 3.当队列满时，条件是(rear+1) % maxSize == front
 * 4.当队列为空时，条件是rear == front
 * 5.当我们这样分析，队列中的有效数据的个数为：(rear+maxSize-front) % maxSize , rear=1,front=0,有效数据个数为1
 */
class CircleArray {
    /** 队列的最大容量 */
    private int maxSize;
    /** 队列头 */
    private int front;
    /** 队列尾 */
    private int rear;
    /** 存储数据 */
    private int[] arr;

    /** 队列构造器 */
    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    /** 判断队列是否已满 */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
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
        // 直接将数据加入
        arr[rear] = n;
        // rear后移
        rear = (rear + 1) % maxSize;
    }

    /** 获取队列的数据，出队列 */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /** 显示队列的所有数据 */
    public void showQueue() {
        //遍历数组输出
        if (isEmpty()) {
            System.out.println("队列空的，没有数据...");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /** 显示队列的头数据 */
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据");
        }
        return arr[front];
    }

    /** 求出当前队列有效数据的个数 */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

}
