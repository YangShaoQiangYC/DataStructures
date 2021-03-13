package com.dayi.sort;

import java.util.Arrays;

/**
 * 2.选择排序
 *  排序思想：有n个数，第一次从arr[0]-arr[n-1]中选取最小值，与arr[0]交换；
 * 第二次从arr[1]-arr[n-1]中选取最小值，与arr[1]交换
 * 第三次从arr[2]-arr[n-1]中选取最小值，与arr[2]交换
 * ...
 * 第i次从arr[i-1]-arr[n-1]中选取最小值，与arr[i-1]交换
 * ...
 * 第n-1次从arr[n-2]-arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次交换，得到一个按排序码从小到大排列的有序序列
 *
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-13 13:41
 */
public class SelectSort {
    public static void main(String[] args) {
        // int[] arr = {3, 9, -1, 10, -2};

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            // 生成一个[0, 8000000)的数
            arr[i] = (int) (Math.random() * 80000000);
        }

        long startTime = System.currentTimeMillis();
        // 冒泡排序
        selectSort(arr);
        long endTime = System.currentTimeMillis();

        // 输出结果
        System.out.println("选择排序执行消耗时间：" + (endTime - startTime)/1000 + "秒");
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr) {
        int temp;
        int index;
        // 当已确认前n-1个最小值之后，无需再确认比较最后一个数值，即前n-1个最小，则n就是最大的了
        // 故比较前n-1个即可
        for (int i = 0; i < arr.length - 1; i++) {
            // 假定当前为最小值
            temp = arr[i];
            index = i;
            // 从i + 1开始找出比arr[i]小的最小值，与arr[i]进行交换
            for (int j = i + 1; j < arr.length ; j++) {
                if (temp > arr[j]) {
                    index = j;
                    temp = arr[j];
                }
            }
            // 有找到最小值，再进行交换
            if (index != i) {
                arr[index] = arr[i];
                arr[i] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
