package com.dayi.huffmancode;

import java.util.*;

/**
 * 哈夫曼编码
 * 1.补充知识点：
 *  1) 正数：
 *    原码，反码和补码都一样
 *   举例：88
 *     原码：01011000
 *     反码：01011000
 *     补码：01011000
 *  2) 负数：
 *    反码：符号位不变，其他位数取反
 *    补码：反码 + 1
 *  举例：-88‘
 *   原码：11011000
 *   反码：10100111
 *   补码：10101000
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-02-01 17:06
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("原始数据对应的字节数组：" + Arrays.toString(contentBytes));
        byte[] zipBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的字节数组：" + Arrays.toString(zipBytes));
        byte[] sourceBytes = unZip(zipBytes, huffmanCodes);
        System.out.println("解压后的字符串：" + new String(sourceBytes));

        // 分步过程
        /*// 组装节点
        List<Node> nodes = getNodes(contentBytes);
        // 创建哈夫曼树，并获取哈夫曼树的根节点
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        System.out.println("哈夫曼树前序遍历：");
        huffmanTreeRoot.preOrder();

        System.out.println("哈夫曼树对应的哈夫曼编码表：");
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println(huffmanCodes);

        System.out.println("压缩后的字节数组为：");
        byte[] zipBytes = zip(contentBytes, huffmanCodes);
        System.out.println(Arrays.toString(zipBytes));*/
    }

    /**
     * 封装哈夫曼编码压缩方法
     * @param bytes 原始字符串数据对应的byte数组
     * @return 哈夫曼编码后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] bytes) {
        // 1.组装节点
        List<Node> nodes = getNodes(bytes);
        // 2.创建哈夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        // 3.根据哈夫曼树创建对应的哈夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        // 4.根据哈夫曼编码表，对原始字符串数据对应的byte数组进行压缩，得到压缩后的哈夫曼编码字节数组
        byte[] zipBytes = zip(bytes, huffmanCodes);
        return zipBytes;
    }

    /**
     * 将字节数组转换为节点集合
     *  统计次数，组装节点
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
                // 是叶子节点，则结束递归，将节点的哈夫曼编码结果存入哈夫曼编码表
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    /**
     * 将原字符数据的byte数组，进行哈夫曼编码后，以8位字符为一个byte字节，重新转换成byte数组
     * @param bytes 原始字符串数据对应的byte数组
     * @param huffmanCodes 哈夫曼编码表
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        // 将哈夫曼编码后的字符串:101010001011111111001000...，转换为byte数组
        System.out.println("哈夫曼编码为：" + stringBuilder.toString());
        int len;
        if ((stringBuilder.length() % 8) == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 转换后的byte数组
        byte[] huffmanBytes = new byte[len];
        // byte数组中的第几个byte
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i+8 > stringBuilder.length()) {
                // 剩余元素不足8个，则截取剩余的所有字符
                strByte = stringBuilder.substring(i);
            } else {
                // 每次截取8个，组装成一个byte
                strByte = stringBuilder.substring(i, i+8);
            }
            // 二进制转byte，10101000 -> -88
            // 注意，10101000是补码，得将二进制补码转原码，再得出十进制
            // 补码：10101000，求出反码：11010111，再求出原码：11011000，对原码转十进制，求出-88
            huffmanBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanBytes;
    }

    /**
     * 将一个byte转为二进制字符串（十进制转补码二进制字符串）
     * @param b 传入的byte
     * @param flag 标识是否需要高位补位，true-需要补高位，false-不需要补高位
     *             字节数组的最后一位，无需补高位（因为最后一个字节，原先就有可能就不是8位二进制字符串）
     * @return byte对应的二进制字符串（补码）
     */
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        // System.out.println("temp1:" + temp);
        // （1）如果是正数，需要补高位（正数的原码，反码和补码是一样的），
        //      因为Integer.toBinaryString(1)返回的是1，
        //      Integer.toBinaryString(2)返回的是01，
        //      Integer.toBinaryString(3)返回的是11，
        //      Integer.toBinaryString(4)返回的是100，
        //      丢失高位，需要补高位（补充到8位二进制）
        // （2）如果是负数，也补一下高位（与正数统一），虽然与256或后效果一样，没有影响
        if (flag) {
            // 按位或，256的二进制为1 0000 0000
            // 举例：1的二进制为 0000 0001，与256或，则 1 0000 0000 | 0000 0001，得1 0000 0001，取后8位依旧是0000 0001
            // 举例：-1的二进制为1000 0001，与256或，则 1000 0001 | 1 0000 0000，得1 1000 0000，取后8位依旧是 1000 0001
            temp |= 256;
            // System.out.println("temp2:" + temp);
        }
        // 注意，Integer.toBinaryString()返回的是补码
        String str = Integer.toBinaryString(temp);
        if (flag) {
            // 因为int类型是64位，正数的高位会自动补0，负数会自动补1，
            // 我们截取最后的8位，即为我们需要的结果即可(前面补高位，补完有9位，截取后8位；负数有64位，也截取后8位)
            return str.substring(str.length() - 8);
        }
        return str;
    }

    /**
     * 将哈夫曼后的字节数组，转换回原字符数据的byte数组
     * @param huffmanBytes 哈夫曼编码后的字节数组
     * @param huffmanCodes 哈夫曼编码表
     * @return 原来未编码前的字符串对应的字节数组
     */
    private static byte[] unZip(byte[] huffmanBytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        // 字节数组的最后一位无需补高位
        for (int i = 0; i < huffmanBytes.length; i++) {
            if (i == huffmanBytes.length - 1) {
                // 最后一个字节无需补高位
                stringBuilder.append(byteToBitString(false, huffmanBytes[i]));
            } else {
                stringBuilder.append(byteToBitString(true, huffmanBytes[i]));
            }
        }
        // System.out.println("恢复原先的哈夫曼编码：" + stringBuilder.toString());
        // 把哈夫曼编码表进行调换，反向哈夫曼编码表，01=32, 100=97
        Map<String, Byte> map = new HashMap<>(huffmanCodes.size());
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        // 创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            Byte b = null;
            boolean flag = true;
            while (flag) {
                // 递增取出二进制字符，到map中比较获取
                String str = stringBuilder.substring(i, i + count);
                b = map.get(str);
                if (null == b) {
                    // 获取不到，则count++,进行下一次比较
                    count++;
                } else {
                    // 获取到了，将该值存入list结果集合中
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        // 把list中的数据存放到byte[]数组中并放回
        byte[] b = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            b[i] = list.get(i);
        }
        return b;
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