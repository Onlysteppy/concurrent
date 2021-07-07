package com.cry.chapter04;

public class TicketWindowsRunnable implements Runnable {
    private int index = 1;
    private final static int MAX = 500;
    private final static Object MUTEX = new Object();

    @Override
    public void run() {
        synchronized (MUTEX) {
            while (index <= MAX) {
                System.out.println(Thread.currentThread() + " 的号码是:" + (index++));
            }
        }
    }

    public static void main(String[] args) {
        final TicketWindowsRunnable task = new TicketWindowsRunnable();
        Thread windownsThread1 = new Thread(task, "一号窗口 ");
        Thread windownsThread2 = new Thread(task, "二号窗口 ");
        Thread windownsThread3 = new Thread(task, "三号窗口 ");
        Thread windownsThread4 = new Thread(task, "四号窗口 ");
        windownsThread1.start();
        windownsThread2.start();
        windownsThread3.start();
        windownsThread4.start();
    }
}
