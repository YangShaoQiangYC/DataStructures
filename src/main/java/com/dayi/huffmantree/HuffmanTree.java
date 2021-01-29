package com.dayi.huffmantree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 哈夫曼树
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-29 13:58
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        System.out.println("中序遍历哈夫曼树");
        preOrder(root);
    }

    /**
     * 前序遍历
     * @param root
     */
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空，不能遍历");
        }
    }

    /**
     * 创建哈夫曼树
     * @param arr 需要创建成哈夫曼树的数组
     * @return 创建好后的哈夫曼树的根节点
     */
    public static Node createHuffmanTree(int[] arr) {
        // 遍历数组，将数组中的每一个元素构造成一个Node，放入到集合中
        ArrayList<Node> nodes = new ArrayList<>(arr.length);
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        // 排序，从小到大
        Collections.sort(nodes);
        while (nodes.size() >= 2) {
            // 取出根节点最小的两颗子树，构建成一颗新的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 将处理过的节点从集合中删除，将新节点加入集合中
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
            // 重新排下序
            Collections.sort(nodes);
            System.out.println(nodes);
        }
        // 返回哈夫曼树的根节点
        return nodes.get(0);
    }
}

/**
 * 节点
 */
class Node implements Comparable<Node> {
    /** 节点的权值 */
    int value;
    /** 左子树 */
    Node left;
    /** 右子树 */
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

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}