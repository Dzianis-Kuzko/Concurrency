package com.kuz.concurrency.thread_state;

public class Runner {
    private static final String MESSAGE_TEMPLATE_THREAD_STATE = "%s : %s\n";

    public static void main(String[] args) throws InterruptedException {

        Thread mainThread = Thread.currentThread();

        Thread thread = new Thread(() -> {
            try {

                mainThread.join();
                showThreadState(Thread.currentThread());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

        showThreadState(thread);

    }

    private static void showThreadState(Thread thread) {
        System.out.printf(String.format(MESSAGE_TEMPLATE_THREAD_STATE, thread.getName(), thread.getState()));
    }
}
