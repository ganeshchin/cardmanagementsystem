package com.cardmanagementsystem.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cardmanagementsystem.configuration.Response;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.service.AddressService;


@RestController
@RequestMapping
public class AddressController {
	@Autowired
	 private AddressService addressService;
	
	@PostMapping("/address")
	public Response saveAddress(@RequestBody @Valid AddressDetails address) {

		return addressService.saveAddress(address);

	}

}
