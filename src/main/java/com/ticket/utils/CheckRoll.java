package com.ticket.utils;

import com.ticket.user.domain.Member;
import com.ticket.user.domain.role.MemberRole;
import com.ticket.user.repository.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CheckRoll {

    private final MemberRepository memberRepository;

    public CheckRoll(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean CheckRollAdmin(final String userId) {
        Optional<Member> byUserId = memberRepository.findByUserId(userId);

        if (byUserId.isEmpty()) {
            throw new RuntimeException("존재하지 않는 유저ID 입니다.");
        }

        return byUserId.get().getRole().equals(MemberRole.ROLE_ADMIN);
    }
}
