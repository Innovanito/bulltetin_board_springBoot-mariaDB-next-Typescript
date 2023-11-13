package com.spring_board.board2.service;

import com.spring_board.board2.dto.MemberDto;
import com.spring_board.board2.entity.Member;
import com.spring_board.board2.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;


import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MemberService {


    @Autowired
    private MemberRepository memberRepository;

    public void createNewMember(Member member) {
        System.out.println("member value in createNewMeber" + member);

        memberRepository.save(member);
    }

    public String getTokenValueIfIdAndPasswordExist(String inputId, String inputPassword) {
        // Member 엔티티에서 memberId와 password를 기준으로 검색
        Member member = memberRepository.findByMemberIdAndPassword(inputId, inputPassword);

        if (member != null) {
            // 멤버가 존재하면 해당 멤버의 tokenValue를 반환
            return member.getTokenValue();
        } else {
            // 멤버가 존재하지 않으면 null을 반환
            return null;
        }
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<MemberDto> getAllMembersAsDtos() {
        List<Member> members = memberRepository.findAll();

        // Member 엔티티를 MemberDto로 변환
        List<MemberDto> memberDtos = members.stream()
                .map(member -> new MemberDto(
                        member.getId(),
                        member.getMemberId(),
                        member.getPassword(),
                        member.getTokenValue()
                ))
                .collect(Collectors.toList());

        return memberDtos;
    }

}
