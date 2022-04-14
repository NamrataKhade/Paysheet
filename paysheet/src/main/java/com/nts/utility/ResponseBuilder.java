package com.nts.utility;

import org.springframework.stereotype.Component;


@Component
public class ResponseBuilder  {

	public Response createResponse(String statusCode, String status, String description, Object t) {
		Response response = new Response();

		response.setResponseCode(statusCode);
		response.setStatus(status);
		response.setDescription(description);
		response.setDetail(t);

		return response;
	}
}
