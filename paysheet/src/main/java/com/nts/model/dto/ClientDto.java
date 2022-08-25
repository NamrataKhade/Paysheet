package com.nts.model.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String Address;

	@NotEmpty
	private String Country;

	@NotEmpty
	private String State;

	@NotEmpty
	private String City;

	@NotEmpty
	private String Pincode;

	@CreationTimestamp
	@Column(updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+05:30")
	private Date createdOn;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+05:30")
	private Date lastUpdatedOn;

}
