## Test Suite - Spin Game

The web page under test is a online game consisting of three reels that spin when you press the spin button. The game features 6 symbols that appear at random on each reel. When the spin ends you will get one of the following three messages based on your winning status:
* ”No Win, try again.” if none of the symbols are equal.
* “Small win, try again to win more.” if two of the symbols are equal.
* “Big win, congratulations.” if all symbols are equal.

## Built With

* 	[JDK8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - Java™ Platform, Standard Edition Development Kit
* 	[Selenium](https://selenium.dev/documentation/en/introduction/)
* 	[Junit5](https://junit.org/junit5/) 
* 	[WebDriverManager](https://github.com/bonigarcia/webdrivermanager) 
* 	[Allure Junit 5 Report](https://docs.qameta.io/allure/#_junit_5) 

## Requirements

For running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Chrome Browser](https://www.google.com/chrome/) (atleast)
- [Spin Game] - Make sure the Spin Game is running either locally or remotely on any server

## Running the tests locally
### Get the code

Clone the repo:

```bash
$ git clone https://github.com/rudrasd29/iamrudra.git
```

Then build the project (build requires JDK 1.8 or higher) using the command line / terminal: 

```bash
$ ./mvnw clean verify
```

Note: Above command picks the default profile. If you want to run the tests in headless mode, append <b><i>-Pchrome-headless</i></b> at the end.

Then, to build Allure report run

```bash
$ ./mvnw allure:report
```

And finally to view the report, run the below command

```bash
$ ./mvnw allure:serve
```

## View the open bugs

Navigate to categories section of the generated report, to view the open bugs.

## Further Improvements to the framework 

* Extend the framework to support other browsers (apart from Chrome and Firefox)
* Add the ability of taking screenshots for failures (or even pass if required)
* Extend the framework to support remote execution
* Support for parallel execution
* Hook the test suite with any continuous integration server (Jenkins or TeamCity)
