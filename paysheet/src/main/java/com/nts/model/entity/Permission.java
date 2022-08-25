package com.nts.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Permission {

	@Id
	private String permissionId;

	private String type;

	private String name;

	private boolean status = false;

}
