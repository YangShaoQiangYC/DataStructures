package com.dayi.recursion;

/**
 * 八皇后问题
 * 规则是：
 *  在一个8*8的棋盘上面，摆放8个皇后，在同一行，同一列和统一斜线上，不能同时存在两个皇后
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-12 9:08
 */
public class Queue8 {

    /** 8个皇后 */
    private final int MAX = 8;
    /** 保存皇后摆放的结果，数组的下标表示皇后摆放的【行】，值表示皇后摆放的【列】 */
    private int[] array = new int[MAX];
    /** 统计 */
    private static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.printf("一共有%d中解法", count);
    }

    /**
     * 放置皇后
     * @param n 放置第几个皇后
     */
    private void check(int n) {
        if (n == MAX) {
            print();
            count++;
            return;
        }
        // 每次从每一行的第一列开始遍历起，第几个皇后固定放在第几行，比如第一个皇后放在第0行
        // n为皇后的行数，i为皇后的所在列
        for (int i = 0; i < MAX; i++) {
            array[n] = i;
            if (judge(n)) {
                check(n+1);
            }
        }
    }

    /**
     * 检查该皇后和前面已经摆放的皇后是否冲突
     * @param n 标识第几个换后
     * @return
     */
    private boolean judge(int n) {
        /**
         * 说明：
         * 因为每次数组的下标会递增，因此每次皇后都摆放在不同行上面，所以无需判断
         * array[i] == array[n]用于判断是否在统一列上面
         * Math.abs(n - i) == Math.abs(array[n] - array[i])用于判断两个皇后是否在同一斜线上面，依据正方形对角长宽相等的原理
         */
        for (int i = 0; i < n; i++) {
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 写一个方法，将皇后的摆放位置输出
     */
    private void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
