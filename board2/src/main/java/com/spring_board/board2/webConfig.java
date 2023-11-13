//package com.spring_board.board2;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class webConfig {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable);
//        http
//                .authorizeHttpRequests(
//                        authorize -> authorize
//                                //.requestMatchers("/member/join").permitAll()
//                                .requestMatchers("/**").permitAll()
//                                .anyRequest().authenticated()
//                );
//        return http.build();
//    }
//}
