package com.spring_board.board2.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;


//@Builder 사용시 @NoArgsConstructor와 @AllArgsConstructor 써야 함.

@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String memberId;
    private String password;
    private String loginPwConfirm;
    private String tokenValue;
}
