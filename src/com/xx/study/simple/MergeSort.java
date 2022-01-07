package com.xx.study.simple;

/**
 * @Author: XX
 * @Description:
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 10:36 2021/8/30
 * @ModifiedDate:
 * @Copyright: ©
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] data = {5, 3, 6, 2, 1, 9, 4, 8, 7};
        doPrint(data);
        doMergeSort(data);
        System.out.println("排序后的数组：");
        doPrint(data);
    }

    private static void doPrint(int[] dataS) {
        for (int i = 0; i < dataS.length; i++) {
            System.out.print(dataS[i] + "\t");
        }
        System.out.println();
    }

    private static void doMergeSort(int[] data) {
        doSort(data, 0, data.length - 1);
    }

    private static void doSort(int[] data, int left, int right) {
        if (left >= right) {
            return;
        }
        // 找出中间的索引
        int center = (left + right) / 2 ;
        // 对左边的数组进行递归
        doSort(data, left, center);
        // 对右边的数组进行递归
        doSort(data, center + 1, right);
        // 合并
        doMerge(data, left, center, right);
        doPrint(data);
    }

    private static void doMerge(int[] data, int left, int center, int right) {
        // 临时数组
        int[] tempArr = new int[data.length];
        // 右边数组第一个索引
        int mid = center + 1;
        // third 记录临时数组的索引
        int third = left;
        // 缓存左数组第一个索引
        int temp = left;
        while (left <= center && mid <= right) {
            // 从两个数组中取出最小的放入临时数组
            if (data[left] <= data[mid]) {
                tempArr[third++] = data[left++];
            } else {
                tempArr[third++] = data[mid++];
            }
        }
        // 剩余部分依次放入临时数组(实际上两个while只会执行一个)
        while (mid <= right) {
            tempArr[third++] = data[mid++];
        }
        while (left <= center) {
            tempArr[third++] = data[left++];
        }
        // 将临时数组中的内容拷贝回到原数组中
        while (temp <= right) {
            data[temp] = tempArr[temp++];
        }
    }
}
