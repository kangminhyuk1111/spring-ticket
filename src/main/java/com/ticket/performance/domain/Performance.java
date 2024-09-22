package com.ticket.performance.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Performance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long performanceId;

    private String performanceName;
    private Integer maxAcceptPeople;
    private Long ticketPrice;

    public Performance(Long performanceId, String performanceName, Integer maxAcceptPeople, Long ticketPrice) {
        this.performanceId = performanceId;
        this.performanceName = performanceName;
        this.maxAcceptPeople = maxAcceptPeople;
        this.ticketPrice = ticketPrice;
    }

    public Performance(String performanceName, Integer maxAcceptPeople, Long ticketPrice) {
        this.performanceName = performanceName;
        this.maxAcceptPeople = maxAcceptPeople;
        this.ticketPrice = ticketPrice;
    }

    public Performance() {}

    public Long getPerformanceId() {
        return performanceId;
    }

    public String getPerformanceName() {
        return performanceName;
    }

    public Integer getMaxAcceptPeople() {
        return maxAcceptPeople;
    }

    public Long getTicketPrice() {
        return ticketPrice;
    }
}
