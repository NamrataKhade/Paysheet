package com.nts.model.response;

import java.util.List;

import com.nts.model.dto.PermissionDto;
import com.nts.model.entity.Permission;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PermissionResponce {

	private PermissionDto permissionDto;
	private List<Permission> permissions;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastpage;

}
