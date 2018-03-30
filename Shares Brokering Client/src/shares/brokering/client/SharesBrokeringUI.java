package shares.brokering.client;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.me.sharesbrokeringsystem.CertificateException_Exception;
import org.me.sharesbrokeringsystem.Company;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;
import org.me.sharesbrokeringsystem.Client;
import org.me.sharesbrokeringsystem.SharePrice;
import org.me.sharesbrokeringsystem.SymbolVolume;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bartek
 */
public final class SharesBrokeringUI extends javax.swing.JFrame 
{
    DefaultTableModel model;
    DefaultTableModel ownedSharesModel;
    ListSelectionModel companyListSelectionModel;
    List<Company> companyList;
    List<Company> ownedCompanyShares;
    Client thisClient = null;
    
    String[] currencyCodes = { "AUD", "BGN", "BRL", "BWP", "CAD", "CHF", "CLP", "CNY", "COP", "DKK", "EEK", 
    "EGP", "EUR", "GBP", "HKD", "HRK", "HUF", "ILS", "INR", "ISK", "JPY", "KRW",
    "KZT", "LKR", "LTL", "LVL", "LYD", "MXN", "MYR", "NOK", "NPR", "NZD", "OMR",
    "PKR", "QAR", "RON", "RUB", "SAR", "SDG", "SEK", "SGD", "THB", "TRY", "TTD",
    "TWD", "UAH", "USD", "VEB", "ZAR" };
    
