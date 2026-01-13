FROM eclipse-temurin:17-jre

WORKDIR /app

COPY target/PhoenixPlaywrightJava-1.0.0.jar app.jar
COPY target/PhoenixPlaywrightJava-1.0.0-tests.jar app-tests.jar

ENTRYPOINT ["java", "-cp", "app-tests.jar:app.jar:libs/*"]

CMD ["-Dwebbrowser=chromium", \
     "-Dsonar.skip=true", \
     "-Denv=qa", \
     "-Dheadless=true", \
     "-Dallure.results.directory=target/allure-results", \
     "-DxmlTestFile=PhoenixTests.xml", \
     "com.consensus.qaauto.TestRunner"]