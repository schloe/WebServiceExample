/*---------------------------------------------------------------------------*\
|
| Copyright (c) Orga Systems GmbH and/or its affiliates, 2013
| 
| All Rights Reserved.
|
| The software contained on this media is proprietary to and
| embodies the confidential technology of the copyright holder.
| Possession, use, duplication or dissemination of the software
| and media is authorized only pursuant to a valid written license
| from the copyright holder.
|
| This copyright notice must appear in all copies of this software.
|
\*---------------------------------------------------------------------------*/
package client;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;

import com.kaustuv.poc.ws.service.greetings.Greetings;
import com.kaustuv.poc.ws.service.greetings.GreetingsRequest;
import com.kaustuv.poc.ws.service.greetings.GreetingsResponse;
import com.kaustuv.poc.ws.service.greetings.GreetingsService;

/**
 * @author KMaji
 *
 */
public class HttpsClient {

  static {
    //for localhost testing only
    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
      public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
        if (hostname.equals("localhost")) {
          System.out.println("inside if");
          return true;
        }
        System.out.println("inside else");
        return false;
      }
    });
  }
  /**
   * 
   */
  public HttpsClient() {
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      SpringBusFactory bf = new SpringBusFactory();
      Bus bus = bf.createBus("client.xml");
      BusFactory.setDefaultBus(bus);
      GreetingsService greetingService;
      URL wsdlLocation = new URL("https://localhost:666/Greetings?wsdl");
      greetingService = new GreetingsService(wsdlLocation);
      Greetings port = greetingService.getGreetingsPort();
      GreetingsRequest parameterIn = new GreetingsRequest();
      parameterIn.setName("Kaustuv");
      GreetingsResponse res = new GreetingsResponse();
      res = port.greetings(parameterIn);
    }
    catch (Exception Ex) {
      Ex.printStackTrace();
    }
  }

}
