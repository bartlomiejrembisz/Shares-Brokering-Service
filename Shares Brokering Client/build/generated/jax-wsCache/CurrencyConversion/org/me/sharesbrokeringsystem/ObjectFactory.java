
package org.me.sharesbrokeringsystem;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.sharesbrokeringsystem package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConvertAmount_QNAME = new QName("http://currencyconversion.me.org/", "convertAmount");
    private final static QName _ConvertAmountResponse_QNAME = new QName("http://currencyconversion.me.org/", "convertAmountResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.sharesbrokeringsystem
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SharePrice }
     * 
     */
    public SharePrice createSharePrice() {
        return new SharePrice();
    }

    /**
     * Create an instance of {@link ConvertAmount }
     * 
     */
    public ConvertAmount createConvertAmount() {
        return new ConvertAmount();
    }

    /**
     * Create an instance of {@link ConvertAmountResponse }
     * 
     */
    public ConvertAmountResponse createConvertAmountResponse() {
        return new ConvertAmountResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertAmount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://currencyconversion.me.org/", name = "convertAmount")
    public JAXBElement<ConvertAmount> createConvertAmount(ConvertAmount value) {
        return new JAXBElement<ConvertAmount>(_ConvertAmount_QNAME, ConvertAmount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConvertAmountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://currencyconversion.me.org/", name = "convertAmountResponse")
    public JAXBElement<ConvertAmountResponse> createConvertAmountResponse(ConvertAmountResponse value) {
        return new JAXBElement<ConvertAmountResponse>(_ConvertAmountResponse_QNAME, ConvertAmountResponse.class, null, value);
    }

}
