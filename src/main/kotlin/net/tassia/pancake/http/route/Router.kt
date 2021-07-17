package net.tassia.pancake.http.route

import io.ktor.routing.*

class Router(val routing: Routing) {

	fun fetchAuth(call: ActiveRequest): Any {
		return Unit // TODO
	}



	@Suppress("FunctionName")
	fun GET(path: String, body: ResponseOnlyRoute) {
		routing.get(path) {
			// Authentication
			val auth = fetchAuth(this)

			// Process
			body.process(this, auth)
		}
	}

	@Suppress("FunctionName")
	inline fun <reified T : Any> POST(path: String, body: RequestPayloadRoute<T>) {
		routing.post(path) {
			// Authentication
			val auth = fetchAuth(this)

			// Receive data
			val data = receive<T>() ?: return@post

			// Process
			body.process(this, auth, data)
		}
	}

}
