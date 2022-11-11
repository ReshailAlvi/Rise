<h1 align="left"> Web Automation Using Selenium </h1>

<h3 align="left"> How To Run </h3>

Clone the project

Start docker

Navigate to project directory

Run the following commands

~~~
docker compose -f docker.yml up
~~~
~~~
Run command `mvn test`
~~~

<h3 align="left"> Reporting </h3>
You will find the emailable-report under target -> surefire-reports -> emailable-report.html

Error screenshots can be found under the errorScreenshots folder.

<h3 align="left"> Project Overview </h3>

The framework follows the data driven approach with POM. You can find the DataProvider class under utilities which reads an excel file. We use the test method name to pick the valid data (if more than one row is present, it means the test will run all scenarios). The docker file will setup the SeleniumGrid for execution and config.properties will provide relevant configuration to run. CommonMethods and constants are used to avoid duplication and keep the code clear and easy to read.
