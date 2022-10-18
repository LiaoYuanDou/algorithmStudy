package com.xx.study.offer.stackqueueheap;

import java.util.Stack;

/**
 * @Author: XX
 * @Description: 31. 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * <p>
 * 例如序列 1,2,3,4,5 是某栈的压入顺序，序列 4,5,3,2,1 是该压栈序列对应的一个弹出序列，但 4,3,5,1,2 就不可能是该压栈序列的弹出序列。
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/31.%20%E6%A0%88%E7%9A%84%E5%8E%8B%E5%85%A5%E3%80%81%E5%BC%B9%E5%87%BA%E5%BA%8F%E5%88%97.md
 * @Since: JDK1.8
 * @Version: V1.0
 * @CreateDate: Create in 11:04 2022/1/8
 * @ModifiedDate:
 * @Copyright: ©
 */
public class Offer31 {

    public static void main(String[] args) {
        int[] pushSequence = {1, 2, 3, 4, 5};
        int[] popSequence1 = {4, 5, 3, 2, 1};
        int[] popSequence2 = {4, 3, 5, 1, 2};
        System.out.println(isPopOrder(pushSequence, popSequence1));
        System.out.println(isPopOrder(pushSequence, popSequence2));

        System.out.println(isPopOrder1(pushSequence, popSequence1));
        System.out.println(isPopOrder1(pushSequence, popSequence2));

        System.out.println(isPopOrder2(pushSequence, popSequence1));
        System.out.println(isPopOrder2(pushSequence, popSequence2));
    }

    /**
     * 使用了一个辅助栈来模拟
     * step1：准备一个辅助栈，用pushIndex和popIndex记录两个序列的下标
     * step2：外层循环将push序列的元素放入stack中
     * step3：内层循环判断 stack的栈顶元素 == pop序列的下标元素 等于 则stack.pop() pop下标 ++
     * step4：stack.isEmpty()
     *
     * @param pushSequence push 顺序组
     * @param popSequence  pop 顺序组
     * @return
     */
    public static boolean isPopOrder(int[] pushSequence, int[] popSequence) {
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();
        for (int pushIndex = 0, popIndex = 0; pushIndex < n; pushIndex++) {
            stack.push(pushSequence[pushIndex]);
            while (popIndex < n && !stack.isEmpty() && stack.peek() == popSequence[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 使用了一个辅助栈来模拟
     * step 1：准备一个辅助栈，两个下标分别访问两个序列。
     * step 2：辅助栈为空或者栈顶不等于出栈数组当前元素，就持续将入栈数组加入栈中。
     * step 3：栈顶等于出栈数组当前元素就出栈。
     * step 4：当入栈数组访问完，出栈数组无法依次弹出，就是不匹配的，否则两个序列都访问完就是匹配的。
     *
     * @param pushSequence
     * @param popSequence
     * @return
     */
    public static boolean isPopOrder1(int[] pushSequence, int[] popSequence) {
        int n = pushSequence.length;
        Stack<Integer> stack = new Stack<>();
        int pushIdx = 0;
        for (int popIdx = 0; popIdx < n; popIdx++) {
            while (pushIdx < n && (stack.isEmpty() || stack.peek() != popSequence[popIdx])) {
                stack.push(pushSequence[pushIdx++]);
            }
            if (stack.peek() == popSequence[popIdx]) {
                stack.pop();
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 数组本来就很类似栈啊，用下标pushIdx表示栈顶。
     * 在方法一种push数组前半部分入栈了，就没用了，这部分空间我们就可以用来当成栈。
     * 原理还是同方法一一样，只是这时我们遍历push数组的时候，用下标pushIdx表示栈空间，pushIdx的位置就是栈顶元素。
     * <p>
     * step 1: 用pushIdx代表入栈下标 用popIdx代表出栈下标
     * step 2: 遍历入栈元素 加入栈顶 就是 push数组pushIdx 位置 后 pushIdx ++
     * step 3: 当栈顶不为空即 pushIdx >= 0 循环判断栈的 pushIdx == pop序列中popIdx 如果等于 popIdx++ pushIdx--
     * step 4: 最后若是栈的为空 即 pushIdx == 0 代表全部出栈完成，否则不匹配。
     *
     * @param pushA
     * @param popA
     * @return
     */
    public static boolean isPopOrder2(int[] pushA, int[] popA) {
        int pushIdx = 0, popIdx = 0;
        for (int num : pushA) {
            pushA[pushIdx] = num;
            while (pushIdx >= 0 && popA[popIdx] == pushA[pushIdx]) {
                popIdx++;
                pushIdx--;
            }
            pushIdx++;
        }
        return pushIdx == 0;
    }
}
