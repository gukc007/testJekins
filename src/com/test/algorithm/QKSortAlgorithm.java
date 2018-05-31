package com.test.algorithm;

import java.util.Random;

/**
 * Created by admin on 2018/1/23.
 */
public class QKSortAlgorithm {

    public void cal() {
        Random random = new Random();

        int n = 10;
        int[] a = new int[n];

        System.out.println("生成随机数");
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(n);
            System.out.print(a[i] + " ");
        }
        System.out.println("\n排序后");
        quickSort(0, a.length - 1, a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    private void quickSort(int begin, int end, int[] a) {
        if (begin >= end) {
            return;
        }
//        int index = i;
        int i = begin;
        int j = end;
        while (i < j) {
            while (i < j) {
                if (a[i] > a[j]) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                    break;
                }
                j--;
            }
            while (i < j) {
                if (a[i] > a[j]) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                    break;
                }
                i++;
            }
        }
        quickSort(begin, i, a);
        quickSort(i + 1, end, a);
    }
}
