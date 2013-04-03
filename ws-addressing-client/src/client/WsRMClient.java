package client;

import java.io.File;
import java.net.URL;
import java.util.Map;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.bus.spring.SpringBusFactory;
import org.apache.cxf.ws.addressing.AddressingBuilder;
import org.apache.cxf.ws.addressing.AddressingProperties;
import org.apache.cxf.ws.addressing.AttributedURIType;
import org.apache.cxf.ws.addressing.ObjectFactory;

import com.kaustuv.poc.ws.service.greetings.Greetings;
import com.kaustuv.poc.ws.service.greetings.GreetingsRequest;
import com.kaustuv.poc.ws.service.greetings.GreetingsService;
import common.MessageLossSimulator;

import static org.apache.cxf.ws.addressing.JAXWSAConstants.CLIENT_ADDRESSING_PROPERTIES;

public class WsRMClient {

  private static final ObjectFactory WSA_OBJECT_FACTORY = new ObjectFactory();

  public WsRMClient() {
  }

  public static void main(String[] args) throws Exception {
    try {
      SpringBusFactory bf = new SpringBusFactory();
      Bus bus = bf.createBus("ws_addressing_client.xml");
      BusFactory.setDefaultBus(bus);
      bus.getOutInterceptors().add(new MessageLossSimulator());
      GreetingsService greetingService;
      URL wsdlURL;
      wsdlURL = new URL("http://localhost:8080/ws-addressing/Greetings?wsdl");
      greetingService = new GreetingsService(wsdlURL);
      Greetings port = greetingService.getGreetingsPort();
      Map<String, Object> requestContext = ((BindingProvider) port).getRequestContext();
      requestContext.put(CLIENT_ADDRESSING_PROPERTIES, createMaps());
      String[] names = new String[] { "Anne", "Bill", "Chris", "Daisy" };
      // make a sequence of invocations
      for (int i = 0; i < names.length; i++) {
        System.out.println("Invoking OneWay..." + i);
        GreetingsRequest parameterIn = new GreetingsRequest();
        parameterIn.setName((names[i]));
        port.greetings(parameterIn);
        System.out.println("No response as method is OneWay\n");
      }
      // allow aynchronous resends to occur
       Thread.sleep(1500);
      bus.shutdown(true);
    }
    catch (Exception Ex) {
      Ex.printStackTrace();
    }
  }

  private static AddressingProperties createMaps() {
    // get Message Addressing Properties instance
    AddressingBuilder builder = AddressingBuilder.getAddressingBuilder();
    AddressingProperties maps = builder.newAddressingProperties();
    // set MessageID property
    AttributedURIType messageID = WSA_OBJECT_FACTORY.createAttributedURIType();
    messageID.setValue("urn:uuid:" + System.currentTimeMillis());
    maps.setMessageID(messageID);
    return maps;
  }
}
