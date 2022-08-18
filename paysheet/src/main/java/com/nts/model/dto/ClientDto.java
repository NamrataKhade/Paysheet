package com.nts.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class ClientDto {
	@Id
	private String id;

	@NotEmpty
	private String clientCode;

	@NotEmpty
	@Size(min = 5, max = 30, message = "Client Name should be min 4 and max 30!!")
	private String clientName;

	@NotEmpty
	private String clientDetail;

	@NotEmpty
	private String address;

	@NotEmpty
	private String country;

	@NotEmpty
	private String state;

	@NotEmpty
	private String city;

	@NotEmpty
	private String pincode;

}
