package com.dayi.linkedlist;

import java.util.Stack;

/**
 * 单向链表
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-11-27 11:25
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        // 创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        // 创建链表，边插入节点
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 1.添加节点到单向链表，不考虑编号顺序时
        /*singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);*/
        // 2.添加节点到单向链表，考虑编号顺序时
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        //singleLinkedList.addByOrder(hero3);
        // 3.修改节点
        HeroNode newHeroNode = new HeroNode(2, "小卢", "小玉麒麟");
        singleLinkedList.update(newHeroNode);
        // 4.删除节点
        singleLinkedList.delete(3);
        // 把链表打印一把
        singleLinkedList.list();

        // 面试题1：求单链表中有效节点的个数
        System.out.println("有效节点个数=" + getLength(singleLinkedList.getHead()));
        // 面试题2：获取单链表倒数第index个节点
        HeroNode heroNode = findLastIndexNode(singleLinkedList.getHead(), 1);
        System.out.println("result:" + heroNode);
        // 面试题3：将单链表反转
        System.out.println("将单链表反转...");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
        // 面试题4：逆序打印单链表
        System.out.println("逆序打印单链表（没有改变原链表的结构）...");
        reversePrint(singleLinkedList.getHead());

    }

    /**
     * 利用栈的数据结构，实现将单向链表的逆袭打印效果
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        // 空链表不能打印
        if (head.next == null) {
            return;
        }
        // 创建一个栈，将各个节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // 将链表的所有节点压入栈中
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 将栈中的节点进行打印
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    /**
     * 将单链表反转
     * @param head
     */
    public static void reverseList(HeroNode head) {
        // 当链表没有节点，或者只有一个节点，无须反转
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 定义一个辅助节点cur，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        // next节点指点当前节点的下一个节点，便于我们遍历原来的链表
        // （因为cur节点遍历过后，其下一个节点的指向会变更，所有需要next节点预先做记录）
        HeroNode next = null;
        // 反向链表预先定义的头节点
        HeroNode reversetHead = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reversetHead.next;
            reversetHead.next = cur;
            cur = next;
        }
        // 将原先列表的头节点指定反向链表
        head.next = reversetHead.next;
    }

    /**
     * 查找链表中倒数第k个节点
     * 思路：
     * 1.编写一个方法，接收head节点，同时接收一个index
     * 2.index表示倒数第index个节点
     * 3.先把链表从头到尾遍历，得到链表的总长度getLength
     * 4.得到size后，我们从链表的第一个节点开始遍历到（size - index）个，就可以得到
     * 5.如果找到了，就返回该节点，否则则返回null
     *
     * @param head 头节点
     * @param index 倒数第几个节点
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        // 过滤空链表
        if (null == head.next) {
            return null;
        }
        // 获取链表有效节点的个数
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        // 定义辅助变量
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 获取单链表的节点个数（如果是带头节点的链表，需要不统计头节点）
     * @param head
     * @return
     */
    public static int getLength(HeroNode head) {
        // 过滤空链表
        if (null == head.next) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (null != cur) {
            length++;
            cur = cur.next;
        }
        return length;
    }

}

/**
 * 定义SingleLinkedList 管理我们的英雄
 */
class SingleLinkedList {
    /** 先初始化一个头节点，头节点不能动，也不能存放具体的数据 */
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * 思路：当不考虑编号顺序时
     * 1.找到当前链表的最后节点
     * 2.将左后这和节点的next，指向新添加的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        // 因为头节点不能动，因此我们需要一个辅助遍历的temp节点
        HeroNode temp = head;
        // 遍历链表，找到最后
        while (true) {
            // 找到链表的最后
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temo后移
            temp = temp.next;
        }
        // 将链表最后的节点，指向新加入的节点
        temp.next = heroNode;
    }

    /**
     * 添加节点到单向链表（按编号排名顺序插入到指定位置）
     *  说明：如果编号排名已存在，则添加失败，并给出失败提示
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        // 因为头节点不能动，因此我们需要一个辅助遍历的temp节点
        HeroNode temp = head;
        // flag标志添加的排名编号是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                // 插入节点已经找到
                break;
            } else if (temp.next.no == heroNode.no) {
                // 说明插入节点的编号已经存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 编号已经存在，不能添加
            System.out.printf("准备插入的英雄编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            // 插入到链表中，temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 修改节点的信息
     * 说明：更具no编号来修改，即no编号不能修改
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        HeroNode temp = head.next;
        // flag标志是否找到要修改的节点
        boolean flag = false;
        // 根据no编号，找到需要修改的节点
        while (true) {
            if (temp == null) {
                // 已经遍历完所有的节点
                break;
            }
            if (temp.no == newHeroNode.no) {
                // 找到要修改的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            // 没有找到准备修改的节点，给出提示信息
            System.out.printf("没有找到编号为%d的节点，不能修改\n", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     * 思路：
     * 1.head节点不能动，因此需要一个temp辅助节点找到待删除节点的前一个节点
     * 2.我们在比较时，是temp.next节点和需要被删除节点的no比较
     * @param no 准备删除的节点的no
     */
    public void delete(int no) {
        HeroNode temp = head;
        // 是否找到要删除的节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                // 已经遍历到链表最后
                break;
            }
            if (temp.next.no == no) {
                // 找到准备删除的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag
        if (flag) {
            // 找到准备删除的节点，可以删除
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n" + no);
        }
    }

    /**
     * 显示链表【遍历】
     */
    public void list() {
        // 判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，没有数据...");
            return;
        }
        // 因为头节点不能动，因此需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (true) {
            // 判断是否到链表最后
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            // 将temp后移
            temp = temp.next;
        }
    }

    public HeroNode getHead() {
        return head;
    }

}

/**
 * 定义HeroNode，每个HeroNode就是一个节点
 */
class HeroNode {
    /** 英雄编号排名 */
    public int no;
    /** 英雄名称 */
    public String name;
    /** 英雄昵称 */
    public String nickName;
    /** 指向下一个节点 */
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}