package com.dayi.recursion;

/**
 * 递归问题测试
 * @author yangshaoqiang <yangshq@pvc123.com>
 * @create 2021-01-11 14:45
 */
public class RecursionTest {
    public static void main(String[] args) {
        // 简单阶层计算
        int num = 3;
        int result = test(num);
        System.out.printf("%d的阶层：%d", num, result);
    }

    public static int test(int num) {
        if (num == 1) {
            return 1;
        } else {
            return test(num -1) * num;
        }
    }
}
