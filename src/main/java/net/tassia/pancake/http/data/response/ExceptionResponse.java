package net.tassia.pancake.http.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class ExceptionResponse {

	@JsonProperty("message")
	public final String message;

	@JsonProperty("cause")
	public final ExceptionResponse cause;



	public ExceptionResponse(Throwable error) {
		this.message = error.getMessage();
		this.cause = error.getCause() != null ? new ExceptionResponse(error.getCause()) : null;
	}

}
