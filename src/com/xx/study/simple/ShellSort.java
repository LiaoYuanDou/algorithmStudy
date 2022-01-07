package com.xx.study.simple;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: XX
 * @Description: 希尔排序
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 16:04 2021/8/27
 * @ModifiedDate:
 * @Copyright: ©
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] intArray = {49, 38, 65, 97, 76, 13, 27};
        doShellSort(intArray);
        Arrays.stream(intArray).boxed().collect(Collectors.toList())
                .stream().forEach(System.out::println);
    }

    public static void doShellSort(int[] arr) {
        int dk = arr.length / 2;
        while (dk >= 1) {
            doQuickSort(arr, dk);
            dk = dk / 2;
        }
    }

    private static void doQuickSort(int[] arr, int dk) {
        int arrSize = arr.length;
        for (int i = dk; i < arrSize; i++) {
            if (arr[i] < arr[i - dk]) {
                int index;
                int indexValue = arr[i];
                arr[i] = arr[i - dk];
                for (index = i - dk; index >= 0 && indexValue < arr[index]; index = index - dk) {
                    arr[index + dk] = arr[index];
                }
                arr[index + dk] = indexValue;
            }
        }
    }
}
