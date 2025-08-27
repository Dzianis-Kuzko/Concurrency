package com.kuz.concurrency.slepp_join;

public class EmployeeTest {
    public static void main(String[] args) throws InterruptedException {

        StatsService statsService = new StatsService();

        StatsDTO statsDTO = statsService.calculate(Util.createEmployees(1, 1_000_000));

        System.out.println(statsDTO);

        Thread.sleep(300000);

    }
}
