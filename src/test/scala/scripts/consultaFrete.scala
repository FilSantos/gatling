package demo

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class soap extends Simulation {

  val httpProtocol = http
    	.baseUrl("https://www.jamef.com.br/sit") 

val headers = Map(
	"Accept-Encoding" -> "gzip,deflate",
	"Content-Type" -> "text/xml;charset=UTF-8",
	"SOAPAction" -> "http://www.jamef.com.br/JAMW0520_01",
	"Content-Length" -> "1134",
	"Host" -> "www.jamef.com.br",
	"Connection" -> "Keep-Alive",
	"User-Agent"-> "Apache-HttpClient/4.1.1 (java 1.5)"
)

  val scn = scenario("Consulta Frete")
    	.exec(http("SOAP")
      	.post("/JAMW0520.apw")
      	.headers(headers)
	.body(RawFileBody("consultafreteProtheus.txt"))

)

  setUp(scn.inject(atOnceUsers(1500)).protocols(httpProtocol))
}
