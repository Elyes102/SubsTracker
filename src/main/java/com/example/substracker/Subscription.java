package com.example.substracker;

import java.time.LocalDate;

public class Subscription {
    String name;
    String frequency;
    Double pricePerMonth;
    Double totalPayed = 0.0;
    LocalDate startDate;
    LocalDate nextPaymentDate;
    int daysRemaining;

    public Subscription() {}


    public String getName() { return name;}

    public void setName(String name) {this.name = name;}

    public String getFrequency() {return frequency;}

    public void setFrequency(String frequency) {this.frequency = frequency;}

    public Double getPricePerMonth() {return pricePerMonth;}

    public void setPricePerMonth(Double pricePerMonth) {this.pricePerMonth = pricePerMonth;}

    public Double getTotalPayed() {return totalPayed;}

    public void setTotalPayed(Double totalPayed) {this.totalPayed = totalPayed;}

    public LocalDate getStartDate() {return startDate;}

    public void setStartDate(LocalDate startDate) {this.startDate = startDate;}

    public LocalDate getNextPaymentDate() {return nextPaymentDate;}

    public void setNextPaymentDate(LocalDate nextPaymentDate) {this.nextPaymentDate = nextPaymentDate;}

    public int getDaysRemaining() {return daysRemaining;}

    public void setDaysRemaining() {
        daysRemaining = nextPaymentDate.getDayOfMonth() - LocalDate.now().getDayOfMonth();
    }

}
