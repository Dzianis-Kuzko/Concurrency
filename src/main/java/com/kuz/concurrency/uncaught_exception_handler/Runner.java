package com.kuz.concurrency.uncaught_exception_handler;

public class Runner {
    private static final String MESSAGE_EXCEPTION_TEMPLATE = "Exception was thrown with message '%s' in thread '%s'.\n";

    public static void main(String[] args) throws InterruptedException {

        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = (thread, exception)
                -> {
            System.out.printf(MESSAGE_EXCEPTION_TEMPLATE, exception.getMessage(), thread.getName());
        };

        Thread.setDefaultUncaughtExceptionHandler(uncaughtExceptionHandler);
        Thread thread = new Thread(new Task());
        thread.start();

        Thread firstThread = new Thread(new Task());
        firstThread.start();

        Thread secondThread = new Thread(new Task());
        secondThread.start();


    }

    private static final class Task implements Runnable {
        private static final String EXCEPTION_MESSAGE = "I am exception";

        @Override
        public void run() {
            throw new RuntimeException(EXCEPTION_MESSAGE);
        }
    }
}
