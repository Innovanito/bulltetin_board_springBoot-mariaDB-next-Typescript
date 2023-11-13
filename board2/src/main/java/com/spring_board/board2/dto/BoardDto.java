package com.spring_board.board2.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class BoardDto {
    @Id
    private Integer id;
    private String title;
    private String content;
    private String filename;
    private String filepath;

}
