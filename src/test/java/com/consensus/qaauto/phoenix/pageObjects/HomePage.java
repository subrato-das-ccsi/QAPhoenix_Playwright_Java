package com.consensus.qaauto.phoenix.pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import testResources.Constant;
import com.consensus.qaauto.utility.*;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import testResources.menuOptions;

public class HomePage extends NavigationBarPage {
    public HomePage(Page page) {
        super(page);
    }
    String opportunity="";
    String netAmount="";
    String quoteId="";
    public int csa,csb,csc,csd;
    // Private Final Locators
    String leadStreet = "Street";
    String leadCity = "City";
    private final Locator alertWarningButtonClose = page.locator("//svg[@data-key='close']/g").first();
    private final Locator salutation = page.locator("//label[text()='Salutation']/following::button[1]");
    private final Locator editButtonOpportunityPage = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first();
    private final Locator leadStatusNew = page.locator("records-record-layout-item").filter(new Locator.FilterOptions().setHasText("*Lead StatusNew")).locator("span");
    private final Locator opportunitiesTabLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first();
    private final Locator editButtonContacts = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true));
    private final Locator apPrimaryContact = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("AP Primary Contact"));
    private final Locator adminPrimaryContact = page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Admin Primary Contact"));
    private final String opportunityInformationDialogSelector = "//div[@role='dialog' and contains(.,'Opportunity Information')]";
    private final String sorryToInterruptDialogSelector = "[role='dialog']:has-text('Sorry to interrupt')";
    private final String typeDropdownXPath = "//label[text()='Type']/following::lightning-base-combobox[1]";
    private final String stageDropdownXPath = "//label[text()='Stage']/following::lightning-base-combobox[1]";
    private final String snagDialogSelector = "[role='dialog']:has-text('We hit a snag')";
    private final Locator corpIdLocator = page.locator("//input[@name='Corp_ID__c']");
    private final Locator draftStatusDropdown = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Draft")).first();
    private final Locator activeStatusDropdown = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Active")).first();
    private final Locator editButtonOnAccounts = page.locator("button[name='Edit']");
    public Locator type1 = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Type").setExact(true));
    private final Locator typeHelpInfoTypeOf = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Type Help Info Type of"));
    public Locator errorMessage = page.locator("//records-record-layout-item[@field-label='Account Status']//div[@role='status']");
    public Locator errorMessageType = page.locator("//records-record-layout-item[@field-label='Type']//div[@role='status']");
    private final Locator buttonNew = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New"));
    private final Locator accountType = page.getByRole(AriaRole.GROUP, new Page.GetByRoleOptions().setName("Select a record type")).locator("span");
    private final Locator buttonNext = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next"));
    private final Locator buttonContinue = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Continue"));
    private final Locator buttonSave = page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("Account Edit Save Save & New Cancel").setExact(true)).getByTitle("Save", new Locator.GetByTitleOptions().setExact(true));
    private final Locator buttonCancel = page.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("Account Edit Save Save & New Cancel").setExact(true)).getByTitle("Cancel");
    private final Locator buttonSubmitApproval = page.getByTitle("Submit for Approval").first();
    private final Locator accountsMenu = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Accounts"));
    private final Locator recordTypeInternal = page.locator("//span[contains(text(),'Internal')]");
    private final Locator saveButtonGeneral = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save").setExact(true));
    private final Locator cancelButtonGeneral = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Cancel").setExact(true));
    private final String accountNameLocator = "*Account Name";
    private final String addressInformationDialog = "//div[@role='dialog' and contains(.,'Address Information')]";
    private final String renewalPricingMethod = "//records-record-layout-item[@field-label='Renewal Pricing Method']//button";
    private final String numberEmployeesDHCLocator = "//records-record-layout-item[@field-label='DHC # of Employees']//lightning-formatted-number";
    private final String renewalPricingField = "//records-record-layout-item[@field-label='Renewal Pricing Method']//lightning-formatted-text";
    private final Locator productMatrix = page.locator("//span[contains(text(),'Product Matrix')]");
    private final Locator accounts_header_name = page.locator("//flexipage-field[@data-field-id='RecordNameField2']//lightning-formatted-text");
    private final Locator typeDropDown = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Type").setExact(true));
    private final Locator accountSourceDropDown = page.locator("//label[text()='Account Source']//following::button[1]");
    private final Locator accountStatusDropDown = page.locator("//button[@aria-label='Account Status']");
    private final Locator industryDropDown = page.locator("//label[text()='Industry']//following::button[2]");
    private final Locator accountCurrencyDropDown = page.locator("//label[text()='Account Currency']//following::button[1]");
    private final Locator accounts_targeted_product = page.locator("forcegenerated-detailpanel_opportunity___012000000000000aaa___full___view___recordlayout2");
    private final List<Locator> assets = page.locator("table[aria-label='Assets'] tbody tr").all();
    private final Locator accounts_primary_contact = page.locator("forcegenerated-detailpanel_opportunity___012000000000000aaa___full___view___recordlayout2");
    private final String usdCurrency = "USD - U.S. Dollar";
    private final String sorryToInterruotDialog = "[role='dialog']:has-text('Sorry to interrupt')";
    private final Locator accounts_currency = page.locator("//flexipage-field[@data-field-id='RecordCurrencyIsoCodeField2']//lightning-formatted-text");
    private final Locator locatorPostalCode = page.locator("//input[@name='postalCode']");
    private final Locator net45Text = page.getByText("Net 45");
    private final Locator newButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New").setExact(true));
    private final Locator nextButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next").setExact(true));
    // --- Locators ---
    // You can declare these as class members (e.g., private final Locator newBtn = ...)
    // for better reusability across methods.
    private final Locator company = page.getByLabel("*Company");
    private final Locator firstName = page.getByLabel("First Name");
    private final Locator lastName = page.getByLabel("Last Name");
    private final Locator emailTextBox = page.getByLabel("*Email");
    private final Locator provinceLeadsLocator = page.getByLabel("State/Province");
    private final Locator leadZipPostalCode = page.getByLabel("Zip/Postal Code");

    private final Locator paymentTerms = page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Payment Terms"));
    public Locator usdCurrencySet = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(usdCurrency));
private final Locator ispCustomer = page.locator("//records-record-layout-item[@field-label='ISP Customer ID']//input");
private final Locator leadSourceType = page.getByText("*Lead Source Type");
private final Locator hospitalTypeDHC = page.locator("//records-record-layout-item[@field-name='DHC Hospital Type']//input");
private final Locator employeeTypeDHC = page.locator("//records-record-layout-item[@field-name='DHC # of Employees']//input");
private final Locator annualRevenueField = page.locator("//records-record-layout-item[@field-label='Annual Revenue']//lightning-formatted-text");
private final Locator annualRevenueInputField = page.locator("//records-record-layout-item[@field-label='Annual Revenue']//input");
private final Locator departmentField = page.locator("//records-record-layout-item[@field-label='Department']//lightning-formatted-text");
private final Locator departmentInputField = page.locator("//records-record-layout-item[@field-label='Department']//input");
private final Locator doNotCallCheckBox = page.locator("//records-record-layout-item[@field-label='Do Not Call']//input");
private final Locator emailCheckBox = page.locator("//records-record-layout-item[@field-label='Email Opt Out']//input");
private final Locator faxCheckBox = page.locator("//records-record-layout-item[@field-label='Fax Opt Out']//input");
/*    String emailID = generateRandomEmailID();*/
/*private final Locator leadSource = page.getByLabel("Lead Source, --None--");
private final Locator leadsourcetype = page.getByLabel("//label[text()='Lead Source Type']");
private final Locator targetedProduct = page.locator("//label[text()='Targeted Product']/following::button[1]");
private final Locator leadType = page.locator("//label[text()='Lead Type']/following::button[1]");
private final Locator Industry = page.locator("//label[text()='Industry']/following::button[1]");*/
private final Locator saveAfterLeadCreation = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save")).last();
private final Locator saveAndNewLeadCreation = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Save & New")).last();
private final Locator convertFromMainPage = page.locator("button[name='Convert']");
private final Locator convertFromPopup = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Convert"));
private final Locator newbtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("New").setExact(true));
private final Locator nextbtn = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Next").setExact(true));
private final Locator radiobtn1 = page.locator(".slds-radio_faux").first();
private final Locator notificationIcon = page.locator("//lightning-icon[@class='slds-icon-utility-notification slds-button__icon slds-global-header__icon slds-icon_container forceIcon']");
private final Locator radiobtn2 = page.locator("fieldset:nth-child(2) > div > .entityPanel > .col2 > .createPanel > .header > .slds-radio > .slds-radio__label > .slds-radio_faux");
private final Locator radiobtn3 = page.locator("fieldset:nth-child(3) > div > .entityPanel > .col2 > .createPanel > .header > .slds-radio > .slds-radio__label > .slds-radio_faux");
    private final String leadStatsProvince = "State/Province";
    private final String leadCountry = "Country";
    private final String leadStreetData = "My Street";
    private final String leadCityData = "New York";
    private final String leadProvinceData = "New York";
    private final String leadZipPostalCodeData = "12401";
    private final String leadCountruData = "US";
    private final String corpID = "1234";
private final Locator newSimpleQuoteLocator = page.locator("//runtime_platform_actions-action-renderer[@title='New Simple Quote']//button");
private final Locator errorWarningSimpleQuote = page.locator("//div[@class='flowruntime-input-error slds-form-element__help']//span");
private final Locator closeLeadConversionSuccessPopup = page.locator("lightning-icon[icon-name='utility:close']");
private final Locator constantContactName = page.locator("//lightning-primitive-cell-factory[@data-label='Contact Name']//a");
private final Locator editPaymentTerms = page.locator("//button[@title='Edit Payment Terms']");
private final Locator constantAccountName = page.locator("//a[@title='" + Constant.accountName + "']");
    String opportunityEstimatedMRRLocator = "//records-record-layout-item[@field-label='Estimated MRR']//input";
    String opportunityMRRLocator = "//records-record-layout-item[@field-label='MRR']//lightning-formatted-text";
private final Locator contractTypeLocator = page.locator("//records-record-layout-item[@field-label='Contract Type']//lightning-formatted-text");
private final Locator productCodeButtonLocator = page.locator("//th[@title='Product Code']//a[@role='button']");
private final Locator orderedCheckBoxLocator = page.locator("//records-record-layout-item[@field-label='Ordered']//input[@type='checkbox']");
private final Locator orderApprovalPageFields = page.locator("//table[@role='grid']//span[@class='slds-truncate']");
private final Locator approvalTextBox = page.locator("//textarea[@role='textbox']");
private final Locator approveButtonLocator = page.locator("//div[@title='Approve']");
private final Locator approveLinkLocator = page.locator("//a[@title='Approve']");
private final Locator opportunityCorpId = page.locator("//input[@name='Corp_ID__c']");
private final Locator dialogAlohaFrameLocator = page.locator("//div[@class='isModal inlinePanel oneRecordActionWrapper']");
private final Locator billingFrequency = page.locator("//records-record-layout-item[@field-label='Billing Frequency']//lightning-formatted-text");
private final Locator submittedForFinanceReview = page.getByText("Submitted for Finance Review");
private final Locator orderStartDateLocator = page.locator("//records-record-layout-item[@field-label='Order Start Date']//lightning-formatted-text");
private final Locator orderAmountLocator = page.locator("//records-record-layout-item[@field-label='Order Amount']//lightning-formatted-text");
private final Locator orderTypeLocator = page.locator("//records-record-layout-item[@field-label='Order Type']//lightning-formatted-text");
private final Locator orderNameLocator = page.locator("//records-record-layout-item[@field-label='Order Name']//lightning-formatted-text");
private final Locator subscriptionTermOpportunity = page.locator("//records-record-layout-item[@field-label='Subscription Term']//lightning-formatted-text");
private final Locator mrrOpportunity = page.locator("//records-record-layout-item[@field-label='MRR Amount']//lightning-formatted-text");
private final Locator acvOpportunity = page.locator("//records-record-layout-item[@field-label='ACV Amount']//lightning-formatted-text");
private final Locator subscriptionTermLocator = page.locator("//records-record-layout-item[@field-label='Subscription Term']//lightning-formatted-number");
private final Locator quoteNumberLocator = page.locator("//lightning-primitive-cell-factory[@data-label='Quote Number']//a[@class='slds-truncate']");
private final Locator accountNameField = page.getByLabel("*Account Name");
private final Locator amountOpportunity = page.locator("//records-record-layout-item[@field-label='Amount']//lightning-formatted-text");
private final Locator contractNumberPage = page.locator("//lightning-primitive-cell-factory[@data-label='Contract Number']");
private final Locator contractNumber = page.locator("//lightning-primitive-cell-factory[@data-label='Contract Number']//a[@class='slds-truncate']");
private final Locator contactsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Contracts \\(\\d+\\+?\\)")));
private final Locator ordersLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Orders \\(\\d+\\+?\\)")));
private final Locator creditApplicationLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Credit Applications \\(\\d+\\+?\\)")));
private final Locator quotesLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Quotes \\(\\d+\\+?\\)")));
private final Locator orderNumberLocator = page.locator("//lightning-primitive-cell-factory[@data-label='Order Number']//a[@class='slds-truncate']");
private final Locator ordersLinkLocator = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Orders \\(\\d+\\+?\\)")));
private final Locator approvalHistoryLocator = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Approval History \\(\\d+\\+?\\)")));
private final Locator quoteDash = page.locator("//slot[starts-with(text(),'Q-')]");
    String documentUploadCompletedLocator = "//button[@aria-label='Contract Upload/Received']";
    String documentUploadLocatorYes = "lightning-base-combobox-item[data-value='Yes']";
    String contractExecutionDateLocator = "//flexipage-field[@data-field-id='RecordContract_Execution_Date_cField']//input";
    String stageLocator = "//flexipage-field[@data-field-id='RecordStageNameField']";
    String saveEditButton = "//button[@name='SaveEdit']";
private final Locator saveButton = page.locator(saveEditButton).first();
    String closedWonPendingStage = "Closed/Won Pending";
private final Locator approveButtonOne = page.locator("//div[@title='Approved']/div/span");
private final Locator opportunityStage = page.locator("//flexipage-field[@data-field-id='RecordStageNameField']//button");
private final Locator opportunityCloseDate = page.locator("//flexipage-field[@data-field-id='RecordCloseDateField']//lightning-formatted-text");
private final Locator opportunityForecaseCategory = page.locator("//flexipage-field[@data-field-id='RecordForecast_Category_Custom_cField']//lightning-formatted-text[@data-output-element-id='output-field']");
private final Locator opportunityEstStartDate = page.locator("//flexipage-tab2//flexipage-field[@data-field-id='RecordEst_Project_Start_Date_cField']//lightning-formatted-text");
private final Locator opportunityEstEndDate = page.locator("//flexipage-field[@data-field-id='RecordEst_Project_End_Date_cField']//lightning-formatted-text");
    String forecastCategoryOverride = "//records-record-layout-item[@field-label='Forecast Category (Override)']//lightning-formatted-text";
private final Locator paymentTermApproval = page.locator("//records-record-layout-item[@field-label='Payment Term Approval']//lightning-formatted-text");
private final Locator neutralButton = page.locator("//button[@class='slds-button slds-button_neutral modal-button-left actionButton uiButton--default uiButton--brand uiButton']");
private final Locator alertWarningButton = page.locator("//div[@data-key='warning']//button").first();
    OpportunitiesPage opportunities = null;
private final Locator labelCCSIBusinessLegalEntity = page.getByLabel("CCSI Business Legal Entity");
    String timeStamp = "";
private final Locator saveButtonOpportunity = page.locator(saveEditButton);
private final Locator searchAccounts = page.locator("//input[@placeholder='Search Accounts...']");
private final Locator submitForApprovalBox = page.locator("//div[@class='commentContainer']//textarea[@role='textbox']");
private final Locator submitForApprovalSubmit = page.locator("//runtime_platform_actions-action-renderer[@title='Submit for Approval']//button[@name='Submit']").first();
private final Locator eFaxCorporateProductCount = page.locator("//records-record-layout-item[@field-label='eFax Corporate Product Count']//lightning-formatted-number");
private final Locator warningMessageLocator = page.locator("//div[@role='alert']//span[@class='toastMessage forceActionsText']");
    String addressLineTest = "My Street,New York, New York 12401,US";
