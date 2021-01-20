package com.dayi.search;

import java.util.Arrays;

/**
 * 斐波那契查找算法（黄金分割法）
 * 1.斐波那契数列符合黄金分割法，因为：斐波那契数列的当前元素的值，等于之前两个元素的值之和，
 *  而两个相邻元素之间的比例，随着数列的递增，比例无限接近于0.618黄金比例
 * 2.黄金分割点：是指把一条线段分割成两个部分，使其中一部分与全长之比等于另一部分与这部分之比。
 *  取其前三位数字的近似值是0.618。由于按此比例设计的造型十分美丽，因此称为黄金分割，也称为中外比。
 *  这是一个神奇的数字，会带来意想不到的效果
 * 3.使用斐波那契查找算法的前提是数组是有序的
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-19 17:35
 */
public class FibonacciSearch {

    private static final int MAXSIZE = 20;
    public static void main(String[] args) {
        int[] arr = {1, 3, 46, 89, 102};
        int resIndex = fibSearch(arr, 89);
        System.out.println("resIndex:" + resIndex);
    }

    /**
     * 获取一个斐波那契数组，【被查找数组】将借助斐波那契数组，依据数组的长度，求出黄金分割点
     * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765
     * 说明：通过原数组的长度，匹配到斐波那契数组的某个数值，从而得出元数组的“黄金分割点”在原数组的哪个位置
     *
     *                       （黄金分割点）
     *                             ↓
     *     +      f(k-1)           +   f(k-2) +
     *     ++++++++++++++++++++++++++++++++++++
     *                       f(k)
     *
     *     f(k) = f(k-1)【黄金分割点】 + f(k-2)，比如：21 = 13 + 8
     *
     * @return
     */
    public static int[] fib() {
        int[] f = new int[MAXSIZE];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < MAXSIZE ; i++) {
            f[i] = f[i-1] + f[i-2];
        }
        return f;
    }

    /**
     * 斐波那契算法
     * @param arr 元数组
     * @param findVal 查找的值
     * @return 找到则返回该则的下标，否则返回-1
     */
    public static int fibSearch(int[] arr, int findVal) {
        int low = 0;
        int high = arr.length - 1;
        // 黄金分割点
        int mid = 0;
        // 匹配斐波那契数组下标（目的是截止斐波那契数列找到黄金分割点）
        int k = 0;
        int[] fib = fib();
        while (high > fib[k]) {
            k++;
        }
        // 创建一个临时数组，将元数组的元素个数，扩充到满足斐波那契元素的个数
        // 比如 {1, 3, 46, 89, 102} -> {1, 3, 46, 89, 102, 0, 0}
        int[] temp = Arrays.copyOf(arr, fib[k]);
        for (int i = high + 1; i < fib[k]; i++) {
            // 新填充的数值，从默认值0更改为原数组最后一个元素
            // 比如 {1, 3, 46, 89, 102, 0, 0} -> {1, 3, 46, 89, 102, 102, 102}
            temp[i] = arr[high];
        }
        // 开始排序
        while (low <= high) {
            // 黄金分割点的位置，-1是因为数组从0开始
            mid = low + fib[k-1] - 1;
            if (findVal < temp[mid]) {
                // 查找值比黄金分割点的值还小，则以黄金分割点为基础，求出黄金分割点以前元素长度的黄金分割点
                // low + 新的黄金分割点的值，就是下次比较的位置
                high = mid - 1;
                // 求出fib（k-1）的黄金分割点
                k = k - 1;
            } else if (findVal > temp[mid]) {
                // 查找值比黄金分割点的值大，求出黄金分割点后，剩余的元素长度的黄金分割点
                // low + 新的黄金分割点的值，就是下次比较的位置
                low = mid + 1;
                // 求出剩余元素fib(k)-fib(k-1)=fib(k-2)的黄金分割点
                k = k - 2;
            } else if (findVal == temp[mid]) {
                // 找到查找值，返回下标。
                // 如果黄金分割点在high的范围内，则返回黄金分割点；否则返回high即可
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }

}
