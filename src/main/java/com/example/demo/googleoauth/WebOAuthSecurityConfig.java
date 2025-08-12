package com.example.demo.googleoauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebOAuthSecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/auth/firebase-login"))
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // 정적 리소스 & 테스트 페이지는 메서드 제한 없이 허용
                        .requestMatchers("/", "/static/google.html", "/css/**", "/js/**", "/img/**").permitAll()
                        // 모든 프리플라이트 허용 (CORS 사전요청)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Firebase ID 토큰 검증 API 허용
                        .requestMatchers("/api/auth/firebase-login","/google.html").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    // 전역 CORS (컨트롤러 @CrossOrigin 없이도 동작) 백엔드랑 프론트랑 포트가 다를 때 필요.
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500")); // 허용 Origin
//        config.setAllowedMethods(List.of("GET", "POST", "OPTIONS")); // 허용 HTTP Method
//        config.setAllowedHeaders(List.of("*")); // 허용 Header
//        config.setAllowCredentials(true); // 쿠키·인증 정보 허용
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", config); // 모든 경로에 적용
//        return source;
//    }
}