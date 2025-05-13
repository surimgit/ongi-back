package com.ongi.ongi_back.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ongi.ongi_back.filter.JwtAuthenticationFilter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// class: Spring Web 보안 설정 클래스 //
// description: Bearer 인증 방식을 사용하기 위해 Basic 인증 미사용 //
// description: REST API 서버로 Session 유지하지 않음 //
// description: CORS 정책은 모든 출처 및 리소스에 대해서 허용 //
@Slf4j
@Configurable
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  // function: Web Security 설정 메서드 //
  @Bean
  protected SecurityFilterChain configure(HttpSecurity security) throws Exception {

    security
      // description: Basic 인증 미사용 지정 //
      .httpBasic(HttpBasicConfigurer::disable)
      // description: Session 유지하지 않음 지정 //
      .sessionManagement(management -> management
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      )
      // description: csrf 취약점 대비 미사용 지정 //
      .csrf(CsrfConfigurer::disable)
      // description: CORS 정책 설정 //
      .cors(cors -> cors.configurationSource(corsConfigurationSource()))
      // description: 인가 설정 //
      .authorizeHttpRequests(request -> request
        .requestMatchers("/api/v1/product/**","/api/v1/auth/**", "/api/v1/payments/**", "/api/v1/mypage/**", "/api/v1/wish", "/api/v1/wish/**").permitAll()
        .requestMatchers("/api/v1/auth", "/api/v1/auth/**").permitAll()
        .requestMatchers("/api/v1/main", "/api/v1/main/**").permitAll()
        .requestMatchers("/ws/**").permitAll()
        .requestMatchers("/api/v1/community", "/api/v1/community/**").permitAll()
        .requestMatchers("/api/v1/alert/**").authenticated()
        .requestMatchers("/api/v1/user/nickname").permitAll()
        .requestMatchers("/file/**").permitAll()
        .anyRequest().authenticated()
      )
      // description: 인증 또는 인가 실패에대한 처리 //
      .exceptionHandling(exception -> exception
        .authenticationEntryPoint(new AuthenticationFailEntryPoint())
      )
      // description: Jwt Authentication Filter 등록 //
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    
    log.debug("[+] WebConfig Start !!");

    return security.build();
  }

  // function: CORS 정책 설정 객체를 반환하는 메서드 //
  @Bean
  protected CorsConfigurationSource corsConfigurationSource() {

    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedOrigin("http://localhost:3000");
    configuration.setAllowCredentials(true); 

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;

  }
  
}

@Slf4j
class AuthenticationFailEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
    
    // 로깅: 인증 실패 시 에러 메시지와 예외를 로그 파일에 기록
    log.error("Authentication failed: {}", authException.getMessage());

    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getWriter().write("{ \"code\": \"AF\", \"message\": \"Auth Fail.\" }");

  }

}
