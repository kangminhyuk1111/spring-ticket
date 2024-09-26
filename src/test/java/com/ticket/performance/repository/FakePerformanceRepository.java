package com.ticket.performance.repository;

import com.ticket.performance.domain.Performance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakePerformanceRepository implements PerformanceRepository{

    List<Performance> performances = new ArrayList<>();
    Long counter = 1L;

    @Override
    public Optional<Performance> findById(final Long performanceId) {
        return performances.stream()
                .filter(performance -> performance.getPerformanceId().equals(performanceId))
                .findFirst();
    }

    @Override
    public Optional<Performance> findByPerformanceName(final String performanceName) {
        return performances.stream()
                .filter(performance -> performance.getPerformanceName().equals(performanceName))
                .findFirst();
    }

    @Override
    public void deleteAll() {
        performances.clear();
    }

    @Override
    public Performance save(final Performance performance) {
        performance.setPerformanceId(counter++);
        performances.add(performance);

        return performance;
    }

    @Override
    public void deleteById(final Long performanceId) {
        performances.removeIf(performance -> performance.getPerformanceId().equals(performanceId));
    }
}
