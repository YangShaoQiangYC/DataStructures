package com.dayi.linkedlist;

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
                return;
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