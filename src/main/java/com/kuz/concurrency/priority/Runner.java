package com.kuz.concurrency.priority;

import java.util.stream.IntStream;

public class Runner {
    private static final String MASSAGE_TEMPLATE_THREAD_NAME = "\n%s : %d\n";
    private static final String MASSAGE_TEMPLATE_THREAD_FINISHED = "Main thread is finished.";

    public static void main(String[] args) {


//        Thread thread = new Thread(() -> printNamePriority(Thread.currentThread()));
//        thread.start();
//
//        printNamePriority(Thread.currentThread());

        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        Thread thread1 = new Thread(new Task());
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        System.out.println(MASSAGE_TEMPLATE_THREAD_FINISHED);

    }

    private static void printNamePriority(Thread thread) {
        System.out.printf(MASSAGE_TEMPLATE_THREAD_NAME, thread.getName(), thread.getPriority());
    }

    private static class Task implements Runnable {
        private static final int RANGE_MINIMAL_BORDER = 0;
        private static final int RANGE_MAXIMAL_BORDER = 100;

        @Override
        public void run() {
            IntStream.rangeClosed(RANGE_MINIMAL_BORDER, RANGE_MAXIMAL_BORDER).forEach(System.out::println);
        }
    }
}
