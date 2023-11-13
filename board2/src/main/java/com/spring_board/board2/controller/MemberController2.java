package com.spring_board.board2.controller;

import com.spring_board.board2.dto.BoardDto;
import com.spring_board.board2.dto.MemberDto;
import com.spring_board.board2.entity.Board;
import com.spring_board.board2.entity.Member;
import com.spring_board.board2.repository.BoardRepository;
import com.spring_board.board2.repository.MemberRepository;
import com.spring_board.board2.service.BoardService;
import com.spring_board.board2.service.LoginService;
import com.spring_board.board2.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CookieValue;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
public class MemberController2 {

    @Autowired
    private MemberService memberService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private BoardService boardService;


    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/fetchMembers")
    public List<MemberDto> fetchAllMembers() {
        List<MemberDto> memberDtos = memberService.getAllMembersAsDtos();
        return memberDtos;
    }

//    getExistingMemberIds
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getExistingMemberIds")
    public List<MemberDto> fetchAllMemberIds() {
        List<MemberDto> memberDtos = memberService.getAllMembersAsDtos();
        return memberDtos;
    }

    @Autowired
    private MemberRepository memberRepository; // Inject the repository

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/memberCreate2")
    public String createMember(@RequestBody MemberDto memberDto) {
        String inputMemberId = memberDto.getMemberId();

        // Check if the memberId already exists in the database
        Optional<Member> existingMember = memberRepository.findByMemberId(inputMemberId);

        if (existingMember.isPresent()) {
            // Member with the same ID already exists, send a response indicating it
            return "Member with the same ID already exists.";
        } else {
            // Member is unique, you can create it
            Member newMember = new Member();
            newMember.setMemberId(inputMemberId);
            newMember.setPassword(memberDto.getPassword());

            memberRepository.save(newMember);

            // Send a success response
            return "New Member created successfully.";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/LoginProcess2")
    public String login2(@RequestBody MemberDto memberDto, HttpServletResponse response) {
        try {
            String inputMemberId = memberDto.getMemberId();
            String inputPassword = memberDto.getPassword();

            String memberToken = loginService.createToken(inputMemberId, 60 * 1000);

            Optional<Member> existingMember = memberRepository.findByMemberId(inputMemberId);

            if (existingMember.isPresent()) {
                Member member = existingMember.get();
                if (inputPassword.equals(member.getPassword())) {
                    memberDto.setTokenValue(memberToken);
                    // Update the tokenValue into the member table
                    member.setTokenValue(memberToken);
                    memberRepository.save(member);

                    return memberToken;
                } else {
                    return "Incorrect password";
                }
            } else {
                return "Member not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during login";
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    @Autowired
    private BoardRepository boardRepository;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getBoardById/{id}")
    public ResponseEntity<BoardDto> getBoardById(@PathVariable Integer id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);

        return optionalBoard.map(board -> ResponseEntity.ok().body(mapBoardToBoardDto(board)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private BoardDto mapBoardToBoardDto(Board board) {
        BoardDto boardDto = new BoardDto();
        boardDto.setId(board.getId());
        boardDto.setTitle(board.getTitle());
        boardDto.setContent(board.getContent());
        return boardDto;
    }


}

