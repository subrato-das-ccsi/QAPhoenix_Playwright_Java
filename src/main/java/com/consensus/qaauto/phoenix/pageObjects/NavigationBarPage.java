package com.consensus.qaauto.phoenix.pageObjects;

import com.consensus.qaauto.common.playwright.BasePage;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.assertj.core.api.SoftAssertions;
import testResources.Constant;
import testResources.menuOptions;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NavigationBarPage extends BasePage {
	public NavigationBarPage(Page page) {
		super(page);
	page.setViewportSize(1247,695);
	}
	Locator searchBox=page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true));
	Locator searchBoxDialog = page.locator("[role='dialog'] input[type='search']");
	Locator lightningButtonOnPage = page.locator("//a[@class='switch-to-lightning']");
	Locator tabHome = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
	Locator tabAccounts = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Accounts"));
	Locator tabContacts = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contacts"));
	Locator tabLeads = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leads"));
	Locator tabOpportunities = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities"));
	Locator tabQuotes = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Quotes"));
	Locator tabOrders = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Orders"));
	Locator tabContracts = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contracts"));
	Locator btnViewProfile = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View profile"));
	Locator searchLabel = page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true));
	//Locator locatorSearch = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Search").setExact(true))
			//page.getByPlaceholder("Search...");
			//page.locator("[role='dialog'] input[type='search']").first();
	private Locator userProfile=page.locator("//div[contains(@class,'profileTrigger')]/span");

	private Locator profileCardName=page.locator(".profile-card-name");

	SoftAssertions softly = new SoftAssertions();

	public void switchToHomeTab() {
		tabHome.first().click();
		page.waitForLoadState();
	}



	public void switchToLeadsTab() {
		try {
			navigateToMenu("Leads");
			page.waitForLoadState();
		} catch (Exception e) {
			 logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
	}



	public SetUpPage switchToSetup() {
		try {
			page.locator("a").filter(new Locator.FilterOptions().setHasText("Setup")).click();
			Page page1 = page.waitForPopup(() -> {
				page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Setup Opens in a new tab Setup for current app")).click();
			});
		} catch (Exception e) {
			logger.error("Setup Page not opened");
		}
		return new SetUpPage(page);
	}

	public void switchToOrdersTab() {
		try {
			tabOrders.first().click();
			page.waitForLoadState();
		} catch (Exception e) {
			 logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
	}

	public void switchToContractsTab() {
		try {
			tabContracts.first().click();
			page.waitForLoadState();
		} catch (Exception e) {
			 logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
	}

	/*public void globalSearch(String searchString) {
		waitForLoader();
		page.waitForTimeout(2500);
		searchBox.click();
		page.waitForTimeout(500);
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		searchBoxDialog.click();
		//locator.type(searchString, new Locator.TypeOptions().setDelay(10));
		searchBoxDialog.press("Enter");
		page.waitForTimeout(500);
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		waitForLoader();
	}*/

	public void globalSearch(String searchString) {
		try {
			waitForLoader();
			page.waitForLoadState(LoadState.LOAD);
			searchLabel.click();
			page.keyboard().insertText(searchString);
			page.keyboard().press("Enter");
			waitForLoader();
			page.waitForLoadState(LoadState.DOMCONTENTLOADED);
			waitForLoader();
		} catch (Exception e) {
			 logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
	}

	public void waitForLoader() {
		page.waitForTimeout(1000);
		page.frames().forEach(i -> i.childFrames().forEach(j -> j.childFrames().forEach(k -> k.waitForLoadState(LoadState.DOMCONTENTLOADED))));
		try {
			page.frames().forEach(frame -> frame.locator("[class*='spinner']").all().forEach(locator -> {
				try {
					locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
				} catch (Exception ex) {

				}
			}));
			page.locator("[class*='spinner']").all().forEach(locator -> {
				try {
					locator.waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.HIDDEN));
				} catch (Exception ex) {

				}
			});
		} catch (Exception e) {

		}
	}

	public void navigateToMenu(menuOptions menuOptions){
		try {
			logger.info("Switching to Menu "+menuOptions+" through App Launcher");
			waitForLoader();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("App Launcher")).click();
			waitForLoader();
			page.getByPlaceholder("Search apps and items...").click();
			page.getByPlaceholder("Search apps and items...").fill(menuOptions.toString());
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(menuOptions.toString())).click();
		} catch (PlaywrightException e) {
			logger.info("Exception occured while Navigating to "+menuOptions +" via AppLauncher");
		}

	}

	public void navigateToMenu(String menuOptions){
		// String menu=menuName.toLowerCase();

		try {
			if(lightningButtonOnPage.isVisible()){
				lightningButtonOnPage.first().click();
				page.waitForTimeout(3000);
			}
			logger.info("Switching to Menu "+menuOptions+" through App Launcher");
			page.waitForSelector("//div[@class='slds-icon-waffle']");
			page.waitForTimeout(5000);
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("App Launcher")).click();
			page.waitForTimeout(3000);
			page.getByPlaceholder("Search apps and items...").click();
			page.getByPlaceholder("Search apps and items...").fill(menuOptions);
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(menuOptions)).first().click();
		} catch (PlaywrightException e) {
			logger.info("Exception occured while Navigating to "+menuOptions +" via AppLauncher");
		}

	}

	public  void searchList(String searchContent,String clickLink){
		try {
			//page.reload().finished();
			waitForLoader();
			if (!searchContent.isEmpty()&&!clickLink.isEmpty()) {
				filterList(searchContent);
				page.waitForTimeout(7000);
				page.locator("//a[contains(text(),'" + clickLink + "')]").first().click();
			}
				else if (!searchContent.isEmpty()){
				filterList(searchContent);
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.locator("//th//span//a").first().click();
			}
			else if (searchContent.isEmpty()&&clickLink.isEmpty()){
				page.waitForLoadState(LoadState.DOMCONTENTLOADED);
				page.locator("//th//span//a").first().click();
			}
		} catch (Exception e) {
			logger.info("No record available for the "+searchContent+"or "+clickLink);
			 logger.info("Error while executing "+Thread.currentThread().getStackTrace()[1].getMethodName()+" method in "+Thread.currentThread().getStackTrace()[1].getClassName()+" class "+e);
		}
	}

	public void filterList(String searchContent){
		page.getByPlaceholder("Search this list...").first().click();
		page.getByPlaceholder("Search this list...").first().fill(searchContent);
		page.getByPlaceholder("Search this list...").first().press("Enter");
		page.waitForTimeout(3000);
		if (page.locator("(//span[text()='No items to display.'])[1]").isVisible()){
			logger.info("Expected record not available in actual List view attempting to global search");
			globalSearch(searchContent);
		}
	}

	public void closeError(){
		page.locator("button[title='Close error dialog']").click();
	}

	public  void openAnylink() {
		int random=faker.number().numberBetween(0,10);
		try {
			waitForLoader();
			page.waitForTimeout(1000);
			page.locator("(//th//span//a)["+random+"]").click();

		} catch (Exception e) {
			logger.info("Unable to open record No recently viewed records availale for the user");
		}
	}
	public  void openFirstlink() {
	try {
		page.waitForTimeout(1000);
		page.locator("//th//span//a").first().click();

	} catch (Exception e) {
		logger.info("Unable to open record No recently viewed records availale for the user");
	}
}
	public  void openOrdersFirstlink(){
		try {
			page.waitForTimeout(3000);
			page.waitForSelector("(//a[starts-with(@title,'00')])[1]").click();
		} catch (Exception e) {
			logger.info("Unable to open record No recently viewed records availale for the user");
		}

}


	//used to open first link from objects list views like opportunity,Contacts,Account lists etc
	public String profileName(){
		String cardName= "";
		waitForLoader();
		page.waitForTimeout(2000);
		userProfile.click();
		page.waitForTimeout(3000);
		cardName = profileCardName.first().textContent();
		logger.info("Logged user is "+cardName);
		return Constant.loggedInProfile=cardName;
	}
	public LoginPage logout2(){
		waitForLoader();
			if(page.locator("//span[@class='uiImage']").first().isVisible()){
				page.locator("//span[@class='uiImage']").first().click();
				page.locator("//a[@class='profile-link-label logout uiOutputURL']").click();
			}
		if(page.locator("//span[@id='userNavLabel']").first().isVisible()){
			page.locator("//span[@id='userNavLabel']").first().click();
			page.locator("//a[@title='Logout']").click();
		}

			logger.info("Logging out from the Application");
			page.waitForTimeout(3000);
			if(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In to Sandbox")).isVisible())
			{
				logger.info("Login screen is visible");
			}
			else
			{
				if(page.locator("//span[@id='userNavLabel']").first().isVisible()){
					page.locator("//span[@id='userNavLabel']").first().click();
					page.locator("//a[@title='Logout']").click();
				}
			}
			page.waitForTimeout(3000);
			//assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In to Sandbox"))).isVisible();
		return new LoginPage(page);
	}
	public LoginPage logout(){
		waitForLoader();
		if(page.locator(".action-link").isVisible()&&!profileName().contains("Kumar")){
			page.locator(".action-link").click();
			page.waitForTimeout(5000);
			if(page.locator("//span[@id='userNavLabel']").first().isVisible()){
				page.locator("//span[@id='userNavLabel']").first().click();
				page.locator("//a[@title='Logout']").click();
			}
			logger.info("Logging out from the Application");
			page.waitForTimeout(5000);
			assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Log In to Sandbox"))).isVisible();
		}
		return new LoginPage(page);
	}

	public void switchToCreateProjectScreen(){

	}


	public  void selectOptionfromDropdowns(List<ElementHandle> list, String value){
		for (ElementHandle li:list){
			if (li.textContent().contains(value)){{
				li.click();
				break;
			}

			}
		}

	}

}