package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2018/6/2.
 */
public class Test2 extends Thread {

    private static int b = 1;
    private int c = 1;

    public static void main(String[] agrs) {

        int a = 0;
        int b = 1;
        try {
            System.out.println("1");
            int c = b/a;
        } catch (Exception e) {
            System.out.println("2");
            int c = b/a;
        } finally {
            System.out.println("3");
        }
    }

}
