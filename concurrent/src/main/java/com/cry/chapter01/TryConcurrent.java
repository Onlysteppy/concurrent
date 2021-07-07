package com.cry.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrent {
    public static void main(String[] args) {
        System.out.println(
                Thread.currentThread());
        fun2();

    }

    /**
     * 2、通过线程实现。
     */
    private static void fun2() {
        new Thread(){
            @Override
            public void run() {
                browseNews();
            }
        }.start();
        enjoyMusic();
    }


    /**
     * 1:想要浏览网页的同时听听歌;
     * 无法实现想要浏览网页的同时听听歌;
     */
    private static void fun1() {
        browseNews();
        enjoyMusic();
    }

    private static void browseNews() {
        for (; ; ) {
            System.out.println("Uh-huh ,the good news.");
            sleep(1);
        }
    }

    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("Uh-huh ,the nice music.");
            sleep(1);
        }
    }

    private static void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
