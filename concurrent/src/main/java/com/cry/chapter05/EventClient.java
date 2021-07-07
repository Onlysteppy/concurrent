package com.cry.chapter05;

import java.util.concurrent.TimeUnit;

public class EventClient {
    public static void main(String[] args) throws InterruptedException {
        //test1();
//        long start = System.currentTimeMillis();
//        Thread thread = Thread.currentThread();
//        thread.sleep(0);
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }

    public synchronized static void test2() throws InterruptedException {

        long start = System.currentTimeMillis();
        EventClient.class.wait();
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    private static void test1() {
        EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            for (; ; ) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();
        new Thread(() -> {
            for (; ;
                    ) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }
}
