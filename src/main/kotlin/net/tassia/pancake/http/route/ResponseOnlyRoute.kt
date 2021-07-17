package net.tassia.pancake.http.route

fun interface ResponseOnlyRoute {

	suspend fun process(call: ActiveRequest, auth: Any)

}
