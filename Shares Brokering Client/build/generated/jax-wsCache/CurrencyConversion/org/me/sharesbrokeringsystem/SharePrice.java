
package org.me.sharesbrokeringsystem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for share_price complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="share_price">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Currency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Value" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="OpenValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="CloseValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="HighValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="LowValue" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="lastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "share_price", namespace = "tradeList", propOrder = {
    "currency",
    "value",
    "openValue",
    "closeValue",
    "highValue",
    "lowValue",
    "lastUpdate"
})
public class SharePrice {

    @XmlElement(name = "Currency", required = true)
    protected String currency;
    @XmlElement(name = "Value")
    protected double value;
    @XmlElement(name = "OpenValue")
    protected double openValue;
    @XmlElement(name = "CloseValue")
    protected double closeValue;
    @XmlElement(name = "HighValue")
    protected double highValue;
    @XmlElement(name = "LowValue")
    protected double lowValue;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the value property.
     * 
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets the value of the openValue property.
     * 
     */
    public double getOpenValue() {
        return openValue;
    }

    /**
     * Sets the value of the openValue property.
     * 
     */
    public void setOpenValue(double value) {
        this.openValue = value;
    }

    /**
     * Gets the value of the closeValue property.
     * 
     */
    public double getCloseValue() {
        return closeValue;
    }

    /**
     * Sets the value of the closeValue property.
     * 
     */
    public void setCloseValue(double value) {
        this.closeValue = value;
    }

    /**
     * Gets the value of the highValue property.
     * 
     */
    public double getHighValue() {
        return highValue;
    }

    /**
     * Sets the value of the highValue property.
     * 
     */
    public void setHighValue(double value) {
        this.highValue = value;
    }

    /**
     * Gets the value of the lowValue property.
     * 
     */
    public double getLowValue() {
        return lowValue;
    }

    /**
     * Sets the value of the lowValue property.
     * 
     */
    public void setLowValue(double value) {
        this.lowValue = value;
    }

    /**
     * Gets the value of the lastUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the value of the lastUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

}
