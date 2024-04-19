package com.example.biblioteka2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTTTokenFilter extends OncePerRequestFilter {
    @Value("${jwt.token.key}")
    private final String key;
    public JWTTTokenFilter(String key){
        this.key = key;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader!= null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.split(" ")[1];
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseSignedClaims(token)
                    .getBody();
            String userId = ((Integer) claims.get("id")).toString();
            String role = (String) claims.get("userRole");

            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userId, null,
                            List.of(new SimpleGrantedAuthority(role)));
            //Rola użytkowimka powinna by zapisana w bazie ROLE_NAZWA
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else{
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        filterChain.doFilter(request,response); //ZAWSZE MUSI BYC NA KONCU FILTRA
    }
}
