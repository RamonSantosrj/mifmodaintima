package com.mifmodaintima.security;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {

	@Value("${jwt.application}")
	private String application;
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private String expiration;
	
	public String generateToken(String email) {
		
		//data de geração do token
		Date now = new Date();
		//data de expiração do token
		Date exp = new Date(now.getTime() + Long.parseLong(expiration));
		
		return Jwts.builder()
					.setIssuer(application) //nome da aplicação
					.setSubject(email) //email do usuario
					.setExpiration(exp)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
	}
}




