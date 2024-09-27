package com.ticket.performance.service;

import com.ticket.exception.member.NotFoundMemberException;
import com.ticket.exception.member.UnauthorizedAccessException;
import com.ticket.exception.performance.AlreadyExistPerformanceException;
import com.ticket.exception.performance.NotFoundPerformanceException;
import com.ticket.payment.service.PaymentService;
import com.ticket.ticket.domain.Ticket;
import com.ticket.performance.domain.Performance;
import com.ticket.performance.repository.PerformanceRepository;
import com.ticket.ticket.repository.TicketRepository;
import com.ticket.token.repository.TokenRepository;
import com.ticket.user.domain.Member;
import com.ticket.user.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final MemberRepository memberRepository;
    private final TicketRepository ticketRepository;
    private final PaymentService paymentService;

    public PerformanceService(PerformanceRepository performanceRepository, MemberRepository memberRepository, final TicketRepository ticketRepository, final PaymentService paymentService) {
        this.performanceRepository = performanceRepository;
        this.memberRepository = memberRepository;
        this.ticketRepository = ticketRepository;
        this.paymentService = paymentService;
    }

    public void createPerformance(final String userId, final String performanceName, final Integer maxAcceptPeople, final Long ticketPrice) {

        // Roll admin 체크
        checkAdminRole(userId);

        validatePerformanceName(performanceName);

        final Performance createPerformance = new Performance(performanceName, maxAcceptPeople, ticketPrice);

        performanceRepository.save(createPerformance);
    }

    public void deletePerformance(final String userId, final Long performanceId) {

        // Roll admin 체크
        checkAdminRole(userId);

        performanceRepository.deleteById(performanceId);
    }

    public void updatePerformance(final String userId, final Long performanceId, final Performance updatePerformance) {

        // Roll admin 체크
        checkAdminRole(userId);

        Performance performance = performanceRepository.findById(performanceId).orElseThrow(NotFoundPerformanceException::new);

        Performance newPerformance = new Performance(
                performance.getPerformanceId(), // 기존 ID 유지
                updatePerformance.getPerformanceName(), // 업데이트된 이름
                updatePerformance.getMaxAcceptPeople(),  // 업데이트된 최대 수용 인원
                updatePerformance.getTicketPrice()
        );

        performanceRepository.save(newPerformance);
    }

    @Transactional
    public void payForTicket(final String userId, final String cardNumber, final Long performanceId, final Long ticketPrice) {
        Performance performance = performanceRepository.findById(performanceId).orElseThrow(NotFoundPerformanceException::new);
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new NotFoundMemberException("존재하지 않는 유저 입니다."));

        // 토큰 검증

        // 결제는 반드시 카드로
        // 결제 api 호출
        paymentService.processPayment(cardNumber, ticketPrice);

        // 티켓 생성
        final Ticket ticket = new Ticket(performance, member, ticketPrice, cardNumber);

        // 티켓 저장
        ticketRepository.save(ticket);
    }

    private void checkAdminRole(String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow(NotFoundPerformanceException::new);

        // admin이 아니라면
        if (!member.isAdmin()) {
            throw new UnauthorizedAccessException();
        }
    }

    private void validatePerformanceName(final String performanceName) {
        if (performanceRepository.findByPerformanceName(performanceName).isPresent()) {
            throw new AlreadyExistPerformanceException();
        }
    }
}
