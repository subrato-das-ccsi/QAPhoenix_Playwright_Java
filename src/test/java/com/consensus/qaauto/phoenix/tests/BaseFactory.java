package com.consensus.qaauto.phoenix.tests;

import com.consensus.qaauto.common.playwright.BaseTest;
import com.consensus.qaauto.common.playwright.TLDriverFactory;
import com.consensus.qaauto.utility.AnalysisLogger;
import com.microsoft.playwright.Page;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public abstract class BaseFactory extends BaseTest {
    protected String browser = System.getProperty("webBrowser","chromium").toLowerCase().replace("chrome", "chromium");
    public String productName ="SalesForce";


@BeforeMethod(alwaysRun = true)
    public void startProjectSpecificLog(Method method) {
        // 'method.getName()' gives you the @Test method name (e.g., "EndToEndValidations")
        AnalysisLogger.startTestLog(method.getName());
        logger.info("Analysis logger started...");
    }

    // This new @AfterMethod will run *before* the @AfterMethod in BaseTest
    @AfterMethod(alwaysRun = true)
    public void stopProjectSpecificLog(ITestResult result) {
        // You can even log the test status to your file
        if (!result.isSuccess()) {
            Throwable t=result.getThrowable();
            String errorMessage=(t!=null)?t.getMessage():"Test failed or was skipped.";

            AnalysisLogger.write("TEST STATUS: FAILED/SKIPPED");
            AnalysisLogger.write("ERROR: " + errorMessage);
        }
        else
        {
            AnalysisLogger.write("TEST STATUS: PASSED");
        }
        AnalysisLogger.endTestLog();
         logger.info("Analysis logger closed...");
    }


//    public void preparePage_New() {
//      First approach
    //TLDriverFactory.setTLDriver();
//        BrowserContext browserContext = TLDriverFactory.getTLDriver();
//        page = browserContext.newPage();
//        page.set(TLDriverFactory.getTLDriver().newPage());
//        Second approach
//        TLDriverFactory.setTLDriver();
//        page = TLDriverFactory.getTLDriver().newPage();
//        TLDriverFactory.setPage(page);
//        page.setDefaultNavigationTimeout(30000);
//    }

    protected Page getPage() {
        return TLDriverFactory.getPage();
    }


//    @AfterClass(alwaysRun = true)
//    public static void teardown() throws IOException {
//        TLDriverFactory.teardown();
////        TLDriverFactory.remove();
//    }

}