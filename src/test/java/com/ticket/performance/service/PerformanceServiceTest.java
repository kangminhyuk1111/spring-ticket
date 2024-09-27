package com.ticket.performance.service;

import com.ticket.payment.api.FakePaymentApi;
import com.ticket.payment.api.PaymentApi;
import com.ticket.payment.service.PaymentService;
import com.ticket.performance.domain.Performance;
import com.ticket.performance.repository.FakePerformanceRepository;
import com.ticket.performance.repository.PerformanceRepository;
import com.ticket.ticket.domain.Ticket;
import com.ticket.ticket.repository.FakeTicketRepository;
import com.ticket.ticket.repository.TicketRepository;
import com.ticket.token.provider.FakeTokenProvider;
import com.ticket.token.repository.FakeTokenRepository;
import com.ticket.token.provider.TokenProvider;
import com.ticket.token.repository.TokenRepository;
import com.ticket.user.domain.Member;
import com.ticket.user.domain.role.MemberRole;
import com.ticket.user.repository.FakeMemberRepository;
import com.ticket.user.repository.MemberRepository;
import com.ticket.user.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PerformanceServiceTest {

    private MemberRepository memberRepository = new FakeMemberRepository();
    private PerformanceRepository performanceRepository = new FakePerformanceRepository();
    private TicketRepository ticketRepository = new FakeTicketRepository();
    private TokenRepository tokenRepository = new FakeTokenRepository();
    private TokenProvider tokenProvider = new FakeTokenProvider();
    private PaymentApi paymentApi = new FakePaymentApi();

    private PaymentService paymentService = new PaymentService(paymentApi);
    private PerformanceService performanceService = new PerformanceService(performanceRepository, memberRepository, ticketRepository, paymentService);
    private MemberService memberService = new MemberService(memberRepository, tokenRepository, tokenProvider);

    @Test
    @DisplayName("공연 생성 테스트")
    public void create_performance() {
        final String userId = "test";
        final String userPw = "1234";
        final String performanceName = "공연1";
        final Integer maxAcceptPeople = 100;
        final Long ticketPrice = 10000L;

        memberRepository.save(new Member(userId, userPw));
        memberService.changeMemberRole(userId, MemberRole.ROLE_ADMIN);

        performanceService.createPerformance(userId, performanceName, maxAcceptPeople, ticketPrice);

        final Optional<Performance> byPerformanceName = performanceRepository.findByPerformanceName(performanceName);

        assertThat(byPerformanceName).isNotEmpty();
    }

    @Test
    @DisplayName("공연 생성 테스트 - 유저가 admin이 아니라면 실패")
    public void create_performance_fail_user_not_admin() {
        final String userId = "test";
        final String userPw = "1234";
        final String performanceName = "공연1";
        final Integer maxAcceptPeople = 100;
        final Long ticketPrice = 10000L;

        memberRepository.save(new Member(userId, userPw));

        assertThrows(RuntimeException.class, () -> performanceService.createPerformance(userId, performanceName, maxAcceptPeople, ticketPrice));
    }

    @Test
    @DisplayName("공연 삭제 테스트")
    public void delete_performance() {
        final Long performanceId = 1L;
        final String userId = "test";
        final String userPw = "1234";
        final String performanceName = "공연1";
        final Integer maxAcceptPeople = 100;
        final Long ticketPrice = 10000L;

        memberRepository.save(new Member(userId, userPw));
        memberService.changeMemberRole(userId, MemberRole.ROLE_ADMIN);

        performanceService.createPerformance(userId, performanceName, maxAcceptPeople, ticketPrice);

        performanceService.deletePerformance(userId, performanceId);

        final Optional<Performance> byPerformanceNameAfterDelete = performanceRepository.findByPerformanceName(performanceName);

        assertThat(byPerformanceNameAfterDelete).isEmpty();
    }

    @Test
    @DisplayName("공연 수정 테스트")
    public void update_performance() {
        final Long performanceId = 1L;
        final String userId = "test";
        final String userPw = "1234";
        final String performanceName = "공연1";
        final Integer maxAcceptPeople = 100;
        final Long ticketPrice = 10000L;

        final String updatePerformanceName = "수정된 공연1";

        memberRepository.save(new Member(userId, userPw));
        memberService.changeMemberRole(userId, MemberRole.ROLE_ADMIN);

        performanceService.createPerformance(userId, performanceName, maxAcceptPeople, ticketPrice);

        performanceService.updatePerformance(userId, performanceId, new Performance(updatePerformanceName, maxAcceptPeople, ticketPrice));

        final Optional<Performance> byPerformanceName = performanceRepository.findByPerformanceName(updatePerformanceName);

        assertThat(byPerformanceName.get().getPerformanceName()).isEqualTo(updatePerformanceName);
    }

    @Test
    @DisplayName("공연 티켓 구매 테스트")
    public void pay_for_ticket() {
        final Long performanceId = 1L;
        final String userId = "test";
        final String userPw = "1234";
        final String performanceName = "공연1";
        final Integer maxAcceptPeople = 100;
        final Long ticketPrice = 10000L;

        memberRepository.save(new Member(userId, userPw));
        memberService.changeMemberRole(userId, MemberRole.ROLE_ADMIN);

        performanceService.createPerformance(userId, performanceName, maxAcceptPeople, ticketPrice);

        performanceService.payForTicket(userId, "1234-1234-1234-1234", performanceId, ticketPrice);

        final Ticket ticket = ticketRepository.findByMemberUserId(userId).orElseThrow(() -> new RuntimeException("티켓 정보가 없습니다."));

        assertThat(ticket.getId()).isEqualTo(1L);
        assertThat(ticket.getMember().getUserId()).isEqualTo(userId);
    }
}