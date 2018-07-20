package com.test.algorithm;

/**
 * Created by admin on 2018/7/5.
 */
//欧拉筛选
public class EratosthenesAlgorithm {

    public void cal() {
//        100000里的素数
        int total = 100;
        int [] a = new int[total];
        int [] b = new int[total];
        int num = 0;
        for (int i = 2; i < total; i++) {
            if (a[i] == 0) {
                //把素数存起来
                b[num++] = i;
            }
            for (int j = 0; j < num && b[j] * i < total; j++) {
                a[b[j] * i] = 1;
                if (i % b[j] == 0) {
                    break;
                }
            }
        }
//        for (int i = 0; i < num; i++) {
//            System.out.print(b[i] + ",");
//        }
        System.out.println("\nsize:" + num);
    }
}
