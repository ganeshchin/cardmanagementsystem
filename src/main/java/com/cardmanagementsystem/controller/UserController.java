package com.cardmanagementsystem.controller;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
	public Response saveUser(@RequestBody @Valid UserDetails userDetails) { // NOSONAR

		return userService.createUser(userDetails);

	}

	@GetMapping("/api/userdetails/{id}")
	public Response getUserAndAddressDetailsById(@PathVariable int id) {
		return userService.getUserAndAddressDetailsById(id);

	}
	@GetMapping("/api/userdetails1/{id}")
	public ResponseEntity<Object> getUserAndAddressDetailsByIdSingleResponse(@PathVariable int id) {
		Response response=userService.getUserAndAddressDetailsById(id);
		if(response.getStatusCode().equals("00")) {
			HashMap<String, Object> mapp=new HashMap<String, Object>();
			mapp.put("userDetalis", response.getUserDetails());
			mapp.put("addressDetails", response.getAddressDetails());
			return new ResponseEntity<>(mapp, new HttpHeaders(), response.getStatus());
			
		}
		return new ResponseEntity<>(response, new HttpHeaders(), response.getStatus());
	}

}