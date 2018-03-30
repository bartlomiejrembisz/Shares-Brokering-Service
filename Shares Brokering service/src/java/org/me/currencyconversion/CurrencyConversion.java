/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.currencyconversion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;
import org.json.JSONException;
import org.json.JSONObject;
import org.me.sharesbrokeringsystem.SharePrice;

/**
 *
 * @author Bartek
 */
@WebService(serviceName = "CurrencyConversion")
@Stateless()
public class CurrencyConversion
{
    String apiKey = "3ECXQS3RP6QBYLD0";
    
    /**
     * Web service operation
     * @param amount
     * @param to
     * @return 
     */
    @WebMethod(operationName = "convertAmount")
    public SharePrice convertAmount(@WebParam(name = "amount") SharePrice amount, @WebParam(name = "to") String to)
    {
        SharePrice newPrice = new SharePrice();
        try
        {
            if (amount.getCurrency().equals(to))
                return amount;
            
            String urlString = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=" +
                    amount.getCurrency() + "&to_currency=" +
                    to + "&apikey=" +
                    this.apiKey;
            
            StringBuilder sb = new StringBuilder();
            
            try
            {
                URL urlObject = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
                if (conn.getResponseCode() != 200)
                {
                    throw new IOException(conn.getResponseMessage());
                }
                BufferedReader ins = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inString;
                while ((inString = ins.readLine()) != null)
                {
                    sb.append(inString + "\n");
                }
                System.out.println(sb.toString());
            } catch (MalformedURLException ex)
            {
                Logger.getLogger(CurrencyConversion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(CurrencyConversion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JSONObject json = new JSONObject(sb.toString());
            JSONObject currencyObject = json.getJSONObject("Realtime Currency Exchange Rate");
            double conversionRate = currencyObject.getDouble("5. Exchange Rate");
            
            newPrice.setValue(amount.getValue() * conversionRate);
            newPrice.setCloseValue(amount.getCloseValue() * conversionRate);
            newPrice.setHighValue(amount.getHighValue() * conversionRate);
            newPrice.setLowValue(amount.getLowValue() * conversionRate);
            newPrice.setOpenValue(amount.getOpenValue() * conversionRate);
            newPrice.setCurrency(to);
            newPrice.setLastUpdate(amount.getLastUpdate());
            
            return newPrice;
        } catch (JSONException ex)
        {
            Logger.getLogger(CurrencyConversion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newPrice;
    }
    
}
