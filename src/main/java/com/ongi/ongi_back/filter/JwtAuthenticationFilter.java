package com.ongi.ongi_back.filter;

import java.io.IOException;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ongi.ongi_back.provider.JwtProvider;
import com.ongi.ongi_back.repository.UserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// class: Request에서 Bearer Token 인증 처리를 위한 필터 //
// description: 필터 처리로 인증이 완료되면 접근 주체의 값에는 userId가 주입 //
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        try{
            String token = getToken(request);
            if(token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String userId = jwtProvider.validate(token);
            if(userId == null){
                filterChain.doFilter(request, response);
                return;
            }

            boolean existUser = userRepository.existsByUserId(userId);
            if(!existUser) {
                filterChain.doFilter(request, response);
                return;
            }
            
            setContext(userId, request);
        }catch (ExpiredJwtException e) {
            log.error("JWT expired at {}. Current time: {}. Error: {}",
                        e.getClaims().getExpiration(),
                        System.currentTimeMillis(),
                        e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token expired");
            return; 
        } catch (JwtException e) {
            log.error("JWT validation failed. Error: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT validation failed");
            return;  
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
            return; 
        }

        filterChain.doFilter(request, response);
    }
    
    // function: Request 객체에서 Token 추출 메서드 //
    private String getToken(HttpServletRequest request){

        String authorization = request.getHeader("Authorization");
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith(authorization);
        if(!isBearer) return null;

        String token = authorization.substring(7);
        return token;
    }

    // function: Security Context 생성 및 등록 //
    private void setContext(String userId, HttpServletRequest request){

        // description: 접근 주체의 정보가 담길 인증 토큰 생성 //
        AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, null, AuthorityUtils.NO_AUTHORITIES);

        // description: 생성한 인증 토큰이 어떤 요청의 정보인지 상세 내역 추가 //
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // description: 빈 Security Context 생성 //
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

        securityContext.setAuthentication(authenticationToken);

        // description: 생성한 Security Context 등록 //
        SecurityContextHolder.setContext(securityContext);

    
    }
}
