package com.kuz.concurrency.interrupt;

import java.util.concurrent.TimeUnit;

public class Runner {
    private static final String MESSAGE_REQUEST_WAS_SENT = "\nRequest was sent.";
    private static final int DURATION_IN_SECONDS_RESPONSE = 1;
    private static final String MASSAGE_RESPONSE_WAS_RECEIVED = "\nResponse was received.";
    private static final String MESSAGE_SERVER_WAS_STOPPED = "\nServer was stopped.";
    private static final String MASSAGE_THREAD_WAS_INTERRUPTED = "\nThread was interrupted.";

    public static void main(String[] args) throws InterruptedException {
        Thread communicatingThread = new Thread(() -> {
            try {

                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().isInterrupted());
                    doRequest();
                    Thread.interrupted();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(MASSAGE_THREAD_WAS_INTERRUPTED);
            }

        });

        communicatingThread.start();

        Thread stoppingThread = new Thread(() -> {
            if (isServerShouldBeOffed()) {
                communicatingThread.interrupt();
                stopServer();
            }
        });

        TimeUnit.SECONDS.sleep(3);

        stoppingThread.start();

    }


    public static void doRequest() throws InterruptedException {
        System.out.println(MESSAGE_REQUEST_WAS_SENT);
        TimeUnit.SECONDS.sleep(1);
        System.out.println(MASSAGE_RESPONSE_WAS_RECEIVED);
    }

    private static boolean isServerShouldBeOffed() {
        return true;
    }

    private static void stopServer() {
        System.out.println(MESSAGE_SERVER_WAS_STOPPED);
    }
}
