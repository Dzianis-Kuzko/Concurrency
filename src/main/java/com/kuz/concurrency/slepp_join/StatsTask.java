package com.kuz.concurrency.slepp_join;

import java.math.BigDecimal;
import java.util.List;

public class StatsTask implements Runnable {
    private final int fromNumber;
    private final int toNumber;
    private final List<Employee> employees;
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal sum;


    public StatsTask(int fromNumber, int toNumber, List<Employee> employees) {
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
        this.employees = employees;
    }


    @Override
    public void run() {
        BigDecimal salary = null;
        this.min = employees.get(fromNumber).getSalary();
        this.max = employees.get(fromNumber).getSalary();
        this.sum = employees.get(fromNumber).getSalary();

        for (int i = fromNumber + 1; i <= toNumber; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

             salary = employees.get(i).getSalary();

            if (salary.compareTo(min) < 0) {
                min = employees.get(i).getSalary();
            }
            if (salary.compareTo(max) > 0) {
                max = salary;
            }
            sum = sum.add(salary);
        }
    }

    public BigDecimal getMin() {
        return min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public BigDecimal getSum() {
        return sum;
    }
}
