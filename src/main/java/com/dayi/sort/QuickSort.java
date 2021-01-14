package com.dayi.sort;

import java.util.Arrays;

/**
 * 快速排序（是对冒泡排序的一种改进，基本思想还是以冒泡的方式来处理的）
 * 排序思想：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都小，
 *  然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-14 14:33
 */
public class QuickSort {
    public static void main(String[] args) {
        // int[] arr = {-9, 78, 0, 23, -567, 70};

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            // 生成一个[0, 8000000)的数
            arr[i] = (int) (Math.random() * 80000000);
        }

        long startTime = System.currentTimeMillis();
        // 快速排序
        quickSort(arr, 0, arr.length-1);
        long endTime = System.currentTimeMillis();

        // 输出结果
        System.out.println("快速排序执行消耗时间：" + (endTime - startTime) + "毫秒");
        // System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     * @param arr
     * @param left 左下标
     * @param right 右下表
     */
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        // 中轴值
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot的值才推出
            while (arr[l] < pivot) {
                l += 1;
            }
            // 在pivot的右边一直找，找到大于等于pivot的值才推出
            while (arr[r] > pivot) {
                r -= 1;
            }
            // 左边找不到比中轴值大，右边也找不到比中轴值小，则推出循环
            if (l >= r) {
                break;
            }
            // 交换
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换完后，发现arr[l] == pivot，r--
            if (arr[l] == pivot) {
                r -= 1;
            }
            // 如果交换后，发现arr[r] == pivot，l--
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        if (l == r) {
            l += 1;
            r -= 1;
        }
        // 左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 右递归
        if (l > right) {
            quickSort(arr, l, right);
        }
    }
}