private final Locator addressLink = page.locator("//lightning-formatted-address[@slot='outputField']/a");
private final Locator statusLocator = page.locator("//flexipage-field[@data-field-id='RecordStageNameField']//lightning-formatted-text");
private final Locator statusLocatorQuote = page.locator("//records-record-layout-item[@field-label='Status']//lightning-formatted-text");
private final Locator opportunityBookings = page.locator("//flexipage-field[@data-field-id='RecordBookings_cField']//lightning-formatted-text").first();
private final Locator opportunityMRR = page.locator("//flexipage-field[@data-field-id='RecordEst_MRR_Amount_cField']//input").first();
private final Locator industryDropdown = page.locator("//label[text()='Industry']//following::button[2]");
private final Locator accountTypeDropdown = page.locator("//label[text()='Type']//following::button[1]");
private final Locator accountStatusDropdown = page.locator("//label[text()='Account Status']//following::button[1]");
private final Locator accountSourceDropdown = page.locator("//label[text()='Account Source']//following::button[1]");
private final Locator typeDropdown = page.locator("//label[text()='Type']//following::button[2]");
private final Locator productCodes = page.locator("//table[@role='grid']//tr//td[2]//a[@data-refid='recordId']");
private final Locator productCodes2 = page.locator("//table[@role='grid']//tr//td[2]//a[@data-refid='recordId']");
private final Locator dialog = page.locator("//div[@role='dialog' and contains(.,'Address Information')]");
private final Locator dateElement = page.locator("//lightning-formatted-text[@data-output-element-id='output-field']").filter(new Locator.FilterOptions().setHasText("/"));
private final Locator projectDates = page.locator("//records-formula-output[@data-output-element-id='output-field']/slot/lightning-formatted-text");
    // Locators initialized at the top
private final Locator salutationLeadPage = page.locator("//label[text()='Salutation']/following::button[1]");
private final Locator leadSourceLeadsPage = page.locator("//label[text()='Lead Source']/following::button[1]");
private final Locator leadSourceTypeLeadsPage= page.getByText("*Lead Source Type");
private final Locator leadProspectLeadsPage = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Prospect")).locator("span").nth(1);
private final Locator targetedProductLeadsPage =page.getByText("*Targeted Product");


private final Locator leadTypeLeadsPage = page.getByText("*Lead Type");

private final Locator industryLeadsPage = page.getByText("*Industry");
private final Locator industryDropdownLeadsPage(String industry){
    return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(industry));
}
private final Locator cityLeadsPage = page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("New York").setExact(true));
    List<String> expectedRegions= Arrays.asList("Australia","APAC","Canada","Europe","Global","Japan","United States");
private final Locator targetedProductSetName(String targetedproduct){
        return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(targetedproduct).setExact(true)).locator("span").nth(1);
    }
private final Locator leadsTypeDropdownLeadsPage(String leadtype){
        return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(leadtype)).locator("span").nth(1);
    }
