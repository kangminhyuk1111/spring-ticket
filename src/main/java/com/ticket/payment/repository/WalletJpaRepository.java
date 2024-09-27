package com.ticket.payment.repository;

import com.ticket.payment.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletJpaRepository extends JpaRepository<Wallet, Long>, WalletRepository {
}
