package com.dayi.tree;

/**
 * 顺序存储二叉树
 * 需求：给你一个数组{1, 2, 3, 4, 5, 6, 7}，要求以二叉树前序、中序和后序的方式进行遍历。
 *  例如：按前序遍历的结果应为1,2,4,5,3,6,7
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-25 14:14
 */
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        /*// 前序遍历 1,2,4,5,3,6,7
        arrayBinaryTree.preOrder();*/
        /*// 中序遍历 4,2,5,1,6,3,7
        arrayBinaryTree.infixOrder(0);*/
        // 后序遍历 4,5,2,6,7,3,1
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree {
    /** 存储数据节点的数组 */
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 重载前序遍历
     */
    public void preOrder() {
        this.preOrder(0);
    }

    /**
     * 重载中序遍历
     */
    public void infixOrder() {
        this.infixOrder(0);
    }

    /**
     * 重载后序遍历
     */
    public void postOrder() {
        this.postOrder(0);
    }

    /**
     * 前序遍历
     * 说明：
     *  1）左子节点为2*n+1
     *  2）右子节点为2*n+2
     *  3）父节点为(n-1)/2
     * @param index
     */
    public void preOrder(Integer index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的方式进行前序遍历");
        }
        // 输出当前节点
        System.out.println(arr[index]);
        // 向左递归遍历
        if ((2 * index + 1) < arr.length) {
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((2 * index + 2) < arr.length) {
            preOrder(2 * index + 2);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(Integer index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的方式进行前序遍历");
        }
        // 向左递归遍历
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        // 输出当前节点
        System.out.println(arr[index]);
        // 向右递归遍历
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }

    /**
     * 后序遍历
     * @param index
     */
    public void postOrder(Integer index) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空，不能按照二叉树的方式进行前序遍历");
        }
        // 向左递归遍历
        if ((2 * index + 1) < arr.length) {
            postOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((2 * index + 2) < arr.length) {
            postOrder(2 * index + 2);
        }
        // 输出当前节点
        System.out.println(arr[index]);
    }

}
