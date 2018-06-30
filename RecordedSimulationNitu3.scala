package gatlingNitu

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulationNitu3 extends Simulation {

	val httpProtocol = http
		.baseURL("http://computer-database.gatling.io")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:45.0) Gecko/20100101 Firefox/45.0")





	//val scn = scenario("RecordedSimulationNitu2")
	val CMPDB = scenario("computerdb2").exec(PageRecording,SearchbyName,RemoveFilter,AddNewName,AddNewDetails,SearchByNewName)
	
		val PageRecording=
		{
			exec(http("request_0")
			.get("/computers"))
			.pause(20)
		}
		val SearchbyName=
		{
			exec(http("request_1")
			.get("/computers?f=ACE"))
			.pause(22)
		}
		val RemoveFilter=
		{
			exec(http("request_2")
			.get("/computers?f="))
			.pause(14)
		}
		val AddNewName=
		{
			exec(http("request_3")
			.get("/computers/new"))
			.pause(61)
		}
		val AddNewDetails=
		{
			exec(http("request_4")
			.post("/computers")
			.formParam("name", "NituComp")
			.formParam("introduced", "2018-06-30")
			.formParam("discontinued", "2018-07-01")
			.formParam("company", "6"))
			.pause(29)
		}
		val SearchByNewName=
		{
			exec(http("request_5")
			.get("/computers?f=Nitu"))

			//setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
		}
		setUp(CMPDB.inject(atOnceUsers(1))).protocols(httpProtocol)
	}