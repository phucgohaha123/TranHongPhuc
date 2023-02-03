package com.example.springdata.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.springdata.entity.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	// Đoạn JWT_SECRET
	private final String JWT_SECRET = "lodaaaaaa";

	 public JwtTokenProvider() {
			super();
			// TODO Auto-generated constructor stub
		}

	// Tạo ra jwt từ thông tin user
    public String generateToken(Users users, long jwtExpir) {
    	Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpir);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder().setSubject(Integer.toString(users.getId())).setIssuedAt(now).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
    }
    
    // Lấy thông tin user từ jwt
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET)
                            .parseClaimsJws(token)
                            .getBody();

        return Long.parseLong(claims.getSubject());
    }
    
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
           //TODO
        } catch (ExpiredJwtException ex) {
           //TODO
        } catch (UnsupportedJwtException ex) {
           //TODO
        } catch (IllegalArgumentException ex) {
           //TODO
        }
        return false;
    }
}
