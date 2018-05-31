package com.test.algorithm;

import java.util.Random;

/**
 * Created by admin on 2018/1/23.
 */
public class MergeSortAlgorithm {

    public void cal() {
        Random random = new Random();

        int n = 100;
        int[] a = new int[n];

        System.out.println("生成随机数");
        for (int i = 0; i < a.length; i++) {
            a[i] = random.nextInt(n);
            System.out.print(a[i] + " ");
        }
        System.out.println("\n排序后");
        merge(a, 0, a.length - 1, new int[n]);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n结束");
    }

    private void merge(int[] a, int begin, int end, int[] t) {

        if (begin >= end) {
            return;
        }

        int mid = (begin + end) / 2;
        merge(a, begin, mid, t);
        merge(a, mid + 1, end, t);

        int i = begin;
        int j = mid + 1;
        int index = begin;
        while (i <= mid || j <= end) {
            if (i > mid) {
                t[index] = a[j];
                j++;
            } else if (j > end) {
                t[index] = a[i];
                i++;
            } else if (a[i] <= a[j]) {
                t[index] = a[i];
                i++;
            } else {
                t[index] = a[j];
                j++;
            }
            index++;
        }
        for (i = begin; i <= end; i++) {
            a[i] = t[i];
        }
    }
}
