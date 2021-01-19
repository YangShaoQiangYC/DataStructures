package com.dayi.search;

/**
 * 顺序查找
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-19 11:06
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr = {1, 3, 11, 2, -3, 100};
        int index = seqSearch(arr, -11);
        if (-1 == index) {
            System.out.println("没有找到");
        } else {
            System.out.println("找到了，下标为：" + index);
        }
    }

    /**
     * 顺序查找(找到一个满足条件的就返回)
     * 说明：顺序查找是逐一比对，发现有相同值就返回下标
     * @param arr 数列
     * @param value 值
     * @return
     */
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
