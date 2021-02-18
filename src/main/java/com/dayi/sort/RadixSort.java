package com.dayi.sort;

import java.util.Arrays;

/**
 * 基数排序：
 *  1.说明：
 *   1）基数排序属于“分配式排序”，又成“桶子法”（顾名思义，它是通过键值的各个位的值，将要排序的元素分配至某些“桶”中，达到排序的作用）
 *   2）基数排序属于“稳定型”的排序，是效率高的稳定型排序法
 *   3）基数排序是桶排序的拓展
 *   4）基数排序是1887年赫尔曼·何乐礼发明的。它是这样实现的：将整数按位数切割成不同的数字，然后按每个位数分别比较
 *  2.排序思想：将所有待比较数值统一为同样数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 *  这样从最低位排序一直到最高位排序完成以后，数列就变成一个有序序列
 *  3.注意
 *  假如测试8千万条数据，则需要 80000000 * 11 * 4 / 1024 /1024 /1024 = 3.3G（11个int数组，每个int4个字节，除以1024得到K，再除以1024得到M,再除以1024得到G）
 *  测试8千万个数据的排序，大约需要暂用3.3G的内存
 * 4.说明2：
 *  1）基数排序是对传统桶排序的扩展，速度很快
 *  2）基数排序是经典的空间换时间的方式，占用内存很大，对于海量数据排序时，容易造成OutOfMemoryError
 *  3)基数排序时稳定的。【注：假定在待排序的记录序列中，存在多个具有相同的关键字的记录，若经过排序后，
 *  这些记录的相对次序保持不变，即在原序列中，r[i]=r[j]，若r[i]在r[j]之前，而在排序后的序列中，r[i]仍在
 *  r[j]之前，则称这种排序算法是稳定的，否则称为不稳定的】
 *  4）对于负数的支持，请参考https://code.i-harness.com/zh-CN/q/e98fa9
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-18 11:30
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    /**
     * 基数排序（不支持负数）
     * 说明：由下面radixSortTest推导出，
     * 1.经过几轮排序是由待排序数列中最大的位数决定
     * 2.每次排序，由数列中(当前数 / 10的【第几轮-1】次方)决定该数据将存放在那个桶中
     * @param arr
     */
    public static void radixSort(int[] arr) {
        /**
         * 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
         * 说明：
         * 1.二维数组包含10个一维数组，每个一维数组就是一个桶
         * 2.为了防止在放入数据的时候数据溢出，则每个一维数组（桶）的大小为arr.length(很明显，基数排序是使用空间换时间的经典做法)
         */
        int[][] bucket = new int[10][arr.length];
        /**
         * 为了记录每个桶每次实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入数据的个数
         * 比如：bucketElementCounts[0]，记录的就是bucket[0]桶的放入数据个数
         */
        int[] bucketElementCounts = new int[10];
        // 得到数组中最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大数是几位数
        int maxLength = (max + "").length();
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 每一轮针对每个元素对应的位数进行排序，第一次是个位，第二次是十位，第三次是百位，以此类推
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素对应位的值，得出元素将存放在那个“桶”
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                // 桶中的数据个数递增
                bucketElementCounts[digitOfElement]++;
            }
            // 遍历每个桶，并将桶中的数据放到原数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                // 将桶中的数据放到原数组之后，bucket中的元素个数要清0，即bucketElementCounts[j] = 0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + (i+1) + "轮排序后：" + Arrays.toString(arr));
        }

    }

    /**
     * 基数排序推导过程
     * @param arr
     */
    public static void radixSortTest(int[] arr) {
        /**
         * 定义一个二维数组，表示10个桶，每个桶就是一个一维数组
         * 说明：
         * 1.二维数组包含10个一维数组，每个一维数组就是一个桶
         * 2.为了防止在放入数据的时候数据溢出，则每个一维数组（桶）的大小为arr.length(很明显，基数排序是使用空间换时间的经典做法)
         */
        int[][] bucket = new int[10][arr.length];
        /**
         * 为了记录每个桶每次实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入数据的个数
         * 比如：bucketElementCounts[0]，记录的就是bucket[0]桶的放入数据个数
         */
        int[] bucketElementCounts = new int[10];

        /**----------------------------------------------------------------------------*/

        //第一轮（针对每个元素的个位进行排序处理）
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素个位的值，得出元素将存放在那个“桶”
            // 53 % 10 = 3; 748 % 10 = 8
            int digitOfElement = arr[i] / 1 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            // 桶中的数据个数递增
            bucketElementCounts[digitOfElement]++;
        }
        // 遍历每个桶，并将桶中的数据放到原数组
        int index = 0;
        for (int j = 0; j < bucketElementCounts.length; j++) {
            if (bucketElementCounts[j] != 0) {
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index] = bucket[j][k];
                    index++;
                }
            }
            // 将桶中的数据放到原数组之后，bucket中的元素个数要清0，即bucketElementCounts[j] = 0
            bucketElementCounts[j] = 0;
        }
        System.out.println("第一轮排序后：" + Arrays.toString(arr));

        /**----------------------------------------------------------------*/

        //第二轮（针对每个元素的十位进行排序处理）
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素个位的值，得出元素将存放在那个“桶”
            // 748 / 10 = 74; 74 % 10 = 4;
            int digitOfElement = arr[i] / 10 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            // 桶中的数据个数递增
            bucketElementCounts[digitOfElement]++;
        }
        // 遍历每个桶，并将桶中的数据放到原数组
        index = 0;
        for (int j = 0; j < bucketElementCounts.length; j++) {
            if (bucketElementCounts[j] != 0) {
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index] = bucket[j][k];
                    index++;
                }
            }
            // 将桶中的数据放到原数组之后，bucket中的元素个数要清0，即bucketElementCounts[j] = 0
            bucketElementCounts[j] = 0;
        }
        System.out.println("第二轮排序后：" + Arrays.toString(arr));

        /**----------------------------------------------------------------*/

        //第三轮（针对每个元素的百位进行排序处理）
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素个位的值，得出元素将存放在那个“桶”
            // 748 / 100 = 7; 7 % 10 = 7;
            int digitOfElement = arr[i] / 100 % 10;
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[i];
            // 桶中的数据个数递增
            bucketElementCounts[digitOfElement]++;
        }
        // 遍历每个桶，并将桶中的数据放到原数组
        index = 0;
        for (int j = 0; j < bucketElementCounts.length; j++) {
            if (bucketElementCounts[j] != 0) {
                for (int k = 0; k < bucketElementCounts[j]; k++) {
                    arr[index] = bucket[j][k];
                    index++;
                }
            }
            // 将桶中的数据放到原数组之后，bucket中的元素个数要清0，即bucketElementCounts[j] = 0
            bucketElementCounts[j] = 0;
        }
        System.out.println("第三轮排序后：" + Arrays.toString(arr));
    }
}
