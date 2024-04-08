
# Fetch SDET Challenge 

Automation written in Java utilizing Selenium framework to play the challenge game and output the result




## Installation Guide

Prereq:

Must be running Java 11 and the latest version of Chrome browser. Java 11 installation guide can be found here.

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

Import the project folder in vscode or your favorite IDE.

```bash
 Run the main class in App.java
```

For stand-alone maven
```bash
**Open up a terminal and go to the sdetchallenge folder.**

mvn clean install

```
Once the build is complete (should see BUILD SUCCESS), go to the target folder and run

```bash
java -jar sdetchallenge-1.0-SNAPSHOT-jar-with-dependencies.jar
```