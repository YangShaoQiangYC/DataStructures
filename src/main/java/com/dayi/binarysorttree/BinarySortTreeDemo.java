package com.dayi.binarysorttree;

/**
 * 二叉排序树
 * 二叉排序树删除节点（需要区分三种情况）
 * 1.第一种情况：删除叶子节点
 *  思路：
 *  1）需要先找到要删除的节点targetNode
 *  2) 找到targetNode的父节点parent
 *  3) 确定targetNode是parent的左子节点，还是右子节点
 *  4) 根据前面的情况来对应的删除
 *  parent.left = null;
 *  parent.right = null;
 * 2.第二种情况：删除只有一颗子树的节点
 *  思路：
 *  1）需要先找到要删除的节点targetNode
 *  2) 找到targetNode的父节点parent
 *  3) 确定targetNode的子节点是左子节点还是右子节点
 *  4）确定targetNode是parent的左子节点还是右子节点
 *  5）如果targetNode有左子节点
 *  5.1）如果targetNode是parent的左子节点
 *    parent.left = targetNode.left
 *  5.2) 如果targetNode是parent的右子节点
 *    parent.right = targetNode.left
 *  6) 如果targetNode有右子节点
 *  6.1) 如果targetNode是parent的左子节点
 *    parent.left = targetNode.right
 *  6.2) 如果targetNode是parent的右子节点
 *    parent.right = targetNode.right
 * 3.第三种情况：删除有两颗子树的节点
 *  思路：
 *  1）需要先找到要删除的节点targetNode
 *  2) 找到targetNode的父节点parent
 *  3）从targetNode的右子树中找到最小的节点
 *  4) 用一个临时变量，将最小节点的值保存 （例如：temp = 12）
 *  5) 删除该最小节点
 *  6) 将临时变量的值，赋值给targetNode，targetNode.value = temp;
 *
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
        if (node.value < this.value) {
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
