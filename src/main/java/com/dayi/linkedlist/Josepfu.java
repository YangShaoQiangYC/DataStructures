package com.dayi.linkedlist;

/**
 * 约瑟夫问题
 * 说明：n个小孩围成一圈报数，当报到m时出列，下个小孩从1开始重新计算报数，当报到m时再次出列，
 * 直到所有的小孩均出列为止，这时，小孩的出列顺序，就是约瑟夫问题
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-12-31 9:53
 */
public class Josepfu {
    public static void main(String[] args) {
        // 测试一般
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        System.out.println("测试小孩出圈的顺序（约瑟夫问题）");
        // 测试小孩出圈的顺序（约瑟夫问题） 2-4-1-5-3
        circleSingleLinkedList.count(1, 2, 5);
    }
}

/**
 * 创建一个单向环形列表
 */
class CircleSingleLinkedList {
    /** 创建第一个节点的引用指针，指向第一个小孩节点 */
    private Boy first = null;

    /**
     * 添加小孩节点
     * @param nums 节点个数
     */
    public void addBoy(int nums) {
        // 个数校验，至少有一个
        if (nums < 1) {
            System.out.println("小孩个数的值不正确");
            return;
        }
        // 临时指针，指向当前小孩节点
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                // 只有一个小孩节点，也要构成环
                first.setNext(first);
                // 让curBoy先指向第一个小孩节点
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                curBoy = boy;
                boy.setNext(first);
            }
        }
    }

    /**
     * 显示链表【遍历】
     */
    public void showBoy() {
        // 判断链表是否为空
        if (first == null) {
            System.out.println("没有任何节点数据");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curboy = first;
        while (true) {
            System.out.printf("小孩的编号%d \n", curboy.getNo());
            if (curboy.getNext() == first) {
                // 说明遍历完毕
                break;
            }
            // curBoy后移
            curboy = curboy.getNext();
        }
    }

    /**
     * 根据用户输入，计算出小孩节点出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩在圈中
     */
    public void count(int startNo, int countNum, int nums){
        // 对数据进行校验
        if (first == null || startNo <= 0 || startNo > nums) {
            System.out.println("请求参数有误，请稍后重试");
            return;
        }
        // 创建辅助指针，辅助完成小孩出圈
        Boy helper = first;
        // 事先架构helper辅助指针指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数前，先让first和helper指针移动（startNo-1）次
        for (int i = 1; i <= startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        // 小孩开始报数
        while (true) {
            // 圈中只有一个人
            if (helper == first) {
                break;
            }
            // 让first和helper指针同时移动（countNum-1）次
            for (int i = 1; i <= countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这是first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            // 报完数之后。将小孩所在节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        // 最后留在圈中的最后小孩输出
        System.out.printf("最后留在圈中的小孩标号%d\n", first.getNo());
    }

}

/**
 * 创建一个Boy类，表示一个节点
 */
class Boy {
    /** 编号 */
    private int no;
    /** 指向下一个节点，默认为null */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
