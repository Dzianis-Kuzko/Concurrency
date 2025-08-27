package com.kuz.concurrency.slepp_join;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public final class Util {
    private Util() {
        throw new UnsupportedOperationException("You can't instantiate me...");
    }

    public static List<Employee> createEmployees(int start, int end) {
        List<Employee> employees = new ArrayList<>();

        IntStream.rangeClosed(start, end)
                .forEach(i -> {
                    Employee employee = new Employee();
                    employee.setId(i);
                    employee.setName("Employee " + i);
                    employee.setSalary(new BigDecimal(i));
                    employees.add(employee);
                });
        return employees;
    }
}
