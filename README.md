selenium_ticketfly
==================
What does the project contain?

1.       The com.ticketfly.pageobjects package contains a class for each page. BasePage, TicketflyHomePage, PurchaseTicketsPage, DeliveryMethodPage and GoogleHomePage are the classes in this package.

2.       The com.ticketfly.tests contains all test classes. BaseTest and InterviewTest are part of this.

3.       com.ticketfly.utils contains the PropertyLoader class that loads the test.properties file.

4.       libs folder contains the required dependencies.

5.       test.properties file -> This file is used to get parameter values for the desired browser and OS.

The values can be as follows:

browser=chrome/firefox

OS=mac/windows

How to run the test?
InterviewTest contains the test case as described in the problem.
1. From Eclipse

Enter the desired browser value and the OS value in test.properties file and run the InterviewTest class as a JUnit Test.

 

2. From command line

     From the tests root folder, execute the below command

java -cp bin/:src/libs/selenium-server-standalone-2.40.0.jar:src/libs/selenium-java-2.40.0 org.junit.runner.JUnitCore com.ticketfly.tests.InterviewTest
