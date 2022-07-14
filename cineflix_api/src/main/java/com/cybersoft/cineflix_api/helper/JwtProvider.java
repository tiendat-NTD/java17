package com.cybersoft.cineflix_api.helper;

import java.util.Date;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	//Tạo tpken
	//Kiểm tra token có phải của hệ thống hay ko
	//Giải mã token
	
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String SECRET_KEY = "PGRlcGVuZGVuY3k+DQoJCQk8Z3JvdXBJZD5pby5qc29ud2VidG9rZW48L2dyb3VwSWQ+DQoJCQk8YXJ0aWZhY3RJZD5qand0PC9hcnRpZmFjdElkPg0KCQkJPHZlcnNpb24+MC45LjE8L3ZlcnNpb24+DQoJCTwvZGVwZW5kZW5jeT4=";//security_jwt
    
    private Gson gson = new Gson();

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 8*60*60*1000;
	
	
	public String generateToken(String username) {
		Date now = new Date();
		Date expriedDate = new Date(now.getTime() + JWT_EXPIRATION);
		
		//String json = gson.toJson(user);
		return Jwts.builder()
				.setSubject(username)//Dữ liệu muốn lưu ở token
				.setIssuedAt(now)//ngày tạo
				.setExpiration(expriedDate)//ngày hết hạn
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)//kiểu mã hoá
                .compact();
	}
	
	public String decodeToken(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	public boolean validationToken(String token) {
		try {
			Jwts.parser()
			.setSigningKey(SECRET_KEY)
			.parseClaimsJws(token);
            return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
