package com.dayi.huffmancode;

import java.util.*;

/**
 * 哈夫曼编码
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-02-01 17:06
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        List<Node> nodes = getNodes(contentBytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        System.out.println("哈夫曼树前序遍历：");
        huffmanTreeRoot.preOrder();

        System.out.println("哈夫曼树对应的哈夫曼编码表：");
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println(huffmanCodes);
    }

    /**
     * 将字节数组转换为节点集合
     * @param bytes 字节数组
     * @return
     */
    private static List<Node> getNodes(byte[] bytes) {
        // 创建集合
        ArrayList<Node> nodes = new ArrayList<>();
        // 遍历bytes，统计每个byte出现的次数
        Map<Byte, Integer> counts = new HashMap<>();
        for (Byte b : bytes) {
            Integer count = counts.get(b);
            if (null == count) {
                // 第一次统计到该字符，次数为1
                counts.put(b, 1);
            } else {
                // 已经统计过字符，则在原先统计的次数上加1
                counts.put(b, count + 1);
            }
        }
        // 将每一个键值对转换成一个Node，并添加到集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 创建哈夫曼树
     * @param nodes
     * @return
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() >= 2) {
            // 排序，从小到大
            Collections.sort(nodes);
            // 取出最小两个节点
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 创建一个新的父节点，注意，该父节点只有权值，没有字符数据
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            // 将处理过的节点从集合中删除，将新节点加入集合中
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);
        }
        return nodes.get(0);
    }

    /**
     * 生成哈夫曼树对应的哈夫曼编码表，存放在Map<Byte, String>中，形式如：
     * {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     */
    private static Map<Byte, String> huffmanCodes = new HashMap<>();
    /**
     * 生成哈夫曼编码，需要拼装路径，定义一个StringBuilder存储某个叶子节点的路径
     */
    private static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 重载获取哈夫曼编码表
     * @param root 根节点
     * @return
     */
    private static Map<Byte, String> getCodes(Node root) {
        if (null == root) {
            return null;
        }
        // 生成哈夫曼编码
        getCodes(root, "", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入的node节点的所有叶子节点的哈夫曼编码得到，并放入huffmanCodes集合
     * @param node 传入节点
     * @param code 路径：左边节点是0，右边节点是1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        if (null != node) {
            stringBuilder2.append(code);
            // 判断当前节点是否为非叶子节点，非叶子节点，则继续递归处理拼装哈夫曼编码
            if (null == node.data) {
                // 左递归
                getCodes(node.left, "0", stringBuilder2);
                // 右递归
                getCodes(node.right, "1", stringBuilder2);
            } else {
                // 是叶子节点，则结束递归，将结果存入存入哈夫曼编码表
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

}

/**
 * 创建节点，带数据和权值
 */
class Node implements Comparable<Node>{
    /** 存放字符数据本身，比如'a' -> 97, ' ' -> 32 */
    Byte data;
    /** 字符数据出现的次数，权值 */
    int weight;
    /** 左子树 */
    Node left;
    /** 右子树 */
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
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