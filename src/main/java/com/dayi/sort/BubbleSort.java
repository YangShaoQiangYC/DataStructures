package com.dayi.sort;

import java.util.Arrays;

/**
 * 冒泡排序
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

        System.out.println("冒泡排序执行消耗时间：" + (endTime - startTime)/1000 + "秒");

        // 输出结果
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
