package com.dayi.sort;

import java.util.Arrays;

/**
 * 插入排序
 *  1.排序思想：把n个待排序的元素看成为一个有序表和一个无序表，【开始时有序表只有一个元素，无序表有n-1个元素】，
 * 排序的过程中，每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的
 * 适当位置，使之成为新的有序表。
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-13 15:50
 */
public class InsertSort {
    public static void main(String[] args) {
        // int[] arr = {3, 9, -1, 10, -2};

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            // 生成一个[0, 8000000)的数
            arr[i] = (int) (Math.random() * 80000000);
        }

        long startTime = System.currentTimeMillis();
        // 冒泡排序
        insertSort(arr);
        long endTime = System.currentTimeMillis();

        // 输出结果
        System.out.println("插入排序执行消耗时间：" + (endTime - startTime)/1000 + "秒");
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        int insertVal;
        int insertIndex;
        // 从第2个开始遍历，即默认有序表最初始为第1个元素（数组从0开始，第一元素的下标为0）
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            // 当前无序表的前一个元素，即当前元素前有序表的最后一个元素的位置
            insertIndex = i - 1;
            // 前面没有元素了，或者当前值比前面有序表最后一个元素值还大，则不会进入循环（因为前面已按从小到大排好序）
            while (insertIndex >= 0 && arr[insertIndex] > insertVal) {
                // 前面的元素的值后移，为插入的位置腾出空间
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex -= 1;
            }
            // 找到插入位置了
            arr[insertIndex+1] = insertVal;
        }
    }
}


