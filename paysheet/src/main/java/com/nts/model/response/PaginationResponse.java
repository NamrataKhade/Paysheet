package com.nts.model.response;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaginationResponse {

	private List<Object> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPage;
	private boolean lastPage;

}
