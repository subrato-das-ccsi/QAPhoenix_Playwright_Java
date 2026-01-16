package com.consensus.qaauto.phoenix.pageObjects;

import com.consensus.qaauto.common.playwright.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class NavigationBar extends BasePage {
	public NavigationBar(Page page) {
		super(page);
	}

	Locator tabHome = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
	Locator tabAccounts = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Accounts"));
	Locator tabContacts = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contacts"));
	Locator tabLeads = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Leads"));
	Locator tabOpportunities = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Opportunities"));
	Locator tabQuotes = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Quotes"));
	Locator tabOrders = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Orders"));
	Locator tabContracts = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contracts"));
	Locator btnViewProfile = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View profile"));

	public void switchToHomeTab() {
		tabHome.first().click();
		page.waitForLoadState();
	}

	public void switchToAccountsTab() {
		tabAccounts.first().click();
		page.waitForLoadState();
	}

	public void switchToContactsTab() {
		tabContacts.first().click();
		page.waitForLoadState();
	}

	public void switchToLeadsTab() {
		tabLeads.first().click();
		page.waitForLoadState();
	}

	public void switchToOpportunitiesTab() {
		tabOpportunities.first().click();
		page.waitForLoadState();
	}

	public void switchToQuotesTab() {
		tabQuotes.first().click();
		page.waitForLoadState();
	}

	public void switchToOrdersTab() {
		tabOrders.first().click();
		page.waitForLoadState();
	}

	public void switchToContractsTab() {
		tabContracts.first().click();
		page.waitForLoadState();
	}

	public void globalSearch(String searchString) {
		waitForLoader();
		page.waitForTimeout(2500);
		page.getByLabel("Search", new Page.GetByLabelOptions().setExact(true)).click();
		Locator locator = page.locator("[role='dialog'] input[type='search']");
		page.waitForTimeout(500);
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		locator.click();
		//locator.type(searchString, new Locator.TypeOptions().setDelay(10));
		locator.press("Enter");
		page.waitForTimeout(500);
		page.waitForLoadState(LoadState.DOMCONTENTLOADED);
		waitForLoader();
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

}