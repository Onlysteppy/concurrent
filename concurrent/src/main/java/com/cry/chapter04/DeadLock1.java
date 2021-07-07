package com.cry.chapter04;

public class DeadLock1 {
    private final Object MUTEXT_READ = new Object();
    private final Object MUTEXT_WRITE = new Object();

    public void read() {
        synchronized (MUTEXT_READ) {
            System.out.println(Thread.currentThread().getName()+"get READ lock");
            synchronized (MUTEXT_WRITE) {
                System.out.println(Thread.currentThread().getName()+"get WRITE lock");
            }
            System.out.println(Thread.currentThread().getName()+"release WRITE lock");
        }
        System.out.println(Thread.currentThread().getName()+"release READ lock");
    }
    public void write(){
        synchronized (MUTEXT_WRITE){
            System.out.println(Thread.currentThread().getName()+"get WRITE lock");
            synchronized (MUTEXT_READ){
                System.out.println(Thread.currentThread().getName()+"get READ lock");
            }
            System.out.println(Thread.currentThread().getName()+"release READ lock");
        }
        System.out.println(Thread.currentThread().getName()+"release WRITE lock");
    }

    public static void main(String[] args) {
        final DeadLock1 deadLock1 = new DeadLock1();
        new Thread(()->{
            while(true){
                deadLock1.read();
            }
        },"READ-THREAD").start();
        new Thread(()->{
            while(true){
                deadLock1.write();
            }
        },"WRITE-THREAD").start();
    }
}
