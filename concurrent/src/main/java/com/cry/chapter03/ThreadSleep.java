package com.cry.chapter03;

import java.util.concurrent.TimeUnit;

/**
 * 休眠有一个非常重要的特性，那就是其不会放弃monitor所的所有权。
 */

public class ThreadSleep {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            sleep(2_000L);
            long endTime = System.currentTimeMillis();
            System.out.println(String.format("Total spend %d ms", (endTime - startTime)));
        }).start();
        long startTime = System.currentTimeMillis();
        sleep(3_000L);

        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Main Total spend %d ms", (endTime - startTime)));
    }

    private static void sleep(long ms) {
        try {
//            Thread.sleep(ms);
//            TimeUnit.M.sleep(ms);
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
