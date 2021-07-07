package com.cry.pattern;

import java.util.concurrent.TimeUnit;

public class TwoPhaseTermination {
    private Thread thread;

    public void start() throws Exception {
        thread = new Thread(() -> {
            while (true) {
                Thread current = Thread.currentThread();
                if (current.isInterrupted()) {
                    System.out.println("料理后事");
                    break;
                }
                try {
                    Thread.sleep(1000);
                    System.out.println("将结果保存");
                } catch (InterruptedException e) {
                    current.interrupt();
                }
                //   执行监控操作
            }
        }, "监控线程");
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public static void main(String[] args) throws Exception {
        TwoPhaseTermination twoPhaseTermination = new TwoPhaseTermination();
        twoPhaseTermination.start();
        TimeUnit.SECONDS.sleep(1);
        twoPhaseTermination.stop();
    }
}
