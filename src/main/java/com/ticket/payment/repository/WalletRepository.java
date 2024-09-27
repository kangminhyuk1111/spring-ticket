package com.ticket.payment.repository;

import com.ticket.payment.domain.Wallet;

import java.util.Optional;

public interface WalletRepository {

    Optional<Wallet> findByMemberUserId(String userId);
}
