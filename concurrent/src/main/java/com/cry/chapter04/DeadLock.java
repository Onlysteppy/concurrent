package com.cry.chapter04;

public class DeadLock {
    private final Object MUTEXT_READ = new Object();
    private final Object MUTEXT_WRITE = new Object();

    public void read() {
        synchronized (MUTEXT_READ) {
            synchronized (MUTEXT_WRITE) {

            }
        }
    }
    public void write(){
        synchronized (MUTEXT_WRITE){
            synchronized (MUTEXT_READ){

            }
        }
    }
}
