package com.example.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.repo.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.services.SecurityService;
import com.example.util.EmailUtilImpl;
import com.example.model.*;
@Controller
public class UserController {
	
	@Autowired
	SecurityService securityService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	EmailUtilImpl emailUtil;
	
	@RequestMapping("/")
	public String home(ModelMap map) {
		boolean yes=currentUserName().equals("anonymousUser");
		map.addAttribute("yes",yes);
		return "index";
	}
	
	@RequestMapping("/signin")
	public String showLogin() {
		System.out.println("Initial user is "+currentUserName());
		return "login";
	}
	
	@RequestMapping("/signup")
	public String showSignup(){
		return "signup";
	}
	
	@PostMapping("/signup")
	public String register(@ModelAttribute("user") User user, ModelMap map){
		
		user.setPassword(encoder.encode(user.getPassword()));
		if(userRepository.findByEmail(user.getEmail())==null) {
			userRepository.save(user);
			map.addAttribute("successSignup","Successfully registered, please login to use the app");
			return "login";
		} else {
			map.addAttribute("errorSignup","Email already exists, try another one");
			return "signup";
		}
	}
	
	@PostMapping("/signin")
	public String loginUser(@RequestParam("email") String email,@RequestParam("password") String password
			, ModelMap map) {
		
		//System.out.println("Inside");
		boolean loginResponse = securityService.login(email, password);
		boolean isAdmin=false;
		User user= userRepository.findByEmail(email);
		System.out.println("Roles is "+user.getRoles()+" and is their role is "+user.getRoles()!=null);
		if(user.getRoles().size()!=0) isAdmin=true;
		if(isAdmin) {
			List<Status> statusList=statusRepository.findAll();
			map.addAttribute("statusInfo", statusList);
			return "admin";
		}
		else if(loginResponse) {
			List<Status> statusList=statusRepository.findByUsernameSorted(currentUserName());
			map.addAttribute("statusInfo", statusList);
			return "home";
		} else {
			
			map.addAttribute("loginError","Invalid email/password");
			return "login";
		}
	}
	
	@RequestMapping("/home")
	public String getHome(ModelMap map) {
		boolean isAdmin=false;
		User user= userRepository.findByEmail(currentUserName());
		if(user.getRoles().size()!=0) isAdmin=true;
		if(isAdmin) {
			List<Status> statusList=statusRepository.findAll();
			map.addAttribute("statusInfo", statusList);
			return "admin";
		}
		List<Status> statusList=statusRepository.findByUsernameSorted(currentUserName());
		map.addAttribute("statusInfo", statusList);
		return "home";
	}
	
	@RequestMapping("/new")
	public String getNewForm() {
		boolean isAdmin=false;
		User user= userRepository.findByEmail(currentUserName());
		if(user.getRoles().size()!=0) isAdmin=true;
		if(!isAdmin)
		return "new";
		else
			return "redirect:/access-denied";
	}
	
	@PostMapping("make-request")
	public String submitHandler(@ModelAttribute("status") Status status, ModelMap map) {
		
		String username=currentUserName();
		status.setStatus("Submitted/Waiting");
		status.setDate(new Date().toString());
		status.setUsername(username);
		statusRepository.save(status);
		emailUtil.sendEmail(username, "Request Submitted", "You request has been submitted successfully, Wait for the further notifications!");
		map.addAttribute("submission-success", "Your request has been successfully made.");
		return "new";
	}
	
	public String currentUserName() {
		String username="";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
		  username = ((UserDetails)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		return username;
	  }
	
	@RequestMapping("/approve")
	public String approveRequest(@RequestParam("id")int id,ModelMap map) {
		Status st=statusRepository.getOne(id);
		st.setStatus("approved");
		statusRepository.save(st);
		emailUtil.sendEmail(st.getUsername(), "Request Approved", "You request has been approved :)");
		List<Status> statusList=statusRepository.findAll();
		map.addAttribute("statusInfo", statusList);
		return "admin";
	}
	
	@RequestMapping("/cancel")
	public String cancelRequest(@RequestParam("id")int id,ModelMap map) {
		Status st=statusRepository.getOne(id);
		st.setStatus("cancelled");
		statusRepository.save(st);
		emailUtil.sendEmail(st.getUsername(), "Request Cancelled", "You request has been cancelled :(");
		List<Status> statusList=statusRepository.findAll();
		map.addAttribute("statusInfo", statusList);
		return "admin";
	}
	
	
	@GetMapping("/access-denied")
	public String denied() {
		return "access-denied";
	}
}
