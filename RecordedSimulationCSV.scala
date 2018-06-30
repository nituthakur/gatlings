package gatlingNitu

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulationCSV extends Simulation {

	val httpProtocol = http
		.baseURL("http://newtours.demoaut.com")
		.inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.(t|o)tf""", """.*\.png"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.5")
		.userAgentHeader("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:45.0) Gecko/20100101 Firefox/45.0")

	val headers_1 = Map("Accept" -> "image/png,image/*;q=0.8,*/*;q=0.5")



	val User1 = scenario("Test1").exec(LaunchNewTour.page1)
	val User2 = scenario("Test2").exec(Login.page2)
	val User3 = scenario("Test3").exec(OneWayradiobutton.page3)
	val User4 = scenario("Test4").exec(Flightdetails.page4)
	val User5 = scenario("Test5").exec(personalflightdetails.page5)
	val User6 = scenario("Test6").exec(Logoff.page6)
	
	object LaunchNewTour
    {	val page1=
    	exec(http("request_0")
			.get("/")
			.resources(http("request_1")
			.get("/black")
			.headers(headers_1)
			.check(status.is(200))))
		.pause(2)
    }
    object Login
    {


			//val page2=exec(http("request_2")
			val feeder = csv("search.csv").random
    		val saerch = exec(http("Home")
    			.get("/")).feeder.exec(http("Search"))
    		.get("/computers?f={searchCriterion}")
    		.check(css("a:contains('${searchComputername}')","href")
    		.saveAs("computeURL")))
    		.exec(http("Select").get("${computerURL}"))
    		.check(status.is(200)))
			.post("/login.php")
			.formParam("osCsid", "064b3d7be2c908e66d748836c24c6e4e")
			.formParam("action", "process")
			.formParam("userName", "$username")
			.formParam("password", "$password")
			.formParam("login.x", "34")
			.formParam("login.y", "9"))
		.pause(2)
    }
	object OneWayradiobutton
    {
    		val page3=
    		exec(http("request_3")
			.post("/mercuryreservation2.php")
			.formParam("tripType", "oneway")
			.formParam("passCount", "1")
			.formParam("fromPort", "Frankfurt")
			.formParam("fromMonth", "6")
			.formParam("fromDay", "30")
			.formParam("toPort", "Acapulco")
			.formParam("toMonth", "7")
			.formParam("toDay", "30")
			.formParam("servClass", "Business")
			.formParam("airline", "Pangea Airlines")
			.formParam("findFlights.x", "62")
			.formParam("findFlights.y", "10"))
		.pause(2)
    }
    object Flightdetails
    {
    		val page4=
			exec(http("request_4")
			.post("/mercurypurchase.php")
			.formParam("fromPort", "Frankfurt")
			.formParam("toPort", "Acapulco")
			.formParam("passCount", "1")
			.formParam("toDay", "30")
			.formParam("toMonth", "7")
			.formParam("fromDay", "30")
			.formParam("fromMonth", "6")
			.formParam("servClass", "Business")
			.formParam("outFlight", "Blue Skies Airlines$360$270$5:03")
			.formParam("inFlight", "Pangea Airlines$632$282$16:37")
			.formParam("reserveFlights.x", "72")
			.formParam("reserveFlights.y", "14")
			.resources(http("request_5")
			.get("/black")
			.headers(headers_1)
			.check(status.is(500))))
		.pause(2)
    }
    object personalflightdetails
    {
		val page5=
			exec(http("request_6")
			.post("/mercurypurchase2.php")
			.formParam("outFlightName", "Blue Skies Airlines")
			.formParam("outFlightNumber", "360")
			.formParam("outFlightPrice", "270")
			.formParam("outFlightTime", "5:03")
			.formParam("inFlightName", "Pangea Airlines")
			.formParam("inFlightNumber", "632")
			.formParam("inFlightPrice", "282")
			.formParam("inFlightTime", "16:37")
			.formParam("fromPort", "Frankfurt")
			.formParam("toPort", "Acapulco")
			.formParam("passCount", "1")
			.formParam("toDay", "30")
			.formParam("toMonth", "7")
			.formParam("fromDay", "30")
			.formParam("fromMonth", "6")
			.formParam("servClass", "Business")
			.formParam("subtotal", "552")
			.formParam("taxes", "45")
			.formParam("passFirst0", "Nitu")
			.formParam("passLast0", "Thakur")
			.formParam("pass.0.meal", "HNML")
			.formParam("creditCard", "BA")
			.formParam("creditnumber", "7894561237894563")
			.formParam("cc_exp_dt_mn", "12")
			.formParam("cc_exp_dt_yr", "2010")
			.formParam("cc_frst_name", "Nitu")
			.formParam("cc_mid_name", "A")
			.formParam("cc_last_name", "Thakur")
			.formParam("billAddress1", "Tingre")
			.formParam("billAddress2", "Nagar")
			.formParam("billCity", "Canada")
			.formParam("billState", "CA")
			.formParam("billZip", "94089")
			.formParam("billCountry", "215")
			.formParam("delAddress1", "Vishrant")
			.formParam("delAddress2", "wadi")
			.formParam("delCity", "Molline")
			.formParam("delState", "CA")
			.formParam("delZip", "94089")
			.formParam("delCountry", "215")
			.formParam("buyFlights.x", "69")
			.formParam("buyFlights.y", "15")
			.resources(http("request_7")
			.get("/black")
			.headers(headers_1)
			.check(status.is(300))))
		.pause(2)
    }
    object Logoff
    {
			val page6=
			exec(http("request_8")
			.get("/mercurysignoff.php")
			.resources(http("request_9")
			.get("/black")
			.headers(headers_1)
			.check(status.is(404))))
    }
	setUp(User1.inject(atOnceUsers(1)),User2.inject(atOnceUsers(5)),User3.inject(atOnceUsers(1)),User4.inject(atOnceUsers(1)),
		User5.inject(atOnceUsers(1)),User6.inject(atOnceUsers(1))).protocols(httpProtocol)
}