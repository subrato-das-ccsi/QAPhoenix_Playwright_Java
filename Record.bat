mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen --load-storage=test-output\state.json ccsi-sf--qa.sandbox.my.salesforce.com"
pause "Press any key to exit"
