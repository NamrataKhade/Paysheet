package com.nts.model.response;

import java.util.List;

import com.nts.model.dto.ProjectsDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data 
public class PaginationResponse {

	private List<ProjectsDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean lastPage;
}
