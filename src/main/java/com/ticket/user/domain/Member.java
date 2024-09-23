package com.ticket.user.domain;

import com.ticket.user.domain.role.MemberRole;
import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String userPw;

    @Enumerated(EnumType.STRING) // Enum을 문자열로 저장
    private MemberRole role;

    public Member() {}

    public Member(final String userId, final String userPw, final MemberRole role) {
        this.userId = userId;
        this.userPw = userPw;
        this.role = role;
    }

    public Member(String userId, String userPw) {
        this.userId = userId.trim();
        this.userPw = userPw.trim();
        this.role = MemberRole.ROLE_USER;
    }

    public boolean checkPassword(String pw) {
        return this.userPw.equals(pw);
    }

    public boolean isCommon() {
        return this.role == MemberRole.ROLE_USER;
    }

    public boolean isAdmin() {
        return this.role == MemberRole.ROLE_ADMIN;
    }

    public MemberRole getRole() {
        return role;
    }

    public void setRole(MemberRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", userPw='" + userPw + '\'' +
                ", role=" + role +
                '}';
    }
}
