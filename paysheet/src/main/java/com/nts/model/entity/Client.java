package com.nts.model.entity;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "client")
public class Client {
	@Id
	private String id;

	@NotEmpty
	private String clientCode;

	@NotEmpty
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
