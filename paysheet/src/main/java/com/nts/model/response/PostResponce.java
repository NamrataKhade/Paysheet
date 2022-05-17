package com.nts.model.response;

import java.util.List;

import com.nts.model.entity.Permission;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostResponce {

	
	private List<Permission> permissions;
	private int pageNumber;
	private int pageSize;
	private int totalElements;
	private int totalPages;
	private boolean lastpage;
}
