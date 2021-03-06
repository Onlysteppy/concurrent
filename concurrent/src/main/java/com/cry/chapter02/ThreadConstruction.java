package com.cry.chapter02;

public class ThreadConstruction {
    public static void main(String[] args) {
        Thread t1 = new Thread("t1");

        ThreadGroup group = new ThreadGroup("TestGroup");

        Thread t2 = new Thread(group, "t2");

        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("Main Thread belong group:" + mainThreadGroup.getName());
        System.out.println("t1 and main belong the sanme group:" + (mainThreadGroup == t1.getThreadGroup())); //true

        System.out.println("t2 thread group not belong main group:" + (mainThreadGroup == t2.getThreadGroup())); //false
        System.out.println("t2 thread group belong main TestGroup:" + (group == t2.getThreadGroup())); //true
    }
}
