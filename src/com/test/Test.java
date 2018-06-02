package com.test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.test.algorithm.*;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by admin on 2017/2/23.
 */
public class Test {

    private static Socket client;
    private static BlockingQueue<byte[]> queue = new ArrayBlockingQueue<>(50000);//队列长度;

    private static int count = 0;

    static class StaticTest {
        AtomicInteger i = new AtomicInteger(0);

    }

    static enum testValue {
        test1("1"),
        test2("2");
        private String value;

        testValue(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    private static StaticTest staticTest = new StaticTest();

    private synchronized static void test(int i, int sleep) {
        staticTest.i.set(i);
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("原值是i:" + staticTest.i);
    }

    public static void main(String[] args) throws Exception {
        RBTreeAlgorithm rbTreeAlgorithm = new RBTreeAlgorithm();
//        rbTreeAlgorithm.cal();

        KMPAlgorithm kmpAlgorithm = new KMPAlgorithm();
//        kmpAlgorithm.cal();

        DPAlgorithm dpAlgorithm = new DPAlgorithm();
//        dpAlgorithm.cal();

        QKSortAlgorithm qkSortAlgorithm = new QKSortAlgorithm();
//        qkSortAlgorithm.cal();

        MergeSortAlgorithm mergeSortAlgorithm = new MergeSortAlgorithm();
//        mergeSortAlgorithm.cal();

//        String test = "abasudgukchyaisuhgukc";
//
//        System.out.println(test.lastIndexOf("gukc"));
//        System.out.println(test.lastIndexOf("gukcaa"));

        for (int i = 0; i < 10; i++) {
            System.out.println("sucesss!!!!");
        }

//        System.out.println(test.lastIndexOf("gukc"));
//        byte[] b = new byte[6291456 * 2];
//        client = new Socket();
//        System.out.println("连接前");
//        client.connect(new InetSocketAddress("172.16.2.58", 8018), 5000);
////        client = new Socket("172.16.2.58", 8018);
//        System.out.println("连接后");
////        outputStream = client.getOutputStream();
//        InputStream inputStream = client.getInputStream();
////        Scanner sc = new Scanner(inputStream);
//        while (true) {
//            int readSize = inputStream.read(b);
//            if (readSize > 0) {
//                ByteBuffer buffer = ByteBuffer.allocate(readSize);
//                buffer.put(b, 0, readSize);
//                String str = new String(buffer.array(), "utf-8");
//                System.out.println(str);
////                System.out.println(buffer);
//                //处理结果
////                handingGkPackage(buffer.array());
//            }
//        }

    }


    private static String get(String launchUserE164, String confCode) {

        String beCallUserE164 = null;
        String separate = "00000";
        if (confCode.endsWith(separate + launchUserE164)) {
            beCallUserE164 = confCode.substring(0, confCode.lastIndexOf(separate + launchUserE164));
        } else if (confCode.startsWith(launchUserE164 + separate)) {
            beCallUserE164 = confCode.substring(launchUserE164.length() + separate.length());
        }
        return beCallUserE164;
    }

    private static void find(int[][] a, int i, int j, int step) {

        if (get(a)) {
            count++;
        }

        if (i + 1 < 5 && a[i + 1][j] == 0) {
            a[i + 1][j] = step + 1;
            find(a, i + 1, j, step + 1);
            a[i + 1][j] = 0;
        }
        if (j + 1 < 5 && a[i][j + 1] == 0) {
            a[i][j + 1] = step + 1;
            find(a, i, j + 1, step + 1);
            a[i][j + 1] = 0;
        }
        if (i - 1 >= 0 && a[i - 1][j] == 0) {
            a[i - 1][j] = step + 1;
            find(a, i - 1, j, step + 1);
            a[i - 1][j] = 0;
        }
        if (j - 1 >= 0 && a[i][j - 1] == 0) {
            a[i][j - 1] = step + 1;
            find(a, i, j - 1, step + 1);
            a[i][j - 1] = 0;
        }
    }

    private static boolean get(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (a[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

//    public static String md5(String s) {
//        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
//        try {
//            byte[] btInput = s.getBytes();
//            // 获得MD5摘要算法的 MessageDigest 对象
//            MessageDigest mdInst = MessageDigest.getInstance("MD5");
//            // 使用指定的字节更新摘要
//            mdInst.update(btInput);
//            // 获得密文
//            byte[] md = mdInst.digest();
//            // 把密文转换成十六进制的字符串形式
//            int j = md.length;
//            char str[] = new char[j * 2];
//            int k = 0;
//            for (byte byte0 : md) {
//                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
//                str[k++] = hexDigits[byte0 & 0xf];
//            }
//            return new String(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public static boolean isNullOrEmpty(String value) {
//        if (value == null)
//            return true;
//        //是否为空字符串
//        String regEx = "^\\s*$";
//        Pattern pattern = Pattern.compile(regEx);
//        // 忽略大小写
//        // Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(value);
//        // 字符串是否与正则表达式相匹配
//        return matcher.matches();
//    }
//    @Override
//    public void test() {
//
//    }
//    public static void smallTest(Enum.test test){
//        System.out.println(test);
//    }
}
