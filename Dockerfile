FROM mcr.microsoft.com/playwright/java:v1.49.0-noble

WORKDIR /app

# 1. Copy settings.xml FIRST (needed for auth to download plugins)
COPY settings.xml .

# 2. Copy the parent POM file
COPY consensus-platform-parent-1.0.16.pom /app/parent-pom.xml

RUN mvn -s settings.xml install:install-file \
    -Dfile=/app/parent-pom.xml \
    -DgroupId=com.consensus \
    -DartifactId=consensus-platform-parent \
    -Dversion=1.0.16 \
    -Dpackaging=pom

COPY common-playwright-1.6.3.jar /app/common-playwright.jar
COPY common-playwright-1.6.3.pom /app/common-playwright.pom

# 3. Install the Parent POM
# CRITICAL: We do this BEFORE copying the main pom.xml.
# This prevents Maven from trying to read your project and failing.
RUN mvn -s settings.xml install:install-file \
    -Dfile=/app/common-playwright.jar \
    -DpomFile=/app/common-playwright.pom \
    -DgroupId=com.consensus.qaauto \
    -DartifactId=common-playwright \
    -Dversion=1.6.3 \
    -Dpackaging=jar

# 4. NOW copy the project POM
COPY pom.xml .

# 5. Download dependencies (Now it works because the parent is installed!)
RUN mvn -s settings.xml dependency:go-offline

# 6. Copy the rest of the source code
COPY . .

# 7. Compile
RUN mvn -s settings.xml compile

# 8. Run Tests
CMD ["mvn", "-s", "settings.xml", "test", \
     "-ntp", "-e", \
     "-P", "qa", \
     "-D", "uploadResults=false", \
     "-D", "skipTests=false", \
     "-D", "testRunId=11111", \
     "-D", "webbrowser=chromium", \
     "-D", "headless=true", \
     "-D", "suiteXmlFiles=PhoenixTests.xml", \
     "-D", "oracle.jdbc.timezoneAsRegion=false"]