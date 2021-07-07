package com.cry.chapter03;

import java.util.stream.IntStream;

public class ThreadYeild {
    public static void main(String[] args) {
        IntStream.range(0, 2).mapToObj(ThreadYeild::create)
                .forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() -> {
            if (index == 0) {
                Thread.yield();
            }
            System.out.println(index);
        });
    }
}
