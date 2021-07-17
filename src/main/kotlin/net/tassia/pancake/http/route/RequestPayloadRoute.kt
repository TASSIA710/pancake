package net.tassia.pancake.http.route

fun interface RequestPayloadRoute<T> {

	suspend fun process(call: ActiveRequest, auth: Any, payload: T)

}
