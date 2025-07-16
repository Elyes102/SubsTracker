package com.example.substracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String frequency;
    private Double pricePerMonth;
    private Double totalPayed = 0.0;
    private LocalDate startDate;
    private LocalDate nextPaymentDate;
    private int daysRemaining;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }


}
