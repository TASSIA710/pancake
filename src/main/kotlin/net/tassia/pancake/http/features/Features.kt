package net.tassia.pancake.http.features

import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import net.tassia.pancake.http.data.response.ExceptionResponse
import net.tassia.pancake.http.data.response.StatusResponse
import net.tassia.pancake.http.route.respondStatus

fun Application.installAutoHeadResponse() {
	install(AutoHeadResponse)
}

fun Application.installCallId() {
	install(CallId)
}

fun Application.installCallLogging() {
	install(Logging) {
		logger = net.tassia.pancake.logging.Logging.get("HTTP")
	}
}

fun Application.installContentNegotiation() {
	install(ContentNegotiation) {
		jackson {
			enable(SerializationFeature.INDENT_OUTPUT)
		}
	}
}

fun Application.installCORS() {
	routing {
		options("/{...}") {
			call.response.headers.let {
				it.append("Access-Control-Allow-Credentials", "true")
				it.append("Access-Control-Allow-Headers", "*")
				it.append("Access-Control-Allow-Methods", "*")
				it.append("Access-Control-Allow-Origin", "*")
				it.append("Access-Control-Expose-Headers", "*")
				it.append("Access-Control-Max-Age", "3600")
			}
			respondStatus(StatusResponse.OK)
		}
	}
}

fun Application.installDefaultHeaders() {
	val version: String = Application::class.java.`package`.implementationVersion ?: "debug"
	install(DefaultHeaders) {
		header(HttpHeaders.Server, "KTor@Netty/$version")
	}
}

fun Application.installDoubleReceive() {
	install(DoubleReceive)
}

fun Application.installRouting() {
	install(Routing)
}

fun Application.installStatusPages() {
	install(StatusPages) {
		fun register(status: HttpStatusCode, response: StatusResponse) {
			status(status) {
				call.respond(status, response)
			}
		}

		register(HttpStatusCode.BadRequest, StatusResponse.BAD_REQUEST)
		register(HttpStatusCode.Unauthorized, StatusResponse.UNAUTHORIZED)
		register(HttpStatusCode.Forbidden, StatusResponse.FORBIDDEN)
		register(HttpStatusCode.NotFound, StatusResponse.NOT_FOUND)
		register(HttpStatusCode.MethodNotAllowed, StatusResponse.METHOD_NOT_ALLOWED)

		exception<Throwable> {
			call.respond(HttpStatusCode.InternalServerError, ExceptionResponse(it))
		}
	}
}
