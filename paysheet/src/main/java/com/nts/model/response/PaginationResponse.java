package com.nts.model.response;

import java.util.List;

import com.nts.model.dto.TasksDto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Getter
@Setter
public class PaginationResponse {

	private List<TasksDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalElemenet;
	private int totalPage;
	private boolean lastPage;

}
