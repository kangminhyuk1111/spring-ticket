package com.ticket.wallet.repository;

import com.ticket.payment.domain.Wallet;
import com.ticket.payment.repository.WalletRepository;

import java.util.Optional;

public class FakeWalletRepository implements WalletRepository {

    @Override
    public Optional<Wallet> findByMemberUserId(final String userId) {
        return Optional.empty();
    }
}
