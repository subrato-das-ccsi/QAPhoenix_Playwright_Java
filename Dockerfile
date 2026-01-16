FROM mcr.microsoft.com/playwright/java:v1.51.0-jammy

WORKDIR /app

#COPY target/libs /app/libs
COPY target/PhoenixPlaywrightJava-1.0.0-fat-tests.jar app.jar
COPY target/PhoenixPlaywrightJava-1.0.0-tests.jar app-tests.jar
COPY credentials.qa .

#EXPOSE 5005

# 1. Change ENTRYPOINT to just "java"
ENTRYPOINT ["java"]

# 2. Put ALL arguments (including -jar) in CMD
#    Notice that "-jar app.jar" is now at the END of the list.
CMD ["-DcliBrowser=firefox", \
     "-DwebBrowser=firefox", \
     "-Dsonar.skip=true", \
     "-Denvironment=qa", \
     "-Dheadless=true", \
     "-Dallure.results.directory=target/allure-results", \
     "-DxmlTestFile=PhoenixTests.xml", \
     "-jar", "app.jar"]

