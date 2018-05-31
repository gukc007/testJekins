package com.test.algorithm;

import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Created by admin on 2018/1/16.
 */
public class KMPAlgorithm {

    private static final String RED = "red";
    private static final String BLUE = "blue";
    private static final String YELLOW = "yellow";
    private static final String WHITE = "white";

    public void cal() {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        System.out.println("生成整串");
        String str = "";
        for (int i = 0; i < 1000; i++) {
            char c = (char) ('a' + random.nextInt('d' - 'a'));
            str += c;
        }
        System.out.println(str);
//        String str = sc.next();
        System.out.println("输入子串");
        String subString = sc.next();

        int[] next = getNextValue(subString);

        List<Integer> allIndexs = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            while (true) {
                if (index < 0) {
                    index = 0;
                    break;
                }
                if (subString.charAt(index) == str.charAt(i)) {
                    index++;
                    if (index >= subString.length()) {
//                        System.out.println("找到一处位置:" + i);
                        allIndexs.add(i - subString.length() + 1);
                        index = next[index] - 1;
                    }
                    break;
                } else {
                    index = next[index] - 1;
                }
            }
        }

        printString(allIndexs, str, subString);
    }

    /**
     * 输出字符串，起点红色，其余黄色
     *
     * @throws Exception
     */
    private void printString(List<Integer> allIndexs, String str, String subString) {
        int lastIndex = Integer.MIN_VALUE;
        int j = 0;
        for (int i = 0; i < str.length(); i++) {
            String color = WHITE;
            if (i < lastIndex + subString.length()) {
                color = YELLOW;
            }
            if (j < allIndexs.size() && allIndexs.get(j).equals(i)) {
                lastIndex = i;
                color = RED;
                j++;
            }
            System.out.print(ansi().eraseScreen().render("@|" + color + " " + str.charAt(i) + "|@"));
        }
        System.out.println("\n共" + allIndexs.size() + "个");
    }

    private int[] getNextValue(String subString) {
        int[] next = new int[subString.length() + 1];

        next[0] = 0;
        for (int i = 1; i <= subString.length(); i++) {
            char c = subString.charAt(i - 1);
            int index = next[i - 1] - 1;
            while (true) {
                if (index < 0) {
                    next[i] = 1;
                    break;
                }
                if (c == subString.charAt(index)) {
                    next[i] = index + 1 + 1;
                    break;
                } else {
                    index = next[index] - 1;
                }
            }
        }
//        System.out.println("生成的kmp码值:");
//        System.out.println(Arrays.toString(next));
        return next;
    }
}
