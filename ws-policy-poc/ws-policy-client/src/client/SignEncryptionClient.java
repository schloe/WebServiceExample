package client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;

import com.kaustuv.poc.ws.service.greetings.Greetings;
import com.kaustuv.poc.ws.service.greetings.GreetingsRequest;
import com.kaustuv.poc.ws.service.greetings.GreetingsResponse;

/**
 * @author KMaji
 *
 */
public class SignEncryptionClient {

  /**
   * 
   */
  public SignEncryptionClient() {
  }

  /**
   * @param args
   * @throws MalformedURLException 
   */
  public static void main(String[] args) throws MalformedURLException {
    Service service = Service.create(new URL("http://localhost:8080/ws-policy/Greetings?wsdl"),
                                     new QName("http://poc.kaustuv.com/ws/service/greetings",
                                               "GreetingsService"));

    Greetings greetings = service.getPort(Greetings.class);
    Client client = ClientProxy.getClient(greetings);
    Endpoint endPoint = client.getEndpoint();
    endPoint.getOutInterceptors().add(new LoggingOutInterceptor());
    endPoint.getInInterceptors().add(new LoggingInInterceptor());

    Map<String, Object> ctx = ((BindingProvider) greetings).getRequestContext();

    ctx.put("ws-security.signature.properties", "clientKeystore.properties");
    ctx.put("ws-security.signature.username", "myclientkey");
    ctx.put("ws-security.encryption.properties", "clientKeystore.properties");
    ctx.put("ws-security.encryption.username", "myservicekey");
    ctx.put("ws-security.callback-handler", "client.SignEncrClientCallback");

    GreetingsRequest gReq = new GreetingsRequest();
    gReq.setName("kaustuv");

    GreetingsResponse gRes = null;
    try {
      gRes = greetings.greetings(gReq);
      System.out.println("Response" + gRes.getMessage());
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  
  }

}
