package com.nts.model.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProjectsDto {

	@Id
	private String projectId;

	@NotEmpty
	@Size(min = 4, message = "Name is NOT NULL")
	private String name;

	@NotEmpty
	private String task;

	@NotNull
	private Boolean status = false;

	@NotEmpty
	private String admin;

	@NotEmpty
	private String manager;

	@NotEmpty
	private String roles;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;

}
