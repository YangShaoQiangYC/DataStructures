package com.dayi.sort;

import java.util.Arrays;

/**
 * 6.归并排序
 *  排序思想：归并排序是利用归并的思想思想的排序方法，该方法采用经典的分治策略（分治法将问题【分】成一些小的问题，
 *  然后递归求解；而【治】的阶段则将分的阶段得到的各答案“修补”在一起，即分而治之）
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-15 11:36
 */
public class MergeSort {
    public static void main(String[] args) {
        /*int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        System.out.println("归并排序后：" + Arrays.toString(arr));*/

        int[] arr = new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            // 生成一个[0, 8000000)的数
            arr[i] = (int) (Math.random() * 80000000);
        }

        long startTime = System.currentTimeMillis();
        // 归并排序
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length-1, temp);
        long endTime = System.currentTimeMillis();

        // 输出结果
        System.out.println("归并排序执行消耗时间：" + (endTime - startTime) + "毫秒");
        // System.out.println(Arrays.toString(arr));

    }

    /**
     * 分 + 合方法
     * @param arr 原始数组
     * @param left 左边有序序列的初始索引
     * @param right 右边有序序列的终止索引
     * @param temp 做中转的数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid+1, right, temp);
            // 当分解到无法递归时，则合并（执行到这条语句，则说明无法再分解了，左递归和右递归均已返回）
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并方法
     * @param arr 原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的终止索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        // 初始化i，左边有序序列的初始索引
        int i = left;
        // 初始化j，右边有序序列的初始索引
        int j = mid + 1;
        // 初始化temp数组的当前索引
        int t = 0;

        // 1.先把左右两边（有序）的数据按照规则填充到temp数组，直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // 如果左边有序序列的当前元素，小于等于右边有序序列的当前元素，则将左边当前元素存到temp临时数组中
                // 同时指针后移
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                // 反之，左边有序序列的当前元素比右边有序序列的当前元素大，则将右边有序序列的当前元素存到temp临时数组中
                // 同时指针后移
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        // 2.把有剩余数据的一边的数据一次填充到temp
        while (i <= mid) {
            // 左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            // 右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }
        // 3.把temp拷贝到原arr中去
        t = 0;
        // System.out.println("left=" + left + ",right=" + right);
        while (left <= right) {
            arr[left] = temp[t];
            t++;
            left++;
        }
    }
}
