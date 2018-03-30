/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.me.sharesbrokeringsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebParam;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.sql.*;
import javax.ejb.EJB;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.me.currencyconversion.CurrencyConversion;

/**
 *
 * @author Bartek
 */
@WebService(serviceName = "SharesBrokeringSystem")
@Stateless()
public class SharesBrokeringSystem {

    @EJB
    private CurrencyConversion currencyConversion;

    String apiKey = "3ECXQS3RP6QBYLD0";
    CurrentTrades companyList = new CurrentTrades();
    List<Client> loggedIn = new ArrayList<>();

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "companyList")
    public java.util.List<Company> companyList()
    {
        InputStream file = SharesBrokeringSystem.class.getResourceAsStream("currentTrades.xml");

        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(companyList.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            companyList = (CurrentTrades) unmarshaller.unmarshal(file); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) 
        {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
        companyList = updateCompanies(companyList);
        
        return companyList.getTradeList();
    }

    /**
     * Web service operation
     * @param _company
     * @return 
     */
    @WebMethod(operationName = "moreCompanyInfo")
    public Company moreCompanyInfo(@WebParam(name = "_company") Company _company) throws CertificateException 
    {
        try 
        {
            String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + _company.getSymbol() + "&interval=1min&apikey="
                    + this.apiKey;
            
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() 
            {
                return null;
            }
            public void checkClientTrusted(X509Certificate[] certs, String authType) { }
            public void checkServerTrusted(X509Certificate[] certs, String authType) { }
            }
            };
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
            
            System.out.println(urlString);
            StringBuilder sb = new StringBuilder();
            
            try 
            {
                URL urlObject = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
                if (conn.getResponseCode() != 200) {
                    throw new IOException(conn.getResponseMessage());
                }
                BufferedReader ins = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inString;
                while ((inString = ins.readLine()) != null)
                {
                    sb.append(inString + "\n");
                }
            } catch (MalformedURLException ex)
            {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JSONObject json = new JSONObject(sb.toString());
            JSONObject metaData = json.getJSONObject("Meta Data");
            JSONObject timeSeries = json.getJSONObject("Time Series (1min)");
            
            List<Timestamp> timeStamps = new ArrayList<>();
            try
            {
                Iterator<String> iter = timeSeries.keys();
                while(iter.hasNext())
                {
                    String key = iter.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    Date parsedDate = dateFormat.parse(key);
                    timeStamps.add(new Timestamp(parsedDate.getTime()));
                }
                
            } catch (ParseException ex)
            {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Timestamp mostRecent = (Timestamp) Collections.max(timeStamps); 
            String mostRecentKey = mostRecent.toString().substring(0, mostRecent.toString().length() - 2);
            
            JSONObject mostRecentObject = timeSeries.getJSONObject(mostRecentKey);
            
            SharePrice tempPrice = new SharePrice();
            tempPrice.setCurrency(_company.getPrice().getCurrency());
            tempPrice.setOpenValue(mostRecentObject.getDouble("1. open"));
            tempPrice.setHighValue(mostRecentObject.getDouble("2. high"));
            tempPrice.setLowValue(mostRecentObject.getDouble("3. low"));
            tempPrice.setCloseValue(mostRecentObject.getDouble("4. close"));
            tempPrice.setValue(_company.getPrice().getValue());
            
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(mostRecent.getTime());
            try
            {
                tempPrice.setLastUpdate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
                        calendar));
            } catch (DatatypeConfigurationException ex) {}
            
            _company.setPrice(tempPrice);
            
            return _company ;
        } catch (JSONException ex) 
        {
            Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _company;
    }


    /**
     * Web service operation
     * @param _currentTrades
     * @return 
     */
    @WebMethod(operationName = "updateCompanies")
    public CurrentTrades updateCompanies(@WebParam(name = "_currentTrades") CurrentTrades _currentTrades)
    {
        System.out.println(_currentTrades.tradeList.size());
        for (int i = 0; i < _currentTrades.tradeList.size(); i += 100)
        {
            String url = "https://www.alphavantage.co/query?function=BATCH_STOCK_QUOTES&symbols=";
            String companySymbols = new String();
        
            for (int j = 0; j < 100; j++)
            {
                try
                {
                    if (i + j >= _currentTrades.tradeList.size()) break;
                    companySymbols += (_currentTrades.tradeList.get(j + i).symbol + ",");
                } catch(Exception ex) {}
            }
            
            url += (companySymbols + "&apikey=");
            url += apiKey;
            
            
            StringBuilder sb = new StringBuilder();
        
            try 
            {
                            
                TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() 
                {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                public void checkServerTrusted(X509Certificate[] certs, String authType) { }
                }
                };
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                // Create all-trusting host name verifier
                HostnameVerifier allHostsValid = new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };

                // Install the all-trusting host verifier
                HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

                url = url.replace(" ", "");
                URL urlObject = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
                if (conn.getResponseCode() != 200) {
                    throw new IOException(conn.getResponseMessage());
                }
                BufferedReader ins = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inString;
                while ((inString = ins.readLine()) != null) 
                {
                    sb.append(inString + "\n");
                }
                
                
                JSONObject websiteJSON = new JSONObject(sb.toString());
                JSONArray companyArray = websiteJSON.getJSONArray("Stock Quotes");
                
                for (int j = 0; j < 100; j++)
                {
                    try
                    {
                        if (i + j >= _currentTrades.tradeList.size()) break;
                        JSONObject company = companyArray.getJSONObject(j);

                        _currentTrades.tradeList.get(i + j).getPrice().setValue(company.getDouble("2. price"));
                        _currentTrades.tradeList.get(i + j).getPrice().setCurrency("USD");
                    } catch(Exception ex) {}
                }
            } catch (MalformedURLException ex) 
            {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return _currentTrades;
    }

    /**
     * Web service operation
     * @param username
     * @param password
     * @return 
     */
    @WebMethod(operationName = "logIn")
    public Client logIn(@WebParam(name = "username") String username, @WebParam(name = "password") String password)
    {
        Statement stmt = null;
        String query = "SELECT * FROM CLIENTS WHERE USERNAME='" + username +"' AND password='" + password + "'";
        
        Client newClient = new Client();
        try
        {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:tcp://localhost/C:\\Users\\Bartek\\Desktop\\Shares Brokering service\\clients", "sa", "");
            
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                newClient.setId(rs.getInt("ID"));
                newClient.setUsername(rs.getString("USERNAME"));
                newClient.setBalance(rs.getDouble("BALANCE"));
                newClient.sharesList = new ArrayList<SymbolVolume>();

                query = "SELECT SYMBOL, VOLUME FROM CLIENTCOMPANYRELATION WHERE CLIENTID=" + newClient.getId();

                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next())
                {
                    SymbolVolume temp = new SymbolVolume();
                    temp.symbol = rs.getString("SYMBOL");
                    temp.volume = rs.getString("VOLUME");
                    newClient.sharesList.add(temp);
                }
                loggedIn.add(newClient);
                
                for (Client c : loggedIn)
                {
                    System.out.println(c.getUsername());
                }
        
                return newClient;
            }
            else
            {
                Client tempClient = new Client();
                tempClient.setId(-1);
            }
            
        } catch(Exception ex) 
        {
            System.out.println(ex);
        }
        
        return newClient;
    }

    /**
     * Web service operation
     * @param username
     * @param password
     * @return 
     */
    @WebMethod(operationName = "signUp")
    public int signUp(@WebParam(name = "username") String username, @WebParam(name = "password") String password)
    {
        Statement stmt = null;
        String query = "SELECT ID FROM CLIENTS WHERE USERNAME='" + username +"'";
        
        try
        {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:tcp://localhost/C:\\Users\\Bartek\\Desktop\\Shares Brokering service\\clients", "sa", "");
            
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
                return 0;
            else
            {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO CLIENTS VALUES(NULL,'" + username + "','" + password +"',0)");
                conn.close();
                return 1;
            }
        } catch (SQLException | ClassNotFoundException ex)
        {
            Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Signup executed");
        return 0;
    }

    /**
     * Web service operation
     * @param client
     * @return 
     */
    @WebMethod(operationName = "logOut")
    public int logOut(@WebParam(name = "client") Client client)
    {
        for (Client c : loggedIn)
        {
            if(c.id == client.id)
            {
                loggedIn.remove(c);
                return 1;
            }
        }
        return 0;
    }

    /**
     * Web service operation
     * @param client
     * @param amount
     * @param currency
     * @return 
     */
    @WebMethod(operationName = "depositAmount")
    public double depositAmount(@WebParam(name = "client") Client client, @WebParam(name = "amount") double amount, @WebParam(name = "currency") String currency)
    {
        if (!currency.equals("USD"))
        {
            SharePrice tempPrice = new SharePrice();
            tempPrice.setValue(amount);
            tempPrice.setCurrency(currency);
            tempPrice.setCloseValue(0);
            tempPrice.setHighValue(0);
            tempPrice.setLastUpdate(null);
            tempPrice.setLowValue(0);
            tempPrice.setOpenValue(0);
            amount = currencyConversion.convertAmount(tempPrice, "USD").getValue();
        }
        double actualAmount = client.getBalance() + amount;
        Statement stmt = null;
        String query = "UPDATE CLIENTS SET BALANCE=" + actualAmount + " WHERE ID=" + client.getId();
        try
        {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.
                    getConnection("jdbc:h2:tcp://localhost/C:\\Users\\Bartek\\Desktop\\Shares Brokering service\\clients", "sa", "");

            stmt = conn.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Updated");
        } catch (Exception ex) 
        {
            System.out.println(ex);
        }
        return actualAmount;
    }

    /**
     * Web service operation
     * @param client
     * @param company
     * @param volume
     */
    @WebMethod(operationName = "buyShares")
    @Oneway
    public void buyShares(@WebParam(name = "client") Client client, @WebParam(name = "company") Company company, @WebParam(name = "volume") int volume)
    {
        CurrentTrades currentTrades = new CurrentTrades();
        Company targetCompany = null;
        InputStream file = SharesBrokeringSystem.class.getResourceAsStream("currentTrades.xml");

        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(currentTrades.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            currentTrades = (CurrentTrades) unmarshaller.unmarshal(file); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) 
        {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
        for (Company c : currentTrades.tradeList)
        {
            if(c.symbol.equals(company.getSymbol()))
            {
                targetCompany = c;
            }
        }
        
        int currentVolume = Integer.parseInt(targetCompany.getVolume());
        currentVolume = currentVolume - volume;
        targetCompany.setVolume(Integer.toString(currentVolume));
        
        System.out.println("File loaded and company changed");
        try
        {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(currentTrades.getClass().getPackage().getName());
            javax.xml.bind.Marshaller marshaller = jaxbCtx.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            String path = SharesBrokeringSystem.class.getResource("currentTrades.xml").getPath();
            path = path.substring(1, path.lastIndexOf("build"));
            path += "src\\java\\org\\me\\sharesbrokeringsystem";
            path = path.replace("%20", " ");
            path = path.replace("/", "\\");
            path += "\\currentTrades.xml";
            OutputStream os = new FileOutputStream(path);
            marshaller.marshal(currentTrades, os);
            os.close();
        } catch (javax.xml.bind.JAXBException ex)
        {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        Statement stmt = null;
        String query = "SELECT * FROM CLIENTCOMPANYRELATION WHERE SYMBOL='" + company.getSymbol() + "' AND CLIENTID='"
                + client.getId() + "'";
        try
        {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.
                        getConnection("jdbc:h2:tcp://localhost/C:\\Users\\Bartek\\Desktop\\Shares Brokering service\\clients", "sa", "");

            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                int newVolume = rs.getInt("VOLUME") + volume;
                stmt = conn.createStatement();
                stmt.executeUpdate("UPDATE CLIENTCOMPANYRELATION SET VOLUME='" + newVolume + "' WHERE SYMBOL='" + company.getSymbol() + "' AND CLIENTID='"
                + client.getId() + "'");
            }
            else
            {
                stmt = conn.createStatement();
                stmt.executeUpdate("INSERT INTO CLIENTCOMPANYRELATION VALUES(null,'" + company.getSymbol() + "'," + client.getId() + "," + volume + ")");
            }
            
            stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE CLIENTS SET BALANCE='" + client.getBalance() + "' WHERE ID='" + client.getId() + "'");
            
        } catch (ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(SharesBrokeringSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "sellShares")
    @Oneway
    public void sellShares(@WebParam(name = "client") Client client, @WebParam(name = "company") Company company, @WebParam(name = "volume") int volume)
    {
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getCompanies")
    public List<Company> getCompanies()
    {
        InputStream file = SharesBrokeringSystem.class.getResourceAsStream("currentTrades.xml");

        CurrentTrades newTrades = new CurrentTrades();
        try {
            javax.xml.bind.JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(newTrades.getClass().getPackage().getName());
            javax.xml.bind.Unmarshaller unmarshaller = jaxbCtx.createUnmarshaller();
            newTrades = (CurrentTrades) unmarshaller.unmarshal(file); //NOI18N
        } catch (javax.xml.bind.JAXBException ex) 
        {
            // XXXTODO Handle exception
            java.util.logging.Logger.getLogger("global").log(java.util.logging.Level.SEVERE, null, ex); //NOI18N
        }
        
        return newTrades.getTradeList();
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getClient")
    public Client getClient(@WebParam(name = "id") int id)
    {
        //TODO write your implementation code here:
        return null;
    }


}

