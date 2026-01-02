package com.consensus.qaauto.phoenix.tests;

import com.consensus.qaauto.common.playwright.TestRail;
import com.consensus.qaauto.phoenix.pageObjects.HomePage;
import com.consensus.qaauto.phoenix.pageObjects.LoginPage;
import com.consensus.qaauto.phoenix.tests.BaseFactory;
import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LogoutTest extends BaseFactory {
    public HomePage preparePageWithLoggedInUser(Page page) {
        LoginPage loginPage=new LoginPage(page);
        loginPage.loginToSalesForce(true,"sales","");
        //loginPage.loginToSalesForce(true,"Brett","");
        return new  HomePage(page);
    }
    public String uploadToGDrive(HomePage homePage) {
        try{
            Page page = getPage();
            page.navigate("https://drive.google.com/drive/home");
            page.waitForTimeout(3000);
            page.locator("//button[@guidedhelpid='new_menu_button']").click();

        }
        catch (Exception e){
            e.printStackTrace();
            return "Failure";
        }
        return "Success";
    }

    @TestRail(id="C98954")
    //@TestRail(id="C84224,C73858, C84224,C80862,C84593,C76099,C90092,C90049,C88071,C69097,C70938,C7336,C88070,C73360,C82639,C83246,C84231,C84576,C76352,C73502,C74731,C78022,C78029,C73478")

    @Test(enabled = true, groups = { "regression" }, description = "Logout test for Automation")
    public void EndToEndValidations() throws IOException {

        Page page = getPage();
        HomePage homePage =preparePageWithLoggedInUser(page);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String className = this.getClass().getSimpleName();
    }
}
