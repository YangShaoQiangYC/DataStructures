package com.dayi.sort;

import java.util.Arrays;

/**
 * 5.快速排序（是对冒泡排序的一种改进，基本思想还是以冒泡的方式来处理的）
 *  排序思想：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都小，
 *  然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-14 14:33
 */
public class QuickSort {
    public static void main(String[] args) {
        /*int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));*/

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
        int pivot = arr[(left + right) / 2];
        while (l < r) {
            // 在pivot的左边一直找，找到大于等于pivot的值才退出（如果找不到，l指针最终会停在中轴值上面）
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot的右边一直找，找到小于等于pivot的值才退出（如果找不到，r指针最终会停在中轴值上面）
            while (arr[r] > pivot) {
                r--;
            }
            // 左边找不到比中间值大，右边也找不到比中间值小，则退出循环
            if (l >= r) {
                break;
            }
            // 交换值
            int temp = arr[r];
            arr[r] = arr[l];
            arr[l] = temp;
            // 左边找不到比中间值大的，arr[l]等于中轴值，交换后，arr[r]等于中轴值，
            // 故交换后，arr[l]的小于等于中轴值，而arr[r]等于中轴值，l指针需要右移
            if (arr[r] == pivot) {
                l++;
            }
            // 右边找不到比中间值小的，arr[r]等于中轴值，交换后，arr[l]等于中轴值，
            // 故交换后，arr[r]大于等于中轴值，而arr[l]等于中轴值，r指针需要左移
            if (arr[l] == pivot) {
                r--;
            }
        }
        // 继续递归排序
        if (l == r) {
            l++;
            r--;
        }
        // 左递归
        if (r > left) {
            quickSort(arr, left, r);
        }
        // 右递归
        if (l < right) {
            quickSort(arr, l, right);
        }
    }
}
