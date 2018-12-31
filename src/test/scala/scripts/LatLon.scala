package demo

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class soap extends Simulation {

  val httpProtocol = http
    	.baseUrl("https://graphical.weather.gov:443/xml/SOAP_server") 

val headers = Map(
	"Accept-Encoding" -> "gzip,deflate",
	"Content-Type" -> "text/xml;charset=UTF-8",
	"SOAPAction" -> "https://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl#LatLonListCityNames",
	"Content-Length" -> "519",
	"Host" -> "graphical.weather.gov:443",
	"Connection" -> "Keep-Alive",
	"User-Agent"-> "Apache-HttpClient/4.1.1 (java 1.5)"
)

  val scn = scenario("getLatLon")
    	.exec(http("getLatLon")
      	.post("/ndfdXMLserver.php")
      	.headers(headers)
	.body(RawFileBody("getLatLon.txt"))

)

  setUp(scn.inject(atOnceUsers(100)).protocols(httpProtocol))
}
