package com.ticket.performance.repository;

import com.ticket.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    @Override
    Optional<Performance> findById(Long performanceId);

    Optional<Performance> findByPerformanceName(String performanceName);
}
