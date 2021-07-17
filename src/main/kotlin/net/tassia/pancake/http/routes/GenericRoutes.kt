package net.tassia.pancake.http.routes

import net.tassia.pancake.Pancake
import net.tassia.pancake.http.data.response.VersionResponse
import net.tassia.pancake.http.route.Router
import net.tassia.pancake.http.route.respondJSON

fun Router.installGenericRoutes() {

	GET("/api/v1/version") { call, _ ->
		call.respondJSON(VersionResponse(Pancake.VERSION))
	}

}
