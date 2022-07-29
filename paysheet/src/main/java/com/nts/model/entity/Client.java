package com.nts.model.entity;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@CreationTimestamp
	@Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+05:30")
    private Timestamp createdOn;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+05:30")
    private Timestamp lastUpdatedOn;
}
