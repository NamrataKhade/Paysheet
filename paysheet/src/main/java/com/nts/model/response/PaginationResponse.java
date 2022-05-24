package com.nts.model.response;

import java.util.List;

import com.nts.model.dto.EmployeeDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaginationResponse {

	private List<EmployeeDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPgae;
	private boolean lastPage;

}
