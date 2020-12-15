package com.example.services;

import com.example.exceptions.CustomException;

public interface SecurityService {
	boolean login(String email, String password) throws CustomException;
}
