package com.ticket.performance.repository;

import com.ticket.performance.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceJpaRepository extends JpaRepository<Performance,Long>, PerformanceRepository{
}
