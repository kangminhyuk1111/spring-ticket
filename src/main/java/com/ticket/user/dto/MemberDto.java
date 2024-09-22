package com.ticket.user.dto;

public class MemberDto {

    private final String userId;
    private final String userPw;

    public MemberDto(String userId, String userPw) {
        this.userId = userId;
        this.userPw = userPw;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPw() {
        return userPw;
    }
}
