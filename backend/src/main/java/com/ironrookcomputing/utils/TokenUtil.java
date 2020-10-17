package com.ironrookcomputing.utils;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ironrookcomputing.hibernate.beans.Login;

@Service
public class TokenUtil {
	public Login createToken(Login login) {
		Date date = new Date();
		
		login.setTokenValue(null);
		login.setTokenRefresh(null);
		
		try {
			String refreshData = login.toString();
			String tokenData = refreshData + date.getTime();
			
			login.setTokenValue(JWT.create()
					.withIssuer("Agbegbe")
					.withIssuedAt(date)
					.withClaim("data", tokenData)
					.sign(algorithm));
			login.setTokenRefresh(JWT.create()
					.withIssuer("Agbegbe")
					.withIssuedAt(date)
					.withClaim("data", refreshData)
					.sign(algorithm));
			login.setTokenDateTime(date);
			
			System.out.println("Token: " + login.getTokenValue());
			System.out.println("Refresh: " + login.getTokenRefresh());
		} catch(JWTCreationException ex) {
			LogUtil.logException(ex, TokenUtil.class);
		}
		
		return login;
	}
	
	public Boolean tokenIsVerified(String token) {
		DecodedJWT decodedJWT = null;
		Boolean isVerified = false;
		
		try {
			System.out.println("TokenSource: verifying token.");
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer("Affiliate Pro")
					.build();
		    decodedJWT = verifier.verify(token);
		} catch(JWTVerificationException ex) {
			LogUtil.logException(ex, TokenUtil.class);
		}
		
		if(decodedJWT != null) {
			isVerified = true;
			System.out.println("TokenSource: verified.");
		} else {
			System.out.println("TokenSource: not verified.");
		}
		return isVerified;
	}
	
	private Algorithm algorithm = Algorithm.HMAC256("secret");
}