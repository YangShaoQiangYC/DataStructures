package com.dayi.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *  排序思想：通过对待排序序列从前向后（从下标较小的元素开始），依次比较相邻元素的值，若发现逆序则交换，
 * 使较大的元素逐渐从前移向后部，就像水底的气泡一样逐渐向上冒
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-13 10:39
 */
public class BubbleSort {
    public static void main(String[] args) {
        // int[] arr = {3, 9, -1, 10, -2};

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            // 生成一个[0, 8000000)的数
            arr[i] = (int) (Math.random() * 80000000);
        }

        // 排序前
        System.out.println("排序前：" + Arrays.toString(arr));

        long startTime = System.currentTimeMillis();
        // 冒泡排序
        bubbleSort(arr);
        long endTime = System.currentTimeMillis();

        // 输出结果
        System.out.println("冒泡排序执行消耗时间：" + (endTime - startTime)/1000 + "秒");
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        // 统计交换次数
        int count = 0;
        int temp = 0;
        for (int i = 0; i < arr.length - 1 ; i++) {
            // 重置统计次数
            count = 0;
            for (int j = 0; j < arr.length -1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    count ++;
                }
            }
            // 如果在某次排序中，没有进行过一次交换，则说明已经排好序，此时退出循环结束排序
            if (count == 0) {
                break;
            }
        }
    }
}
