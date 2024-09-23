package com.ticket.performance.service;

import com.ticket.exception.member.NotFoundMemberException;
import com.ticket.exception.member.UnauthorizedAccessException;
import com.ticket.exception.performance.AlreadyExistPerformanceException;
import com.ticket.exception.performance.NotFoundPerformanceException;
import com.ticket.exception.token.InvalidTokenException;
import com.ticket.performance.domain.Performance;
import com.ticket.performance.repository.PerformanceRepository;
import com.ticket.token.domain.Token;
import com.ticket.token.repository.TokenRepository;
import com.ticket.user.domain.Member;
import com.ticket.user.domain.role.MemberRole;
import com.ticket.user.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PerformanceService {

    private final PerformanceRepository performanceRepository;
    private final MemberRepository memberRepository;
    private final TokenRepository tokenRepository;

    public PerformanceService(PerformanceRepository performanceRepository, MemberRepository memberRepository, TokenRepository tokenRepository) {
        this.performanceRepository = performanceRepository;
        this.memberRepository = memberRepository;
        this.tokenRepository = tokenRepository;
    }

    public void createPerformance(final String userId, final String performanceName, final Integer maxAcceptPeople, final Long ticketPrice) {

        // Roll admin 체크
        checkAdminRole(userId);

        if(performanceRepository.findByPerformanceName(performanceName).isPresent()) {
            throw new AlreadyExistPerformanceException();
        }

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

    // 공연 여부 확인, 유저 확인(토큰이 맞는지), userId에 맞는 지갑 가져오기,
    public void payForTicket(final String userId, final Long performanceId, final Token token, final Long ticketPrice) {
        Performance performance = performanceRepository.findById(performanceId).orElseThrow(NotFoundPerformanceException::new);
        Member member = memberRepository.findByUserId(userId).orElseThrow(NotFoundMemberException::new);
        Token findTokenByUserId = tokenRepository.findByUserId(userId).orElseThrow(InvalidTokenException::new);
    }

    private void checkAdminRole(String userId) {
        Member member = memberRepository.findByUserId(userId).orElseThrow(NotFoundPerformanceException::new);

        // admin이 아니라면
        if (!member.isAdmin()) {
            throw new UnauthorizedAccessException();
        }
    }
}
