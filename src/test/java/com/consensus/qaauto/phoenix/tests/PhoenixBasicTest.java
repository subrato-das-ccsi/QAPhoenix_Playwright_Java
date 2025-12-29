package com.consensus.qaauto.phoenix.tests;

import com.consensus.qaauto.common.playwright.BaseTest;
import com.consensus.qaauto.common.playwright.TestRail;
import com.consensus.qaauto.utility.AnalysisLogger;
import org.testng.annotations.Test;
import testResources.Constant;

import java.io.IOException;

public class PhoenixBasicTest extends BaseTest {

    //@TestRail(id = "C68886,C71153,C71150,C81488,C81489,C81490,C81928,C81929,C81930,C68890,C80862,C68965")
    @TestRail(id = "C68886,C71153,C71150,C81490,C81930,C68890,C80862,C68965")
    @Test(enabled = true,groups = {"Regression"},description = "Account>New>Validate Customer Account Creation and Mandatory Error validations")
    public void accountCreationValidations() throws IOException {
        try {
            AccountsPage accountsPage=new AccountsPage(page);
            accountsPage.preparePageWithLoggedInUser();
            Constant.accountName = accountsPage.validateAccountCreation();
            AnalysisLogger.write("Account Name: " + Constant.accountName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @TestRail(id = "C84632")
    @Test(enabled = true,groups = {"Regression"},description = "Account>New>Validate Customer Account Creation and Mandatory Error validations")
    public void accountFormerCustomerActiveErrorValidation() throws IOException {
        try {
            AccountsPage accountsPage=new AccountsPage(page);
            accountsPage.preparePageWithLoggedInUser();
            Constant.accountName = accountsPage.createAccountWithGivenConditions("Former Customer","Draft","Former Customer","Active");;
            AnalysisLogger.write("Account Name: " + Constant.accountName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}