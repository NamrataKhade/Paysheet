package com.nts.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection = "task")
public class Task {

	@Id
	private String taskId;

	@NotEmpty
	private String name;

	@NotEmpty
	private String status;

	@NotEmpty
	private String projectId;
	@CreationTimestamp
	@Column(updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+05:30")
	private Date createdOn;

	@UpdateTimestamp
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+05:30")
	private Date lastUpdatedOn;

}
