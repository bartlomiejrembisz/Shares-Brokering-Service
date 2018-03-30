
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

    private final static QName _LogOut_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "logOut");
    private final static QName _CompanyListResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "companyListResponse");
    private final static QName _LogOutResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "logOutResponse");
    private final static QName _SignUpResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "signUpResponse");
    private final static QName _CompanyList_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "companyList");
    private final static QName _UpdateCompanies_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "updateCompanies");
    private final static QName _LogInResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "logInResponse");
    private final static QName _GetCompanies_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "getCompanies");
    private final static QName _LogIn_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "logIn");
    private final static QName _BuyShares_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "buyShares");
    private final static QName _SignUp_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "signUp");
    private final static QName _MoreCompanyInfoResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "moreCompanyInfoResponse");
    private final static QName _GetCompaniesResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "getCompaniesResponse");
    private final static QName _UpdateCompaniesResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "updateCompaniesResponse");
    private final static QName _CertificateException_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "CertificateException");
    private final static QName _DepositAmountResponse_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "depositAmountResponse");
    private final static QName _MoreCompanyInfo_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "moreCompanyInfo");
    private final static QName _DepositAmount_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "depositAmount");
    private final static QName _SellShares_QNAME = new QName("http://sharesbrokeringsystem.me.org/", "sellShares");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.sharesbrokeringsystem
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateCompanies }
     * 
     */
    public UpdateCompanies createUpdateCompanies() {
        return new UpdateCompanies();
    }

    /**
     * Create an instance of {@link UpdateCompaniesResponse }
     * 
     */
    public UpdateCompaniesResponse createUpdateCompaniesResponse() {
        return new UpdateCompaniesResponse();
    }

    /**
     * Create an instance of {@link DepositAmount }
     * 
     */
    public DepositAmount createDepositAmount() {
        return new DepositAmount();
    }

    /**
     * Create an instance of {@link SellShares }
     * 
     */
    public SellShares createSellShares() {
        return new SellShares();
    }

    /**
     * Create an instance of {@link CertificateException }
     * 
     */
    public CertificateException createCertificateException() {
        return new CertificateException();
    }

    /**
     * Create an instance of {@link DepositAmountResponse }
     * 
     */
    public DepositAmountResponse createDepositAmountResponse() {
        return new DepositAmountResponse();
    }

    /**
     * Create an instance of {@link MoreCompanyInfo }
     * 
     */
    public MoreCompanyInfo createMoreCompanyInfo() {
        return new MoreCompanyInfo();
    }

    /**
     * Create an instance of {@link LogIn }
     * 
     */
    public LogIn createLogIn() {
        return new LogIn();
    }

    /**
     * Create an instance of {@link BuyShares }
     * 
     */
    public BuyShares createBuyShares() {
        return new BuyShares();
    }

    /**
     * Create an instance of {@link SignUp }
     * 
     */
    public SignUp createSignUp() {
        return new SignUp();
    }

    /**
     * Create an instance of {@link MoreCompanyInfoResponse }
     * 
     */
    public MoreCompanyInfoResponse createMoreCompanyInfoResponse() {
        return new MoreCompanyInfoResponse();
    }

    /**
     * Create an instance of {@link GetCompaniesResponse }
     * 
     */
    public GetCompaniesResponse createGetCompaniesResponse() {
        return new GetCompaniesResponse();
    }

    /**
     * Create an instance of {@link LogInResponse }
     * 
     */
    public LogInResponse createLogInResponse() {
        return new LogInResponse();
    }

    /**
     * Create an instance of {@link GetCompanies }
     * 
     */
    public GetCompanies createGetCompanies() {
        return new GetCompanies();
    }

    /**
     * Create an instance of {@link CompanyList }
     * 
     */
    public CompanyList createCompanyList() {
        return new CompanyList();
    }

    /**
     * Create an instance of {@link LogOutResponse }
     * 
     */
    public LogOutResponse createLogOutResponse() {
        return new LogOutResponse();
    }

    /**
     * Create an instance of {@link SignUpResponse }
     * 
     */
    public SignUpResponse createSignUpResponse() {
        return new SignUpResponse();
    }

    /**
     * Create an instance of {@link LogOut }
     * 
     */
    public LogOut createLogOut() {
        return new LogOut();
    }

    /**
     * Create an instance of {@link CompanyListResponse }
     * 
     */
    public CompanyListResponse createCompanyListResponse() {
        return new CompanyListResponse();
    }

    /**
     * Create an instance of {@link org.me.sharesbrokeringsystem.CurrentTrades }
     * 
     */
    public org.me.sharesbrokeringsystem.CurrentTrades createCurrentTrades() {
        return new org.me.sharesbrokeringsystem.CurrentTrades();
    }

    /**
     * Create an instance of {@link Company }
     * 
     */
    public Company createCompany() {
        return new Company();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link SharePrice }
     * 
     */
    public SharePrice createSharePrice() {
        return new SharePrice();
    }

    /**
     * Create an instance of {@link SymbolVolume }
     * 
     */
    public SymbolVolume createSymbolVolume() {
        return new SymbolVolume();
    }

    /**
     * Create an instance of {@link UpdateCompanies.CurrentTrades }
     * 
     */
    public UpdateCompanies.CurrentTrades createUpdateCompaniesCurrentTrades() {
        return new UpdateCompanies.CurrentTrades();
    }

    /**
     * Create an instance of {@link UpdateCompaniesResponse.Return }
     * 
     */
    public UpdateCompaniesResponse.Return createUpdateCompaniesResponseReturn() {
        return new UpdateCompaniesResponse.Return();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOut }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "logOut")
    public JAXBElement<LogOut> createLogOut(LogOut value) {
        return new JAXBElement<LogOut>(_LogOut_QNAME, LogOut.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompanyListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "companyListResponse")
    public JAXBElement<CompanyListResponse> createCompanyListResponse(CompanyListResponse value) {
        return new JAXBElement<CompanyListResponse>(_CompanyListResponse_QNAME, CompanyListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogOutResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "logOutResponse")
    public JAXBElement<LogOutResponse> createLogOutResponse(LogOutResponse value) {
        return new JAXBElement<LogOutResponse>(_LogOutResponse_QNAME, LogOutResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignUpResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "signUpResponse")
    public JAXBElement<SignUpResponse> createSignUpResponse(SignUpResponse value) {
        return new JAXBElement<SignUpResponse>(_SignUpResponse_QNAME, SignUpResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompanyList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "companyList")
    public JAXBElement<CompanyList> createCompanyList(CompanyList value) {
        return new JAXBElement<CompanyList>(_CompanyList_QNAME, CompanyList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCompanies }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "updateCompanies")
    public JAXBElement<UpdateCompanies> createUpdateCompanies(UpdateCompanies value) {
        return new JAXBElement<UpdateCompanies>(_UpdateCompanies_QNAME, UpdateCompanies.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogInResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "logInResponse")
    public JAXBElement<LogInResponse> createLogInResponse(LogInResponse value) {
        return new JAXBElement<LogInResponse>(_LogInResponse_QNAME, LogInResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompanies }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "getCompanies")
    public JAXBElement<GetCompanies> createGetCompanies(GetCompanies value) {
        return new JAXBElement<GetCompanies>(_GetCompanies_QNAME, GetCompanies.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LogIn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "logIn")
    public JAXBElement<LogIn> createLogIn(LogIn value) {
        return new JAXBElement<LogIn>(_LogIn_QNAME, LogIn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuyShares }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "buyShares")
    public JAXBElement<BuyShares> createBuyShares(BuyShares value) {
        return new JAXBElement<BuyShares>(_BuyShares_QNAME, BuyShares.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SignUp }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "signUp")
    public JAXBElement<SignUp> createSignUp(SignUp value) {
        return new JAXBElement<SignUp>(_SignUp_QNAME, SignUp.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoreCompanyInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "moreCompanyInfoResponse")
    public JAXBElement<MoreCompanyInfoResponse> createMoreCompanyInfoResponse(MoreCompanyInfoResponse value) {
        return new JAXBElement<MoreCompanyInfoResponse>(_MoreCompanyInfoResponse_QNAME, MoreCompanyInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCompaniesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "getCompaniesResponse")
    public JAXBElement<GetCompaniesResponse> createGetCompaniesResponse(GetCompaniesResponse value) {
        return new JAXBElement<GetCompaniesResponse>(_GetCompaniesResponse_QNAME, GetCompaniesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateCompaniesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "updateCompaniesResponse")
    public JAXBElement<UpdateCompaniesResponse> createUpdateCompaniesResponse(UpdateCompaniesResponse value) {
        return new JAXBElement<UpdateCompaniesResponse>(_UpdateCompaniesResponse_QNAME, UpdateCompaniesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CertificateException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "CertificateException")
    public JAXBElement<CertificateException> createCertificateException(CertificateException value) {
        return new JAXBElement<CertificateException>(_CertificateException_QNAME, CertificateException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepositAmountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "depositAmountResponse")
    public JAXBElement<DepositAmountResponse> createDepositAmountResponse(DepositAmountResponse value) {
        return new JAXBElement<DepositAmountResponse>(_DepositAmountResponse_QNAME, DepositAmountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MoreCompanyInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "moreCompanyInfo")
    public JAXBElement<MoreCompanyInfo> createMoreCompanyInfo(MoreCompanyInfo value) {
        return new JAXBElement<MoreCompanyInfo>(_MoreCompanyInfo_QNAME, MoreCompanyInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DepositAmount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "depositAmount")
    public JAXBElement<DepositAmount> createDepositAmount(DepositAmount value) {
        return new JAXBElement<DepositAmount>(_DepositAmount_QNAME, DepositAmount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SellShares }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sharesbrokeringsystem.me.org/", name = "sellShares")
    public JAXBElement<SellShares> createSellShares(SellShares value) {
        return new JAXBElement<SellShares>(_SellShares_QNAME, SellShares.class, null, value);
    }

}
