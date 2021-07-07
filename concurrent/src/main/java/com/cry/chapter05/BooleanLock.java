package com.cry.chapter05;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;
import static java.lang.System.currentTimeMillis;

public class BooleanLock implements Lock {

    //当前用有锁的线程
    private Thread currentThread;

    //locked是一个boolean开关，false代表当前锁没有被任何线程获得或者已经释放，true代表该锁已经被某个线程获得，该线程就是currentThread
    private boolean locked = false;

    //用来存储那些线程子啊获取当前线程时进入了阻塞状态
    private final List<Thread> blockedList = new ArrayList<>();

    //①lock()方法使用同步代码块的方式进行方法同步
    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            //②如果当前锁已经被某个线程获得，则该线程将加入阻塞队列，并且使当前线程wait释放对this monitor的所有权
            while (locked) {
                blockedList.add(currentThread());
                this.wait();
            }
            /**
             * ③如果当前锁没有被其他线程获得，则该线程将尝试从阻塞队列中删除自己
             * 如果当前线程从未进入过阻塞队列，删除方法不会有任何影响；
             * 如果当前线程时从wait set中被唤醒的，则需要从阻塞队列中将自己删除。
             */
            blockedList.remove(currentThread());
            //④locked开关被指定为true
            this.locked = true;
            //⑤记录获取锁的线程
            this.currentThread = currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            //①如果mills不合法，则默认调用lock()方法，当然也可以抛出参数非法的异常，一般来说，则抛出异常时一种比较好的做法。
            if (mills <= 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = currentTimeMillis() + remainingMills;
                while (locked) {
                    //②如果remainingMills小于等于0，则意味着当前线程被其他线程唤醒或者在指定的wait时间到了之后没有获得锁，这种情况下或抛出超时异常
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get the lock during" + mills);
                    }
                    if (!blockedList.contains(currentThread()))
                        blockedList.add(currentThread());
                    //③等待啊remainingMills的毫秒数，该值最开始时由其他线程传入的，但在多次wait的过程中会重新计算。
                    this.wait(remainingMills);

                    //④重新计算remainingMills时间
                    remainingMills = endMills - currentTimeMillis();
                }
                //⑤获得该锁，并且从block列表中删除当前线程，将locked的状态修改为true并且指定获得锁的线程就是当前线程。
                blockedList.remove(currentThread());
                this.locked = true;
                this.currentThread = currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (currentThread == currentThread) {
                this.locked = false;
                Optional.of(currentThread.getName() + " release the lock")
                        .ifPresent(System.out::println);
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }

//    public void synMethodTimeoutable)
//
//    {
//        try {
//            lock.lock();
//
//        }
//    }
}
