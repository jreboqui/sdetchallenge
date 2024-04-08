
# Fetch SDET Challenge

Automation written in Java utilizing Selenium framework to play the challenge game and output the result




## Installation Guide

Prereq:

Must be running Java 11 and the latest version of Chrome browser. Java 11 installation guide can be found here https://www.codejava.net/java-se/download-and-install-java-11-openjdk-and-oracle-jdk.

To check your version of Java, open a terminal and type
```bash
  java --version
```

Selenium dependencies and the latest version of chromedriver are included in the repo.

Download and install Maven (follow installation guide like this https://www.baeldung.com/install-maven-on-windows-linux-mac)




## Run Locally

Clone the project

```bash
  git clone https://github.com/jreboqui/sdetchallenge.git
```

Import the project folder in vscode or your favorite IDE. Make sure that chromedriver is pointing to the correct file directory. See BaseApp.java line 8

```bash
 Run the main class in App.java
```

For stand-alone maven
```bash
**Open up a terminal and go to the sdetchallenge folder.**

mvn clean install

```
Once the build is complete (should see BUILD SUCCESS), go to the target folder and run

```java
java -jar sdetchallenge-1.0-SNAPSHOT-jar-with-dependencies.jar
```
The program should start and open up a chrome browser, go to the challenge url, and start playing the game


## Structure Documentation and How it works

Using page object model pattern, the page for the challenge http://sdetchallenge.fetch.com/ is represented as GameBoardPage.java

All the elements on the screen that users can interact with are segregated by using components. In essence, the GameBoardPage contains the components and calls them for actions/command when needed.

PageObjects:
```bash
    GameBoardPage.java
```
Components(see screenshots):

```bash
    CoinsComponent.java
    WeighingScaleComponent.java
    WeighingsComponent.java
```
![component1_screenshot](https://github.com/jreboqui/sdetchallenge/assets/19766843/82e1a799-ac7b-4078-acb0-516b5e020eb0)
![component2_screenshot2](https://github.com/jreboqui/sdetchallenge/assets/19766843/54370034-6aa2-413f-8cd4-17facb683912)
The GameBoardPage.start() is responsible for initializing some parameters such as the number of coins/bars we have and setting what is the initial low and initial high values(needed for the algorithm). It is also responsible for outputing the list of weighings and clicking the correct fake gold bar number once the algorithm is done.


The algorithm to help us determine what is the fake gold bar is called recursively in **recursiveSplitAndWeigh** method. Basically the algorithm goes like this:
```

    Determine if we have odd or even number of bars
    Get the middle value of the bars
    If it's even then
        Weigh the bars on the left and right excluding the middle
    If it's not even then 
        Weigh the bars up to the middle on the left bowl, and
        the bars right of the middle bar on the right bowl
 
    If left and right are equal, then the mid bar is the fake one
    Otherwise, select the lighter group of bars and repeat
     the  algorithm until you get the correct one
```



