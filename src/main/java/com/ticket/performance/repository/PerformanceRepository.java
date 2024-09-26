package com.ticket.performance.repository;

import com.ticket.performance.domain.Performance;

import java.util.Optional;

public interface PerformanceRepository {

    Optional<Performance> findById(Long performanceId);

    Optional<Performance> findByPerformanceName(String performanceName);

    void deleteAll();

    Performance save(Performance createPerformance);

    void deleteById(Long performanceId);
}
