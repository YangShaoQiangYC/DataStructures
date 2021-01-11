package com.dayi.recursion;

/**
 * 迷宫 - 小球找路
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-11 14:54
 */
public class MiGong {
    public static void main(String[] args) {
        // 使用二维数组模拟迷宫
        int[][] map = new int[8][7];
        // 使用1表示墙，迷宫的上下左右都是墙，即为边界
        for (int i = 0; i < 7; i++ ) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int j = 0; j < 8; j++ ) {
            map[j][0] = 1;
            map[j][6] = 1;
        }
        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;
        map[4][2] = 1;
        map[5][2] = 1;
        map[5][3] = 1;
        map[5][4] = 1;
        map[4][4] = 1;
        // 输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        // 使用递归回溯给小球找路
        setWay(map, 1, 1);
        // 输出地图，小球走过并标识过的地图
        System.out.println("小球走过，并标识过的地图情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来给小球找路
     * 说明：
     * 1.map表示地图
     * 2.i和j表示从地图的哪个位置开始出发(1,1)；
     * 3.如果小球能到map[6][5]位置，则说明通路找到
     * 约定：
     * 1.当map[i][j]为0表示该点没有走过；为1表示墙，不能走；为2表示通路可以走；为3表示该点已经走过，但是走不通
     * 2.在走迷宫时，需要确定一个策略（方法），下->右->上->左，如果该点走不通，在回溯
     * @param map 地图
     * @param i 行
     * @param j 列
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        // 通路
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                // 如果当前点没有走过，则标记一下【按照策略 下->右->上->左走】
                map[i][j] = 2;
                if (setWay(map, i+1, j)) {
                    // 往下试探
                    return true;
                } else if (setWay(map, i, j+1)) {
                    // 往右试探
                    return true;
                } else if (setWay(map, i-1, j)) {
                    // 往上试探
                    return true;
                } else if (setWay(map, i, j-1)) {
                    // 往左试探
                    return true;
                } else {
                    // 说明该点走不通,是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j]!=0，说明是1,2,3，即不是墙，就是已走过，或者是走不通的
                return false;
            }
        }
    }

}
