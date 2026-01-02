package com.consensus.qaauto.phoenix.tests;

import com.consensus.qaauto.common.playwright.TestRail;
import com.consensus.qaauto.phoenix.pageObjects.HomePage;
import com.consensus.qaauto.phoenix.pageObjects.LoginPage;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogoutTest extends BaseFactory {
    public HomePage preparePageWithLoggedInUser(Page page) {
        LoginPage loginPage=new LoginPage(page);
        loginPage.loginToApplication(true,"sales","chromium");
        return new  HomePage(page);
    }

    @TestRail(id="C00000")
    @Test(enabled = true, groups = { "regression" }, description = "Logout test for Automation")
    public void EndToEndValidations() throws IOException {

        Page page = getPage();
        HomePage homePage =preparePageWithLoggedInUser(page);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String className = this.getClass().getSimpleName();
    }
}
