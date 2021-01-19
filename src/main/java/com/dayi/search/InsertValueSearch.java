package com.dayi.search;

import java.util.Arrays;

/**
 * 插值查找算法
 * 说明：
 *  1.插值查找算法类型与二分查找，不同的是插值查找每次从自适应的mid出开始查找
 *  2.将二分查找中求mid索引的公式替换为：int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low])
 *  low是指left左索引，high是指right右索引，key指查找的关键字
 * 注意：
 *  1.使用二分查找的前提是该数组是有序的
 *  2.对于数据量较大，关键字（值）分布比较均匀的查找表来说，采用插值查找速度较快
 *  3.对于关键字（值）分布不均匀（值之间的跳跃性比较大）的情况下，【插值查找】不一定比【二分查找】 要好
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-19 15:59
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int resIndex = insertValueSearch(arr, 0, arr.length - 1, 100);
        // int resIndex = binarySearch(arr, 0, arr.length - 1, 100);
        System.out.println("resIndex:" + resIndex);
    }

    /**
     *  插值查找算法
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找算法");
        // 注意findVal < arr[left] 和 findVal > arr[right]必须需要，否则我们下面得到的mid可能越界
        // 因为findVal是我们传进来的数值，可能会传进来一个无比巨大的数值，就会导致求出的mid的值也很大，这样arr[mid]就会越界
        if (left > right || findVal < arr[0] || findVal > arr[arr.length-1]) {
            return -1;
        }
        // 求出mid，下面的公式中findVal的值也参与计算，是一种自适应的方式求出mid的值，可以更快的查找到我们想要的数据
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int minVal = arr[mid];
        if (findVal > minVal) {
            // 比中间值大，则向右递归
            return insertValueSearch(arr, mid + 1, right, findVal);
        } else if (findVal < minVal) {
            // 比中间值小，则向左递归
            return insertValueSearch(arr, left, mid - 1, findVal);
        } else {
            // 刚好minVal等于finVal，则直接返回下标
            return mid;
        }
    }

    /**
     * 二分查找算法
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 查找的值
     * @return 如果找到就返回下标，没有找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        System.out.println("二分查找算法");

        // 如果left > right，则退出递归
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int minVal = arr[mid];
        if (findVal < minVal) {
            // 比中间值小，左递归
            return binarySearch(arr, left, mid - 1, findVal);
        } else if (findVal > minVal) {
            // 比中间值大，右递归
            return binarySearch(arr, mid + 1, right, findVal);
        } else {
            // 刚好minVal等于finVal，则直接返回下标
            return mid;
        }
    }
}
