#######################################################################################################
#########             Run with mvn commandline                                               ##########
#######################################################################################################

How to run this project from command line in jenkins: (in jenkins with parameters)
mvn clean compile test -P ${Environment} -D uploadResults=false -D skipTests=false -D testRunId=${TestRunId}  -D headless=true -D enableVideoRecording=true -D recordFailedOnly=true -D suiteXmlFiles=secure.efax.us.xml

How to run this project from command line in localbox: (from local commandline)
mvn clean compile test -U -P <environment> -DuploadResults=false -DskipTests=false -DtestRunId=<test-runid-testrail> -D headless=false -D enableVideoRecording=<true/false> -D recordFailedOnly=<true/false> -D suiteXmlFile=<your-filename>.xml
mvn clean compile test -U -P qa -DuploadResults=false -DskipTests=false -D headless=false -D enableVideoRecording=true -D recordFailedOnly=true -D suiteXmlFile=LogoutTest.xml


Note: Can run the scripts with maximum 3 threads in parallel (if Salesforce application accepts multiple logins with the same account)

#######################################################################################################
#########             Run with docker & mvn commandline                                      ##########
#######################################################################################################
docker pull mcr.microsoft.com/playwright/java:v1.23.0-focal

To invoke the container above
docker run -d --rm --ipc=host mcr.microsoft.com/playwright/java:v1.23.0-focal /bin/bash
or
docker exec -it 8bd60a7960d4 /bin/bash

To copy current folder contents onto the above container to a folder tmp/pw-java 
docker cp . 8bd60a7960d4:/tmp/pw-java

docker exec -it 8bd60a7960d4 /bin/bash && cd /tmp/pw-java && mvn clean compile test -X -P qa -D uploadResults=false -D skipTests=false -D testRunId=R00000  -D headless=false -D suiteXmlFiles=secure.efax.us.xml

To copy files from container to local filesystem
docker cp 8bd60a7960d4:/tmp/pw-java/target/surefire-reports ./target/docker/surefire-reports


#######################################################################################################
# Create fat jar resolving dependencies from artifactory on devjk with ivalice               ##########
#######################################################################################################
https://confluence.j2.com/confluence/display/FAXOPS/Ivalice+-+User%27s+Guide+-+Java+Edition

ivalice generator init
#Artifactory API Key = AKCp8mYofNBHGbvgj38sJJxCCgYcJCCSeHBSNE2cRTm9nzM3d1bj6z15m5FtnHavfaUAoX5CV
ivalice jenkins create-jobs -u jagadeesh.medabalimi -p 11ff9f8e11101447e70da38773440ad3d2


ivalice jenkins create-jobs -u jagadeesh.medabalimi -p 11ff9f8e11101447e70da38773440ad3d2
ivalice jenkins run-job  --wait-for-job -u jagadeesh.medabalimi -p 11ff9f8e11101447e70da38773440ad3d2

