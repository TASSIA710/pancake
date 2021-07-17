package net.tassia.pancake.http

import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import net.tassia.pancake.config.BootConfiguration
import net.tassia.pancake.http.features.*
import net.tassia.pancake.http.route.Router
import net.tassia.pancake.http.routes.installGenericRoutes

/**
 * Manages the Pancake HTTP server.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class HttpServer(config: BootConfiguration) {

	/**
	 * The HTTP server engine.
	 */
	private val engine: ApplicationEngine = embeddedServer(Netty, host = config.httpBindAddress, port = config.httpBindPort) {

		// Install features
		installAutoHeadResponse()
		installCallId()
		installCallLogging()
		installContentNegotiation()
		installDefaultHeaders()
		installDoubleReceive()
		installRouting()
		installCORS()
		installStatusPages()

		// Routes
		routing {
			Router(this).apply {
				installGenericRoutes()
			}
		}

	}



	/**
	 * Starts the HTTP server.
	 */
	fun start() {
		engine.start(false)
	}

	/**
	 * Stops the HTTP server.
	 */
	fun stop() {
		engine.stop(2000, 2000)
	}

}
