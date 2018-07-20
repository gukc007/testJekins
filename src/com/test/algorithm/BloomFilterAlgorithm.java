package com.test.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by admin on 2018/7/20.
 */
public class BloomFilterAlgorithm {

    //20倍左右大小
    private static final int SIZE = 10000;
    private static final int NUM_SIZE = 1000;

    public void cal() {
        int[][] a = new int[NUM_SIZE][2];

//        a[i] = random.nextInt();
        byte[] b = new byte[SIZE * 2 - 1];
        init(a);

        int[] c = new int[3];
        for (int i = 0; i < a.length; i++) {
            if (a[i][1] == 1) {
                //为1则需要标记
                initC(a[i][0], c);
                for (int i1 = 0; i1 < c.length; i1++) {
                    b[c[i1]] = 1;
                }
            }
        }

//        for (byte b1 : b) {
//            System.out.print(b1 + ",");
//        }
        System.out.println();
        //对比存储的正确
        testAccuracy(a, b);
        int countZero = 0;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == 0) {
                countZero++;
            }
        }
        System.out.println("存储为空率:" + countZero * 1.0 / b.length);
    }

    private void testAccuracy(int[][] a, byte[] b) {
        int[] c = new int[3];
        int wrongCount = 0;
        int countNotMark = 0;
        for (int i = 0; i < a.length; i++) {
//            System.out.print("原数字:" + a[i][0] + ",标记状况:" + a[i][1]);
            if (a[i][1] == 0) {
                countNotMark++;
            }
            //为1则需要标记
            initC(a[i][0], c);
            int i1 = 0;
            while (i1 < c.length) {
                if (b[c[i1]] == 0) {
//                    System.out.print(",存储状况: 0");
//                    if (a[i][1] == 1) {
//                        wrongCount++;
//                        System.out.print(",不一致!!!!!!!");
//                    }
                    break;
                }
                i1++;
            }
            if (i1 == c.length) {
//                System.out.print(",存储状况: 1");
                if (a[i][1] == 0) {
                    wrongCount++;
//                    System.out.print(",不一致!!!!!!!");
                }
            }
//            System.out.println();
        }
        System.out.println("\n判断未标记错误率:" + wrongCount * 1.0 / countNotMark);
    }

    private void init(int[][] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            a[i][0] = random.nextInt();
            a[i][1] = random.nextInt(2);
        }
    }

    private void initC(int num, int[] c) {
        c[0] = mod(num) + SIZE - 1;
        c[1] = change1(num) + SIZE - 1;
        c[2] = change2(num) + SIZE - 1;
    }

    private int change1(int num) {
        //右移5位 随便写的改变数字
        return mod(num >> 5);
    }

    private int change2(int num) {
        //平方后再取余
        double pow2 = Math.pow(num, 2);
        return mod(pow2);
    }

    private int mod(int num) {
        return num % SIZE;
    }

    private int mod(double num) {
        return (int) (num % SIZE);
    }

}
