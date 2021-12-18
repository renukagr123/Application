package com.example.myjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.myjwt.models.User;
import com.example.myjwt.repo.UserRepository;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/test")
public class TestController {
	
	@Autowired
	UserRepository repo;
	
	@GetMapping("/all")
	public String allAccess() {
		return "Welcome to the App. "
				+ "Let's Login or SignUp";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String userAccess() {
		return "Hello user! You are authorized :) ";
	}
	
	@GetMapping("/verify/{vcode}")
	public String verifyUser(@PathVariable String vcode) {
		 System.out.println(vcode);
		 User user = repo.findByVerificationCode(vcode);
		 //if (user == null || user.isEnabled())
		    if (user == null) {
		    	//System.out.println(user.isEnabled());
		    	return "verify_failed! Verification invalid or already verified!";
		    } else {
		        user.setVerificationCode(null);
		        user.setEnabled(true);
		        repo.save(user);
		         
		        return "verify_success!!!   Login to explore!!!";
		    }
	 
	}

}
