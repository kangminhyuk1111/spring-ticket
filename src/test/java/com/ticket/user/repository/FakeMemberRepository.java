package com.ticket.user.repository;

import com.ticket.user.domain.Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FakeMemberRepository implements MemberRepository {

    List<Member> members = new ArrayList<>();

    @Override
    public Optional<Member> findByUserId(final String userId) {
        return members.stream()
                .filter(member -> member.getUserId().equals(userId))
                .findFirst();
    }

    @Override
    public Member save(final Member member) {
        members.add(member);

        return member;
    }

    @Override
    public void deleteAll() {
        members.clear();
    }
}
