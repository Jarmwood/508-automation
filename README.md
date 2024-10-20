# 508 Compliance Automation | Java + Selenium
508 compliance automation testing to access and validate an applications frontend for 508 compliance violations and output the errors to a txt file.

## Prerequisites:
* [Maven](https://maven.apache.org/index.html): Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information.
  -  Ensure maven is configured correctly on your system $PATH to be able to utilize the 'mvn' commands
    -  Confirm with ```mvn -v``` in a new shell. The result should look similar to this:
        ```
        Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)
        Maven home: /opt/apache-maven-3.9.9
        Java version: 1.8.0_45, vendor: Oracle Corporation
        Java home: /Library/Java/JavaVirtualMachines/jdk1.8.0_45.jdk/Contents/Home/jre
        Default locale: en_US, platform encoding: UTF-8
        OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"
        ```

## Installation:
Get tool running locally
* Clone repo on to your local machine
* cd to project

## Prepare test environment
* run: ```mvn clean```
* run: ```mvn compile```

## Test:
* run: ```mvn test```

## Dependencies
All dependencies and versions will be housed in the ```pom.xml``` file
- Noteable dependencies are:
  -   Axe Deque
  -   Selenium
  -   Java
  -   TestNG

## Expected end result:
![image](https://github.com/user-attachments/assets/446b6183-9662-493c-92e8-64abbdac7bd2)


