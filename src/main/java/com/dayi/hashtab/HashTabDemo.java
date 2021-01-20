package com.dayi.hashtab;

import java.util.Scanner;

/**
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-20 15:42
 */
public class HashTabDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTab hashTab = new HashTab(7);

        // 编写一个简单的菜单
        boolean loop = true;
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (loop) {
            System.out.println("add：添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("delete：删除雇员");
            System.out.println("exit：退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id:");
                    Integer id = scanner.nextInt();
                    System.out.println("输入名字:");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的雇员id:");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除的雇员id");
                    id = scanner.nextInt();
                    hashTab.deleteById(id);
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序已退出");
    }
}

/**
 * 创建HashTab管理多条链表
 */
class HashTab {
    /** 链表数组 */
    private EmpLinkedList[] empLinkedListArray;
    /** 链表数组的大小 */
    private Integer size;

    /** 构造器 */
    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        // 注意，上面只是初始化链表数组，数组中每一个链表元素并没有初始化，也需要初始化
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    /**
     * 添加雇员
     * @param emp
     */
    public void add(Emp emp) {
        // 根据员工的id，得到该员工应当添加到哪条链表
        Integer empLinkedListArrayNo = hashFun(emp.id);
        // 将emp添加到对应的链表中
        empLinkedListArray[empLinkedListArrayNo].add(emp);
    }

    /**
     * 遍历哈希表
     */
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    /**
     * 根据输入的id，查找雇员
     * @param id
     */
    public void findEmpById(Integer id) {
        // 使用散列函数确定到哪条链表上进行查找
        Integer empLinkedListArrayNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListArrayNo].findEmpById(id);
        if (emp != null) {
            System.out.printf("在第%d条链表中找到id为%d的雇员", (empLinkedListArrayNo+1), id);
            System.out.println();
        } else {
            System.out.println("在哈希表中，没有找到该雇员~");
        }
    }

    /**
     * 根据id删除雇员
     * @param id
     */
    public void deleteById(Integer id) {
        // 使用散列函数确定到哪条链表上进行查找
        Integer empLinkedListArrayNo = hashFun(id);
        // 将该emp从链表中删除
        empLinkedListArray[empLinkedListArrayNo].deleteById(id);
    }

    /**
     * 散列函数，简单取模的方式
     * @param id
     * @return
     */
    public Integer hashFun(Integer id) {
        return id % size;
    }

}

/**
 * 雇员链表
 */
class EmpLinkedList {

    /** 指定一个头指针，指向第一个元素 */
    private Emp head;

    /**
     * 添加雇员到链表
     * 说明：假定当添加雇员时，id是自增长，即id的分配总是从小到大
     *   因此我们直接将链表加入到本链表的最后即可
     * @param emp
     */
    public void add(Emp emp) {
        // 如果是第一个雇员，head就指向第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员，则使用一个辅助指针，帮助定位到最后
        Emp curEmp = head;
        while (true) {
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
        curEmp.next = emp;
    }

    /**
     * 遍历链表的雇员信息
     */
    public void list(int no) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("第" + (no+1) + "条链表为空");
            return;
        }
        System.out.println("第" + (no+1) + "条链表的信息为：");
        Emp curEmp = head;
        while (true) {
            System.out.println(curEmp);
            // 已经到最后一个节点了，则推出循环
            if (curEmp.next == null) {
                break;
            }
            curEmp = curEmp.next;
        }
    }

    /**
     * 根据id查找雇员
     * @param id
     * @return
     */
    public Emp findEmpById(Integer id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp curEmp = head;
        while (true) {
            if (curEmp.id.equals(id)) {
                break;
            }
            // 找不到，则继续后移
            curEmp = curEmp.next;
            if (curEmp == null) {
                // 退出
                break;
            }
        }
        return curEmp;
    }

    /**
     * 根据id删除雇员
     * @param id
     */
    public void deleteById(Integer id) {
        // 判断链表是否为空
        if (head == null) {
            System.out.println("找不到该雇员");
            return;
        }
        Emp curEmp = head;
        // 第一个节点就是要查找的雇员
        if (curEmp.id.equals(id)) {
            head = curEmp.next;
            System.out.printf("id为%d的雇员删除成功", id);
            System.out.println();
            return;
        }
        Emp nextEmp = curEmp.next;
        while (true) {
            if (nextEmp == null) {
                System.out.println("找不到该雇员");
            }
            if (nextEmp.id.equals(id)) {
                curEmp.next = nextEmp.next;
                System.out.printf("id为%d的雇员删除成功", id);
                System.out.println();
                return;
            }
            curEmp = nextEmp;
            nextEmp = nextEmp.next;
        }
    }
}

/**
 * 雇员
 */
class Emp {
    public Integer id;
    public String name;
    /** next默认为null */
    public Emp next;

    public Emp(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
