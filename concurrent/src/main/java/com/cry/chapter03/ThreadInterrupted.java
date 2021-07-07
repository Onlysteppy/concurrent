package com.cry.chapter03;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
    public static void main(String[] args) throws InterruptedException {
//        test1();
        test2();


    }

    private static void test2() {
        //1、判断当前线程是否被中断
        System.out.printf("Main thread is interrupted? " + Thread.interrupted() + " \n");//false

        //2、中断当前线程
        Thread.currentThread().interrupt();

        //3、判断当前线程是否已经被中断
        System.out.printf("Main thread is interrupted? " + Thread.currentThread().isInterrupted() + "\n");//true
        try {
            //4、当前线程执行可中断方法
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            //5、捕获中断信号
            System.out.println("I will be interrupted still ");//1
        }
    }

    private static void test1() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.interrupted());
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        //shortly block make sure the thread is started.
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
    }
}
