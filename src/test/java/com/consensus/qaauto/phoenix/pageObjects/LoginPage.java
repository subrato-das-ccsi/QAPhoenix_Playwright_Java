package com.consensus.qaauto.phoenix.pageObjects;

import com.consensus.qaauto.common.playwright.Config;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import testResources.Constant;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage extends NavigationBarPage {


	public LoginPage(Page page) {
		super(page);
	}


	private Locator username = page.locator("#username");
	private Locator password = page.locator("#password");
	private Locator login = page.locator("#Login");
	private Locator inputNameLogin = page.locator("input[name='login']");

	private Locator userProfile=page.locator("//div[contains(@class,'profileTrigger')]/span");

	private Locator profileCardName=page.locator(".profile-card-name");

	//switchuser - defines the user need to switched from Admin to other type
	//UserType - what type of user i.e Sales,PSA etc
	//browserType - default will be chrome
	public Page loginToSalesForce(boolean switchuser,String userType,String browserType){
		setUserName(userType);
		if (page.url().contains("blank")) {
			page.navigate(Config.getProperties().getProperty("CCSISalesForceURL")).finished();
		}
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		// When creating a new browser instance, we check if there is a saved login
		// (test-output/state.json) session then we load it automatically, hence the
		// user lands directly on the
		// homepage of the application without the need to login with every new browser creation
		// creation or test method
		Login();
		if (switchuser&&!userType.contains("admin")){
			switchUserParentWindow(Config.getProperties().getProperty(userType));
			if (browserType.isEmpty()) {
				//Login();
				if(inputNameLogin.first().isVisible())
				{
					inputNameLogin.first().click();
					waitForLoader();
					logger.info(page.getByText("Logged in as").textContent());
				}
			} else {
				FrameLocator frameLocator = page.frameLocator(".oneAlohaPage iframe");
				Locator frameLocatorLogin = frameLocator.locator("input[name='login']");
				if(frameLocatorLogin.first().isVisible()){
					frameLocatorLogin.first().click();
				}
				Locator frameInputLocatorLogin = frameLocator.locator("//input[@name='login']");
				if(frameInputLocatorLogin.first().isVisible()){
					frameInputLocatorLogin.first().click();
				}
				page.reload().finished();
			}
		}
		return page;
	}

	public Page switchUserMode(String url){
		Page p=null;
		try {
			p=page.context().browser().newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("test-output/state.json"))).newPage();
			page=p;
			page.navigate(url).finished();
		} catch (Exception e) {
			logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
		return page;
	}

	public Page switchUserParentWindow(String url){
		try {
			page.context().browser().newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("test-output/state.json")));
			page.navigate(url).finished();
		} catch (Exception e) {
			logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
		return page;
	}


	//while User switch required this method will check the existing user is the expected user or not
	//if yes then switching wont happen else the user will be switched or logged out
	public void setUserName(String userType){

		if (userType.equalsIgnoreCase("admin")){
			Constant.username="Kumar";
		}
		if (userType.equalsIgnoreCase("sales")){
			Constant.username="Ted";
		}
		if (userType.equalsIgnoreCase("psa")){
			Constant.username="Jamie";
		}
		if (userType.equalsIgnoreCase("Data governance")){
			Constant.username="Michael";
		}
		logger.info("UserName is "+Constant.username);
	}



	public void switchToSalesApp(){
		//String PageName=page.locator("//span[contains(@class,'appName')]").textContent();
        //String PageName=page.locator("//span[contains(@class,'slds-truncate')]").first().textContent();
		//if (!PageName.equalsIgnoreCase("Sales")) {
			waitForLoader();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("App Launcher")).click();
			page.getByPlaceholder("Search apps and items...").fill("Sales");
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Sales").setExact(true)).click();
			page.waitForLoadState(LoadState.DOMCONTENTLOADED);
			page.waitForTimeout(3000);
		///}
	}


	public void Login(){
		logout2();
		if (username.isVisible()) {
			logger.info("Log in to the application with given UserName and Password");
			username.fill(Config.getLoginIdFor("SalesForceCreds"));
			password.fill(Config.getPasswordFor("SalesForceCreds"));
			login.click();
			page.context().storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("test-output/state.json")));
			page.waitForLoadState();
			switchToSalesApp();
		}
		else {
			logger.info("User is already authenticated and logged in to the application");
		}
	}

	public Page createAnotherUserLoginContext(String url) {
		Page p = null;
		try {
			 p=page.context().browser().newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("test-output/state.json"))).newPage();
			p.navigate(url).finished();
			waitForLoader();
			p.locator("input[name='login']").first().click();
			waitForLoader();
			logger.info(p.getByText("Logged in as").textContent());
			return p;
		} catch (Exception e) {
			 logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
		return p;
	}
















	

}
