package com.cry.chapter02;

import java.util.stream.IntStream;

public class ThreadName {
    private final static String PREFIX = "ALEX-";

    public static void main(String[] args) {
        //box 将int 转为integer
        IntStream.range(0, 5).boxed().map(i -> new Thread(() -> System.out.println(Thread.currentThread().getName()))).forEach(Thread::start);
        IntStream.range(0, 5).mapToObj(ThreadName::createThread)
                .forEach(Thread::start);

    }

    private static Thread createThread(final int intName) {
        return new Thread(
                () -> System.out.println(Thread.currentThread().getName())
                , PREFIX + intName);
    }
}
