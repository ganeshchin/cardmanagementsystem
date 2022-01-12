package com.cardmanagementsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.dao.UserDao;
import com.cardmanagementsystem.model.UserDetails;


@SpringBootTest
class UserServiceTest {
	@Autowired
	private UserService userService;

	@Mock
	private UserDao userDao;
	@Test
	public void createUserTestCheckingtitle() {
		UserDetails userDetails=new UserDetails(1, "9985758068", "566566656655", "andrew", "CPMPK2943H", "df", "DONE");
		Response response=userService.createUser(userDetails); 
		assertEquals(true, response.getErrors().contains("please provide  proper title details"));
		 
	}

	@Test
	public void createUserTestCheckingPAN() {
		UserDetails userDetails=new UserDetails(1, "9985758068", "566566656655", "andrew", "12345678", "Mrs", "DONE");
		Response response=userService.createUser(userDetails);
		assertEquals(true, response.getErrors().contains("please provide  proper pan details"));
		 
	}
	@Test
	public void createUserTestCheckingStatus() {
		UserDetails userDetails=new UserDetails(1, "9985758068", "566566656655", "andrew", "CPMPK2943H", "Mrs", "correct");
		Response response=userService.createUser(userDetails);
		assertEquals(true, response.getErrors().contains("please provide proper valid status"));
		 
	}
	@Test
	public void createUserSuccess() {
		UserDetails userDetails=new UserDetails(1, "9985758068", "566566656655", "andrew", "CPMPK2943H", "Mrs", "DONE");
	Response response=userService.createUser(userDetails);
	assertEquals(true, response.getStatusCode().equals("00"));
		System.out.println(response);
	
	}
	@Test
	public void createUserFailure() {
		UserDetails userDetails=new UserDetails(1, "99857580689999999", "566566656655", "andrew", "CPMPK2943H", "Mrs", "DONE");
	Response response=userService.createUser(userDetails);
	assertEquals(true, response.getStatusCode().equals("01"));
		System.out.println(response);
	
	}

}
