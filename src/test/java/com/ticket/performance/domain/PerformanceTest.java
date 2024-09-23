package com.ticket.performance.domain;

import com.ticket.performance.repository.PerformanceRepository;
import com.ticket.performance.service.PerformanceService;
import com.ticket.token.repository.TokenRepository;
import com.ticket.user.domain.Member;
import com.ticket.user.domain.role.MemberRole;
import com.ticket.user.repository.MemberRepository;
import com.ticket.user.service.MemberService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PerformanceTest {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private MemberService memberService;

    @MockBean
    private MemberRepository memberRepository;

    @MockBean
    private PerformanceRepository performanceRepository;

    @MockBean
    private TokenRepository tokenRepository;

    @BeforeEach
    public void beforeEach() {
        final String userId = "test";
        final String userPw = "1234";
        final MemberRole userRole = MemberRole.ROLE_ADMIN;

        when(memberRepository.findByUserId(userId)).thenReturn(Optional.of(new Member(userId, userPw, userRole)));
    }

    @AfterEach
    public void afterEach() {
        memberRepository.deleteAll();
        performanceRepository.deleteAll();
        tokenRepository.deleteAll();
    }

    @Test
    @DisplayName("공연 생성 테스트")
    public void create_performance() {
        final Optional<Member> test = memberRepository.findByUserId("test");

        System.out.println("+++++++++++++++++++++++" + test.toString());

        String performanceName = "공연1";
        Integer maxAcceptPeople = 100;
    }
}