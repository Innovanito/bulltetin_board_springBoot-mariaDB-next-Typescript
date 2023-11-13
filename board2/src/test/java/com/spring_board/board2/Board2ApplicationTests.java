package com.spring_board.board2;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@SpringBootTest
class Board2ApplicationTests {
//	private static final String SECRET_KEY = "askdlfjlk32jlkjkljlkajsdkljaslkdj12";
//
//
//	@Test
//	void contextLoads() {
//		System.out.println(createToken("kang2", 3600000));
//	}
//
//	public String createToken(String subject, long expTime) {
//		if (expTime <= 0) {
//			throw new RuntimeException("만료시간 0보다 커야함;");
//		}
//
//		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//		Key signingKey = Keys.secretKeyFor(signatureAlgorithm); // 크기가 충분한 비밀 키 생성
//
//		String jwtToken = Jwts.builder()
//							.setSubject(subject)
//							.signWith(signingKey, signatureAlgorithm)
//							.setExpiration(new Date(System.currentTimeMillis() + expTime))
//							.compact();
//
//		System.out.println(jwtToken);
//		return jwtToken;
//	}
//


}
