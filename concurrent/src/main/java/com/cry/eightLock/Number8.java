package com.cry.eightLock;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class Number8 {
    public static synchronized void a() {
        try {
            sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("1");
    }
    public static synchronized void b() {
        log.debug("2");
    }
}
