package net.tassia.pancake.http.features

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.request.*
import io.ktor.util.*
import io.ktor.util.pipeline.*
import java.util.logging.Logger

/**
 * The HTTP server feature used to log incoming requests.
 *
 * @since Pancake 1.0
 * @author Tassilo
 */
class Logging {

	class Configuration {
		var logger: Logger? = null
	}

	companion object : ApplicationFeature<Application, Configuration, Logging> {

		override val key: AttributeKey<Logging> = AttributeKey("Logging")

		override fun install(pipeline: Application, configure: Configuration.() -> Unit): Logging {

			val config = Configuration()
			configure(config)

			val logger = config.logger!!

			val loggingPhase = PipelinePhase("Logging")
			pipeline.insertPhaseBefore(ApplicationCallPipeline.Monitoring, loggingPhase)

			pipeline.intercept(loggingPhase) {
				val start = System.currentTimeMillis()
				proceed()
				val total = System.currentTimeMillis() - start

				val scheme = call.request.origin.version
				val address = call.request.origin.remoteHost
				val method = call.request.httpMethod.value
				val path = call.request.origin.uri
				val code = call.response.status()?.value ?: -1

				when {
					code >= 500 -> {
						logger.warning("$scheme $method $path - $code | $total ms for $address")
					}
					code >= 300 -> {
						logger.finer("$scheme $method $path - $code | $total ms for $address")
					}
					else -> {
						logger.finest("$scheme $method $path - $code | $total ms for $address")
					}
				}
			}

			return Logging()
		}

	}

}
