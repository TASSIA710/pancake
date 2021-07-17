package net.tassia.pancake.http.data.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class StatusResponse {

	@JsonProperty("code")
	public final int code;

	@JsonProperty("name")
	public final String name;

	@JsonProperty("description")
	public final String description;



	public StatusResponse(int code, String name, String description) {
		this.code = code;
		this.name = name;
		this.description = description;
	}



	public static final StatusResponse OK = new StatusResponse(200, "OK",
		"The action has executed successfully.");

	public static final StatusResponse BAD_REQUEST = new StatusResponse(400, "Bad Request",
		"The request was malformed.");

	public static final StatusResponse UNAUTHORIZED = new StatusResponse(401, "Unauthorized",
		"This action requires the client to be authenticated and authorized.");

	public static final StatusResponse FORBIDDEN = new StatusResponse(403, "Forbidden",
		"The client explicitly has no permission to access the requested resource.");

	public static final StatusResponse NOT_FOUND = new StatusResponse(404, "Not Found",
		"The requested resource was not found.");

	public static final StatusResponse METHOD_NOT_ALLOWED = new StatusResponse(405, "Method Not Allowed",
		"The given request method is not allowed on this resource.");

}
