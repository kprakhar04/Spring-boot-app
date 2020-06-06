package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	
	@Autowired
	UserDetailsService userDetailsService;
	
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Override
	public boolean login(String email, String password) {
	
		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(
				userDetails,password,userDetails.getAuthorities());
		System.out.println("token "+token);
		authenticationManager.authenticate(token);
		System.out.println("Result is waiting");
		boolean result = token.isAuthenticated();
		System.out.println("Result is "+result);
		if(result) {
			System.out.println("Inside if got the result as "+result);
			SecurityContextHolder.getContext().setAuthentication(token);
		}
		System.out.println("returning the result");
		return result;
	}
}

