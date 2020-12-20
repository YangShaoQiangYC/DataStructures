package com.dayi.linkedlist;

/**
 * 双向链表
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2020-12-03 11:38
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();
        // 修改
        System.out.println("修改节点之后...");
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();
        // 删除
        System.out.println("删除节点之后...");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
        // 顺序加入双向链表
        System.out.println("按顺序添加双向链表节点...");
        DoubleLinkedList doubleLinkedList2 = new DoubleLinkedList();
        doubleLinkedList2.addByOrder(hero2);
        doubleLinkedList2.addByOrder(hero1);
        doubleLinkedList2.addByOrder(hero4);
        doubleLinkedList2.addByOrder(hero3);
        doubleLinkedList2.list();
    }
}

/**
 * 定义一个双向链表
 */
class DoubleLinkedList {
    /** 先初始化一个头节点，头节点不能动，也不能存放具体的数据 */
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 添加节点
     * 说明：将一个节点添加到双向链表的最后
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        // 因为头节点不能动，因此我们需要一个辅助遍历的temp节点
        HeroNode2 temp = head;
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
        heroNode.pre = temp;
    }

    /**
     * 按顺序添加双向链表节点
     * @param heroNode
     */
    public void addByOrder(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        // flag标志添加的排名编号是否存在
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            // 编号已经存在，不能添加
            System.out.printf("准备插入的英雄编号%d已经存在了，不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
            heroNode.pre = temp;
            if (heroNode.next != null) {
                heroNode.next.pre = heroNode;
            }
        }
    }

    /**
     * 修改节点的信息
     * 说明：更具no编号来修改，即no编号不能修改
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空...");
            return;
        }
        HeroNode2 temp = head.next;
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
     * @param no
     */
    public void delete(int no) {
        // 判断单前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;
        // 是否找到要删除的节点
        boolean flag = false;
        while (true) {
            if (temp == null) {
                // 已经遍历到链表最后
                break;
            }
            if (temp.no == no) {
                // 找到准备删除的节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag
        if (flag) {
            // 找到准备删除的节点，可以删除
            temp.pre.next = temp.next;
            // 如果要删除的节点不是最后一个节点，则执行下面这句话，否则会报空指针异常
            // （因为最后一个节点没有再下一个节点了，temp.next == null, null.pre就会报空指针异常）
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
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
        HeroNode2 temp = head.next;
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
class HeroNode2 {
    /** 英雄编号排名 */
    public int no;
    /** 英雄名称 */
    public String name;
    /** 英雄昵称 */
    public String nickName;
    /** 指向下一个节点 */
    public HeroNode2 next;
    /** 指向上一个节点 */
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
