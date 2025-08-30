package com.kuz.concurrency.daemon_thread;

import java.util.concurrent.TimeUnit;

public class Runner {
    private static final String MESSAGE_MAIN_THREAD_FINISHED = "Main thread is finished";
    private static final String MESSAGE_TEMPLATE_NAME_AND_DAEMON_STATUS = "%s : %b\n";

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        thread.setDaemon(true);
        thread.start();
        System.out.println(thread.isDaemon());
        System.out.println("----------------------------------------------");

        Thread firstDaemonThread = new Thread((Runnable) () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            printThreadNameAndDaemonStatus(Thread.currentThread());

            Thread secondDaemonThread = new Thread((Runnable) () -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                printThreadNameAndDaemonStatus(Thread.currentThread());
            });

            secondDaemonThread.start();
        });

      //  firstDaemonThread.setDaemon(true);
        firstDaemonThread.start();

        firstDaemonThread.join();

        System.out.println(MESSAGE_MAIN_THREAD_FINISHED);
    }

    private static void printThreadNameAndDaemonStatus(Thread thread) {
        System.out.printf(MESSAGE_TEMPLATE_NAME_AND_DAEMON_STATUS, thread.getName(), thread.isDaemon());
    }

    private static final class Task implements Runnable {
        private static final String MESSAGE = "I am working";
        private static int DURATION_BETWEEN_MESSAGE_IN_SECONDS = 2;

        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(MESSAGE);
                    TimeUnit.SECONDS.sleep(DURATION_BETWEEN_MESSAGE_IN_SECONDS);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

