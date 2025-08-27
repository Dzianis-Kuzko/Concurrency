package com.kuz.concurrency.slepp_join;

import java.math.BigDecimal;

public class StatsDTO {
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal sum;
    private BigDecimal avg;

    public StatsDTO() {
    }

    public StatsDTO(BigDecimal min, BigDecimal max, BigDecimal sum, BigDecimal avg) {
        this.min = min;
        this.max = max;
        this.sum = sum;
        this.avg = avg;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "StatsDTO{" +
                "\nmin=" + min +
                "\nmax=" + max +
                "\nsum=" + sum +
                ",\navg=" + avg +
                '}';
    }

}
