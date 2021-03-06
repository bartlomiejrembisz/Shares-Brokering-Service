
package org.me.sharesbrokeringsystem;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CurrencyConversion", targetNamespace = "http://currencyconversion.me.org/", wsdlLocation = "http://localhost:8080/CurrencyConversion/CurrencyConversion?wsdl")
public class CurrencyConversion_Service
    extends Service
{

    private final static URL CURRENCYCONVERSION_WSDL_LOCATION;
    private final static WebServiceException CURRENCYCONVERSION_EXCEPTION;
    private final static QName CURRENCYCONVERSION_QNAME = new QName("http://currencyconversion.me.org/", "CurrencyConversion");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/CurrencyConversion/CurrencyConversion?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        CURRENCYCONVERSION_WSDL_LOCATION = url;
        CURRENCYCONVERSION_EXCEPTION = e;
    }

    public CurrencyConversion_Service() {
        super(__getWsdlLocation(), CURRENCYCONVERSION_QNAME);
    }

    public CurrencyConversion_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), CURRENCYCONVERSION_QNAME, features);
    }

    public CurrencyConversion_Service(URL wsdlLocation) {
        super(wsdlLocation, CURRENCYCONVERSION_QNAME);
    }

    public CurrencyConversion_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CURRENCYCONVERSION_QNAME, features);
    }

    public CurrencyConversion_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CurrencyConversion_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns CurrencyConversion
     */
    @WebEndpoint(name = "CurrencyConversionPort")
    public CurrencyConversion getCurrencyConversionPort() {
        return super.getPort(new QName("http://currencyconversion.me.org/", "CurrencyConversionPort"), CurrencyConversion.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CurrencyConversion
     */
    @WebEndpoint(name = "CurrencyConversionPort")
    public CurrencyConversion getCurrencyConversionPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://currencyconversion.me.org/", "CurrencyConversionPort"), CurrencyConversion.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CURRENCYCONVERSION_EXCEPTION!= null) {
            throw CURRENCYCONVERSION_EXCEPTION;
        }
        return CURRENCYCONVERSION_WSDL_LOCATION;
    }

}
