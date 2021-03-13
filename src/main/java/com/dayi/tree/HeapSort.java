package com.dayi.tree;

import java.util.Arrays;

/**
 * 8.堆排序
 * 1.基本思想
 *  1）将待排序序列构造成一个大顶堆
 *  2）此时，整个序列的最大值就是堆顶的根节点
 *  3）将其与末尾元素进行交换，此时末尾就为最大值
 *  4）然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
 * 2.注意：对顺序存储二叉树进行排序
 *  大顶堆：从小到大的升序排序方式，使用大顶推
 *  小顶堆：从大到小的降序排序方式，使用小顶堆
 * 3.说明
 *  1）堆排序是利用堆这种数据结构而设计的一种的排序算法，堆排序是一种选择排序，他的最好，最坏，平均时间复杂度均为O(nlogn)线性对数阶，是一种不稳定的排序算法
 *  2）堆是具有以下性质的完全二叉树。
 *   a.每个节点的值都大于或等于其左右孩子节点的值，称为大顶堆。
 *   b.每个节点的值都小于或等于其左右孩子节点的值，称为小顶堆。
 *   注意：没有要求节点的“左孩子”的值和“右孩子”的值的大小关系
 * 4.完全二叉树最后一个非叶子节点为：【数组的长度】/ 2 - 1;
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-28 11:21
 */
public class HeapSort {
    public static void main(String[] args) {
        // 要求将数组进行升序排序
        int[] arr = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    /**
     * 大顶推排序
     * @param arr 待排序数组
     */
    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        // 将无序序列构建成一个大顶堆，堆顶元素为最大节点值
        for (int i = arr.length/2-1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }
        // 每次将堆顶元素与数组的最后一个元素进行替换，再对剩余元素进行大顶堆调整
        // 每调整一次，替换一下，直到整个数组调整替换完毕
        int temp = 0;
        for (int j = arr.length - 1; j >= 0; j--) {
            // 将堆顶元素的值与当前数组比较的最后一个元素对调
            temp = arr[0];
            arr[0] = arr[j];
            arr[j] = temp;
            // 数组长度-1，对剩余元素再次进行大顶推调整，以便进行下一下替换
            // 注意，每次替换后，数组的最后一个元素就是最大值了，无须再参与下一次大顶推调整和替换操作了，故循环进行j--
            adjustHeap(arr, 0, j);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 将数组调整成大顶推
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐减少的
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 保存待调整的节点的堆顶节点
        int temp = arr[i];
        // 从当前节点的左节点开始比较，k指向左节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 左右子节点进行比较
            if (k+1 < length && arr[k] < arr[k+1]) {
                // 左节点比右节点小，则k指向右节点
                k = k + 1;
            }
            if (temp < arr[k]) {
                // 堆顶节点比子节点小，则进行替换
                arr[i] = arr[k];
                // 被替换的子节点需要重新进行大顶推调整，其子节点的值继续与最原始的堆顶节点的值进行比较
                i = k;
            } else {
                // 对顶节点已经是最大的值了，无须再调整
                break;
            }
        }
        // 如果有进行调整，则最后被调整节点的值，就是最开始堆顶节点被替换下来的值
        // 如果无须进行调整，则循环里面的操作没有执行，则开始的int temp = arr[i];和结束的arr[i] = temp; 对原数组没有影响
        arr[i] = temp;
    }

}
