package com.cry.chapter03;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ThreadisInterrupted {
    public static void main(String[] args) throws InterruptedException {
        //test1();
        test2();
    }

    private static void test1() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {

                }
            }
        };
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());//false
        thread.interrupt();
        System.out.printf("Thread is interrupted ? %s \n", thread.isInterrupted()); //true
    }

    private static void test2() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        //here the interrupt flag will be clear.
                        System.out.printf("I am be interruped ? %s\n", isInterrupted());//false
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());//false
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.printf("Thread is interrupted ? %s\n", thread.isInterrupted());//false
    }


}
