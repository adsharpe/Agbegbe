package com.ironrookcomputing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironrookcomputing.beans.LoginData;
import com.ironrookcomputing.hibernate.beans.Login;
import com.ironrookcomputing.services.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {
	
	@PostMapping
	public ResponseEntity<Login> login(@RequestBody LoginData loginData) {
		Login login = loginService.login(loginData);
		
		if(login != null) {
			return ResponseEntity.ok(login);
		}
		return ResponseEntity.status(401).build();
	}
	
	@GetMapping(path="/refresh")
	public ResponseEntity<Login> refresh(@RequestParam(value = "refresh") String refresh) {
		Login login = loginService.refreshToken(refresh);
		
		if(login != null) {
			return ResponseEntity.ok(login);
		}
		return ResponseEntity.status(401).build();
	}
	
	@GetMapping(path="/isauth")
	public ResponseEntity<Boolean> isAuthorized(@RequestParam(value = "token") String token) {
		return ResponseEntity.ok(!loginService.isTokenExpired(token));
	}
	
	@DeleteMapping
	public ResponseEntity<Void> logout(@RequestParam(value = "token") String tokenValue) {
		loginService.deleteAuthorization(tokenValue);
		return ResponseEntity.noContent().build();
	}
	
	@Autowired
	private LoginService loginService;
}
