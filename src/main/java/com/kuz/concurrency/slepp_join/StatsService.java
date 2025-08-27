package com.kuz.concurrency.slepp_join;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class StatsService {
    public StatsDTO calculate(List<Employee> employees) throws InterruptedException {

        int size = employees.size();

        int fromNumberFirstThread = 0;
        int toNumberFirstThread = size / 2 - 1;
        int fromNumberSecondThread = size / 2;
        int toNumberSecondThread = size - 1;

        StatsTask statsTask1 = new StatsTask(fromNumberFirstThread, toNumberFirstThread, employees);
        StatsTask statsTask2 = new StatsTask(fromNumberSecondThread, toNumberSecondThread, employees);

        Thread thread1 = new Thread(statsTask1);
        Thread thread2 = new Thread(statsTask2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        BigDecimal min = calculateMin(statsTask1, statsTask2);
        BigDecimal max = calculateMax(statsTask1, statsTask2);
        BigDecimal sum = calculateSum(statsTask1, statsTask2);
        BigDecimal avg = calculateAvg(sum, employees.size());


        return new StatsDTO(min, max, sum, avg);

    }

    private BigDecimal calculateMin(StatsTask statsTask1, StatsTask statsTask2) {

        if (statsTask1.getMin().compareTo(statsTask2.getMin()) < 0) {
            return statsTask1.getMin();
        }

        return statsTask2.getMin();
    }

    private BigDecimal calculateMax(StatsTask statsTask1, StatsTask statsTask2) {
        if (statsTask1.getMax().compareTo(statsTask2.getMax()) > 0) {
            return statsTask1.getMax();
        }
        return statsTask2.getMax();
    }

    private BigDecimal calculateSum(StatsTask statsTask1, StatsTask statsTask2) {
        return statsTask1.getSum().add(statsTask2.getSum());
    }

    private BigDecimal calculateAvg(BigDecimal sum, int count) {
        return sum.divide(new BigDecimal(count), 2, RoundingMode.HALF_UP);
    }


}
