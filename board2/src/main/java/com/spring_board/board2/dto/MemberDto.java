package com.spring_board.board2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private int id;
    private String memberId;
    private String password;
    private String tokenValue;

    public MemberDto(int id, String memberId, String password, String tokenValue) {
        this.id = id;
        this.memberId = memberId;
        this.password = password;
        this.tokenValue = tokenValue;
    }

}
