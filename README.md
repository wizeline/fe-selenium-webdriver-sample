# fe-selenium-webdriver-sample project


## Prerequisites
1. [Maven](https://maven.apache.org/download.cgi) [Install manual](https://maven.apache.org/install.html )
2. [Java 8](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html)

## How to use the project
Firstly, you have to clone the repository to get the project structure.

The project uses TestNG and it is configured to exetcute the tests using the  _maven test_  command.
You will be able to execute it going to project directory (use promp cmd) and executing there the maven command.
If you want to execute a concreted test suite you can do it executing  _mvn clean test -Dtest.suite=[testSuiteName].xml_

### Configuring pom.xml parameters
_pom.xml_  is located in the root path of the project. There, there are defined versions of plugins and technologies, parameters for test executions and dependencies.

### Parameters for test executions
Important parameters to execute tests:

1. _browser_: Browser used to launch tests. Allowed values: _Firefox_, _Chrome_
2. _local.OS_: OS used to launch tests. Allowed values: _Windows_ , _MAcOS_
3. _webdriver.chrome.driver_: Path in which the Chrome driver is. 
4. _webdriver.firefox.driver_: Path in which the Firefox driver is.

## src/main/resources folder:
1. files/software: In this folder we store the browser drivers ([geckodriver versions](https://github.com/mozilla/geckodriver/releases) , [chromedriver versions](https://chromedriver.chromium.org/))
2. suites: Here you can group sets of tests to run it together.
3. _log4j.properties_: This file sets the logging properties, in this case the output to console.
4. _test.properties_: In this file we store some common tests properties.

## src/main/java folder:
1. pageObject: Here you have every PageObjects. All PageObjects extends BasePageObject, it has important functions.
2. testSets: Here you can find the tests classes. _@before_ and _@after_ methods are defined here. All tests classes extends DefaultTestSet.
3. utils: 
 * Inizialization - Contains the main selenium webdriver configuration.
 * DataUtils - Class that contains useful methods to provide to us mock data.
