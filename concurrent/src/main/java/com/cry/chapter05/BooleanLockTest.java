package com.cry.chapter05;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class BooleanLockTest {
    //定义BooleanLock
    private final Lock lock = new BooleanLock();

    //使用try..finally语句块确保lock每次都能被正确释放
    public void syncMethod() {
        //枷锁
        try {
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            int randomInt = current().nextInt(10);
            System.out.println(currentThread() + "get the lock.");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        BooleanLockTest blt = new BooleanLockTest();
//        IntStream.range(0, 10)
//                .mapToObj(i -> new Thread(blt::syncMethod))
//                .forEach(Thread::start);
        BooleanLockTest blt = new BooleanLockTest();
        Thread t1 = new Thread(blt::syncMethod, "T1");
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        t2.interrupt();
    }

    private static void test1() {
        BooleanLockTest blt = new BooleanLockTest();
        IntStream.range(0, 10)
                .mapToObj(i -> new Thread(blt::syncMethod))
                .forEach(Thread::start);
    }
}
