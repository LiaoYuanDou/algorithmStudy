package com.xx.study.simple;

import javax.security.auth.login.LoginException;
import java.net.HttpCookie;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @Author: XX
 * @Description: 快速排序
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 14:27 2021/8/27
 * @ModifiedDate:
 * @Copyright: ©
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] intArray = {49, 38, 65, 97, 76, 13, 27};
        quickSortTest(intArray, 0, intArray.length - 1);
        Arrays.stream(intArray).boxed().collect(Collectors.toList())
                .stream().forEach(System.out::println);
        System.out.println(tableSizeFor(1048576));
    }

    public static void doQuickSort(int[] arr, int low, int high) {
        int start = low;
        int end = high;
        int key = arr[low];
        while (start < end) {
            // 从后往前比较
            while (end > start && arr[end] >= key) {
                // 如果没有比关键值小的，比较下一个，直到比关键值小的交换位置，然后从前往后开始比较
                end--;
            }
            if (arr[end] <= key) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
            }
            // 从前往后比较
            while (start < end && arr[start] <= key) {
                // 如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
                start++;
            }
            if (arr[start] >= key) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
            }
        }
        // 递归
        // 左边的序列 第一个索引位置到关键索引位置-1
        if (start > low) {
            doQuickSort(arr, low, start - 1);
        }
        // 右边的序列 从关键值索引+1到最后一个
        if (end < high) {
            doQuickSort(arr, end + 1, high);
        }
    }


    public static void quick(int[] arr, int start, int end) {

        //如果开始位置和结束位置重合，实际上就是一个数字，所以开始位置一定要比结束位置小，而且不能相等
        if (start < end) {
            //设定标准数，也就是快速排序的过程中，和谁进行比较，通常用第一个数字即可
            //注意这里用的是arr[start],按说在第一次的时候是0，应该写成arr[start],但是不可以
            //因为快速排序是一个递归的操作，第二次进来的时候，就不是arr[0]了
            int stand = arr[start];

            //开始找开始位置和结束位置
            //我们知道快速排序其实就是有一个开始位置和一个结束位置，当开始位置比选定的标准数字大的时候，就会替换掉
            //结束位置的数字，这个时候结束位置的下表开始向前移动，然后如果结束位置的数字比标准数字，则替换开始位置的数字
            //在循环的过程中如果开始位置/结束位置的数字 不比标准数字大或者小，这个时候开始位置和结束位置就会向后/向前移动

            //开始位置
            int low = start;
            //结束位置
            int high = end;

            while (low < high) {

                //这个时候我们已经找定了开始位置和结束位置

                //第一次是要拿高位和低位进行比较,如果高位比标准数字大，则高位则向前移动一位
                while (low < high && arr[high] > stand) {
                    high--;
                }
                //当然了并不是所有高位数字都比低位大，如果比低位要小，则这个数字要覆盖低位的数字
                arr[low] = arr[high];

                //然后这个时候低位开始移动，如果低位比标准数字小，则低位的下标向后移动一位
                while (low < high && arr[low] < stand) {
                    low++;
                }

                //当然了并不是所有时候低位都比标准数字要小，如果比标准数字大的话，这个时候就要替换掉高位的数字
                arr[high] = arr[low];
            }

            //经过上面的一番折腾，这个时候就会出现一个情况，低位和高位相同，那么这个位置就用标准数字去替换
            //这里low和high实际上是相同的数字，所以写哪个都无所谓
            arr[low] = stand;

            //然后第一轮排序完毕，我们就会发现以标准数字为分界线，我们有两个学列了，这个时候，我们就调用递归来
            //分别对两个序列进行排序

            //先出来低位的递归,最后的位置实际上有可能是高位，有可能是低位，既然上面写的是低位，这里就写低位就好了
            quick(arr, start, low);
            //然后出来高位的数字
            quick(arr, low + 1, end);
        }
    }


    public static void quickSortTest(int[] arr, int start, int end) {
        int low = start;
        int high = end;
        int key = arr[start];
        while (low < high) {
            while (high > low && arr[high] >= key) {
                high--;
            }
            if (arr[high] <= key) {
                int temp = arr[high];
                arr[high] = arr[low];
                arr[low] = temp;
            }
            while (low < high && arr[low] <= key) {
                low++;
            }
            if (arr[low] >= key) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
            }
            if (low > start) {
                quickSortTest(arr, start, low - 1);
            }
            if (high < end) {
                quickSortTest(arr, high + 1, end);
            }
        }
    }


    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }
}
