package com.socialMedia.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import com.socialMedia.constants.JwtConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
		
		//this will create a JWT Token
		public static String generateToken(Authentication auth) {
			
			String jwt = Jwts.builder()
					.issuer("SHIVENDU")
					.issuedAt(new Date())
					.expiration(new Date(new Date().getTime()+86400000)) //this makes a token valid for next 24Hrs
					.claim("email", auth.getName())
					.signWith(key)
					.compact();
			
			return jwt;
			
		}
		
		public static String getEmailFromJwtToken(String jwt) {
			
			//token format would be like below
			// Bearer sdfdsdfhkjsdfhksjdfhskdhfkjshf 
			//here we see, we need to remove the "Bearer " and its space from the above string to get our Email
			
			jwt = jwt.substring(7);
			
			Claims claims = Jwts.parser()
					.verifyWith(key)     	//deprecated method : setSigningKey()
					.build()
					.parseSignedClaims(jwt)	//deprecated method : parseClaimsJws()
					.getPayload();			//deprecated metho  : getBody()
			
			//now we will fetch the email from the body
			String email = String.valueOf(claims.get("email"));
			
			return email;
			
		}
}
