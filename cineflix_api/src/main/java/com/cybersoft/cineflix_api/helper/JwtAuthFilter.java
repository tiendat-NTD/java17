package com.cybersoft.cineflix_api.helper;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cybersoft.cineflix_api.security.UserService;
import com.google.gson.Gson;

public class JwtAuthFilter extends OncePerRequestFilter{

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	
	private Gson gson = new Gson();
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getServletPath().startsWith("/api/v1/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		String token = getJwtToken(request);
		System.out.println("token" + token);
			
		
		if(jwtProvider.validationToken(token)) {
			//hợp lệ
			String jsonData = jwtProvider.decodeToken(token);
			System.out.println("kiểm tra: " +jsonData);
			//User user = gson.fromJson(jsonData, User.class);
			
			User userDetail = (User) userService.loadUserByUsername(jsonData);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//			Authentication authentication = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()
//	                ));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			//gọi lại đăng nhập mặc định
		}else {
			//ko hợp lệ
			System.out.println("Đăng nhập thất bại");
		}
		filterChain.doFilter(request, response);
	}
	
	private String getJwtToken(HttpServletRequest request) {
		String authenToken = request.getHeader("Authorization");
		System.out.println("authen: " + authenToken);
		if(StringUtils.hasText(authenToken) && authenToken.contains("Bearer")) {
			//loại bỏ chữ bearer và lấy token
			String token = authenToken.substring(7);
			return token;
		}
		return null;
	}

}
