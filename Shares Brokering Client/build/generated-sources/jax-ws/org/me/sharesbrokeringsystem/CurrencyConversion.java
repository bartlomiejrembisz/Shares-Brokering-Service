
package org.me.sharesbrokeringsystem;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CurrencyConversion", targetNamespace = "http://currencyconversion.me.org/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CurrencyConversion {


    /**
     * 
     * @param amount
     * @param to
     * @return
     *     returns org.me.sharesbrokeringsystem.SharePrice
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "convertAmount", targetNamespace = "http://currencyconversion.me.org/", className = "org.me.sharesbrokeringsystem.ConvertAmount")
    @ResponseWrapper(localName = "convertAmountResponse", targetNamespace = "http://currencyconversion.me.org/", className = "org.me.sharesbrokeringsystem.ConvertAmountResponse")
    @Action(input = "http://currencyconversion.me.org/CurrencyConversion/convertAmountRequest", output = "http://currencyconversion.me.org/CurrencyConversion/convertAmountResponse")
    public SharePrice convertAmount(
        @WebParam(name = "amount", targetNamespace = "")
        SharePrice amount,
        @WebParam(name = "to", targetNamespace = "")
        String to);

}
