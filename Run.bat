call mvn dependency:analyze dependency:resolve clean compile package -U -e -DskipTests=true -Dmaven.artifact.threads=16
pause "Press 'Enter' to continue running tests from fat jar"
java -jar target\SalesForceCCSI-1.0.0-fat-tests.jar -environment qa -skipTests false -uploadResults false -testRunId R00000 -suiteXmlFile end2end.xml -headless true -timeout 180000
pause

