package com.spring_board.board2.controller;

import com.spring_board.board2.dto.BoardDto;
import com.spring_board.board2.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardController2 {
    @Autowired
    private BoardService boardService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getBoardInfo/{id}")
    public ResponseEntity<BoardDto> getBoardInfo(@PathVariable int id) {
        try {
            BoardDto boardDto = boardService.getBoardInfo(id);
            return new ResponseEntity<>(boardDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/updateBoard/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") int id, @RequestBody BoardDto updatedBoardDto) {
        try {
            boardService.updateBoard(id, updatedBoardDto);
            return ResponseEntity.ok("게시글이 성공적으로 수정되었습니다.");
        } catch (BoardService.BoardNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/deleteBoard/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") int id) {
        try {
            boardService.deleteBoard(id);
            return ResponseEntity.ok("The post has been successfully deleted.");
        } catch (BoardService.BoardNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/createBoard")
    public ResponseEntity<String> createBoard(@RequestBody BoardDto newBoardDto) {
        try {
            boardService.createBoard(newBoardDto);
            return ResponseEntity.ok("The post has been successfully created.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error creating the post: " + e.getMessage());
        }
    }

}
