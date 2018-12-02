Starting the service:
---------------------
./gradlew bootRun (in Linux)

or 

gradlew.bat bootRun (in Windows)

or just run the jar the old fashioned way!:

./gradlew build (in Linux) or
gradlew.bat build (in Windows)

then ...

java -jar build/libs/user-service-0.1.0.jar

Using the UI:
-------------
A UI for the application is available at the following url:

http://localhost:10000/swagger-ui.html

Shutdown endpoint:
------------------
A shutdown endpoint can be activated via:

curl -X POST localhost:10000/shutdown


Running Cucumber tests:
-----------------------
To run the acceptance tests use:

./gradlew cucumber (in Linux)

or

gradlew.bat cucumber (in Windows)
