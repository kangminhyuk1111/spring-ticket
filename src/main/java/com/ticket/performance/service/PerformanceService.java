package com.ticket.performance.service;

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
            throw new RuntimeException("이미 존재하는 공연정보 입니다.");
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

        Performance performance = performanceRepository.findById(performanceId).orElseThrow(() -> new RuntimeException("존재하지 않는 공연정보 입니다."));

        Performance newPerformance = new Performance(
                performance.getPerformanceId(), // 기존 ID 유지
                updatePerformance.getPerformanceName(), // 업데이트된 이름
                updatePerformance.getMaxAcceptPeople(),  // 업데이트된 최대 수용 인원
                updatePerformance.getTicketPrice()
        );

        performanceRepository.save(newPerformance);
    }

    public void payForTicket(final String userId, final Long performanceId, final Long ticketPrice) {
        Performance performance = performanceRepository.findById(performanceId).orElseThrow(() -> new RuntimeException("존재하지 않는 공연정보 입니다."));
        Member member = memberRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("존재하지 않는 유저ID 입니다."));
        Token findTokenByUserId = tokenRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("토큰 정보가 일치하지 않습니다."));
    }

    private void checkAdminRole(String userId) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저ID입니다."));

        if (!member.getRole().equals(MemberRole.ROLE_ADMIN)) {
            throw new RuntimeException("권한이 없습니다.");
        }
    }
}
