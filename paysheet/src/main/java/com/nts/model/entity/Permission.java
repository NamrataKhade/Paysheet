package com.nts.model.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document

public class Permission {

	@Id
	private String perId;
	@NotEmpty(message = "Type is not null")
	private String type;
	@NotEmpty
	@Size(min = 3,message = "Name will be at list 4 charecter")
	private String name;
	@NotNull
	private boolean status = false;

}
