package com.dayi.sort;

import java.util.Arrays;

/**
 * 希尔排序（交换法，效率较慢）
 *  1.说明：希尔排序，也是一种插入排序，是对简单插入排序进行优化的一个更高效的版本，也称为【缩小增量排序】
 *  2.排序思想：希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序;随着增量逐渐减少，每组包含
 * 的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止。
 *  3.举例解释排序思想：假如有10个数需要排序
 *      第一次，先按（10/2=5）分为5组数据，分别对每组数据进行插入排序
 *      第二次，再按（5/2=2.5，取整为2）分为2组数据，再分别对每组数据进行插入排序
 *      第三次，再按（2/2=1,此时增量为1，则不会再进行分组了）分为1个组，对这个组进行插入排序，即完成对本次数据的排序工作
 *  4.希尔排序通过分组每次对数据元素进行微调，大大降低了当目标元素数据比较靠后时，所进行位置调整的次数，从而达到优化的目的
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-14 9:25
 */
public class ShellSort {
    public static void main(String[] args) {
        /*int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);*/

        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            // 生成一个[0, 8000000)的数
            arr[i] = (int) (Math.random() * 80000000);
        }

        long startTime = System.currentTimeMillis();
        // 冒泡排序
        shellSort(arr);
        long endTime = System.currentTimeMillis();

        // 输出结果
        System.out.println("希尔排序执行消耗时间：" + (endTime - startTime)/1000 + "秒");
        System.out.println("排序后：" + Arrays.toString(arr));
    }

    /**
     * 希尔排序
     * 每一次分组，依次缩短2倍的步长
     * 步长越小，则相当于有越多的数据元素为一组，同组间的元素从头到尾进行交换排序
     * 直到步长为1
     * @param arr
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        // 逐次分组
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 查找该元素在本组中的下一个元素，进行比较，符合条件则交换
                // (注意，会回溯比较之前已经比较的元素，依据步长回溯)
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    if (arr[j] > arr[j+gap]) {
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }
}
