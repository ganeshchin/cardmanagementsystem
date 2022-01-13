package com.cardmanagementsystem.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.model.UserDetails;
import com.cardmanagementsystem.service.UserService;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public Response saveUser(@RequestBody @Valid UserDetails userDetails) {  //NOSONAR

		return userService.createUser(userDetails);

	}
	@GetMapping("/api/userdetails/{id}")
	public Response getUserAndAddressDetailsById(@PathVariable int id) {
		return userService.getUserAndAddressDetailsById(id);
		
	}
	
	
	
}