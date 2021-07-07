package com.cry.eightLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Number")
public class MainTest {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
    }

    private static void test8() {
        Number8 n1 = new Number8();
        Number8 n2 = new Number8();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n2.b(); }).start();
    }

    private static void test7() {
        Number7 n1 = new Number7();
        Number7 n2 = new Number7();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n2.b(); }).start();
    }

    private static void test6() {
        Number6 n1 = new Number6();
        new Thread(()->{ n1.a(); }).start();
        new Thread(()->{ n1.b(); }).start();
    }

    private static void test5() {
        Number6 n1 = new Number6();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
    }

    private static void test4() {
        Number4 n1 = new Number4();
        Number4 n2 = new Number4();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n2.b();
        }).start();
    }

    private static void test3() {
        Number3 n1 = new Number3();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
        new Thread(() -> {
            n1.c();
        }).start();
    }

    private static void test2() {
        Number2 n2 = new Number2();
        new Thread(() -> {
            n2.b();
        }).start();
        new Thread(() -> {
            n2.a();
        }).start();
    }

    private static void test1() {
        Number1 n1 = new Number1();
        new Thread(() -> {
            n1.a();
        }).start();
        new Thread(() -> {
            n1.b();
        }).start();
    }
}