private final Locator leadSourceOption(String leadSource){
        return page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(leadSource));
    }
    public Locator opportunityLocator(String opportunity){
        return page.locator("//a[@title='"+opportunity+"']");
    }
    List<String> expectedPractices= Arrays.asList( "Business Continuity",
            "Global",
            "Government",
            "Integration Services",
            "Professional Services",
            "Veterans Administration");

    /*public boolean verifyRecenctlyViewedAssets() {
        try {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("App Launcher")).click();
            page.getByLabel("View All Applications").click();
            page.getByPlaceholder("Search apps or items...").click();
            page.getByPlaceholder("Search apps or items...").fill("Assets");
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Assets")).click();
            boolean b = page.locator("a[data-refId='recordId']").count() > 0;
            Assert.assertTrue(b);
            logger.info("Recently viewed Assets list displayed");
            return b;
        } catch (Exception e) {
            logger.info("User not opened any Assets recently or the issue with page");
        }
        return false;
    }*/
    /*public String getDateInSimpleFormat() {
        return new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }*/
    /*public String getRecordFileLocation(String className, String timeStamp) {
        return Paths.get("test-output/report/Files/") + "\\" + className + "-" + timeStamp + ".log";
    }*/
    /*public String approveQuoteAsUser(String user) {
        LoginPage loginPage = new LoginPage(page);
        try {
            loginPage.loginToSalesForce(true, user, "");
            quoteId = readFromFile("QuoteId.txt");
            readAllNotificationsOpenQuote();
            page.waitForTimeout(3000);
            approveButtonLocator.first().click();
            page.waitForTimeout(3000);
            approvalTextBox.first().fill("Approving the Quote as Manager");
            neutralButton.first().click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Quote is approved";
    }*/

   /* public String getTimeStamp(){
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        return timeStamp;
    }*/
    /*public String createQuotefromApplauncher4(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates = projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccount(true, account, contact, opportunity);
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTest(quote,12,0,0,0,0);
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }

    public String createQuotefromApplauncher3(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccount(true, account, contact, opportunity);
            netAmount = quotes.addQuoteLineItemsEndToEndTest(quote,12);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contacts")).first().click();
            page.waitForTimeout(3000);
            page.locator("//a[@title='"+contact+"']").click();
            page.waitForTimeout(3000);
            logger.info("Contact page opened "+contact);
            fw.write("Contact page opened "+contact+"\n");
            page.waitForTimeout(5000);
            if(addressLink.isVisible())
            {
                String fullAddress=addressLink.getAttribute("aria-label");
                fullAddress=fullAddress.replaceAll("[\n\r]", ",");
                logger.info(fullAddress);
                fw.write(fullAddress+"\n");
                if(fullAddress.equalsIgnoreCase(addressLineTest))
                {
                    logger.info("Address String matches");
                    fw.write("Address String matches"+"\n");
                }
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }*/

    /*public String editExistingAccount(String recordFileLocation) throws IOException {

        navigateToMenu("Accounts");
        try {
            String filePath = Paths.get("test-output/report/Files/")+"\\EndToEndTest.txt";
            File myObj = new File(filePath);Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                opportunity = myReader.nextLine();
                System.out.println(opportunity);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileWriter fw=new FileWriter(recordFileLocation);
        String account = opportunity.substring(0,opportunity.indexOf(":"));
        account = account.trim();
        fw.write(account+"\n");
        logger.info(account);
        searchList(account,account);
        page.waitForTimeout(3000);
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
        page.waitForTimeout(3000);
        try {


            accountNameField.first().click();
            Name contact = faker.name();
            String firstName = contact.firstName().replaceAll("'"," ").replaceAll(" ","");
            String lastName = contact.lastName().replaceAll("'"," ").replaceAll(" ","");
            String newName = firstName+" "+lastName;
            logger.info(newName);
            fw.write(newName+"\n");
            accountNameField.first().fill(newName);
            typeDropdown.click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Prospect").setExact(true)).click();
            accountSourceDropdown.click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Website")).click();
            accountStatusDropdown.click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Active")).click();
            industryDropdown.click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Other")).click();
            page.locator(saveEditButton).first().click();
            fw.write("Saved Successfully");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return account;
    }*/
    /*public String createQuotefromApplauncherSimpleDraft(String recordFileLocation) throws IOException {
        try {
            FileWriter fw = new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName = account;
            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact = contact;
            writeToFile("EndToEndTestContact.txt", contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='" + account + "']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            writeToFile("EndToEndTest.txt", opportunity);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            page.locator("//a[@title='" + opportunity + "']").first().click();
            page.waitForTimeout(3000);
            String closeDateElement = dateElement.first().textContent();
            logger.info("Close Date " + closeDateElement);
            fw.write("Close Date " + closeDateElement + "\n");
            Locator locatorProjectDates = projectDates.filter(new Locator.FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement = locatorProjectDates.first().textContent();
            logger.info("Est Project Start Date " + estProjectStartDateElement);
            fw.write("Est Project Start Date " + estProjectStartDateElement + "\n");
            String endDate = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates = projectDates.filter(new Locator.FilterOptions().setHasText(endDate));

            String estProjectEndDateElement = locatorProjectEndDates.first().textContent();
            logger.info("Est Project End Date " + estProjectEndDateElement);
            fw.write("Est Project End Date " + estProjectEndDateElement + "\n");
            
            String quote = quotes.createQuoteFromAccount(true, account, contact, opportunity);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return opportunity;
    }
*/
    /*public String createQuotefromApplauncher(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;
            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            writeToFile("EndToEndTest.txt",opportunity);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).first().click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(1).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccount(true, account, contact, opportunity);
            netAmount = quotes.addQuoteLineItemsEndToEndTest(quote,0);
            
            if(alertWarningButton.isVisible())
            {
               logger.info("Alert warning button visible");
               fw.write("Alert warning button visible"+"\n");
               alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contacts")).first().click();
            page.waitForTimeout(3000);
            page.locator("//a[@title='"+contact+"']").click();
            page.waitForTimeout(3000);
            logger.info("Contact page opened "+contact);
            fw.write("Contact page opened "+contact+"\n");
            page.waitForTimeout(5000);
            if(addressLink.isVisible())
            {
                String fullAddress=addressLink.getAttribute("aria-label");
                fullAddress=fullAddress.replaceAll("[\n\r]", ",");
                logger.info(fullAddress);
                fw.write(fullAddress+"\n");
                if(fullAddress.equalsIgnoreCase(addressLineTest))
                {
                    logger.info("Address String matches");
                    fw.write("Address String matches"+"\n");
                }
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }*/
    /*public String createQuotefromApplauncher2(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccount(true, account, contact, opportunity);
            netAmount = quotes.addQuoteLineItemsEndToEndTest(quote,12);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contacts")).first().click();
            page.waitForTimeout(3000);
            page.locator("//a[@title='"+contact+"']").click();
            page.waitForTimeout(3000);
            logger.info("Contact page opened "+contact);
            fw.write("Contact page opened "+contact+"\n");
            page.waitForTimeout(5000);
            if(addressLink.isVisible())
            {
                String fullAddress=addressLink.getAttribute("aria-label");
                fullAddress=fullAddress.replaceAll("[\n\r]", ",");
                logger.info(fullAddress);
                fw.write(fullAddress+"\n");
                if(fullAddress.equalsIgnoreCase(addressLineTest))
                {
                    logger.info("Address String matches");
                    fw.write("Address String matches"+"\n");
                }
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }*/
    /*public void checkAddress(FileWriter fw, Locator addressLink){
        try {
            if(addressLink.isVisible())
            {
                String fullAddress=addressLink.getAttribute("aria-label");
                fullAddress=fullAddress.replaceAll("[\n\r]", ",");
                logger.info(fullAddress);
                fw.write(fullAddress+"\n");
                if(fullAddress.equalsIgnoreCase(addressLineTest))
                {
                    logger.info("Address String matches");
                    fw.write("Address String matches"+"\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

/*    public String createQuotefromApplauncherEfax(String recordFileLocation, int efaxDiscountPercent) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax(false, account, contact, opportunity);

            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax(quote, efaxDiscountPercent);
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            for(int i=0;i<30;i++)
            {
                page.waitForTimeout(3000);
                page.reload();
                String approvalStatus = statusLocator.first().textContent();
                logger.info(approvalStatus);
                fw.write(approvalStatus+"\n");
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    break;
                }
            }
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher21a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax3(true, account, contact,"MTM", opportunity);
            Assert.assertNotEquals("",quote);
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax2(quote,50);
            Assert.assertNotEquals("",netAmount);
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher27a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 60","Contract");
            writeToFile("QuoteId.txt",quote);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,48, "75");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher30a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");

            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 60","Contract");
            writeToFile("QuoteId.txt",quote);
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher30b(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            QuotesPage quotes = new QuotesPage(page);
            String quote = readFromFile("QuoteId.txt");
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,48, "250");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher31a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 30","Contract");
            writeToFile("QuoteId.txt",quote);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,48, "200");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");


            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);

            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");


            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher32a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;
            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 30","Contract");
            writeToFile("QuoteId.txt",quote);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,55, "400");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher24a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;


            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 15","Contract");
            writeToFile("QuoteId.txt",quote);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,48, "75");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher22a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;
            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 15","Contract");
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,35, "75");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher25a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;


            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);



            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 45","Contract");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,35, "75");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }

    public String createQuotefromApplauncher20a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            writeToFile("EndToEndTest.txt",opportunity);
            Constant.Opportunity=opportunity;
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax(false, account, contact, opportunity);
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax(quote,0);
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            for(int i=0;i<30;i++)
            {
                page.waitForTimeout(3000);
                page.reload();
                String approvalStatus = statusLocator.first().textContent();
                logger.info(approvalStatus);
                fw.write(approvalStatus+"\n");
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    break;
                }
            }
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher23a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;
            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            //writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 30","Contract");
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,35, "75");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher28a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 30","Contract");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,35, "110");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher29a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax4(true, account, contact, opportunity,"Net 30","Contract");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax3(quote,38, "100");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }

    public String createQuotefromApplauncher19a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax2(true, account, contact, opportunity,"MTM");
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTestEfax(quote,0);
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            String quoteEfaxCorporateProductCount = eFaxCorporateProductCount.textContent();
            softly.assertThat(quoteEfaxCorporateProductCount).isEqualToIgnoringCase("10");
            logger.info(quoteEfaxCorporateProductCount);
            fw.write(quoteEfaxCorporateProductCount);

            String quotePaymentTermApproval = paymentTermApproval.textContent();
            softly.assertThat(quotePaymentTermApproval).isEqualToIgnoringCase("Approved");
            logger.info(quotePaymentTermApproval);
            fw.write(quotePaymentTermApproval);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher19ab(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountEfax2a(true, account, contact, opportunity,"Purchase Order");
            writeToFile("QuoteId.txt",quote);
            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestEfax(quote,0,"12");
            page.waitForTimeout(3000);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            String quoteContractType =  contractTypeLocator.first().textContent();
            logger.info(quoteContractType);
            fw.write(quoteContractType);

            String quoteEfaxCorporateSubscriptionTerm = subscriptionTermLocator.first().textContent();
            softly.assertThat(quoteEfaxCorporateSubscriptionTerm).isEqualToIgnoringCase("12");
            logger.info(quoteEfaxCorporateSubscriptionTerm);
            fw.write(quoteEfaxCorporateSubscriptionTerm);

            String quoteEfaxCorporateProductCount = eFaxCorporateProductCount.first().textContent();
            softly.assertThat(quoteEfaxCorporateProductCount).isEqualToIgnoringCase("10");
            logger.info(quoteEfaxCorporateProductCount);
            fw.write(quoteEfaxCorporateProductCount);

            page.waitForTimeout(3000);

            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createJSignQuotefromApplauncher1(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            Constant.Opportunity = opportunity;
            writeToFile("EndToEndTest.txt",opportunity);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"MTM","Net 30");
            writeToFile("QuoteId.txt",quote);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createJSignQuotefromApplauncher2(String recordFileLocation) throws IOException {
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Quotes");
            QuotesPage quotesPage = new QuotesPage(page);
            String quoteId = readFromFile("QuoteId.txt");
            quotesPage.searchAndOpenQuotes(quoteId);
            netAmount = quotesPage.addQuoteLineItemsSubscriptionTermEndToEndTestJSign2(quoteId,0,"12","15");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher35a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"MTM","Net 30");
            writeToFile("QuoteId.txt",quote);
            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSignMTM(quote,4,"12","15");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher37a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign(quote,19,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher42(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign4(quote,19,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher38a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign(quote,21,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            String quoteContractType = contractTypeLocator.first().textContent();
            softly.assertThat(quoteContractType).isEqualToIgnoringCase("Contract");
            logger.info(quoteContractType);
            fw.write(quoteContractType);

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher43a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;
            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);
            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign3(quote,21,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher42a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 45");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign4(quote,21,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }*/
    /*public String generateRandomEmailID() {
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        return firstname + "." + lastname + "@yopmail.com";
    }*/
    public String validateLeadCreation(String leadsource, String leadcurrency, String leadtype, String industry, String targetedproduct) {
        leadCreation(leadsource, leadcurrency, leadtype, industry, targetedproduct);
        waitForLoader();
        page.waitForLoadState();
        createAndconvertLead();
        String opportinutyName = Constant.companyName + ":" + targetedproduct;
        Constant.Opportunity=opportinutyName;
        logger.info(opportinutyName);
        OpportunitiesPage opportunitiesPage = switchToOpportunityDetailsScreen(opportinutyName);
        logger.info("Lead to Opportunity converted Successfully");
        return opportinutyName;
    }
    public AccountsPage createAndconvertLead() {
        try {
            // 1. Click the main convert button
            convertFromMainPage.first().click();
            waitForLoader(); // Assuming this waits for a general page loader to disappear
            convertFromPopup.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            page.waitForLoadState(LoadState.LOAD);
            logger.info("Convert from popup is now enabled/clickable.");

            // 2. Click the convert button in the popup
            convertFromPopup.first().click();
            waitForLoader(); // Wait for any loader that appears after clicking the popup convert button

            // 3. Close the success popup
            // Wait for the close button on the success popup to be visible
            closeLeadConversionSuccessPopup.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            closeLeadConversionSuccessPopup.first().click();
            logger.info("Lead Conversion successful");

            return new AccountsPage(page);
        } catch (Exception e) {
            logger.error("Error while executing " + Thread.currentThread().getStackTrace()[1].getMethodName() +
                    " method in " + Thread.currentThread().getStackTrace()[1].getClassName() +
                    " class. Exception: " + e.getMessage(), e); // Log the full exception for better debugging
            // You might want to handle this error more gracefully, e.g., throw a custom exception
            // or return null if conversion truly failed.
            // Returning a new AccountsPage here might mask a failure.
            return new AccountsPage(page); // Consider if this is the correct behavior on failure
        }
    }



    public void leadCreation(String leadSource, String leadCurrency, String leadType, String industryName, String targetedProduct) {
        try {
            Constant.emailAddress= GeneralUtility.generateRandomEmailID();
            leadCreationWithoutSave(leadSource, leadType,industryName, targetedProduct,Constant.emailAddress);
            waitForLoader();
            saveAfterLeadCreation.click();
            logger.info("Lead created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leadCreationWithoutSave(String leadSource, String leadType, String industryName,String targetedProduct,String email) {
        try {
            navigateToMenu(menuOptions.Leads);
            waitForLoader();
            newButton.click();
            waitForLoader();
            if (nextButton.isVisible()) {
                nextButton.first().click();
            }
            Constant.companyName = faker.company().name().replaceAll("'", " ") + GeneralUtility.getSaltString();
            logger.info("Account : " + Constant.companyName);
            company.fill(Constant.companyName);
            salutationLeadPage.click();
            page.getByText("Mr.").click();
            firstName.click();
            Constant.firstName = faker.name().firstName().replaceAll("'", "");
            firstName.fill(Constant.firstName);
            lastName.click();
            Constant.lastName = faker.name().lastName().replaceAll("'", "");
            lastName.fill(Constant.lastName);
            logger.info("Contact : " + Constant.firstName + " " + Constant.lastName);
            waitForLoader();
            leadSourceLeadsPage.click();
            leadSourceOption(leadSource).click();
            leadSourceTypeLeadsPage.click();
            leadProspectLeadsPage.click();
            targetedProductLeadsPage.click();
            targetedProductSetName(targetedProduct).click();
            leadTypeLeadsPage.click();
            leadsTypeDropdownLeadsPage(leadType).click();
            industryLeadsPage.click();
            industryDropdownLeadsPage(industryName).click();
            waitForLoader();
            page.getByLabel(leadStreet).fill(leadStreetData);
            page.getByLabel(leadCity).fill(leadCityData);
            provinceLeadsLocator.first().click();
            cityLeadsPage.click();
            locatorPostalCode.fill(leadZipPostalCodeData);
            emailTextBox.fill(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String openAccount(String company) {
        globalSearch(company);
        waitForLoader();
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(company).setExact(true)).first().click();
        try {
            page.waitForSelector(sorryToInterruotDialog, new Page.WaitForSelectorOptions().setTimeout(5000));
            page.locator(sorryToInterruotDialog).getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName("OK")).click();
        } catch (PlaywrightException ex) {
            company = null;
            logger.info("No interrupt dialog to clear, continuing...");
        }
        return company;
    }
    public void editOpenAccount() {
        try {
            String url = page.url();
            url = url.replaceAll("view", "edit");
            page.navigate(url);
            page.waitForLoadState();
            page.waitForTimeout(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updateAccountType(String newType) {
        try {
            accountTypeDropdown.click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(newType)).first().click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void updateAccountStatus(String newStatus) {
        try {
            accountStatusDropDown.click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(newStatus)).first().click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void saveAccount() {
        try {
            saveButtonGeneral.click();
            page.waitForTimeout(3000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String openContact(String firstName, String lastName) {
        String contact = null;
        try {
            contact = firstName + " " + lastName;
            globalSearch(contact);
            page.waitForTimeout(2500);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(contact).setExact(true)).first().click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contact;
    }
    private void performCommonEdits(String user, String accountName, String contactName, String opportunity) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginToSalesForce(true, user, "");
        waitForLoader();
        openAccount(accountName);
        editOpenAccount();
        waitForLoader();
        updateAccountStatus("Active");
        saveAccount();
        waitForLoader();
        openContact(contactName, "");
        waitForLoader();
        editButtonContacts.first().click();
        waitForLoader();
        if (apPrimaryContact.isVisible()) {
            apPrimaryContact.check();
            logger.info("AP Primary Contact checked");
        }
        if (adminPrimaryContact.isVisible()) {
            adminPrimaryContact.check();
            logger.info("Admin Primary Contact checked");
        }
        saveButton.click();
        waitForLoader();
    }
    public void projectStartEndDates(String closeDateElement) throws IOException {
        Locator locatorProjectDates = projectDates.filter(new Locator.FilterOptions().setHasText(closeDateElement));
        for (int i = 0; i < 4; i++) {
            if (!opportunityEstStartDate.first().isVisible()) {
                page.mouse().wheel(0, 1000);
            } else {
                break;
            }
        }
        String estProjectStartDateElement = opportunityEstStartDate.first().textContent();
        logger.info("Est Project Start Date " + estProjectStartDateElement);
        AnalysisLogger.write("Est Project Start Date " + estProjectStartDateElement + "\n");
        String endDate = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
        Locator locatorProjectEndDates = projectDates.filter(new Locator.FilterOptions().setHasText(endDate));
        String estProjectEndDateElement = opportunityEstEndDate.first().textContent();
        logger.info("Est Project End Date " + estProjectEndDateElement);
        AnalysisLogger.write("Est Project End Date " + estProjectEndDateElement + "\n");
    }
    public void selectNet45PaymentTerms() throws IOException {
        paymentTerms.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000)); // Max 10s wait
        paymentTerms.click();
        net45Text.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000)); // Max 10s wait

        net45Text.click();
        logger.info("Payment terms changed to - Net 45");
        AnalysisLogger.write("Payment terms changed to - Net 45" + "\n");
        page.locator(saveEditButton).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(10000));
        page.locator(saveEditButton).first().click();
        page.locator(saveEditButton).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN).setTimeout(15000)); // Wait up to 15s for it to disappear

        if (warningMessageLocator.isVisible()) {
            String warningMessage = warningMessageLocator.first().textContent();
            logger.info(warningMessage);

            if (alertWarningButtonClose.isVisible()) {
                logger.info("Net 45 Alert warning button visible");
                AnalysisLogger.write("Net 45 Alert warning button visible" + "\n");
                alertWarningButton.click();
            }

            closeAlertWarning();
        }
    }
    public void closeAlertWarning() throws IOException {
        if (alertWarningButton.isVisible()) {
            logger.info("Alert warning button visible");
            AnalysisLogger.write("Alert warning button visible" + "\n");
            alertWarningButton.click();
        }
    }
    public HomePage preparePageWithLoggedInUser() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginToSalesForce(true, "sales", "");
        return new HomePage(page);
    }

    // This method is a combined version from multiple files, handling both 'sales' and 'Jamie' users.
    public HomePage preparePageWithLoggedInUser(String user) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginToSalesForce(true, user, "");
        return new HomePage(page);
    }
    public String createQuoteClinicalSales(int software_discount, int consulting_discount, int maintainence_discount, int outsourcing_discount) throws IOException {
        String urlCurrent = "";
        try {
            Constant.Opportunity = validateLeadCreation("Email", "USD", "Prospect", "Other", "Clarity");
            performCommonEdits("Michael", Constant.companyName, Constant.firstName + " " + Constant.lastName,Constant.Opportunity);
            globalSearch(Constant.Opportunity);
            openLink(Constant.Opportunity);
            waitForLoader();
            String closeDateElement = dateElement.first().textContent();
            logger.info("Close Date " + closeDateElement);
            AnalysisLogger.write("Close Date " + closeDateElement + "\n");
            projectStartEndDates(closeDateElement);
            QuotesPage quotes = new QuotesPage(page);
            String quote = quotes.createQuoteFromAccount(true, Constant.companyName, Constant.contact, Constant.Opportunity);
            AnalysisLogger.write("QuoteID :" + quote + "\n");
            writeToFile("QuoteId.txt", quote);
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTest(quote, software_discount, consulting_discount, maintainence_discount, outsourcing_discount);
            waitForLoader();
            editPaymentTerms.click();
            selectNet45PaymentTerms();
            submitForApprovalSubmit.click();
            waitForLoader();
            submitForApprovalBox.fill("Test record " + Constant.quoteId + " Submitting for approval");
            waitForLoader();
            closeAlertWarning();
            neutralButton.click();
            waitForLoader();
            String approvalStatus = statusLocatorQuote.first().textContent();
            logger.info(approvalStatus);
            AnalysisLogger.write(approvalStatus + "\n");
            closeAlertWarning();
            /*LoginPage loginPage = getLoginAndApprove("Josh");
            loginPage.loginToSalesForce(true, "sales", "");
            waitForLoader();
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(Constant.Opportunity);
            quotesLink.first().click();
            waitForLoader();
            quoteNumberLocator.click();
            String quoteStatus = getQuoteStatus();
            getPaymentTermApprovalStatus();
            AnalysisLogger.write(quoteStatus);*/

        } catch (Exception exception) {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String getQuoteStatus() throws IOException {
        String quoteStatus = statusLocatorQuote.textContent();
        if (quoteStatus.equalsIgnoreCase("In Review")) {
            logger.info(quoteStatus + "  Level 2 approval required");
        } else {
            softly.assertThat(quoteStatus).isEqualToIgnoringCase("Accepted");
            logger.info(quoteStatus);
            AnalysisLogger.write(quoteStatus);
        }
        return quoteStatus;
    }
    public void getPaymentTermApprovalStatus() {
        String quotePaymentTermApproval = paymentTermApproval.textContent();
        softly.assertThat(quotePaymentTermApproval).isEqualToIgnoringCase("Approved");
        logger.info(quotePaymentTermApproval);
    }
    public void readAllNotificationsOpenQuote() {
        notificationIcon.click();
        page.waitForTimeout(10000);
        Locator notifications = page.locator("//span[@class='notification-text slds-show uiOutputText']");
        quoteId = GeneralUtility.readFromFile("QuoteId.txt");
        for (int i = 0; i < 2; i++) {
            logger.info(notifications.nth(i).textContent());
            if (notifications.nth(i).textContent().contains(quoteId)) {
                notifications.nth(i).click();
                break;
            }
        }
    }
    public LoginPage getLoginAndApprove(String user) {
        LoginPage loginPage = new LoginPage(page);
        loginPage.loginToSalesForce(true, user, "");
        quoteId = GeneralUtility.readFromFile("QuoteId.txt");
        readAllNotificationsOpenQuote();
        waitForLoader();
        approveButtonLocator.first().click();
        waitForLoader();
        approvalTextBox.first().fill("Approving the Quote as Manager");
        neutralButton.first().click();
        return loginPage;
    }
    /*public String createQuotefromApplauncher39a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(3).format(DateTimeFormatter.ofPattern("M/d/yyyy"));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign3(quote,5,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher40a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign3(quote,10,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher41a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign3(quote,20,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher36a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign(quote,8,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher44a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);
            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"Contract","Net 30");
            writeToFile("QuoteId.txt",quote);

            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign4(quote,8,"12","");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher34a(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");
            
            String quote = quotes.createQuoteFromAccountJsign(true, account, contact, opportunity,"MTM","Net 45");
            writeToFile("QuoteId.txt",quote);
            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestJSign4(quote,0,"12","15");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);
            String quoteContractType = contractTypeLocator.first().textContent();
            softly.assertThat(quoteContractType).isEqualToIgnoringCase("Contract");
            logger.info(quoteContractType);
            fw.write(quoteContractType);

            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            for(int i=0;i<20;i++){
                String approvalStatus = statusLocator.first().textContent();
                logger.info(i+" "+ approvalStatus);
                fw.write(i+" "+approvalStatus+"\n");
                page.reload();
                if(approvalStatus.equalsIgnoreCase("Accepted")){
                    logger.info(i+" "+ approvalStatus);
                    fw.write(i+" "+ approvalStatus+"\n");
                    break;
                }
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher19aa(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            String account = switchToAccountsTab().createAccount("Customer");
            Constant.accountName=account;

            ContactsPage contacts = new ContactsPage(page);
            String contact = contacts.createCRMContact(account);
            Constant.contact=contact;
            writeToFile("EndToEndTestContact.txt",contact);

            opportunities = new OpportunitiesPage(page);
            page.locator("//a[@title='"+account+"']").first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(account, contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            Locator locatorProjectDates =projectDates.filter(new Locator. FilterOptions().setHasText(closeDateElement));

            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");
            String endDate = LocalDate.now().plusMonths(4).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            Locator locatorProjectEndDates =projectDates.filter(new Locator. FilterOptions().setHasText(endDate));

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");

            String quote = quotes.createQuoteFromAccountEfax2(true, account, contact, opportunity,"Contract");
            netAmount = quotes.addQuoteLineItemsSubscriptionTermEndToEndTestEfax(quote,0,"12");
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            fw.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }

            page.waitForTimeout(3000);


            String quoteEfaxCorporateSubscriptionTerm = subscriptionTermLocator.first().textContent();
            softly.assertThat(quoteEfaxCorporateSubscriptionTerm).isEqualToIgnoringCase("12");
            logger.info(quoteEfaxCorporateSubscriptionTerm);
            fw.write(quoteEfaxCorporateSubscriptionTerm);

            String quoteEfaxCorporateProductCount = eFaxCorporateProductCount.first().textContent();
            softly.assertThat(quoteEfaxCorporateProductCount).isEqualToIgnoringCase("10");
            logger.info(quoteEfaxCorporateProductCount);
            fw.write(quoteEfaxCorporateProductCount);

            page.waitForTimeout(3000);

            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(5000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(3000);
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            Locator acvOpportunity=page.locator("//records-record-layout-item[@field-label='Actual ACV']//lightning-formatted-text");
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }*/
    public void writeToFile(String fileName, String data){
        try {
            String filePath2 = Paths.get("test-output/report/Files/")+ "\\" + fileName;
            FileWriter fw2=new FileWriter(filePath2);
            fw2.write(data);
            fw2.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /*public String checkQuoteApprovalStatus(Locator statusQuoteLocator){
        String quoteStatus="";
        for(int i=0;i<20;i++){
            quoteStatus = statusQuoteLocator.textContent();
            logger.info(quoteStatus);
            if(quoteStatus.equalsIgnoreCase("Accepted")){
                softly.assertThat(quoteStatus).isEqualToIgnoringCase("Accepted");
                break;
            }
            page.waitForTimeout(3000);
            page.reload();
        }
        return quoteStatus;
    }*/
    /*public String createOpportunityWithoutQuote(String recordFileLocation){
        try {
            navigateToMenu("Accounts");
            Constant.accountName = switchToAccountsTab().createAccount("Customer");

            ContactsPage contacts = new ContactsPage(page);
            Constant.contact = contacts.createCRMContact(Constant.accountName);
            writeToFile("EndToEndTestContact.txt",Constant.contact);
            opportunities = new OpportunitiesPage(page);
            constantAccountName.first().click();
            page.waitForTimeout(3000);
            Constant.Opportunity = opportunities.createOpportunity(Constant.accountName, Constant.contact);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Constant.Opportunity;
    }*/
    /*public String createQuoteEfaxAthena(String recordFileLocation) throws IOException {
        //String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            navigateToMenu("Accounts");
            Constant.accountName = switchToAccountsTab().createAccount("Customer");

            ContactsPage contacts = new ContactsPage(page);
            Constant.contact = contacts.createCRMContactWithAdmin(Constant.accountName);
            writeToFile("EndToEndTestContact.txt",Constant.contact);

            opportunities = new OpportunitiesPage(page);
            constantAccountName.first().click();
            page.waitForTimeout(3000);
            opportunity = opportunities.createOpportunity(Constant.accountName, Constant.contact);
            QuotesPage quotes = new QuotesPage(page);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities")).first().click();
            page.waitForTimeout(3000);
            opportunityLocator(opportunity).first().click();
            page.waitForTimeout(3000);
            String closeDateElement= dateElement.first().textContent();
            logger.info("Close Date "+closeDateElement);
            fw.write("Close Date "+closeDateElement+"\n");
            String estProjectStartDateElement=opportunityEstStartDate.first().textContent();
            logger.info("Est Project Start Date "+ estProjectStartDateElement);
            fw.write("Est Project Start Date "+ estProjectStartDateElement+"\n");

            String estProjectEndDateElement=opportunityEstEndDate.first().textContent();
            logger.info("Est Project End Date "+ estProjectEndDateElement);
            fw.write("Est Project End Date "+ estProjectEndDateElement+"\n");

            String quote = quotes.createQuoteFromAccount(true, Constant.accountName, Constant.contact, opportunity);
            writeToFile("QuoteId.txt",quote);
            netAmount = quotes.addQuoteLineItemsDiscountEndToEndTest(quote,9,9,5,5);
            page.waitForTimeout(3000);
            String status = statusLocator.first().textContent();
            logger.info(status);
            AnalysisLogger.write(status+"\n");
            softly.assertThat(status).isEqualToIgnoringCase("Draft");
            editPaymentTerms.click();
            page.waitForTimeout(1000);
            page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Payment Terms")).click();
            page.waitForTimeout(1000);
            page.selectFromDropDown("Due on receipt").first().click();
            logger.info("Payment terms changed to - Due on receipt");
            AnalysisLogger.write("Payment terms changed to - Due on receipt"+"\n");
            page.locator(saveEditButton).first().click();
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(3000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");

            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }*/
    /*public void approveAsUserWithComments(String user, String comments){
        try {
            Locator quoteIdOnHomeScreen = page.locator("//h3[@title='"+ Constant.quoteId+"']/a").first();
            LoginPage loginPage=new LoginPage(page);
            loginPage.loginToSalesForce(true,user,"");
            quoteIdOnHomeScreen.click();
            page.waitForTimeout(3000);
            approveLinkLocator.first().click();
            page.waitForTimeout(3000);
            approvalTextBox.first().fill(comments);
            neutralButton.first().click();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }*/
    /*public String createQuotefromApplauncher10b(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            Constant.quoteId=readFromFile("QuoteId.txt");
            LoginPage loginPage=new LoginPage(page);
            approveAsUserWithComments("Brett","Approving the Quote as Manager");
            loginPage.loginToSalesForce(true,"sales","");
            opportunity=readFromFile("EndToEndTest.txt");
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            String quoteStatus = statusLocator.textContent();
            if(quoteStatus.equalsIgnoreCase("In Review")){
                logger.info(quoteStatus +"  Level 2 approval required");
            }
            else {
                softly.assertThat(quoteStatus).isEqualToIgnoringCase("Accepted");
                logger.info(quoteStatus);
                fw.write(quoteStatus);
            }
            String quotePaymentTermApproval = paymentTermApproval.textContent();
            softly.assertThat(quotePaymentTermApproval).isEqualToIgnoringCase("Approved");
            logger.info(quotePaymentTermApproval);
            fw.write(quoteStatus);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher10c(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            Constant.quoteId=readFromFile("QuoteId.txt");
            LoginPage loginPage=new LoginPage(page);
            approveAsUserWithComments("Franklin","Approving the Quote as Sales VP");
            loginPage.loginToSalesForce(true,"sales","");
            opportunity=readFromFile("EndToEndTest.txt");
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            String quoteStatus = statusLocator.textContent();
            if(quoteStatus.equalsIgnoreCase("In Review")){
                logger.info(quoteStatus +"  Level 2 approval required");
            }
            else {
                softly.assertThat(quoteStatus).isEqualToIgnoringCase("Accepted");
                logger.info(quoteStatus);
                fw.write(quoteStatus);
            }
            String quotePaymentTermApproval = paymentTermApproval.textContent();
            softly.assertThat(quotePaymentTermApproval).isEqualToIgnoringCase("Approved");
            logger.info(quotePaymentTermApproval);
            fw.write(quoteStatus);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher10d1(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            Constant.quoteId=readFromFile("QuoteId.txt");
            LoginPage loginPage=new LoginPage(page);
            approveAsUserWithComments("Michael","Approving the Quote as  Commercial Management (CM) / Client Services");
            loginPage.loginToSalesForce(true,"sales","");
            opportunity=readFromFile("EndToEndTest.txt");
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            String quoteStatus = statusLocator.textContent();
            if(quoteStatus.equalsIgnoreCase("In Review")){
                logger.info(quoteStatus +"  Level 2 approval required");
                writeToFile("QuoteId.txt",quoteId);
            }
            else {
                softly.assertThat(quoteStatus).isEqualToIgnoringCase("Accepted");
                logger.info(quoteStatus);
                fw.write(quoteStatus);
            }
            String quotePaymentTermApproval = paymentTermApproval.textContent();
            softly.assertThat(quotePaymentTermApproval).isEqualToIgnoringCase("Approved");
            logger.info(quotePaymentTermApproval);
            fw.write(quoteStatus);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher10d(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            Constant.quoteId=readFromFile("QuoteId.txt");
            LoginPage loginPage=new LoginPage(page);
            approveAsUserWithComments("Josh","Approving the Quote as  Commercial Management (CM) / Client Services");
            loginPage.loginToSalesForce(true,"sales","");
            opportunity=readFromFile("EndToEndTest.txt");
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            String quoteStatus = statusLocator.textContent();
            if(quoteStatus.equalsIgnoreCase("In Review")){
                logger.info(quoteStatus +"  Level 2 approval required");
            }
            else {
                softly.assertThat(quoteStatus).isEqualToIgnoringCase("Accepted");
                logger.info(quoteStatus);
                fw.write(quoteStatus);
            }
            String quotePaymentTermApproval = paymentTermApproval.textContent();
            softly.assertThat(quotePaymentTermApproval).isEqualToIgnoringCase("Approved");
            logger.info("quotePaymentTermApproval :"+ quotePaymentTermApproval);
            fw.write(quoteStatus);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }
    public String createQuotefromApplauncher11b(String recordFileLocation) throws IOException {
        String  urlCurrent="";
        try {
            FileWriter fw=new FileWriter(recordFileLocation);
            Constant.quoteId=readFromFile("QuoteId.txt");
            LoginPage loginPage=new LoginPage(page);
            approveAsUserWithComments("Jon","Approving the Quote as  Commercial Management (CM) / Client Services");
            loginPage.loginToSalesForce(true,"sales","");
            opportunity=readFromFile("EndToEndTest.txt");
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            String quoteStatus = statusLocator.textContent();
            if(quoteStatus.equalsIgnoreCase("In Review")){
                logger.info(quoteStatus +"  Level 2 approval required");
                writeToFile("QuoteId.txt",quoteId);

            }
            else {
                softly.assertThat(quoteStatus).isEqualToIgnoringCase("Accepted");
                logger.info(quoteStatus);
                fw.write(quoteStatus);
            }
            String quotePaymentTermApproval = paymentTermApproval.textContent();
            softly.assertThat(quotePaymentTermApproval).isEqualToIgnoringCase("Approved");
            logger.info(quotePaymentTermApproval);
            fw.write(quoteStatus);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating quote from Applauncher");
        }
        return opportunity;
    }*/

    public void openFirstQuote(){
        quotesLink.first().click();
        page.waitForTimeout(3000);
        Constant.quoteId=quoteDash.first().textContent();
        quoteNumberLocator.click();
        logger.info(Constant.quoteId + " quote page opened");
    }
    //used in other placed apart from clinical sales hence not commenting out
    /*public String validateOrderUnderQuote(String opportunity){
        String urlCurrent="";
        try{
            urlCurrent = page.url();
            logger.info(urlCurrent);
            AnalysisLogger.write("Current URL: " + urlCurrent + "\n");
            // Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            AnalysisLogger.write("Opportunity screen opened"+"\n");
            // Click Quotes link and wait for the quote ID to be visible
            quotesLink.first().click();
            // Wait for the quote ID locator to be visible
            quoteDash.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            Constant.quoteId = quoteDash.first().textContent();
            AnalysisLogger.write("Quote ID: " + Constant.quoteId + "\n");
            // Click the quote number and wait for the orders link to appear on the quote details page
            quoteNumberLocator.click();
            ordersLink.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.info(Constant.quoteId + " quote page opened");
            AnalysisLogger.write(Constant.quoteId + " quote page opened"+"\n");
            // Click Orders link and wait for the order number to be visible
            ordersLink.first().click();
            orderNumberLocator.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            AnalysisLogger.write("Orders list page opened"+"\n");
            // Click the order number and wait for the approve button to be visible
            orderNumberLocator.click();
            approveButtonLocator.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.info("Order page opened");
            AnalysisLogger.write("Order page opened"+"\n");
            // Click Approve button and wait for the approval text box to be visible
            approveButtonLocator.first().click();
            approvalTextBox.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            logger.info("Order Approval page opened");
            AnalysisLogger.write("Order Approval page opened"+"\n");
            // Fill text and click neutral button
            approvalTextBox.first().fill("Approving the Order as Jamie");
            neutralButton.first().click();
            orderApprovalPageFields.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE)); // Or a different state/locator
            AnalysisLogger.write("Order approval process initiated/completed." + "\n");
            for(int i=0;i<orderApprovalPageFields.count();i++){
                logger.info(i+" "+orderApprovalPageFields.nth(i).textContent());
                AnalysisLogger.write(i+" "+orderApprovalPageFields.nth(i).textContent()+"\n");
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.error("Exception occurred while validating order under Quote: " + exception.getMessage()); // Use logger.error
            AnalysisLogger.write("Exception occurred while validating order under Quote"+"\n");
        }
        return Constant.quoteId;
    }*/
    /*public String validateOrderQuoteOpportunityData2(String opportunity,String recordFileLocation){
        String urlCurrent="";
        try{
            urlCurrent = page.url();
            logger.info(urlCurrent);
            AnalysisLogger.write(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            AnalysisLogger.write("\n"+"Opportunity screen opened");
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            AnalysisLogger.write("\n"+Constant.quoteId + " quote page opened");
            page.waitForTimeout(3000);
            ordersLink.first().click();
            page.waitForTimeout(3000);
            logger.info("Order page opened");
            AnalysisLogger.write("\n"+"Order page opened");
            orderNumberLocator.click();
            page.waitForTimeout(3000);
            String status = statusLocator.nth(1).textContent();
            logger.info(status);
            AnalysisLogger.write("\n"+status);
            String activatedDate = page.locator("//records-record-layout-item[@field-label='Activated Date']//lightning-formatted-text").textContent();
            logger.info("Activated Date "+activatedDate);
            AnalysisLogger.write("\n"+"Activated Date "+activatedDate);
            String financeReview = page.locator("//records-record-layout-item[@field-label='Finance Review']//lightning-formatted-text").textContent();
            logger.info(financeReview);
            AnalysisLogger.write("\n"+financeReview);
            //String orderPaymentPercentageTotal = page.locator("//records-record-layout-item[@field-label='Order Payment Schedule Pct Total']//lightning-formatted-number").textContent();
            //logger.info(orderPaymentPercentageTotal);
            //AnalysisLogger.write("\n"+orderPaymentPercentageTotal);
            boolean contracted = page.locator("//records-record-layout-item[@field-label='Contracted']//span[@class='slds-checkbox_faux']").isChecked();
            logger.info("Contracted is set to "+ contracted);
            AnalysisLogger.write("\n"+"Contracted is set to "+ contracted);
            contactsLink.first().click();
            page.waitForTimeout(3000);
            contractNumber.click();
            page.waitForTimeout(3000);
            String accountName = page.locator("//records-record-layout-item[@field-label='Account Name']//a").nth(1).textContent();
            logger.info(accountName);
            AnalysisLogger.write("\n"+accountName);
            navigateToMenu("Accounts");
            searchList(accountName,accountName);
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Assets \\(\\d+\\+?\\)"))).first().click();
            page.waitForTimeout(3000);
            String filterActivateDate = activatedDate.substring(0,activatedDate.indexOf(","));
            logger.info("filter activated date "+filterActivateDate);
            Locator assetsLinks = page.locator("//lightning-primitive-cell-factory[@data-label='Asset Name']//a[@class='slds-truncate']");
            for(int i=0;i<assetsLinks.count();i++){
                assetsLinks.nth(i).click();
                page.waitForTimeout(3000);
                String assetName = page.locator("//records-record-layout-item[@field-label='Asset Name']//lightning-formatted-text").first().textContent();
                String purchaseDate = page.locator("//records-record-layout-item[@field-label='Purchase Date']//lightning-formatted-text").first().textContent();
                logger.info(assetName+" - "+purchaseDate);
                AnalysisLogger.write(assetName+" - "+purchaseDate);
                softly.assertThat(purchaseDate).isEqualToIgnoringCase(filterActivateDate);
                page.waitForTimeout(3000);
                page.goBack();
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String validateOrderQuoteOpportunityData3(String opportunity,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw=new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            fw.write(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("\n"+"Opportunity screen opened");
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write("\n"+Constant.quoteId + " quote page opened");
            page.waitForTimeout(3000);
            ordersLink.first().click();
            page.waitForTimeout(3000);
            logger.info("Order page opened");
            fw.write("\n"+"Order page opened");
            orderNumberLocator.click();
            page.waitForTimeout(3000);
            String status = statusLocator.nth(1).textContent();
            logger.info(status);
            fw.write("\n"+status);
            String activatedDate = page.locator("//records-record-layout-item[@field-label='Activated Date']//lightning-formatted-text").textContent();
            logger.info(activatedDate);
            fw.write("\n"+activatedDate);
            String financeReview = page.locator("//records-record-layout-item[@field-label='Finance Review']//lightning-formatted-text").textContent();
            logger.info(financeReview);
            fw.write("\n"+financeReview);
            *//*String orderPaymentPercentageTotal = page.locator("//records-record-layout-item[@field-label='Order Payment Schedule Pct Total']//lightning-formatted-number").textContent();
            logger.info(orderPaymentPercentageTotal);
            fw.write("\n"+orderPaymentPercentageTotal);*//*
            boolean contracted = page.locator("//records-record-layout-item[@field-label='Contracted']//span[@class='slds-checkbox_faux']").isChecked();
            logger.info("Contracted is set to "+ contracted);
            fw.write("\n"+"Contracted is set to "+ contracted);
            contactsLink.first().click();
            page.waitForTimeout(3000);
            contractNumber.click();
            page.waitForTimeout(3000);
            String contractName = page.locator("//records-record-layout-item[@field-label='Contract Name']//lightning-formatted-text").textContent();
            logger.info(contractName);
            fw.write("\n"+contractName);
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Renewal Forecast")).check();
            page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Renewal Quoted")).check();
            page.locator(saveEditButton).first().click();
            page.waitForTimeout(3000);
            softly.assertThat(page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Related")).first().isVisible()).isTrue();
            page.getByRole(AriaRole.TAB, new Page.GetByRoleOptions().setName("Related")).first().click();
            page.waitForTimeout(3000);
            page.mouse().wheel(0,1024);
            String renewalOpportunity = page.locator("//article[@aria-label='Renewal Opportunities']//span[@class='slds-truncate']").first().textContent();
            logger.info(renewalOpportunity);
            page.waitForTimeout(3000);
            page.locator("//article[@aria-label='Renewal Opportunities']//span[@class='slds-truncate']").first().click();
            page.waitForTimeout(3000);
            String renewalType = page.locator("//records-record-layout-item[@field-label='Type']//lightning-formatted-text").first().textContent();
            logger.info(renewalType);
            fw.write(renewalType);
            String salesRep = page.locator("//records-record-layout-item[@field-label='Sales Rep']//span[@class='slds-assistive-text']").first().textContent();
            salesRep = salesRep.replace("Open ","");
            salesRep = salesRep.replace(" Preview","");
            logger.info(salesRep);
            fw.write(salesRep);
            String primaryContact = page.locator("//records-record-layout-item[@field-label='Primary Contact']//div[@class='slds-grid']").first().textContent();
            primaryContact = primaryContact.substring(0, primaryContact.indexOf("Open"));
            logger.info(primaryContact);
            fw.write(primaryContact);
            String targetedProduct = page.locator("//records-record-layout-item[@field-label='Targeted Product']//lightning-formatted-text").first().textContent();
            logger.info(targetedProduct);
            fw.write(targetedProduct);
            String ccsiBusinessLegalEntity = page.locator("//records-record-layout-item[@field-label='CCSI Business Legal Entity']//div[@class='slds-grid']").first().textContent();
            ccsiBusinessLegalEntity = ccsiBusinessLegalEntity.substring(0, ccsiBusinessLegalEntity.indexOf("Open"));
            logger.info(ccsiBusinessLegalEntity);
            fw.write(ccsiBusinessLegalEntity);
            String forecastCategoryCustom = page.locator(forecastCategoryOverride).first().textContent();
            logger.info(forecastCategoryCustom);
            fw.write(forecastCategoryCustom);
            String opportunityOwner = page.locator("//records-record-layout-item[@field-label='Opportunity Owner']//div[@class='slds-grid']").first().textContent();
            opportunityOwner = opportunityOwner.substring(0, opportunityOwner.indexOf("Open"));
            logger.info(opportunityOwner);
            fw.write(opportunityOwner);
            String description = page.locator("//records-record-layout-item[@field-label='Description']//lightning-formatted-text").first().textContent();
            logger.info(description);
            fw.write(description);
            fw.close();

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }*/
    //used in other places apart from clinical sales hence not commenting out.
    /*public String validateOrderQuoteOpportunityData(String opportunity){
        String urlCurrent="";
        try{
            urlCurrent = page.url();
            logger.info(urlCurrent);
            AnalysisLogger.write(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            AnalysisLogger.write("\n"+"Opportunity screen opened");
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            AnalysisLogger.write("\n"+Constant.quoteId + " quote page opened");
            page.waitForTimeout(3000);
            page.waitForLoadState(LoadState.NETWORKIDLE);
            ordersLink.first().click();
            page.waitForTimeout(3000);
            logger.info("Order page opened");
            AnalysisLogger.write("\n"+"Order page opened");
            orderNumberLocator.click();
            page.waitForTimeout(3000);
            String status = statusLocator.nth(1).textContent();
            logger.info(status);
            AnalysisLogger.write("\n"+status);
            String activatedDate = page.locator("//records-record-layout-item[@field-label='Activated Date']//lightning-formatted-text").textContent();
            logger.info(activatedDate);
            AnalysisLogger.write("\n"+activatedDate);
            String financeReview = page.locator("//records-record-layout-item[@field-label='Finance Review']//lightning-formatted-text").textContent();
            logger.info(financeReview);
            AnalysisLogger.write("\n"+financeReview);
            //String orderPaymentPercentageTotal = page.locator("//records-record-layout-item[@field-label='Order Payment Schedule Pct Total']//lightning-formatted-number").textContent();
            //logger.info(orderPaymentPercentageTotal);
            //AnalysisLogger.write("\n"+orderPaymentPercentageTotal);
            boolean contracted = page.locator("//records-record-layout-item[@field-label='Contracted']//span[@class='slds-checkbox_faux']").isChecked();
            logger.info("Contracted is set to "+ contracted);
            AnalysisLogger.write("\n"+"Contracted is set to "+ contracted);
            contactsLink.first().click();
            page.waitForTimeout(3000);
            contractNumber.click();
            page.waitForTimeout(3000);
            String contractName = page.locator("//records-record-layout-item[@field-label='Contract Name']//lightning-formatted-text").textContent();
            logger.info(contractName);
            AnalysisLogger.write("\n"+contractName);
            String accountName = page.locator("//records-record-layout-item[@field-label='Account Name']//a").nth(1).textContent();
            logger.info(accountName);
            AnalysisLogger.write("\n"+accountName);
            String orderNumber = page.locator("//records-record-layout-item[@field-label='Order']//a").textContent();
            logger.info(orderNumber);
            AnalysisLogger.write("\n"+orderNumber);
            String priceBook = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Consensus Price Book - US")).textContent();
            logger.info(priceBook);
            AnalysisLogger.write("\n"+priceBook);
            String opportunityInOrder = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(opportunity)).textContent();
            logger.info(opportunityInOrder);
            AnalysisLogger.write("\n"+opportunityInOrder);
            String quote = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Q-")).textContent();
            logger.info(quote);
            AnalysisLogger.write("\n"+quote);
            status = statusLocator.nth(1).textContent();
            logger.info(status);
            AnalysisLogger.write("\n"+status);
            String contractStartDate = page.locator("//records-record-layout-item[@field-label='Contract Start Date']//lightning-formatted-text").textContent();
            logger.info(contractStartDate);
            AnalysisLogger.write("\n"+contractStartDate);
            String contractEndDate = page.locator("//records-record-layout-item[@field-label='Contract End Date']//lightning-formatted-text").textContent();
            logger.info(contractEndDate);
            AnalysisLogger.write("\n"+contractEndDate);
            String contractTerm = page.locator("//records-record-layout-item[@field-label='Contract Term (months)']//lightning-formatted-number").textContent();
            logger.info(contractTerm);
            AnalysisLogger.write("\n"+contractTerm);
            String amendmentAndRenewalDate = page.locator("//records-record-layout-item[@field-label='Amendment & Renewal Behavior']//lightning-formatted-text").textContent();
            logger.info(amendmentAndRenewalDate);
            AnalysisLogger.write("\n"+amendmentAndRenewalDate);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Subscriptions \\(\\d+\\+?\\)"))).first().click();
            page.waitForTimeout(3000);
            String productCode=page.locator("//slot[starts-with(text(),'All')]").first().textContent();
            logger.info(productCode);
            AnalysisLogger.write("\n"+productCode);
            String startDate1=page.locator("//lightning-primitive-cell-factory[@data-label='Start Date']//lightning-formatted-date-time").first().textContent();
            logger.info(startDate1);
            AnalysisLogger.write("\n"+startDate1);
            String endDate1=page.locator("//lightning-primitive-cell-factory[@data-label='End Date']//lightning-formatted-date-time").first().textContent();
            logger.info(endDate1);
            AnalysisLogger.write("\n"+endDate1);
            String productCode2=page.locator("//slot[starts-with(text(),'SST')]").first().textContent();
            logger.info(productCode2);
            AnalysisLogger.write("\n"+productCode2);
            String startDate2=page.locator("//lightning-primitive-cell-factory[@data-label='Start Date']//lightning-formatted-date-time").nth(1).textContent();
            logger.info(startDate2);
            AnalysisLogger.write("\n"+startDate2);
            String endDate2=page.locator("//lightning-primitive-cell-factory[@data-label='End Date']//lightning-formatted-date-time").nth(1).textContent();
            logger.info(endDate2);
            AnalysisLogger.write("\n"+endDate2);
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            String contractExecutionDate = page.locator("//records-record-layout-item[@field-label='Contract Execution Date']//lightning-formatted-text").textContent();
            logger.info(contractExecutionDate);
            AnalysisLogger.write("\n"+contractExecutionDate);
            navigateToMenu("Accounts");
            searchList(accountName,accountName);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile("Subscriptions \\(\\d+\\+?\\)"))).first().click();
            page.waitForTimeout(3000);
            Locator subscriptionProducts=page.locator("//lightning-primitive-cell-factory[@data-label='Product Name']//span[@part='formatted-rich-text']");
            Locator productStartDates=page.locator("//lightning-primitive-cell-factory[@data-label='Subscription Start Date']//lightning-formatted-date-time");
            for(int i=0;i<subscriptionProducts.count();i++){
                logger.info(subscriptionProducts.nth(i).textContent());
                AnalysisLogger.write("\n"+subscriptionProducts.nth(i).textContent());
                if(subscriptionProducts.nth(i).textContent().equalsIgnoreCase(productCode))
                {
                    logger.info("Product 1 match "+productCode);
                    AnalysisLogger.write("\n"+"Product 1 match "+productCode);
                }
                if(subscriptionProducts.nth(i).textContent().equalsIgnoreCase(productCode2))
                {
                    logger.info("Product 2 match "+productCode2);
                    AnalysisLogger.write("\n"+"Product 2 match "+productCode2);
                }
                logger.info(productStartDates.nth(i).textContent());
                AnalysisLogger.write("\n"+productStartDates.nth(i).textContent());

            }
            navigateToMenu("Accounts");
            searchList(accountName,accountName);
            page.waitForTimeout(3000);
            String accountType =  page.locator("//records-record-layout-item[@field-label='Type']//lightning-formatted-text").selectFromDropDown("Customer", new Locator.GetByTextOptions().setExact(true)).textContent();
            softly.assertThat(accountType).isEqualToIgnoringCase("Customer");
            logger.info("Account type changed to "+accountType);
            AnalysisLogger.write("Account type changed to "+accountType);
            String accountNumber =  page.locator("//records-record-layout-item[@field-label='Account Number']//lightning-formatted-text").first().textContent();
            logger.info("Account number is "+accountNumber);
            AnalysisLogger.write("Account number is "+accountNumber);
            softly.assertThat(accountNumber).contains("C-");
            page.waitForTimeout(3000);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String paymentScheduleValidations4(String opportunity, String timeStamp2, String milestone, String milestomeTerm,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            
            if(amountOpportunity.isVisible())
            {
                logger.info(amountOpportunity.textContent());
                fw.write(amountOpportunity.textContent()+"\n");
                if(amountOpportunity.textContent().equalsIgnoreCase(netAmount))
                {
                    logger.info("Net Amount and Opportunity amount matched");
                    fw.write("Net Amount and Opportunity amount matched"+"\n");
                }
            }
            
            if(subscriptionTermOpportunity.isVisible())
            {
                logger.info(subscriptionTermOpportunity.textContent());
                fw.write(subscriptionTermOpportunity.textContent()+"\n");
            }
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            //page.locator("//button[@name='Edit']").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            Locator dialog=dialogAlohaFrameLocator;
            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            String futureDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            dialog.locator(contractExecutionDateLocator).fill(futureDate);
            dialog.locator(saveEditButton).first().click();
            logger.info("Clicked Save on Opportunity 1");
            fw.write("Clicked Save on Opportunity 1"+"\n");
            page.waitForTimeout(3000);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String paymentScheduleValidations3(String opportunity, String timeStamp2, String milestone, String milestomeTerm,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            
            if(amountOpportunity.isVisible())
            {
                logger.info(amountOpportunity.textContent());
                fw.write(amountOpportunity.textContent()+"\n");
                if(amountOpportunity.textContent().equalsIgnoreCase(netAmount))
                {
                    logger.info("Net Amount and Opportunity amount matched");
                    fw.write("Net Amount and Opportunity amount matched"+"\n");
                }
            }
            
            if(subscriptionTermOpportunity.isVisible())
            {
                logger.info(subscriptionTermOpportunity.textContent());
                fw.write(subscriptionTermOpportunity.textContent()+"\n");
            }
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            //page.locator("//button[@name='Edit']").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            Locator dialog=dialogAlohaFrameLocator;
            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            String futureDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            dialog.locator(contractExecutionDateLocator).fill(futureDate);
            dialog.locator(saveEditButton).first().click();
            logger.info("Clicked Save on Opportunity 1");
            fw.write("Clicked Save on Opportunity 1"+"\n");
            page.waitForTimeout(3000);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Payment Schedules (Quote) (1)")).first().click();
            page.waitForTimeout(3000);
            Constant.paymentSchedule=page.locator("//slot[starts-with(text(),'QPS-')]").first().textContent();
            page.locator("//lightning-primitive-cell-factory[@data-label='Payment Schedule (Quote) Name']//a[@class='slds-truncate']").click();
            page.waitForTimeout(3000);
            logger.info(Constant.paymentSchedule+" Payment Schedule (Quote) opened");
            fw.write(Constant.paymentSchedule+" Payment Schedule (Quote) opened"+"\n");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            String defaultStatus = page.locator("//button[@role='combobox']").first().getAttribute("data-value");
            logger.info(defaultStatus);
            softly.assertThat(defaultStatus).isEqualToIgnoringCase("On Execution");
            page.locator("//button[@aria-label='Milestone']").click();
            page.locator("lightning-base-combobox-item[data-value='"+milestone+"']").click();
            logger.info("Payment Schedule set to "+milestone);
            fw.write("Payment Schedule set to "+milestone+"\n");
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill(milestomeTerm);
            String pctValue = page.locator("//input[@name='Pct_Due__c']").textContent();
            page.locator("//input[@name='Pct_Due__c']").clear();
            page.locator("//input[@name='Pct_Due__c']").fill("");
            page.locator(saveEditButton).first().click();
            page.waitForTimeout(3000);
            String subscriptionMonth = page.locator("//records-record-layout-item[@field-label='Milestone Term (Month)']//lightning-formatted-number").textContent();
            softly.assertThat(subscriptionMonth).isEqualToIgnoringCase("36");
            logger.info("Record saved successfully after subscription month set to "+subscriptionMonth+" which is divisible by 3");
            fw.write("Record saved successfully after subscription month set to "+subscriptionMonth+" which is divisible by 3");
            page.waitForTimeout(3000);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String paymentScheduleValidations(String opportunity, String timeStamp2, String milestone, String milestomeTerm,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            
            if(amountOpportunity.isVisible())
            {
                logger.info(amountOpportunity.textContent());
                fw.write(amountOpportunity.textContent()+"\n");
                if(amountOpportunity.textContent().equalsIgnoreCase(netAmount))
                {
                    logger.info("Net Amount and Opportunity amount matched");
                    fw.write("Net Amount and Opportunity amount matched"+"\n");
                }
            }
            
            if(subscriptionTermOpportunity.isVisible())
            {
                logger.info(subscriptionTermOpportunity.textContent());
                fw.write(subscriptionTermOpportunity.textContent()+"\n");
            }
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            //page.locator("//button[@name='Edit']").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            Locator dialog=dialogAlohaFrameLocator;
            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            String futureDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            dialog.locator(contractExecutionDateLocator).fill(futureDate);
            dialog.locator(saveEditButton).first().click();
            logger.info("Clicked Save on Opportunity 1");
            fw.write("Clicked Save on Opportunity 1"+"\n");
            page.waitForTimeout(3000);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Payment Schedules (Quote) (1)")).first().click();
            page.waitForTimeout(3000);
            Constant.paymentSchedule=page.locator("//slot[starts-with(text(),'QPS-')]").first().textContent();
            page.locator("//lightning-primitive-cell-factory[@data-label='Payment Schedule (Quote) Name']//a[@class='slds-truncate']").click();
            page.waitForTimeout(3000);
            logger.info(Constant.paymentSchedule+" Payment Schedule (Quote) opened");
            fw.write(Constant.paymentSchedule+" Payment Schedule (Quote) opened"+"\n");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            String defaultStatus = page.locator("//button[@role='combobox']").first().getAttribute("data-value");
            logger.info(defaultStatus);
            softly.assertThat(defaultStatus).isEqualToIgnoringCase("On Execution");
            page.locator("//button[@aria-label='Milestone']").click();
            page.locator("lightning-base-combobox-item[data-value='"+milestone+"']").click();
            logger.info("Payment Schedule set to "+milestone);
            fw.write("Payment Schedule set to "+milestone+"\n");
            page.locator("//input[@name='Pct_Due__c']").clear();
            page.locator("//input[@name='Pct_Due__c']").fill("0");
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill(milestomeTerm);
            //page.locator(saveEditButton).first().click();
            page.waitForTimeout(3000);
            opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the following fields", "Pct Due", "Milestone Term (Month)"});
            Locator helpText = page.locator("//div[@class='slds-form-element__help']");
            for(int i=0;i<helpText.count();i++){
                logger.info(helpText.nth(i).textContent());
                fw.write(helpText.nth(i).textContent()+"\n");
            }
            softly.assertThat(helpText.nth(0).textContent()).isEqualToIgnoringCase("Invalid MIlestone Term:  Must be divisible by 3 when Milestone = \"Quarterly\"");
            softly.assertThat(helpText.nth(1).textContent()).isEqualToIgnoringCase("Pct Due must be blank when Milestone is Monthly or Quarterly.");
            page.waitForTimeout(3000);
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill("0");
            opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the following fields", "Pct Due", "Milestone Term (Month)"});
            softly.assertThat(helpText.nth(0).textContent()).isEqualToIgnoringCase("Invalid MIlestone Term:  Must be divisible by 3 when Milestone = \"Quarterly\"");
            softly.assertThat(helpText.nth(1).textContent()).isEqualToIgnoringCase("Pct Due must be blank when Milestone is Monthly or Quarterly.");

            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String paymentScheduleValidations7(String opportunity, String timeStamp2, String milestone, String milestoneTerm,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            page.waitForTimeout(3000);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Payment Schedules (Quote) (1)")).first().click();
            page.waitForTimeout(3000);
            Constant.paymentSchedule=page.locator("//slot[starts-with(text(),'QPS')]").first().textContent();
            page.locator("//lightning-primitive-cell-factory[@data-label='Payment Schedule (Quote) Name']//a[@class='slds-truncate']").click();
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            String defaultStatus = page.locator("//button[@role='combobox']").first().getAttribute("data-value");
            logger.info(defaultStatus);
            softly.assertThat(defaultStatus).isEqualToIgnoringCase("On Execution");
            page.locator("//button[@aria-label='Milestone']").click();
            page.locator("lightning-base-combobox-item[data-value='"+milestone+"']").click();
            logger.info("Payment Schedule set to "+milestone);
            fw.write("Payment Schedule set to "+milestone+"\n");
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill(milestoneTerm);
            //page.locator(saveEditButton).first().click();
            page.waitForTimeout(3000);
            //opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the following fields", "Pct Due", "Milestone Term (Month)"});
            Locator helpText = page.locator("//div[@class='slds-form-element__help']");
            for(int i=0;i<helpText.count();i++){
                logger.info(helpText.nth(i).textContent());
                fw.write(helpText.nth(i).textContent()+"\n");
            }
            softly.assertThat(helpText.nth(0).textContent()).isEqualToIgnoringCase("Invalid MIlestone Term:  Must be divisible by 3 when Milestone = \"Quarterly\"");
            softly.assertThat(helpText.nth(1).textContent()).isEqualToIgnoringCase("Pct Due must be blank when Milestone is Monthly or Quarterly.");
            page.waitForTimeout(3000);
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill("0");
            opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the following fields", "Pct Due", "Milestone Term (Month)"});
            softly.assertThat(helpText.nth(0).textContent()).isEqualToIgnoringCase("Invalid MIlestone Term:  Must be divisible by 3 when Milestone = \"Quarterly\"");
            softly.assertThat(helpText.nth(1).textContent()).isEqualToIgnoringCase("Pct Due must be blank when Milestone is Monthly or Quarterly.");
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill("40");
            opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the following fields", "Pct Due", "Milestone Term (Month)"});
            softly.assertThat(helpText.nth(0).textContent()).isEqualToIgnoringCase("Invalid MIlestone Term:  Must be divisible by 3 when Milestone = \"Quarterly\"");
            softly.assertThat(helpText.nth(1).textContent()).isEqualToIgnoringCase("Pct Due must be blank when Milestone is Monthly or Quarterly.");
            page.locator("//button[@name='CancelEdit']").first().click();
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill("45");
            page.locator(saveEditButton).first().click();
            logger.info("Record Saved Successfully "+milestone);
            fw.write("Record Saved Successfully  "+milestone+"\n");
            page.waitForTimeout(3000);
            Locator milestoneMonth=page.locator("//records-record-layout-item[@field-label='Milestone Term (Month)']//lightning-formatted-number");
            if(milestoneMonth.isVisible())
            {
                logger.info(milestoneMonth.textContent());
                fw.write(milestoneMonth.textContent()+"\n");
            }
            softly.assertThat(milestoneMonth.textContent()).isEqualToIgnoringCase("45");

            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String paymentScheduleValidations6(String opportunity, String timeStamp2, String milestone, String milestoneTerm,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            
            if(amountOpportunity.isVisible())
            {
                logger.info(amountOpportunity.textContent());
                fw.write(amountOpportunity.textContent()+"\n");
                if(amountOpportunity.textContent().equalsIgnoreCase(netAmount))
                {
                    logger.info("Net Amount and Opportunity amount matched");
                    fw.write("Net Amount and Opportunity amount matched"+"\n");
                }
            }
            
            if(subscriptionTermOpportunity.isVisible())
            {
                logger.info(subscriptionTermOpportunity.textContent());
                fw.write(subscriptionTermOpportunity.textContent()+"\n");
            }
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }

            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            ordersLink.first().click();
            page.waitForTimeout(3000);
            orderNumberLocator.click();
            page.waitForTimeout(3000);
            approvalHistoryLocator.first().click();
            logger.info("Order Approval page opened");
            fw.write("Order Approval page opened"+"\n");
            page.locator("//div[@title='Reject']").first().click();
            page.waitForTimeout(3000);
            approvalTextBox.first().fill("Rejecting the Order as Jamie");
            neutralButton.first().click();
            page.waitForTimeout(3000);
            for(int i=0;i<orderApprovalPageFields.count();i++){
                logger.info(i+" "+orderApprovalPageFields.nth(i).textContent());
                fw.write(i+" "+orderApprovalPageFields.nth(i).textContent()+"\n");
            }
            page.goBack();
            //page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile(""))).first().click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Payment Schedules (Order) (1)")).first().click();
            page.waitForTimeout(3000);
            Constant.paymentSchedule=page.locator("//slot[starts-with(text(),'OPS')]").first().textContent();
            page.locator("//lightning-primitive-cell-factory[@data-label='Payment Schedule Name']//a[@class='slds-truncate']").click();
            page.waitForTimeout(3000);
            logger.info(Constant.paymentSchedule+" Payment Schedule (Order) opened");
            fw.write(Constant.paymentSchedule+" Payment Schedule (Order) opened"+"\n");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            String defaultStatus = page.locator("//button[@role='combobox']").first().getAttribute("data-value");
            logger.info(defaultStatus);
            softly.assertThat(defaultStatus).isEqualToIgnoringCase("On Execution");
            page.locator("//button[@aria-label='Milestone']").click();
            page.locator("lightning-base-combobox-item[data-value='"+milestone+"']").click();
            logger.info("Payment Schedule set to "+milestone);
            fw.write("Payment Schedule set to "+milestone+"\n");
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill(milestoneTerm);
            page.locator("//input[@name='Pct_Due__c']").clear();
            page.locator("//input[@name='Pct_Due__c']").click();
            for(int i=0;i<3;i++){
                page.keyboard().press("Backspace");
            }
            page.locator(saveEditButton).first().click();
            logger.info("Record Saved Successfully "+milestone);
            fw.write("Record Saved Successfully  "+milestone+"\n");
            page.waitForTimeout(3000);
            Locator milestoneMonth=page.locator("//records-record-layout-item[@field-label='Milestone Term (Month)']//lightning-formatted-number");
            if(milestoneMonth.isVisible())
            {
                logger.info(milestoneMonth.textContent());
                fw.write(milestoneMonth.textContent()+"\n");
            }
            softly.assertThat(milestoneMonth.textContent()).isEqualToIgnoringCase(milestoneTerm);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String paymentScheduleValidations5(String opportunity, String timeStamp2, String milestone, String milestomeTerm,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            
            if(amountOpportunity.isVisible())
            {
                logger.info(amountOpportunity.textContent());
                fw.write(amountOpportunity.textContent()+"\n");
                if(amountOpportunity.textContent().equalsIgnoreCase(netAmount))
                {
                    logger.info("Net Amount and Opportunity amount matched");
                    fw.write("Net Amount and Opportunity amount matched"+"\n");
                }
            }
            
            if(subscriptionTermOpportunity.isVisible())
            {
                logger.info(subscriptionTermOpportunity.textContent());
                fw.write(subscriptionTermOpportunity.textContent()+"\n");
            }
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }

            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            ordersLink.first().click();
            page.waitForTimeout(3000);
            orderNumberLocator.click();
            page.waitForTimeout(3000);
            approvalHistoryLocator.first().click();
            logger.info("Order Approval page opened");
            fw.write("Order Approval page opened"+"\n");
            page.locator("//div[@title='Reject']").first().click();
            page.waitForTimeout(3000);
            approvalTextBox.first().fill("Rejecting the Order as Jamie");
            neutralButton.first().click();
            page.waitForTimeout(3000);
            for(int i=0;i<orderApprovalPageFields.count();i++){
                logger.info(i+" "+orderApprovalPageFields.nth(i).textContent());
                fw.write(i+" "+orderApprovalPageFields.nth(i).textContent()+"\n");
            }
            page.goBack();
            //page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(Pattern.compile(""))).first().click();
            page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Payment Schedules (Order) (1)")).first().click();
            page.waitForTimeout(3000);
            Constant.paymentSchedule=page.locator("//slot[starts-with(text(),'OPS')]").first().textContent();
            page.locator("//lightning-primitive-cell-factory[@data-label='Payment Schedule Name']//a[@class='slds-truncate']").click();
            page.waitForTimeout(3000);
            logger.info(Constant.paymentSchedule+" Payment Schedule (Order) opened");
            fw.write(Constant.paymentSchedule+" Payment Schedule (Order) opened"+"\n");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            String defaultStatus = page.locator("//button[@role='combobox']").first().getAttribute("data-value");
            logger.info(defaultStatus);
            softly.assertThat(defaultStatus).isEqualToIgnoringCase("On Execution");
            page.locator("//button[@aria-label='Milestone']").click();
            page.locator("lightning-base-combobox-item[data-value='"+milestone+"']").click();
            logger.info("Payment Schedule set to "+milestone);
            fw.write("Payment Schedule set to "+milestone+"\n");
            page.locator("//input[@name='Pct_Due__c']").clear();
            page.locator("//input[@name='Pct_Due__c']").fill("0");
            page.locator("//input[@name='Milestone_Term_Month__c']").clear();
            page.locator("//input[@name='Milestone_Term_Month__c']").fill(milestomeTerm);
            //page.locator(saveEditButton).first().click();
            page.waitForTimeout(3000);
            opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the following fields", "Pct Due", "Milestone Term (Month)"});
            Locator helpText = page.locator("//div[@class='slds-form-element__help']");
            for(int i=0;i<helpText.count();i++){
                logger.info(helpText.nth(i).textContent());
                fw.write(helpText.nth(i).textContent()+"\n");
            }
            softly.assertThat(helpText.nth(0).textContent()).isEqualToIgnoringCase("Invalid MIlestone Term:  Must be divisible by 3 when Milestone = \"Quarterly\"");
            softly.assertThat(helpText.nth(1).textContent()).isEqualToIgnoringCase("Pct Due must be blank when Milestone is Monthly or Quarterly.");
            page.waitForTimeout(3000);


            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }*/
    /*public String createOrderUnderQuote2(String opportunity, String timeStamp2){
        String urlCurrent="";
        String recordFileLocation="C:\\Users\\Ranjan\\Git Repositories\\QaAutomation-SalesforceCCSI\\test-output\\report\\videos\\ContractDocumentUploadStatusCheck\\ContractDocumentUploadStatusCheck-"+timeStamp2+".log";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            Locator dialog=dialogAlohaFrameLocator;
            dialog.locator(stageLocator).first().click();
            dialog.selectFromDropDown("Closed Won").first().click();
            String documentUploadStatus = dialog.locator(documentUploadCompletedLocator).textContent();
            logger.info(documentUploadStatus);
            fw.write(documentUploadStatus+"\n");
            softly.assertThat(documentUploadStatus).isEqualToIgnoringCase("No");
            opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the errors on this page.", "Opportunity must have a Primary Quote and it must be \"Accepted\" or \"Approved\"","This Opportunity requires a Primary Quote with a Start Date value. Please update",
                    "Review the following fields","Contract Document Upload Completed?","Contract Execution Date"});
            page.waitForTimeout(3000);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }*/
    /*public String createOrderUnderQuote(String opportunity) {
        String urlCurrent = "";
        try {
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            // Constant.Opportunity=readFromFile("EndToEndTest.txt"); // This line might need to be outside, or readToFile adjusted
            // Assuming opportunityn is the 'opportunity' parameter used later
            opportunities = new OpportunitiesPage(page); // Re-initialize or ensure this is done once
            opportunities.switchToOpportunityDetailsScreen(opportunity); // Use the passed parameter
            openLink(opportunity);
            logger.info("Opportunity screen opened");
            AnalysisLogger.write("Opportunity screen opened" + "\n");
            page.waitForLoadState(LoadState.LOAD);

            // Wait for amountOpportunity to be visible before checking
            if (amountOpportunity.isVisible()) {
                logger.info(amountOpportunity.textContent());
                AnalysisLogger.write(amountOpportunity.textContent() + "\n");
                if (amountOpportunity.textContent().equalsIgnoreCase(netAmount)) {
                    logger.info("Net Amount and Opportunity amount matched");
                    AnalysisLogger.write("Net Amount and Opportunity amount matched" + "\n");
                }
            }
            if (subscriptionTermOpportunity.isVisible()) {
                logger.info(subscriptionTermOpportunity.textContent());
                AnalysisLogger.write(subscriptionTermOpportunity.textContent() + "\n");
            }
            if (mrrOpportunity.isVisible()) {
                logger.info(mrrOpportunity.textContent());
                AnalysisLogger.write(mrrOpportunity.textContent() + "\n");
            }
            if (acvOpportunity.isVisible()) {
                logger.info(acvOpportunity.textContent());
                AnalysisLogger.write(acvOpportunity.textContent() + "\n");
            }
            Locator dialog = dialogAlohaFrameLocator;
            String futureDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            editButtonOpportunityPage
                    .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            editButtonOpportunityPage.isEnabled();
            editButtonOpportunityPage.click();

            // Replaced page.waitForTimeout(5000); - Wait for the dialog to be visible after clicking Edit
            dialogAlohaFrameLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            // dialogAlohaFrameLocator.isVisible(); // No need to call isVisible() after waitFor
            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            dialog.locator(contractExecutionDateLocator).fill(futureDate);

            dialog.locator(corpIdLocator).click();
            dialog.locator(corpIdLocator).fill(generateSixDigitNumber());


            // Wait for stageLocator to be visible/enabled before clicking
            dialog.locator(stageLocator).first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            dialog.locator(stageLocator).first().click();

            // Wait for closedWonPendingStage text to appear in the dialog before clicking
            dialog.selectFromDropDown(closedWonPendingStage).first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            dialog.selectFromDropDown(closedWonPendingStage).first().click();

            // Wait for saveEditButton to be enabled/visible before clicking
            dialog.locator(saveEditButton).first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            dialog.locator(saveEditButton).first().click();
            logger.info("Clicked Save on Opportunity with Successful save with Closed/Won Pending");
            AnalysisLogger.write("Clicked Save on Opportunity with Successful save with Closed/Won Pending" + "\n");

            // Replaced page.waitForTimeout(3000); - Wait for the stage text to update after save
            opportunityStage.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String stage = opportunityStage.first().textContent();
            logger.info(stage);
            AnalysisLogger.write(stage + "\n");
            String closeDate = opportunityCloseDate.first().textContent();
            logger.info(closeDate);
            AnalysisLogger.write(closeDate + "\n");
            String forecastCategory = opportunityForecaseCategory.first().textContent();
            logger.info(forecastCategory);
            AnalysisLogger.write(forecastCategory + "\n");
            String estProjectStartDate = opportunityEstStartDate.first().textContent();
            logger.info(estProjectStartDate);
            AnalysisLogger.write(estProjectStartDate + "\n");
            String estProjectEndDate = opportunityEstEndDate.first().textContent();
            logger.info(estProjectEndDate);
            AnalysisLogger.write(estProjectEndDate + "\n");

            LoginPage loginPage = new LoginPage(page); // Re-initialize if necessary, or pass from constructor
            loginPage.loginToSalesForce(true, "Michael", "");

            // Replaced page.waitForTimeout(5000); - Wait for an element on the dashboard/home page after login
            // Assuming this navigates to a new page, Playwright implicitly waits for navigation.
            // If there's a specific element that appears after successful login, wait for that.
            // Example: page.locator("#dashboardTitle").waitFor();

            readAllNotificationsAndClose(); // Assuming this method handles its own waits.

            // Wait for the opportunity link to be visible before clicking
            page.locator("//h3[@title='" + opportunity + "']/a").first()
                    .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            page.locator("//h3[@title='" + opportunity + "']/a").first().click();

            // Replaced page.waitForTimeout(3000); - Wait for the approve button to be visible
            approveButtonLocator.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            approveButtonLocator.first().click();

            // Replaced page.waitForTimeout(3000); - Wait for the text box to be visible
            approvalTextBox.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            approvalTextBox.first().fill("Approving the Opportunity for testing");

            // Wait for neutral button to be enabled
            neutralButton.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            neutralButton.first().click();

            // Replaced page.waitForTimeout(3000); - Wait for the approval status to update
            approveButtonOne.nth(0).waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String checkApproval = approveButtonOne.nth(0).textContent();
            logger.info(checkApproval);
            AnalysisLogger.write(checkApproval + "\n");
            String checkApproval2 = approveButtonOne.nth(1).textContent();
            logger.info(checkApproval2);
            AnalysisLogger.write(checkApproval2 + "\n");

            //Login as Sales user again
            // Replaced page.waitForTimeout(5000); - Wait for navigation or a key element after re-login
            // Assuming opportunities.switchToOpportunityDetailsScreen also handles navigation waits
            opportunities.switchToOpportunityDetailsScreen(opportunity); // Use the passed parameter

            // Wait for quotesLink to be visible
            quotesLink.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            quotesLink.first().click();

            // Replaced page.waitForTimeout(3000); - Wait for the quote ID to appear
            quoteDash.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            Constant.quoteId = quoteDash.first().textContent();

            // Wait for quoteNumberLocator to be clickable
            quoteNumberLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            AnalysisLogger.write(Constant.quoteId + " quote page opened" + "\n");

            // Replaced page.waitForTimeout(3000); - Wait for statusLocator to be visible
            statusLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String quoteStatus = statusLocator.textContent();
            logger.info(quoteStatus);
            AnalysisLogger.write(quoteStatus + "\n");
            boolean orderedCheckbox = orderedCheckBoxLocator.isChecked();
            logger.info("Ordered Checkbox is checked :" + orderedCheckbox);
            AnalysisLogger.write("Ordered Checkbox is checked :" + orderedCheckbox + "\n");

            // Wait for ordersLink to be visible
            ordersLink.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            ordersLink.first().click();

            // Replaced page.waitForTimeout(3000); - Wait for orderNumberLocator to be visible
            orderNumberLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            orderNumberLocator.click();

            // Replaced page.waitForTimeout(3000); - Wait for orderNameLocator to be visible
            orderNameLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String orderName = orderNameLocator.textContent();
            logger.info("Order Name :" + orderName);
            AnalysisLogger.write("Order Name :" + orderName + "\n");
            String orderType = orderTypeLocator.textContent();
            logger.info("Order Type :" + orderType);
            AnalysisLogger.write("Order Type :" + orderType + "\n");
            String orderAmount = orderAmountLocator.textContent();
            logger.info("Order Amount :" + orderAmount);
            AnalysisLogger.write("Order Amount :" + orderAmount + "\n");

            // Wait for submittedForFinanceReview to be visible
            submittedForFinanceReview.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            String quoteOrderStatus = submittedForFinanceReview.first().textContent();
            logger.info("Order Status :" + quoteOrderStatus);
            AnalysisLogger.write("Order Status :" + quoteOrderStatus + "\n");
            String orderStartDate = orderStartDateLocator.textContent();
            logger.info("Order Start Date :" + orderStartDate);
            AnalysisLogger.write("Order Start Date :" + orderStartDate + "\n");
            String orderBillingFrequency = billingFrequency.first().textContent();
            logger.info("Billing Frequency :" + orderBillingFrequency);
            AnalysisLogger.write("Billing Frequency :" + orderBillingFrequency + "\n");

            // Wait for the article link to be visible
            page.locator("//article[@aria-label='Order Products']//a").first()
                    .waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            page.locator("//article[@aria-label='Order Products']//a").first().click();

            // Replaced page.waitForTimeout(3000); - Wait for the product codes to load
            productCodes.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            String lastElement = "";

            int productCount = productCodes.count();
            String[] products = {"AAC", "AABL", "AACLK", "AACS", "AATSR", "KNTR", "AA_PM", "AAC_IMP", "RPT_SRV", "MGSR-M", "CPS", "CONPS_IMP", "CPXINT", "SMPINT", "KNTR", "COND_PM", "PM", "SST", "SSTPBK", "SSTLK", "SCHLK", "SAT", "SSTTR", "SST_IMP", "AA_MAINT", "SSTMAINT"};
            String[] products2 = new String[26];
            int lastElementCount = 0;
            for (int i = 0; i < productCount; i++) {
                if (allUpperCase(productCodes.nth(i).textContent())) {
                    logger.info(productCodes.nth(i).textContent());
                    AnalysisLogger.write(i + " " + productCodes.nth(i).textContent() + "\n");
                    products2[i] = productCodes.nth(i).textContent();
                    if (i == productCount - 1) {
                        lastElement = productCodes.nth(i).textContent();
                        logger.info("lastElement =" + lastElement);
                        lastElementCount = i;
                    }
                }
            }
            // Wait for productCodeButtonLocator to be enabled
            productCodeButtonLocator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
            productCodeButtonLocator.click();

            // Replaced page.waitForTimeout(3000); - Wait for productCodes2 to be visible
            productCodes2.first().waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));

            for (int i = 0; i < productCodes2.count(); i++) {
                if (allUpperCase(productCodes2.nth(i).textContent())) {
                    if (productCodes2.nth(i).textContent().equalsIgnoreCase(lastElement)) {
                        break;
                    }
                    int no = 16 + i;
                    logger.info(productCodes2.nth(i).textContent());
                    AnalysisLogger.write(no + " " + productCodes2.nth(i).textContent() + "\n");
                    products2[lastElementCount + i + 1] = productCodes2.nth(i).textContent();
                }
            }
            for (int i = 0; i < products2.length; i++) {
                logger.info("products2 " + i + " " + products2[i]);
                if (products2[i] != null && products2[i].equalsIgnoreCase(null)) // Fixed null check
                {
                    break;
                }
            }
            Arrays.sort(products);
            Arrays.sort(products2);
            for (int i = 0; i < products.length; i++) {
                if (products2[i] != null && products[i].equalsIgnoreCase(products2[i])) { // Added null check for products2[i]
                    logger.info("Match " + i + " " + products[i]);
                    AnalysisLogger.write(products[i] + " in Quote :" + Constant.quoteId + "matches with " + products2[i] + " in Order :" + orderName + "\n");
                } else {
                    logger.info(products[i] + " did not match or products2["+i+"] is null");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }*/
    public String openContract(String opportunity) {
        opportunities = new OpportunitiesPage(page);
        opportunities.switchToOpportunityDetailsScreen(opportunity);
        logger.info("Opportunity screen opened");
        contactsLink.first().click();
        page.waitForTimeout(3000);
        //contractNumber.click();
        for(int i=0;i<30;i++)
        {
            page.waitForTimeout(5000);
            if(contractNumberPage.isVisible())
            {
                String contractNumber = contractNumberPage.textContent();
                logger.info("Contract Number :" + contractNumber+" is GENERATED AS EXPECTED");

                softly.assertThat(contractNumber).isNotBlank();
                return contractNumber;
            }
            page.reload();
        }
        String contractNumber = contractNumberPage.textContent();
        softly.assertThat(contractNumber).isNotBlank();
        return "Contract Number is not Visisble";
    }
    public void openLink(String link) {
        page.locator("//a[@title='" + link + "']").first().click();
    }
    /*public String createOrderUnderQuote5(String opportunity, String timeStamp2,String recordFileLocation){
        //This is under client sales discount Lv2 end to end test 2
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(Constant.Opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            //Locator opportunitySearchField = page.locator("//input[@name='Opportunity-search-input']");
            
            if(amountOpportunity.isVisible())
            {
                logger.info(amountOpportunity.textContent());
                fw.write(amountOpportunity.textContent()+"\n");
                if(amountOpportunity.textContent().equalsIgnoreCase(netAmount))
                {
                    logger.info("Net Amount and Opportunity amount matched");
                    fw.write("Net Amount and Opportunity amount matched"+"\n");
                }
            }
            
            if(subscriptionTermOpportunity.isVisible())
            {
                logger.info(subscriptionTermOpportunity.textContent());
                fw.write(subscriptionTermOpportunity.textContent()+"\n");
            }
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            Locator dialog=dialogAlohaFrameLocator;
            String futureDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            page.waitForTimeout(5000);
            opportunities.switchToOpportunityDetailsScreen(Constant.Opportunity);
            logger.info("Opportunity screen opened for Save  ");
            fw.write("Opportunity screen opened for Save  "+"\n");
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(5000);
            dialogAlohaFrameLocator.isVisible();
            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            dialog.locator(contractExecutionDateLocator).fill(futureDate);
            dialog.locator(stageLocator).first().click();
            dialog.selectFromDropDown(closedWonPendingStage).first().click();
            String opportunityMRR = dialog.locator(opportunityMRRLocator).textContent();
            logger.info("Opportunity MRR with USD :"+opportunityMRR);
            opportunityMRR = opportunityMRR.replaceAll("USD ","");
            logger.info("Opportunity MRR  :"+opportunityMRR);
            dialog.locator(opportunityEstimatedMRRLocator).fill(opportunityMRR);

            dialog.locator(saveEditButton).first().click();
            logger.info("Clicked Save on Opportunity with Successful save with Closed/Won Pending");
            fw.write("Clicked Save on Opportunity with Successful save with Closed/Won Pending"+"\n");
            page.waitForTimeout(3000);
            String stage = opportunityStage.first().textContent();
            logger.info(stage);
            fw.write(stage+"\n");
            String closeDate = opportunityCloseDate.first().textContent();
            logger.info(closeDate);
            fw.write(closeDate+"\n");

            String estProjectStartDate = opportunityEstStartDate.first().textContent();
            logger.info(estProjectStartDate);
            fw.write(estProjectStartDate+"\n");
            String estProjectEndDate = opportunityEstEndDate.first().textContent();
            logger.info(estProjectEndDate);
            fw.write(estProjectEndDate+"\n");
            LoginPage loginPage=new LoginPage(page);
            loginPage.loginToSalesForce(true,"Michael","");
            page.waitForTimeout(5000);
            //Check notifications
            readAllNotificationsAndClose();
            page.locator("//h3[@title='"+Constant.Opportunity+"']/a").first().click();
            page.waitForTimeout(3000);
            approveButtonLocator.first().click();
            page.waitForTimeout(3000);
            approvalTextBox.first().fill("Approving the Opportunity for testing");
            neutralButton.first().click();
            page.waitForTimeout(3000);
            String checkApproval = approveButtonOne.nth(0).textContent();
            logger.info(checkApproval);
            fw.write(checkApproval+"\n");
            String checkApproval2 = approveButtonOne.nth(1).textContent();
            logger.info(checkApproval2);
            fw.write(checkApproval2+"\n");
            //Login as Sales user again
            page.waitForTimeout(5000);
            opportunities.switchToOpportunityDetailsScreen(Constant.Opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            String quoteStatus = statusLocator.textContent();
            logger.info(quoteStatus);
            fw.write(quoteStatus+"\n");
            boolean orderedCheckbox = orderedCheckBoxLocator.isChecked();
            logger.info("Ordered Checkbox is checked :"+orderedCheckbox);
            fw.write("Ordered Checkbox is checked :"+orderedCheckbox+"\n");

            ordersLink.first().click();
            page.waitForTimeout(3000);

            orderNumberLocator.click();
            page.waitForTimeout(3000);
            String orderName = orderNameLocator.textContent();
            logger.info("Order Name :" + orderName);
            fw.write("Order Name :" + orderName+"\n");
            String orderType = orderTypeLocator.textContent();
            logger.info("Order Type :" + orderType);
            fw.write("Order Type :" + orderType+"\n");
            String orderAmount = orderAmountLocator.textContent();
            logger.info("Order Amount :"+ orderAmount);
            fw.write("Order Amount :"+ orderAmount+"\n");
            String quoteOrderStatus = submittedForFinanceReview.first().textContent();
            logger.info("Order Status :"+ quoteOrderStatus);
            fw.write("Order Status :"+ quoteOrderStatus+"\n");
            String orderStartDate = orderStartDateLocator.textContent();
            logger.info("Order Start Date :"+ orderStartDate);
            fw.write("Order Start Date :"+ orderStartDate+"\n");
            String orderBillingFrequency = billingFrequency.first().textContent();
            logger.info("Billing Frequency :"+ orderBillingFrequency);
            fw.write("Billing Frequency :"+ orderBillingFrequency+"\n");
            page.locator("//article[@aria-label='Order Products']//a").first().click();
            page.waitForTimeout(3000);
            String lastElement ="";

            int productCount = productCodes.count();
            String[] products={"EFAX_PROF_SVC_ONBOARD_TRN","EFAX_LOCAL_DID","EFAX_FAX_PAGES","EFAX_CORP_LOCAL_PKG","EFAX_CORP_CONTRACT","EFAX_ACTIVATION"};
            String[] products2= new String[6];
            int lastElementCount=0;
            for(int i=0;i<productCount;i++){
                if(allUpperCase(productCodes.nth(i).textContent()))
                {
                    logger.info(productCodes.nth(i).textContent());
                    fw.write(i+" "+productCodes.nth(i).textContent()+"\n");
                    products2[i]=productCodes.nth(i).textContent();
                    if(i==productCount-1){
                        lastElement = productCodes.nth(i).textContent();
                        logger.info("lastElement =" + lastElement);
                        lastElementCount=i;
                    }
                }
            }
            productCodeButtonLocator.click();
            page.waitForTimeout(3000);

            for(int i=0;i<productCodes2.count();i++){
                if(allUpperCase(productCodes2.nth(i).textContent()))
                {
                    if(productCodes2.nth(i).textContent().equalsIgnoreCase(lastElement))
                    {
                        break;
                    }
                    int no = 16+i;
                    logger.info(productCodes2.nth(i).textContent());
                    fw.write(no+" "+productCodes2.nth(i).textContent()+"\n");
                    products2[lastElementCount+i+1]=productCodes2.nth(i).textContent();
                }
            }
            for(int i=0;i<products2.length;i++)
            {
                logger.info("products2 "+i+" "+products2[i]);
            }
            Arrays.sort(products);
            Arrays.sort(products2);
            for(int i=0;i<products.length;i++)
            {
                if(products[i].equalsIgnoreCase(products2[i])){
                    logger.info("Match "+i+" "+products[i]);
                    fw.write(products[i]+" in Quote :"+Constant.quoteId+"matches with "+products2[i]+" in Order :"+orderName+"\n");
                }
                else{
                    logger.info(products[i]+" did not match");
                }
                if(i==products.length-1){
                    logger.info(" PRODUCT COUNT MATCHES AS EXPECTED");
                }
            }

            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String createOrderUnderQuote(String opportunity, String timeStamp2, String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            //Locator opportunitySearchField = page.locator("//input[@name='Opportunity-search-input']");
            
            if(amountOpportunity.isVisible())
            {
                logger.info(amountOpportunity.textContent());
                fw.write(amountOpportunity.textContent()+"\n");
                if(amountOpportunity.textContent().equalsIgnoreCase(netAmount))
                {
                    logger.info("Net Amount and Opportunity amount matched");
                    fw.write("Net Amount and Opportunity amount matched"+"\n");
                }
            }
            
            if(subscriptionTermOpportunity.isVisible())
            {
                logger.info(subscriptionTermOpportunity.textContent());
                fw.write(subscriptionTermOpportunity.textContent()+"\n");
            }
            
            if(mrrOpportunity.isVisible())
            {
                logger.info(mrrOpportunity.textContent());
                fw.write(mrrOpportunity.textContent()+"\n");
            }
            
            if(acvOpportunity.isVisible())
            {
                logger.info(acvOpportunity.textContent());
                fw.write(acvOpportunity.textContent()+"\n");
            }
            //page.locator("//button[@name='Edit']").click();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            Locator dialog=dialogAlohaFrameLocator;
            dialog.locator(stageLocator).first().click();
            dialog.selectFromDropDown(closedWonPendingStage).first().click();
            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            String futureDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            dialog.locator(contractExecutionDateLocator).fill(futureDate);
            opportunities.assertErrorMessages(new String[]{"We hit a snag.", "Review the errors on this page.", "Opportunity must have a Primary Quote and it must be \"Accepted\" or \"Approved\""});
            page.waitForTimeout(5000);
            dialog.locator("//button[@name='CancelEdit']").first().click();
            page.waitForTimeout(3000);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            String startDate = LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            page.locator("//input[@name='SBQQ__StartDate__c']").fill(startDate);
            page.waitForTimeout(3000);
            dialog.locator(saveEditButton).first().click();
            page.waitForTimeout(3000);
            submitForApprovalSubmit.click();
            page.waitForTimeout(3000);
            submitForApprovalBox.fill("Test record "+ Constant.quoteId +" Submitting for approval");
            page.waitForTimeout(3000);
            
            if(alertWarningButton.isVisible())
            {
                logger.info("Alert warning button visible");
                fw.write("Alert warning button visible"+"\n");
                alertWarningButton.click();
            }
            neutralButton.click();
            page.waitForTimeout(3000);
            String approvalStatus = statusLocator.first().textContent();
            logger.info(approvalStatus);
            fw.write(approvalStatus+"\n");
            if(page.locator("//records-record-layout-item[@field-label='eFax Corp Subscription Discount Total']//lightning-formatted-text").first().isVisible())
            {
                String discountAmount = page.locator("//records-record-layout-item[@field-label='eFax Corp Subscription Discount Total']//lightning-formatted-text").first().textContent();
                logger.info("Discount applied   "+ discountAmount);
                fw.write("Discount applied   "+ discountAmount+"\n");
            }


            LoginPage loginPage1=new LoginPage(page);
            loginPage1.loginToSalesForce(true,"sales","");
            page.waitForTimeout(5000);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened for Save  ");
            fw.write("Opportunity screen opened for Save  "+"\n");
            page.waitForTimeout(3000);
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(5000);
            dialogAlohaFrameLocator.isVisible();
            dialog.locator(stageLocator).first().click();

            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            *//*page.getByLabel("Contract Document Upload Completed? - Current Selection: No").click();
            page.locator(documentUploadLocatorYes).click();*//*

            dialog.locator(contractExecutionDateLocator).fill(futureDate);
            dialog.locator(stageLocator).first().click();
            dialog.selectFromDropDown(closedWonPendingStage).first().click();
            dialog.locator(saveEditButton).first().click();
            logger.info("Clicked Save on Opportunity with Successful save with Closed/Won Pending");
            fw.write("Clicked Save on Opportunity with Successful save with Closed/Won Pending"+"\n");
            page.waitForTimeout(3000);
            String stage = opportunityStage.first().textContent();
            logger.info(stage);
            fw.write(stage+"\n");
            String closeDate = opportunityCloseDate.first().textContent();
            logger.info(closeDate);
            fw.write(closeDate+"\n");
            String forecastCategory = opportunityForecaseCategory.first().textContent();
            logger.info(forecastCategory);
            fw.write(forecastCategory+"\n");
            String estProjectStartDate = opportunityEstStartDate.first().textContent();
            logger.info(estProjectStartDate);
            fw.write(estProjectStartDate+"\n");
            String estProjectEndDate = opportunityEstEndDate.first().textContent();
            logger.info(estProjectEndDate);
            fw.write(estProjectEndDate+"\n");
            LoginPage loginPage=new LoginPage(page);
            loginPage.loginToSalesForce(true,"Michael","");
            page.waitForTimeout(5000);
            readAllNotificationsAndClose();
            page.locator("//h3[@title='"+Constant.Opportunity+"']/a").first().click();
            page.waitForTimeout(3000);
            approveButtonLocator.first().click();
            page.waitForTimeout(3000);
            approvalTextBox.first().fill("Approving the Opportunity for testing");
            neutralButton.first().click();
            page.waitForTimeout(3000);
            String checkApproval = approveButtonOne.nth(0).textContent();
            logger.info(checkApproval);
            fw.write(checkApproval+"\n");
            String checkApproval2 = approveButtonOne.nth(1).textContent();
            logger.info(checkApproval2);
            fw.write(checkApproval2+"\n");
            //Login as Sales user again
            loginPage1.loginToSalesForce(true,"sales","");
            logger.info("Login as Sales user again");
            fw.write("Login as Sales user again"+"\n");
            page.waitForTimeout(5000);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            String quoteStatus = statusLocator.textContent();
            logger.info(quoteStatus);
            fw.write(quoteStatus+"\n");
            boolean orderedCheckbox = orderedCheckBoxLocator.isChecked();
            logger.info("Ordered Checkbox is checked :"+orderedCheckbox);
            fw.write("Ordered Checkbox is checked :"+orderedCheckbox+"\n");

            ordersLink.first().click();
            page.waitForTimeout(3000);
            *//*String orderNumber = orderNumberLocator.textContent();
            logger.info("Order number " + orderNumber);*//*
            orderNumberLocator.click();
            page.waitForTimeout(3000);
            String orderName = orderNameLocator.textContent();
            logger.info("Order Name :" + orderName);
            fw.write("Order Name :" + orderName+"\n");
            String orderType = orderTypeLocator.textContent();
            logger.info("Order Type :" + orderType);
            fw.write("Order Type :" + orderType+"\n");
            String orderAmount = orderAmountLocator.textContent();
            logger.info("Order Amount :"+ orderAmount);
            fw.write("Order Amount :"+ orderAmount+"\n");
            String quoteOrderStatus = submittedForFinanceReview.first().textContent();
            logger.info("Order Status :"+ quoteOrderStatus);
            fw.write("Order Status :"+ quoteOrderStatus+"\n");
            String orderStartDate = orderStartDateLocator.textContent();
            logger.info("Order Start Date :"+ orderStartDate);
            fw.write("Order Start Date :"+ orderStartDate+"\n");
            String orderBillingFrequency = billingFrequency.first().textContent();
            logger.info("Billing Frequency :"+ orderBillingFrequency);
            fw.write("Billing Frequency :"+ orderBillingFrequency+"\n");
            page.locator("//article[@aria-label='Order Products']//a").first().click();
            page.waitForTimeout(3000);
            String lastElement ="";

            int productCount = productCodes.count();
            String[] products={"AAC","AABL","AACLK","AACS","AATSR","KNTR","AA_PM","AAC_IMP","RPT_SRV","MGSR-M","CPS","CONPS_IMP","CPXINT","SMPINT","KNTR","COND_PM","PM","SST","SSTPBK","SSTLK","SCHLK","SAT","SSTTR","SST_IMP","AA_MAINT","SSTMAINT"};
            String[] products2= new String[26];
            int lastElementCount=0;
            for(int i=0;i<productCount;i++){
                if(allUpperCase(productCodes.nth(i).textContent()))
                {
                    logger.info(productCodes.nth(i).textContent());
                    fw.write(i+" "+productCodes.nth(i).textContent()+"\n");
                    products2[i]=productCodes.nth(i).textContent();
                    if(i==productCount-1){
                        lastElement = productCodes.nth(i).textContent();
                        logger.info("lastElement =" + lastElement);
                        lastElementCount=i;
                    }
                }
            }
            productCodeButtonLocator.click();
            page.waitForTimeout(3000);

            for(int i=0;i<productCodes2.count();i++){
                if(allUpperCase(productCodes2.nth(i).textContent()))
                {
                    if(productCodes2.nth(i).textContent().equalsIgnoreCase(lastElement))
                    {
                        break;
                    }
                    int no = 16+i;
                    logger.info(productCodes2.nth(i).textContent());
                    fw.write(no+" "+productCodes2.nth(i).textContent()+"\n");
                    products2[lastElementCount+i+1]=productCodes2.nth(i).textContent();
                }
            }
            for(int i=0;i<products2.length;i++)
            {
                logger.info("products2 "+i+" "+products2[i]);
            }
            Arrays.sort(products);
            Arrays.sort(products2);
            for(int i=0;i<products.length;i++)
            {
                if(products[i].equalsIgnoreCase(products2[i])){
                  logger.info("Match "+i+" "+products[i]);
                  fw.write(products[i]+" in Quote :"+Constant.quoteId+"matches with "+products2[i]+" in Order :"+orderName+"\n");
                  }
                else{
                    logger.info(products[i]+" did not match");
                }
            }
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }
    public String createOrderUnderQuote3(String opportunity, String timeStamp2,String recordFileLocation){
        String urlCurrent="";
        try{
            FileWriter fw =new FileWriter(recordFileLocation);
            urlCurrent = page.url();
            logger.info(urlCurrent);
            //Open the opportunity
            opportunities = new OpportunitiesPage(page);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            logger.info("Opportunity screen opened");
            fw.write("Opportunity screen opened"+"\n");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Edit").setExact(true)).first().click();
            page.waitForTimeout(3000);
            Locator dialog=dialogAlohaFrameLocator;
            dialog.locator(documentUploadCompletedLocator).click();
            dialog.locator(documentUploadLocatorYes).click();
            dialog.locator(stageLocator).first().click();
            dialog.selectFromDropDown(closedWonPendingStage).first().click();
            String futureDate = LocalDate.now().plusDays(30).format(DateTimeFormatter.ofPattern("M/d/yyyy"));
            dialog.locator(contractExecutionDateLocator).fill(futureDate);
            page.waitForTimeout(3000);
            dialog.locator(saveEditButton).first().click();
            logger.info("Clicked Save on Opportunity with Successful save with Closed/Won Pending");
            fw.write("Clicked Save on Opportunity with Successful save with Closed/Won Pending"+"\n");
            page.waitForTimeout(3000);
            String stage = opportunityStage.first().textContent();
            logger.info(stage);
            fw.write(stage+"\n");
            String closeDate = opportunityCloseDate.first().textContent();
            logger.info(closeDate);
            fw.write(closeDate+"\n");
            String estProjectStartDate = opportunityEstStartDate.first().textContent();
            logger.info(estProjectStartDate);
            fw.write(estProjectStartDate+"\n");
            String estProjectEndDate = opportunityEstEndDate.first().textContent();
            logger.info(estProjectEndDate);
            fw.write(estProjectEndDate+"\n");
            LoginPage loginPage=new LoginPage(page);
            loginPage.loginToSalesForce(true,"Michael","");
            page.waitForTimeout(5000);
            //Check notifications
            readAllNotificationsAndClose();
            page.locator("//h3[@title='"+opportunity+"']/a").first().click();
            page.waitForTimeout(5000);
            approveButtonLocator.first().click();
            page.waitForTimeout(3000);
            approvalTextBox.first().fill("Approving the Opportunity for testing");
            neutralButton.first().click();
            page.waitForTimeout(3000);
            String checkApproval = approveButtonOne.nth(0).textContent();
            logger.info(checkApproval);
            fw.write(checkApproval+"\n");
            String checkApproval2 = approveButtonOne.nth(1).textContent();
            logger.info(checkApproval2);
            fw.write(checkApproval2+"\n");
            //Login as Sales user again
            loginPage.loginToSalesForce(true,"sales","");
            logger.info("Login as Sales user again");
            fw.write("Login as Sales user again"+"\n");
            page.waitForTimeout(5000);
            opportunities.switchToOpportunityDetailsScreen(opportunity);
            page.waitForTimeout(5000);
            quotesLink.first().click();
            page.waitForTimeout(3000);
            Constant.quoteId=quoteDash.first().textContent();
            quoteNumberLocator.click();
            logger.info(Constant.quoteId + " quote page opened");
            fw.write(Constant.quoteId + " quote page opened"+"\n");
            page.waitForTimeout(3000);
            String quoteStatus = statusLocator.textContent();
            logger.info(quoteStatus);
            fw.write(quoteStatus+"\n");
            boolean orderedCheckbox = orderedCheckBoxLocator.isChecked();
            logger.info("Ordered Checkbox is checked :"+orderedCheckbox);
            fw.write("Ordered Checkbox is checked :"+orderedCheckbox+"\n");

            ordersLink.first().click();
            page.waitForTimeout(3000);
            String orderNumber = orderNumberLocator.textContent();
            logger.info("Order number " + orderNumber);
            orderNumberLocator.click();
            page.waitForTimeout(3000);
            String orderName = orderNameLocator.textContent();
            logger.info("Order Name :" + orderName);
            fw.write("Order Name :" + orderName+"\n");
            String orderType = orderTypeLocator.textContent();
            logger.info("Order Type :" + orderType);
            fw.write("Order Type :" + orderType+"\n");
            String orderAmount = orderAmountLocator.textContent();
            logger.info("Order Amount :"+ orderAmount);
            fw.write("Order Amount :"+ orderAmount+"\n");
            String quoteOrderStatus = submittedForFinanceReview.first().textContent();
            logger.info("Order Status :"+ quoteOrderStatus);
            fw.write("Order Status :"+ quoteOrderStatus+"\n");
            String orderStartDate = orderStartDateLocator.textContent();
            logger.info("Order Start Date :"+ orderStartDate);
            fw.write("Order Start Date :"+ orderStartDate+"\n");
            String orderBillingFrequency = billingFrequency.first().textContent();
            logger.info("Billing Frequency :"+ orderBillingFrequency);
            fw.write("Billing Frequency :"+ orderBillingFrequency+"\n");
            page.waitForTimeout(3000);
            fw.close();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            logger.info("Exception occured while creating order from Quote");
        }
        return Constant.quoteId;
    }*/
    public void readAllNotificationsAndClose(){
        page.locator("//button[@class='slds-button slds-button slds-button_icon slds-button_icon slds-button_icon-container slds-button_icon-small slds-global-actions__notifications slds-global-actions__item-action']").click();
        page.waitForTimeout(10000);
        Locator notifications = page.locator("//span[@class='notification-text uiOutputText']");
        for(int i=0;i<2;i++)
        {
            logger.info(notifications.nth(i).textContent());
        }
        page.locator("//button[@title='Close Notifications']").click();
    }
    static boolean allUpperCase(String input) {
        return input.equals(input.toUpperCase());
    }
    public List<String> regions(){
        List<String> menuContents=null;
        try {
            navigateToMenu("Regions");
            waitForLoader();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Select a List View: Regions")).click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("All")).click();
            waitForLoader();
            page.waitForTimeout(3000);
            //menuContents=page.locator("//table[@aria-label='All']//th/span/a").allTextContents();
            if(page.locator("//div[@class='slds-truncate").first().isVisible())
            {
                menuContents=page.locator("//div[@class='slds-truncate").allTextContents();
            }
            logger.info("Column contents in Region list views are "+menuContents);
        } catch (Exception e) {
            logger.error("No Data found in Regions list"+e);
        }
        return menuContents;
    }

    public List<String> practices(){
        List<String> menuContents=null;
        try {
            navigateToMenu("Practices");
            waitForLoader();
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Select a List View: Practices")).click();
            page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("All")).click();
            waitForLoader();
            page.waitForTimeout(3000);
            //menuContents=page.locator("//table[@aria-label='All']//th/span/a").allTextContents();
            if(page.locator("//div[@class='slds-truncate").first().isVisible())
            {
                menuContents=page.locator("//div[@class='slds-truncate").allTextContents();
            }
            logger.info("Column contents in Practices list views are "+menuContents);
        } catch (Exception e) {
            logger.error("No Data found in Practice list");
        }
        return menuContents;
    }
    public boolean checkAlertDialog()
    {
        if(page.locator("//div[@role='alert']").isVisible())
        {
            logger.info("After add Quote line items");
            return true;
        }
        else{
            return false;
        }
    }
}