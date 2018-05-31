package com.test.algorithm;

import java.util.Scanner;

/**
 * Created by admin on 2018/1/22.
 */
public class DPAlgorithm {

    public static class Good {
        int weight;
        int value;
    }

    public void cal() {
        Scanner sc = new Scanner(System.in);

        int c = sc.nextInt();
        int n = sc.nextInt();

        Good[] goods = new Good[n];

        for (int i = 0; i < goods.length; i++) {
            goods[i] = new Good();
            goods[i].weight = sc.nextInt();
            goods[i].value = sc.nextInt();
        }

        int[][] dp = new int[n][c];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < c; j++) {
                if (i > 0) {
                    if (j >= goods[i].weight) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - goods[i].weight] + goods[i].value);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                } else {
                    if (j >= goods[i].weight) {
                        dp[i][j] = goods[i].value;
                    }
                }
            }
        }

        System.out.println(dp[n - 1][c - 1]);
    }
}
