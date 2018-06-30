package gatlingNitu

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulationNitu extends Simulation {

	val httpProtocol = http
		.baseURL("http://computer-database.gatling.io")
		.inferHtmlResources()
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:45.0) Gecko/20100101 Firefox/45.0")





	val scn = scenario("RecordedSimulationNitu")
		.exec(http("request_0")
			.get("/computers"))
		.pause(48)
		.exec(http("request_1")
			.get("/computers?f=ACE"))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}