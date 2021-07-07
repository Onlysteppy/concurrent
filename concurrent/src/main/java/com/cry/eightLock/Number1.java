package com.cry.eightLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Number1 {
    public synchronized void a() {
        log.debug("1");
    }

    public synchronized void b() {
        log.debug("2");
    }
}
