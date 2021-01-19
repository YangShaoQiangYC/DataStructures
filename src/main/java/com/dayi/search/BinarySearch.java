package com.dayi.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找（也叫做 折半查找）
 * 注意：使用二分查找的前提是该数组是有序的
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-19 11:26
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length-1, 89);
        System.out.println("resIndex:" + resIndex);

        int[] arr2 = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> resIndex2 = binarySearch2(arr2, 0, arr.length-1, 1000);
        System.out.println("resIndex2:" + resIndex2);
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

    /**
     * 二分查找算法
     * 思路分析：
     *  1.在找到mid索引值的时候，不要马上返回
     *  2.向mid索引值的左边扫描，将所有满足条件的元素的下标，加入到集合中
     *  3.向mid索引值的右边扫描，将所有满足条件的元素的下标，加入到集合中
     *  4.将集合返回
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 查找的值
     * @return 将所有满足条件的值的下标返回
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        // 如果left > right，则退出递归
        if (left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int minVal = arr[mid];
        if (minVal > findVal) {
            // 比中间值小，左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        } else if (minVal < findVal) {
            // 比中间值大，右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        } else {
            /**思路分析：
             *  1.在找到mid索引值的时候，不要马上返回
             *  2.向mid索引值的左边扫描，将所有满足条件的元素的下标，加入到集合中
             *  3.向mid索引值的右边扫描，将所有满足条件的元素的下标，加入到集合中
             *  4.将集合返回
             */
            List<Integer> resList = new ArrayList<>();
            // 向mid索引值的左边扫描，将所有满足条件的元素的下标，加入到集合中
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                resList.add(temp);
                temp--;
            }
            // 将第一次找到的mid也加入到集合中
            resList.add(mid);
            // 向mid索引值的右边扫描，将所有满足条件的元素的下标，加入到集合中
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                resList.add(temp);
                temp++;
            }
            return resList;
        }
    }
}
