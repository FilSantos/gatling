package jamef

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

  val scn = scenario("Consulta Frete - Protheus") // A scenario is a chain of requests and pauses
    	.exec(http("Request") // Here's an example of a POST request
      	.post("/JAMW0520.apw")
      	.headers(headers)
	.body(RawFileBody("<soapenv:Envelope xmlns:soapenv='http://schemas.xmlsoap.org/soap/envelope/' xmlns:tot='http://totjobcl03.dtc.jamef:8088/'>
   <soapenv:Header/>
   <soapenv:Body>
      <tot:JAMW0520_01>
         <tot:TIPTRA>1</tot:TIPTRA>
         <tot:CNPJCPF>05148230000190</tot:CNPJCPF>
         <tot:MUNORI>MOGI DAS CRUZES</tot:MUNORI>
         <tot:ESTORI>SP</tot:ESTORI>
         <tot:MUNDES>BERTIOGA</tot:MUNDES>
         <tot:ESTDES>SP</tot:ESTDES>
         <tot:SEGPROD>1</tot:SEGPROD>
         <tot:QTDVOL>1</tot:QTDVOL>
         <tot:PESO>1</tot:PESO>
         <tot:VALMER>1000</tot:VALMER>
         <tot:METRO3>1</tot:METRO3>
         <tot:CNPJREM>05148230000190</tot:CNPJREM>
         <tot:CNPJDES>05148230000190</tot:CNPJDES>
         <tot:CIFFOB>1</tot:CIFFOB>
         <!--Optional:-->
         <tot:FILCOT>?</tot:FILCOT>
         <!--Optional:-->
         <tot:GRVLOG>?</tot:GRVLOG>
         <!--Optional:-->
         <tot:SEQEND>?</tot:SEQEND>
         <!--Optional:-->
         <tot:ENTFIL>?</tot:ENTFIL>
         <!--Optional:-->
         <tot:CONTRIB>?</tot:CONTRIB>
      </tot:JAMW0520_01>
   </soapenv:Body>
</soapenv:Envelope>"))

)

  setUp(scn.inject(atOnceUsers(1500)).protocols(httpProtocol))
}
