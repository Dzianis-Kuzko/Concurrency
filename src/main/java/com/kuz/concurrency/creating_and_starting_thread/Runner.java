package com.kuz.concurrency.creating_and_starting_thread;

import java.util.stream.IntStream;

public class Runner {
    private static final int CREATED_THREADS_AMOUNT = 10;

    public static void main(String[] args) {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName);

        final Thread thread = new MyThread();
        thread.start();

        final Thread thread2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread2.start();

        Runnable taskDisplayingThreadName = () -> System.out.println(Thread.currentThread().getName());
        Runnable taskCreatingThreads = () ->
                IntStream.range(0, CREATED_THREADS_AMOUNT)
                        .forEach(i -> startThread(taskDisplayingThreadName));

        startThread(taskCreatingThreads);

    }

    private static void startThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private static final class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(currentThread().getName());
        }
    }
}
