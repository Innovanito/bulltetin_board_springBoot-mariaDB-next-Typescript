package com.spring_board.board2.service;

import com.spring_board.board2.entity.Member;
import com.spring_board.board2.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Service

public class LoginService {
    private static final String SECRET_KEY = "askdlfjlk32jlkjkljlkajsdkljaslkdj12";

    public String createToken(String subject, long expTime) {
        if (expTime <= 0) {
            throw new RuntimeException("만료시간 0보다 커야함;");
        }

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        Key signingKey = Keys.secretKeyFor(signatureAlgorithm); // 크기가 충분한 비밀 키 생성

        String jwtToken = Jwts.builder()
                .setSubject(subject)
                .signWith(signingKey, signatureAlgorithm)
                .setExpiration(new Date(System.currentTimeMillis() + expTime))
                .compact();

        System.out.println(jwtToken);
        return jwtToken;
    }


}
