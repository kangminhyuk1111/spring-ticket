package com.ticket.user.domain;

import com.ticket.user.domain.role.MemberRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    private String userId;
    private String userPw;

    @Enumerated(EnumType.STRING) // Enum을 문자열로 저장
    private MemberRole role;

    public Member() {}

    public Member(String userId, String userPw) {
        this.userId = userId.trim();
        this.userPw = userPw.trim();
        this.role = MemberRole.ROLE_USER;
    }

    public boolean checkPassword(String pw) {
        return this.userPw.equals(pw);
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }
}