    /**
     * Creates new form SharesBrokeringUI
     */
    public SharesBrokeringUI(Client _newClient) 
    {
        initComponents();
        this.thisClient = _newClient;
        this.model = (DefaultTableModel) TradeList.getModel();
        this.ownedSharesModel = (DefaultTableModel) ownedSharesList.getModel();
        this.companyListSelectionModel = (ListSelectionModel) TradeList.getSelectionModel();
        this.ownedCompanyShares = new ArrayList<>();
        
        try
        {
            companyList = companyList();
        } catch (Exception ex) {}
        
        loadContentToTable();
        updateOwnedSharesTable();
        
        for (SymbolVolume c : thisClient.getSharesList())
        {
            System.out.println(c.getSymbol());
        }
        
        Timer timer = new Timer();
        TimerTask task = new TimerTask() 
        {
            @Override
            public void run() 
            {
                balanceLabel.setText(Double.toString(Math.floor(thisClient.getBalance())) + " USD");
            }
        };      
        timer.scheduleAtFixedRate(task, 1000, 1000);
        currencyCombo.setSelectedItem((String) "USD");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        TradeListPane = new javax.swing.JScrollPane();
        TradeList = new javax.swing.JTable();
        moreInfoButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        depositButton = new javax.swing.JButton();
        amountField = new javax.swing.JTextField();
        currencyCombo = new javax.swing.JComboBox<>();
        balanceTextLabel = new javax.swing.JLabel();
        balanceLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        TradeListPane1 = new javax.swing.JScrollPane();
        ownedSharesList = new javax.swing.JTable();
        buySharesButton = new javax.swing.JButton();
        buySharesAmount = new javax.swing.JTextField();
        sellSharesVolume = new javax.swing.JTextField();
        sellSharesButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        TradeList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Symbol", "Name", "Sector", "Volume", "Price", "Currency"
            }
        )
        {
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        TradeList.setColumnSelectionAllowed(false);
        TradeListPane.setViewportView(TradeList);
        TradeList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        if (TradeList.getColumnModel().getColumnCount() > 0)
        {
            TradeList.getColumnModel().getColumn(0).setResizable(false);
            TradeList.getColumnModel().getColumn(1).setResizable(false);
            TradeList.getColumnModel().getColumn(2).setResizable(false);
            TradeList.getColumnModel().getColumn(3).setResizable(false);
            TradeList.getColumnModel().getColumn(4).setResizable(false);
            TradeList.getColumnModel().getColumn(5).setResizable(false);
        }
        JComboBox comboBox = new JComboBox<>(currencyCodes);
        comboBox.addItemListener(new ItemListener()
            {
                @Override
                public void itemStateChanged(ItemEvent e)
                {
                    if(e.getStateChange() == ItemEvent.SELECTED)
                    {
                        try
                        {
                            Company tempComp = companyList.get(TradeList.getSelectedRow());

                            String selectedCurrency = (String) comboBox.getSelectedItem();
                            SharePrice newPrice = convertAmount(tempComp.getPrice(), selectedCurrency);

                            System.out.println(tempComp.getPrice().getHighValue() + " "
                                + tempComp.getPrice().getCloseValue());
                            tempComp.setPrice(newPrice);

                            model.setValueAt(Math.floor(tempComp.getPrice().getValue() * 100) / 100, TradeList.getSelectedRow(), 4);
                            //loadContentToTable();
                        } catch (Exception ex)
                        {
                            System.out.println(ex.toString());
                        }
                    }
                }
            });

            TableColumn comboColumn = TradeList.getColumnModel().getColumn(5);
            comboColumn.setCellEditor(new DefaultCellEditor(comboBox));

            moreInfoButton.setText("More info");
            moreInfoButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    moreInfoButtonActionPerformed(evt);
                }
            });

            logOutButton.setText("Log out");
            logOutButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    logOutButtonActionPerformed(evt);
                }
            });

            searchButton.setText("Advanced Search");
            searchButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    searchButtonActionPerformed(evt);
                }
            });

            depositButton.setText("Deposit Money");
            depositButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    depositButtonActionPerformed(evt);
                }
            });

            currencyCombo.setModel(new javax.swing.DefaultComboBoxModel<>(currencyCodes));

            balanceTextLabel.setText("Balance:");

            jLabel1.setText("Currently available shares:");

            jLabel2.setText("Currently obtained shares:");

            ownedSharesList.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][]
                {

                },
                new String []
                {
                    "Symbol", "Name", "Sector", "Volume", "Price", "Currency"
                }
            )
            {
                boolean[] canEdit = new boolean []
                {
                    false, false, false, false, false, true
                };

                public boolean isCellEditable(int rowIndex, int columnIndex)
                {
                    return canEdit [columnIndex];
                }
            });
            ownedSharesList.setColumnSelectionAllowed(false);
            TradeListPane1.setViewportView(ownedSharesList);
            ownedSharesList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            if (ownedSharesList.getColumnModel().getColumnCount() > 0)
            {
                ownedSharesList.getColumnModel().getColumn(0).setResizable(false);
                ownedSharesList.getColumnModel().getColumn(1).setResizable(false);
                ownedSharesList.getColumnModel().getColumn(2).setResizable(false);
                ownedSharesList.getColumnModel().getColumn(3).setResizable(false);
                ownedSharesList.getColumnModel().getColumn(4).setResizable(false);
                ownedSharesList.getColumnModel().getColumn(5).setResizable(false);
            }

            buySharesButton.setText("Buy");
            buySharesButton.addActionListener(new java.awt.event.ActionListener()
            {
                public void actionPerformed(java.awt.event.ActionEvent evt)
                {
                    buySharesButtonActionPerformed(evt);
                }
            });

            sellSharesButton.setText("Sell");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(logOutButton)
                                    .addGap(14, 14, 14))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(searchButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buySharesAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(buySharesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(moreInfoButton)
                                    .addGap(17, 17, 17))))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TradeListPane, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(TradeListPane1)))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addComponent(jLabel1)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)
                    .addComponent(currencyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(depositButton)
                    .addGap(18, 18, 18)
                    .addComponent(balanceTextLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(129, 129, 129)
                    .addComponent(sellSharesVolume, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(sellSharesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(99, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(TradeListPane, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(moreInfoButton)
                        .addComponent(searchButton)
                        .addComponent(buySharesButton)
                        .addComponent(buySharesAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(TradeListPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(depositButton)
                            .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currencyCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(balanceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(balanceTextLabel)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sellSharesVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sellSharesButton)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                    .addComponent(logOutButton)
                    .addContainerGap())
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void moreInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreInfoButtonActionPerformed
        try
        {
            Company tempCompany = companyList.get(TradeList.getSelectedRow());
            String tempCurrency = tempCompany.getPrice().getCurrency();
            
            if (!tempCurrency.equals("USD"))
            {
                tempCompany.setPrice(convertAmount(tempCompany.getPrice(), "USD"));
                System.out.println("Unconverting");
            }
            
            System.out.println("Fetching company info");
            tempCompany = moreCompanyInfo(tempCompany);
            
            if (!tempCurrency.equals("USD"))
            {
               tempCompany.setPrice(convertAmount(tempCompany.getPrice(), tempCurrency));
            }
            
            ShareInfo shareInfoDialog = new ShareInfo(tempCompany);
            shareInfoDialog.setVisible(true);
            shareInfoDialog.setLocationRelativeTo(this);
        } catch (ArrayIndexOutOfBoundsException ex) {} 
        catch (CertificateException_Exception ex)
        {
            Logger.getLogger(SharesBrokeringUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_moreInfoButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        if(logOut(thisClient) == 1)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositButtonActionPerformed
        double tempAmount = Double.parseDouble(amountField.getText());
        String tempCurrency = (String) currencyCombo.getSelectedItem();
        
        thisClient.setBalance(depositAmount(thisClient, tempAmount, tempCurrency));
    }//GEN-LAST:event_depositButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        advancedSearch searchWindow = new advancedSearch(companyList);
        searchWindow.setVisible(true);
        searchWindow.setLocationRelativeTo(this);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void buySharesButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_buySharesButtonActionPerformed
    {//GEN-HEADEREND:event_buySharesButtonActionPerformed
        String tempSymbol = (String) model.getValueAt(TradeList.getSelectedRow(), 0);
        System.out.println(TradeList.getSelectedRow());
        ownedSharesModel.addRow(new Object[]{
        (String) model.getValueAt(TradeList.getSelectedRow(), 0), 
        (String) model.getValueAt(TradeList.getSelectedRow(), 1),
        (String) model.getValueAt(TradeList.getSelectedRow(), 2),
        (String) model.getValueAt(TradeList.getSelectedRow(), 3),
        (String) model.getValueAt(TradeList.getSelectedRow(), 4),
        (String) model.getValueAt(TradeList.getSelectedRow(), 5)});
        int rowNumber = findRowNumber(tempSymbol);
        
        double currentBalance = thisClient.getBalance();
        double shareCost = Integer.parseInt(buySharesAmount.getText()) * Double.parseDouble((String) model.getValueAt(TradeList.getSelectedRow(), 4));
        currentBalance = currentBalance - shareCost;
        thisClient.setBalance(currentBalance);
        buyShares(thisClient, companyList.get(rowNumber), Integer.parseInt(buySharesAmount.getText()));
        
        companyList = getCompanies();
        loadContentToTable();
        
        ownedSharesList.repaint();
    }//GEN-LAST:event_buySharesButtonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TradeList;
    private javax.swing.JScrollPane TradeListPane;
    private javax.swing.JScrollPane TradeListPane1;
    private javax.swing.JTextField amountField;
    private javax.swing.JLabel balanceLabel;
    private javax.swing.JLabel balanceTextLabel;
    private javax.swing.JTextField buySharesAmount;
    private javax.swing.JButton buySharesButton;
    private javax.swing.JComboBox<String> currencyCombo;
    private javax.swing.JButton depositButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton logOutButton;
    private javax.swing.JButton moreInfoButton;
    private javax.swing.JTable ownedSharesList;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton sellSharesButton;
    private javax.swing.JTextField sellSharesVolume;
    // End of variables declaration//GEN-END:variables
    
    void loadContentToTable()
    {
        model.getDataVector().removeAllElements();
        for (Company c : companyList)
        {
            model.addRow(new Object[]{c.getSymbol(), c.getName(), c.getSector(), c.getVolume(), Double.toString(Math.floor(c.getPrice().getValue() * 100) / 100), 
                c.getPrice().getCurrency()});
        }
    }
    
    private void updateOwnedSharesTable()
    {
        ownedSharesModel.getDataVector().removeAllElements();
        for (SymbolVolume c : thisClient.getSharesList())
        {
            Company tempComp = companyList.get(findRowNumber(c.getSymbol()));
            ownedCompanyShares.add(tempComp);
            ownedSharesModel.addRow(new Object[]{tempComp.getSymbol(), tempComp.getName(), tempComp.getSector(), c.getVolume(), Double.toString(Math.floor(tempComp.getPrice().getValue() * 100) / 100), 
                tempComp.getPrice().getCurrency()});
        }
    }
    
    int findRowNumber(String symbol)
    {
        for(int i = 0; i < companyList.size(); i++)
        {
            if (companyList.get(i).getSymbol().equals(symbol))
                return i;
        }
        return -1;
    }
    
    private static Company moreCompanyInfo(org.me.sharesbrokeringsystem.Company company) throws CertificateException_Exception {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        return port.moreCompanyInfo(company);
    }

    private static SharePrice convertAmount(org.me.sharesbrokeringsystem.SharePrice amount, java.lang.String from)
    {
        org.me.sharesbrokeringsystem.CurrencyConversion_Service service = new org.me.sharesbrokeringsystem.CurrencyConversion_Service();
        org.me.sharesbrokeringsystem.CurrencyConversion port = service.getCurrencyConversionPort();
        return port.convertAmount(amount, from);
    }

    private static int logOut(org.me.sharesbrokeringsystem.Client client)
    {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        return port.logOut(client);
    }

    private static int signUp(java.lang.String username, java.lang.String password)
    {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        return port.signUp(username, password);
    }

    private static Client logIn(java.lang.String username, java.lang.String password)
    {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        return port.logIn(username, password);
    }

    private static double depositAmount(org.me.sharesbrokeringsystem.Client client, double amount, java.lang.String currency)
    {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        return port.depositAmount(client, amount, currency);
    }

    private static void buyShares(org.me.sharesbrokeringsystem.Client client, org.me.sharesbrokeringsystem.Company company, int volume)
    {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        port.buyShares(client, company, volume);
    }

    private static java.util.List<org.me.sharesbrokeringsystem.Company> companyList()
    {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        return port.companyList();
    }

    private static java.util.List<org.me.sharesbrokeringsystem.Company> getCompanies()
    {
        org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service service = new org.me.sharesbrokeringsystem.SharesBrokeringSystem_Service();
        org.me.sharesbrokeringsystem.SharesBrokeringSystem port = service.getSharesBrokeringSystemPort();
        return port.getCompanies();
    }
}