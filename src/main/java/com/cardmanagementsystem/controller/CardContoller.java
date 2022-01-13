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
import com.cardmanagementsystem.model.CardDetails;
import com.cardmanagementsystem.service.CardService;

@RestController
@RequestMapping
public class CardContoller {
	@Autowired
	private CardService cardService;

	@PostMapping("/card")
	public Response saveCard(@RequestBody @Valid CardDetails cardDetails) {  //NOSONAR

		return cardService.saveCard(cardDetails);

	}

	@GetMapping("/api/carddetails/{id}")
	public Response getCard(@PathVariable int id) {
		return cardService.getCardById(id);

	}
	@GetMapping("/api/getallcards")
	public Response getAll() {
		return cardService.getAllCardDetails();
		 
	}
}