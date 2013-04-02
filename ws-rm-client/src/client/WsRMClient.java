package client;

import java.io.File;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

import com.kaustuv.poc.ws.service.greetings.Greetings;
import com.kaustuv.poc.ws.service.greetings.GreetingsRequest;
import com.kaustuv.poc.ws.service.greetings.GreetingsService;

import common.MessageLossSimulator;

public class WsRMClient {

  public WsRMClient() {
  }

  public static void main(String[] args) throws Exception{
    try {
      SpringBusFactory bf = new SpringBusFactory();
      Bus bus = bf.createBus("ws_rm.xml");
      BusFactory.setDefaultBus(bus);
      bus.getOutInterceptors().add(new MessageLossSimulator());
      GreetingsService greetingService;
        URL wsdlURL;
        wsdlURL = new URL("http://localhost:8080/ws-rm-poc/Greetings?wsdl");
        greetingService = new GreetingsService(wsdlURL);
      Greetings port = greetingService.getGreetingsPort();
      String[] names = new String[] { "abc", "xyz", "rpc", "jaxws" , "jaxrs"  };
      // make a sequence of invocations
      for (int i = 0; i < names.length; i++) {
        System.out.println("Invoking OneWay..."+i);
        GreetingsRequest parameterIn = new GreetingsRequest();
        parameterIn.setName( (names[i]));
        port.greetings(parameterIn);
        System.out.println("No response as method is OneWay\n");
      }
      // allow aynchronous resends to occur
      Thread.sleep(30 * 1000);
      bus.shutdown(true);
    }
    catch (Exception Ex) {
      Ex.printStackTrace();
    }
  }
}
