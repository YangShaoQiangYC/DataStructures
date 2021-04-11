package com.dayi.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 无向图
 * 1.使用邻接矩阵的方式表示
 * 2.0表示没有相连，1表示相连
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-02-23 10:37
 */
public class Graph {
    /** 存储顶点的集合 */
    private ArrayList<String> vertexList;
    /** 存储图对应的邻接矩阵 */
    private int[][] edges;
    /** 表示边的数目 */
    private int numOfEdges;
    /** 记录某个节点是否被访问 */
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 顶点个数
        int n = 5;
        // 顶点的值
        String[] vertexs = {"A", "B", "C", "D", "E"};
        // 创建图
        Graph graph = new Graph(n);
        // 添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边 A-B A-C B-C B-D B-E
        graph.insertEdges(0, 1, 1);
        graph.insertEdges(0, 2, 1);
        graph.insertEdges(1, 2, 1);
        graph.insertEdges(1, 3, 1);
        graph.insertEdges(1, 4, 1);
        // 显示邻接矩阵
        graph.showGraph();
        // 深度优先遍历
        // System.out.println("深度优先遍历");
        // graph.dfs();
        // 广度优先遍历
        System.out.println("广度优先遍历");
        graph.bfs();
    }

    public Graph(int n) {
        // 初始化邻接矩阵和顶点集合
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    /**
     * 获取第一个邻接节点的下标
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 获取下一个邻接节点
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历(i第一次是0)
     * @param isVisited
     */
    private void dfs(boolean[] isVisited, int i) {
        // 输出我们访问到的节点
        System.out.print(getValueByIndex(i) + "->");
        // 将访问过的节点，设置为已经访问过
        isVisited[i] = true;
        // 查找i的第一个邻接节点
        int firstNeighbor = getFirstNeighbor(i);
        // 找不到邻接节点则推出循环
        while (-1 != firstNeighbor) {
            if (!isVisited[firstNeighbor]) {
                // 找到邻接节点且没有被访问过，则在该邻接节点的基础上继续深度遍历进去
                dfs(isVisited, firstNeighbor);
            }
            // 如果该找到的邻接节点已经被访问过，则找下一个邻接节点
            firstNeighbor = getNextNeighbor(i, firstNeighbor);
        }
    }

    /**
     * 遍历所有的节点
     */
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 广度优先搜索遍历
     * @param isVisited
     * @param i
     */
    public void bfs(boolean[] isVisited, int i) {
        // 队列的头节点对应的下标
        int u;
        // 邻接节点
        int w;
        // 队列，记录访问顺序
        LinkedList queue = new LinkedList();
        // 访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "->");
        // 标记节点为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        // 当第一个节点所有的邻接节点都访问完了，则按队列中的访问顺序，继续广度遍历队列中元素的邻接节点
        while (!queue.isEmpty()) {
            // 取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接节点
            w = getFirstNeighbor(u);
            while (w != -1) {
                // 找到邻接节点，如果没有被访问过，需要访问输出，标记为访问，并入队
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    // 标记为访问
                    isVisited[w] = true;
                    // 将节点加入队列
                    queue.addLast(w);
                }
                // 查找下一个邻接节点
                w = getNextNeighbor(u, w);
            }
        }
    }

    /**
     * 遍历所有节点
     */
    public void bfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 获取节点的个数
     * @return
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 获取边的个数
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回节点i（下标）对应的数据 0->'A' 1->'B' 2->'C'
     * @return
     */
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    /**
     * 返回两个顶点之间权值
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图
     */
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    /** 插入顶点 */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 插入边
     * @param v1 第一个顶点的下标
     * @param v2 第二个顶点的下标
     * @param weight 表示两个顶点之间的连接关系，0表示没有连接，1表示连接
     */
    public void insertEdges(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

}
