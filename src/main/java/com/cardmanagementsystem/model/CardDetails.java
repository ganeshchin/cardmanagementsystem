package com.cardmanagementsystem.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "carddetails")
public class CardDetails {
	@Id
	@Column(name = "userid", nullable = false)
	private Integer userId;

	@NotNull
	@Length(min = 5, max = 20)
	@Column(name = "card_number", nullable = false)
	private String cardNumber;

	@Column(name = "dailylimt", nullable = false)
	@Min(2)
	@Max(200)
	private Integer dailyLimit;

	@Length(min = 5, max = 200)
	@Column(name = "card_type", columnDefinition = "VARCHAR(6) CHECK (card_type IN ('DEBIT', 'CREDIT'))")
	private String cardType;

	@Column(name = "card_status", columnDefinition = "VARCHAR(8) CHECK (card_status IN ('ACTIVE', 'INACTIVE'))")
	@Length(min = 6, max = 8)
	private String cardStatus;

	@Column(name = "expirydate")
	@NotNull
	@Future
	@JsonFormat(pattern = "MMyyyy")
	@DateTimeFormat(pattern = "MMyyyy")
	private Date expiryDate;

}
