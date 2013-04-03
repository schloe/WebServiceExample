package com.kaustuv.poc.ws.service.greetings;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.5.2
 * 2013-04-03T13:43:15.326+05:30
 * Generated source version: 2.5.2
 * 
 */
@WebServiceClient(name = "GreetingsService", 
                  wsdlLocation = "http://www.kaustuv.com/ws/service/?wsdl",
                  targetNamespace = "http://poc.kaustuv.com/ws/service/greetings") 
public class GreetingsService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://poc.kaustuv.com/ws/service/greetings", "GreetingsService");
    public final static QName GreetingsPort = new QName("http://poc.kaustuv.com/ws/service/greetings", "GreetingsPort");
    static {
        URL url = null;
        try {
            url = new URL("http://www.kaustuv.com/ws/service/?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(GreetingsService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://www.kaustuv.com/ws/service/?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public GreetingsService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public GreetingsService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GreetingsService() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns Greetings
     */
    @WebEndpoint(name = "GreetingsPort")
    public Greetings getGreetingsPort() {
        return super.getPort(GreetingsPort, Greetings.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Greetings
     */
    @WebEndpoint(name = "GreetingsPort")
    public Greetings getGreetingsPort(WebServiceFeature... features) {
        return super.getPort(GreetingsPort, Greetings.class, features);
    }

}
