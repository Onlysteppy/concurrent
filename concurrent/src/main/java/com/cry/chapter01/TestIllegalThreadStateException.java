package com.cry.chapter01;

import java.util.concurrent.TimeUnit;

/**
 * 两个程序都会抛出IllegalThreadStateException异常，但是这两个异常的抛出却有本质上的区别，第一个是重复启动。第二个启动重新激活已经生命周期已经被终结的线程。
 */
public class TestIllegalThreadStateException {
    public static void main(String[] args) throws InterruptedException {
        fun2();
    }

    /**
     * 企图重新激活已经terminate的线程
     * @throws InterruptedException
     */
    private static void fun2() throws InterruptedException {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.start();
    }

    /**
     * 重复启动抛出IlegalThreadStateException
     */
    private static void fun1() {
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
        thread.start();
    }

}
