
package org.me.sharesbrokeringsystem;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateCompanies complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateCompanies">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="_currentTrades" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="trade_list" type="{tradeList}Company" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateCompanies", propOrder = {
    "currentTrades"
})
public class UpdateCompanies {

    @XmlElement(name = "_currentTrades")
    protected UpdateCompanies.CurrentTrades currentTrades;

    /**
     * Gets the value of the currentTrades property.
     * 
     * @return
     *     possible object is
     *     {@link UpdateCompanies.CurrentTrades }
     *     
     */
    public UpdateCompanies.CurrentTrades getCurrentTrades() {
        return currentTrades;
    }

    /**
     * Sets the value of the currentTrades property.
     * 
     * @param value
     *     allowed object is
     *     {@link UpdateCompanies.CurrentTrades }
     *     
     */
    public void setCurrentTrades(UpdateCompanies.CurrentTrades value) {
        this.currentTrades = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="trade_list" type="{tradeList}Company" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "tradeList"
    })
    public static class CurrentTrades {

        @XmlElement(name = "trade_list", namespace = "http://sharesbrokeringsystem.me.org/")
        protected List<Company> tradeList;

        /**
         * Gets the value of the tradeList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the tradeList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getTradeList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Company }
         * 
         * 
         */
        public List<Company> getTradeList() {
            if (tradeList == null) {
                tradeList = new ArrayList<Company>();
            }
            return this.tradeList;
        }

    }

}
