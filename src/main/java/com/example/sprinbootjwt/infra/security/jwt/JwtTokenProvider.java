package com.example.sprinbootjwt.infra.security.jwt;

import com.example.sprinbootjwt.infra.security.jwt.dto.TokenResponse;
import com.example.sprinbootjwt.infra.security.service.CustomUserDetailsService;
import com.example.sprinbootjwt.user.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.secret-key}")
    private String secretkey;

    @Value("${security.jwt.accesstoken.expire-time}")
    private long accessTokenExpireTime;

    private final CustomUserDetailsService userDetailsService;

    public JwtTokenProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public TokenResponse createAccessToken(User user) {
        Date now = new Date();
        Date expire = new Date(now.getTime() + accessTokenExpireTime);
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        claims.put("id", user.getId());
        claims.put("role", user.getRole());
        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256, secretkey)
                .compact();
        return new TokenResponse(accessToken, accessTokenExpireTime);
    }

    public Authentication getAuthentication(String token) {
        String email = getPayload(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public String getPayload(String token) {
        return Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretkey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
