package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.exceptions.CustomException;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public boolean login(String email, String password) throws CustomException {

		UserDetails userDetails = userDetailsService.loadUserByUsername(email);
		System.out.println("User details " + userDetails);
		if (!userDetails.getPassword().equals(password)) {
			throw new CustomException("Invalid credentials!");
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());
//		System.out.println("token "+token);
		authenticationManager.authenticate(token);
		System.out.println("Result is waiting");
		boolean result = token.isAuthenticated();
//		System.out.println("Result is "+result);
		if (result) {
//			System.out.println("Inside if got the result as "+result);
			SecurityContextHolder.getContext().setAuthentication(token);
		}
//		System.out.println("returning the result");
		return result;
	}
}
