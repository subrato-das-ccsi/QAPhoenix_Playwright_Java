FROM mcr.microsoft.com/playwright/java:focal
RUN rm -rf /tmp/pw-java && \
	mkdir /tmp/pw-java
WORKDIR  /tmp/pw-java
COPY . .
RUN sh -c "mvn clean compile test -ntp -e -P qa -D uploadResults=false -D skipTests=false -D testRunId=11111 -D webbrowser=chrome -D headless=true -D suiteXmlFiles=secure.fax.xml -D oracle.jdbc.timezoneAsRegion=false"