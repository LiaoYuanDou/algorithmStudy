package com.xx.study.simple;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * @Author: XX
 * @Description: 插入排序
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 11:28 2021/8/27
 * @ModifiedDate:
 * @Copyright: ©
 */
public class InsertionSort {
    public static void main(String[] args) {
            int[] intArray = {49, 38, 65, 97, 76, 13, 27};
            doInsertionSor(intArray);
            Arrays.stream(intArray).boxed().collect(Collectors.toList())
                    .stream().forEach(System.out::println);
    }

    public static void doInsertionSor(int[] intArray) {
        int arraySize = intArray.length;
        for (int i = 1; i < arraySize; i++) {
            // 插入的数
            int indexValue = intArray[i];
            // 被插入的位置(准备和前一个数比较)
            int index = i - 1;
            // 如果插入的数比被插入的数小
            while (index >= 0 && indexValue < intArray[index]) {
                // 将被插入的数往后移
                intArray[index + 1] = intArray[index];
                // 让index向前移动
                index--;
            }
            // 把插入的数放到合适位置
            intArray[index + 1] = indexValue;

            Collections.synchronizedSet(new HashSet<>());
            Collections.synchronizedList(new ArrayList<>());
            Collections.synchronizedMap(new HashMap<>());

            CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
            CopyOnWriteArraySet<String> strings1 = new CopyOnWriteArraySet<>();
        }
    }
}
