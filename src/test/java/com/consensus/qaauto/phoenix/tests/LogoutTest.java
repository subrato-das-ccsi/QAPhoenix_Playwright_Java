package com.consensus.qaauto.phoenix.tests;

import com.consensus.qaauto.common.playwright.TestRail;
import com.consensus.qaauto.phoenix.pageObjects.HomePage;
import com.consensus.qaauto.phoenix.pageObjects.LoginPage;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.io.IOException;

public class LogoutTest extends BaseFactory {
    public HomePage preparePageWithLoggedInUser(Page page) {
        LoginPage loginPage=new LoginPage(page);
        loginPage.loginToApplication(true,"sales","");
        return new HomePage(page);
    }

    @TestRail(id="C98954")
    @Test(enabled = true, groups = { "regression" }, description = "Logout test for Automation")
    public void EndToEndValidations() throws IOException {
        Page page = getPage();
        HomePage homePage =preparePageWithLoggedInUser(page);
    }
}
