package com.consensus.qaauto;

import org.testng.TestNG;
import com.consensus.qaauto.common.playwright.TestExecutionListener;
import com.consensus.qaauto.common.playwright.TestNGReportListener;
import com.consensus.qaauto.common.playwright.Utilities;

import org.apache.commons.cli.*;
//For commit
public class TestRunner extends Utilities {
    public static void main(String args[]) {
        logger.info("Invoked from TestRunner.");
    	try {
            // Create Options
            Options options = new Options();
            options.addOption("environment", true, "Environment");
            options.addOption("skipTests", true, "Skip tests flag");
            options.addOption("testGroups", true, "Test groups");
            options.addOption("testrailBaseUrl", true, "TestRail base URL");
            options.addOption("uploadResults", true, "Upload results flag");
            options.addOption("testRunId", true, "Test Run ID");
            options.addOption("suiteXmlFile", true, "Suite XML file");
            options.addOption("webBrowser", true, "Web browser");
            options.addOption("headless", true, "Headless mode flag");
            options.addOption("timeout", true, "Timeout");
            options.addOption("secretServerUrl", true, "Secret Server URL");
            options.addOption("secretserverSecrets", true, "Secretserver Secret IDs");
            options.addOption("testRailUrl", true, "TestRail URL");

            // Parse command line arguments
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            // Default parameter values
            String environment = cmd.getOptionValue("environment", "qa");
            String skipTests = cmd.getOptionValue("skipTests", "false");
            String testGroups = cmd.getOptionValue("testGroups", "");
            String testrailBaseUrl = cmd.getOptionValue("testrailBaseUrl", "https://testrail.j2noc.com");
            String uploadResults = cmd.getOptionValue("uploadResults", "false");
            String testRunId = cmd.getOptionValue("testRunId", "R00000");
            String suiteXmlFile = cmd.getOptionValue("suiteXmlFile", "end2end.xml");
            String webBrowser = cmd.getOptionValue("webBrowser", "chromium");
            String headless = cmd.getOptionValue("headless", "true");
            String timeout = cmd.getOptionValue("timeout", "120000");
            String secretServerUrl = cmd.getOptionValue("secretServerUrl", "https://secretserver.j2noc.com/SecretServer");
            String secretserverSecrets = cmd.getOptionValue("secretserverSecrets", "4479;3254;14944");
            String testRailUrl = cmd.getOptionValue("testRailUrl", "https://testrail.j2noc.com");

            // Set system properties
            System.setProperty("environment", environment);
            System.setProperty("skipTests", skipTests);
            System.setProperty("testGroups", testGroups);
            System.setProperty("testrailBaseUrl", testrailBaseUrl);
            System.setProperty("uploadResults", uploadResults);
            System.setProperty("testRunId", testRunId);
            System.setProperty("suiteXmlFiles", suiteXmlFile);
            System.setProperty("webBrowser", webBrowser);
            System.setProperty("headless", headless);
            System.setProperty("timeout", timeout);
            System.setProperty("secretServerUrl", secretServerUrl);
            System.setProperty("secretserverSecrets", secretserverSecrets);
            System.setProperty("testRailUrl", testRailUrl);

            // Create an instance of TestNG
            TestNG testng = new TestNG();

            testng.setTestJar(TestRunner.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            testng.setXmlPathInJar(suiteXmlFile);
            testng.initializeSuitesAndJarFile();

            // Add listeners and other configurations
            testng.addListener(new TestExecutionListener());
            testng.addListener(new TestNGReportListener());
            testng.setVerbose(0);
            testng.setOutputDirectory("test-output/report");

            // Run the tests
            testng.run();
            System.exit(0);
        } catch (ParseException e) {
            System.err.println("Error parsing command line arguments: " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
