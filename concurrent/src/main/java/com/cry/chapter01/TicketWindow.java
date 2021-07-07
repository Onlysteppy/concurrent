package com.cry.chapter01;

/**
 * 1、    private int index = 1;未加static
 * 2、    private static int index = 1;加static
 */
public class TicketWindow extends Thread {
    //柜台名称
    private final String name;
    private static final int MAX = 50;
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台：" + name + "当前号码是：" + (index++));
        }
    }

    public static void main(String[] args) {
        /*TicketWindow ticketWindow1 = new TicketWindow("一号出号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号出号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号出号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("四号出号机");
        ticketWindow4.start();
*/
        TicketWindowRunnable task = new TicketWindowRunnable();
        Thread windowThread1 = new Thread(task, "一号窗口 ");
        Thread windowThread2 = new Thread(task, "二号窗口 ");
        Thread windowThread3 = new Thread(task, "三号窗口 ");
        Thread windowThread4 = new Thread(task, "四号窗口 ");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
        windowThread4.start();
        System.out.println("~~"+Thread.currentThread());
    }
}

class TicketWindowRunnable implements Runnable {
    private int index = 1;//不做static修饰
    private static final int MAX = 50;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread() + "的号码是：" + (index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}