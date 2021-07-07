package com.cry.chapter05;

import java.util.LinkedList;

import static java.lang.Thread.currentThread;

public class EventQueue2 {
    private final int max;

    static class Event {

    }

    private final LinkedList<Event> eventQueue = new LinkedList<>();

    private final static int DEFAULT_MAX_EVENT = 10;

    public EventQueue2() {
        this(DEFAULT_MAX_EVENT);
    }

    public EventQueue2(int max) {
        this.max = max;
    }

    public void offer(Event event) {
        synchronized (eventQueue) {
            while (eventQueue.size() >= max) {
                try {
                    console(" the queue is full");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            console(" the new queue is submitted");
            eventQueue.addLast(event);
            eventQueue.notifyAll();
        }
    }

    public Event take() {
        synchronized (eventQueue) {
            while (eventQueue.isEmpty()) {
                try {
                    console(" the queue is empty.");
                    eventQueue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Event event = eventQueue.removeFirst();
            this.eventQueue.notifyAll();
            console("the event " + event + "is handle.`");
            return event;
        }
    }

    private void console(String message) {
        System.out.printf("%s:%s\n", currentThread().getName(), message);
    }


    private final Object MUTEX = new Object();

    private synchronized void testWait() {
        try {
            MUTEX.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void testNotify() throws InterruptedException {
        MUTEX.notify();
    }
}
