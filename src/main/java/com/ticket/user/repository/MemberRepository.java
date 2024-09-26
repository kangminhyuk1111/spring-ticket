package com.ticket.user.repository;

import com.ticket.user.domain.Member;
import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByUserId(String userId);

    Member save(Member member);

    void deleteAll();
}
