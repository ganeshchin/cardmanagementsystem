package com.cardmanagementsystem.configuration;

import java.util.List;
import org.springframework.http.HttpStatus;
import com.cardmanagementsystem.model.AddressDetails;
import com.cardmanagementsystem.model.CardDetails;
import com.cardmanagementsystem.model.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
	private HttpStatus status;
	private String statusCode;
	private String statusDescription;
	private UserDetails userDetails;
	private AddressDetails addressDetails;
	private CardDetails cardDetails;
	private List<CardDetails> allCardDetails;
	private List<String> errors;
}