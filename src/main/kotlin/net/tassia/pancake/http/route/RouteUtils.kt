package net.tassia.pancake.http.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.util.pipeline.*
import net.tassia.pancake.http.data.response.StatusResponse

typealias ActiveRequest = PipelineContext<Unit, ApplicationCall>

suspend inline fun ActiveRequest.respondStatus(status: StatusResponse) {
	this.respondJSON(status, HttpStatusCode.fromValue(status.code))
}

suspend inline fun ActiveRequest.respondJSON(data: Any, status: HttpStatusCode = HttpStatusCode.OK) {
	this.call.respond(status, data)
}

suspend inline fun ActiveRequest.respondString(data: String, contentType: ContentType = ContentType.Text.Plain, status: HttpStatusCode = HttpStatusCode.OK) {
	this.call.respondText(data, contentType, status)
}

suspend inline fun <reified T : Any> ActiveRequest.receive(): T? {
	try {
		return call.receive()
	} catch (ex: ContentTransformationException) {
		respondStatus(StatusResponse.BAD_REQUEST)
	}
	return null
}
