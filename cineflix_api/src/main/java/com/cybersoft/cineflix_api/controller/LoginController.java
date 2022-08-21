package com.cybersoft.cineflix_api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybersoft.cineflix_api.helper.JwtProvider;
import com.cybersoft.cineflix_api.payload.LoginRequest;
import com.google.gson.Gson;



@RestController
@RequestMapping("/api/v1")
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	private Gson gson = new Gson();
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest login) {
		String json = gson.toJson(login);
		logger.info("[IN REQUEST] " + json);			
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        //String jwt = jwtProvider.generateToken((User) authentication.getPrincipal());
        String jwt = jwtProvider.generateToken(login.getUsername());
        System.out.println("token: " +jwt);
        return new ResponseEntity<String>(jwt, HttpStatus.OK);
	}
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
}
