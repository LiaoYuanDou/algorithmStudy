package com.xx.study.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: XX
 * @Description: 冒泡排序
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 11:02 2021/8/27
 * @ModifiedDate:
 * @Copyright: ©
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] intArray = {49, 38, 65, 97, 76, 13, 27};
        doBubbleSortArray(intArray);
        Arrays.stream(intArray).boxed().collect(Collectors.toList())
                .stream().forEach(System.out::println);
    }

    public static void doBubbleSortList(List<Integer> list) {
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            for (int j = 1; j < listSize - i; j++) {
                if (list.get(j - 1) > list.get(j)) {
                    int temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }

    public static void doBubbleSortArray(int[] intArray) {
        int arraySize = intArray.length;
        for (int i = 0; i < arraySize; i++) {
            for (int j = 1; j < arraySize - i; j++) {
                if (intArray[j - 1] > intArray[j]) {
                    int temp = intArray[j - 1];
                    intArray[j - 1] = intArray[j];
                    intArray[j] = temp;
                }
            }
        }
    }
}
