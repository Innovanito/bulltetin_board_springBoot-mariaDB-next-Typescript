package com.spring_board.board2.repository;

import com.spring_board.board2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findByMemberIdAndPassword(String memberId, String password);
    Optional<Member> findByMemberId(String memberId);

}
