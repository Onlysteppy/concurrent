package com.cry.chapter03;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
    }
    private static void test2() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };
        t.start();
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println("System will be shutdown");
        t.interrupt();
    }

    private static void test1() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("I will start work");
                while (!isInterrupted()) {
                    //working
                }
                System.out.println("I will be exiting.");
            }
        };
        t.start();
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.println("System will be shutdown");
        t.interrupt();
    }
}
