package com.cry.chapter02;

public class DaemonThreadTest {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println("我是t1:");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            System.out.println("我是t2");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        super.run();
                    }
                }.start();
            }
        };
        t1.setDaemon(true);
        t1.start();
        for (int i=1;i<=5; i++){
            try {
                System.out.println("我是main：");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
