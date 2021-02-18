package com.dayi.binarysorttree;

/**
 * 二叉排序树
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-02-18 16:16
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        // 中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();
    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree {
    /** 根节点 */
    private Node root;

    /**
     * 添加节点
     * @param node
     */
    public void add(Node node) {
        if (null == root) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (null != root) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }

}

/**
 * 创建节点
 */
class Node {
    /** 节点的值 */
    int value;
    /** 左子节点 */
    Node left;
    /** 右子节点 */
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点（使用递归的方式添加节点，注意需要满足二叉排序树的要求）
     * @param node 被添加的节点
     */
    public void add(Node node) {
        if (null == node) {
            return;
        }
        // 判断传入的节点值，和当前节点值的关系
        if (this.value > node.value) {
            if (null == this.left) {
                // 如果当前节点的左子树为空，则直接加入
                this.left = node;
            } else {
                // 否则，则递归向左子树添加
                this.left.add(node);
            }
        } else {
            // 传入的节点不小于当前节点的值
            if (null == this.right) {
                // 如果当前节点的右子树为空，则直接加入
                this.right = node;
            } else {
                // 否则，则递归向右子树添加
                this.right.add(node);
            }
        }
    }

    /**
     * 前序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}
