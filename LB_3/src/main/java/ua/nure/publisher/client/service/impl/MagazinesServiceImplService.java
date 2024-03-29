package ua.nure.publisher.client.service.impl;

import ua.nure.publisher.client.service.MagazinesService;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebServiceClient(name = "MagazinesServiceImplService", targetNamespace = "http://impl.service.server.publisher.nure.ua/", wsdlLocation = "file:/D:/Study/7 \u0441\u0435\u043c\u0435\u0441\u0442\u0440/INTERNET/Publisher/LB_3/lab_3/src/main/resources/publisher.wsdl")
public class MagazinesServiceImplService extends Service {

    private final static URL MAGAZINESSERVICEIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException MAGAZINESSERVICEIMPLSERVICE_EXCEPTION;
    private final static QName MAGAZINESSERVICEIMPLSERVICE_QNAME = new QName("http://impl.service.server.publisher.nure.ua/", "MagazinesServiceImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/Study/7 \u0441\u0435\u043c\u0435\u0441\u0442\u0440/INTERNET/Publisher/LB_3/lab_3/src/main/resources/publisher.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MAGAZINESSERVICEIMPLSERVICE_WSDL_LOCATION = url;
        MAGAZINESSERVICEIMPLSERVICE_EXCEPTION = e;
    }

    public MagazinesServiceImplService() {
        super(__getWsdlLocation(), MAGAZINESSERVICEIMPLSERVICE_QNAME);
    }

    public MagazinesServiceImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MAGAZINESSERVICEIMPLSERVICE_QNAME, features);
    }

    public MagazinesServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, MAGAZINESSERVICEIMPLSERVICE_QNAME);
    }

    public MagazinesServiceImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MAGAZINESSERVICEIMPLSERVICE_QNAME, features);
    }

    public MagazinesServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MagazinesServiceImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * @return returns MagazinesService
     */
    @WebEndpoint(name = "MagazinesServiceImplPort")
    public MagazinesService getMagazinesServiceImplPort() {
        return super.getPort(new QName("http://impl.service.server.publisher.nure.ua/", "MagazinesServiceImplPort"), MagazinesService.class);
    }

    /**
     * @param features A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return returns MagazinesService
     */
    @WebEndpoint(name = "MagazinesServiceImplPort")
    public MagazinesService getMagazinesServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.service.server.publisher.nure.ua/", "MagazinesServiceImplPort"), MagazinesService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MAGAZINESSERVICEIMPLSERVICE_EXCEPTION != null) {
            throw MAGAZINESSERVICEIMPLSERVICE_EXCEPTION;
        }
        return MAGAZINESSERVICEIMPLSERVICE_WSDL_LOCATION;
    }

}
