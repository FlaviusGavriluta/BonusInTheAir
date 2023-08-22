
# Bonus in the air

## What are you going to learn?
* Understand legacy code.
* Write unit tests.
* Refactor code guarded by unit tests.

## Story

Life is hard for poor brokers: they got low (okay, relatively low) fixed salaries, and the bigger part
of their income come from bonus payments. And bonus comes after the profit they made that month.
This incentive makes them fight for success fiercely. "Man is wolf to man", as they say, and it's
even more true on Wall street.

The bank you are working for has a software to calculate company-level profit, and implicitly broker
salaries as well. This piece of code works, but it is a complete mess, and the guys who created it are
long gone. The management would like to change the bonus system a bit but no one dares to touch the legacy code.

Here you come to save the day and clean up this mess. As a well-trained programmer, you plan to cover
the existing profit calculator with unit tests. After that you can start refactoring the code until you
reverse engineered the whole thing.

If you succeed, you can expect a fat bonus check :)

## Tasks


**Add JUnit dependency**

Add JUnit 5 as a Maven dependency to your **pom.xml**.
Include that specific JUnit 5 version you were advised to use in the JUnit tutorial.

1. JUnit dependency is added to **pom.xml**, using the version suggested in the previous JUnit tutorial.


**Write tests for the legacy company profit calculator**

Cover the code with unit tests, try to cover edge cases. The more you understand from the code,
the better you can do this. Some examples:

* Create a test for the case when no brokers has achieved any profit.
* Create a test for the case when brokers have achieved some profit but none has reached the minimum bonus limit.
* Create a test for the case when at least some brokers have reached the minimum bonus limit.

1. There is a test file with at least three test cases for **CompanyProfitCalculator's calculate()** method.

2. The test cases and arguments try to cover significantly different scenarios for the profit calculation logic.


**Refactor the calculator**

After securing the code with unit tests, you can start refactoring it. First, "save" the legacy version
and its test class into a different subpackage (**legacy** will do), so that you can run it and compare the
outcomes with the refactored version. Then do what you have to do: rename, rephrase, extract parts of the
original code until you feel it clean enough to go into production. You can create new fields, methods,
classes, as you see fit.

1. The refactored version has meaningful and fit names for new classes, methods, fields, and local variables.
	There are no remnants of cryptic, abbreviated names from the original version.

2. The refactored version has methods with single responsibilities.

3. The refactored version passes the same test assertions as the original one.

4. The original **CompanyProfitCalculator** is reachable in a legacy subpackage, along with the tests you'd
	created for it.
	
	
**Add more tests**

Now that you have a clean and easily understandable profit calculator, extend your test suite to cover
the newly created parts of the code. For example, if you have extracted a class from the **CompanyProfitCalculator**
class based on the SRP, then you should provide a unit test class for it as well, and cover it fully.

1. Every newly created public service methods are covered with tests.


## **Hints**

* Remember that test classes (like **CompanyProfitCalculatorTest**) should be placed not in the **src/main**,
	but in the **src/test** directory, under the same package name as the original class.
	
## **Background materials**
[How to write JUnit 5 tests](https://junit.org/junit5/docs/current/user-guide/#writing-tests)

[Refactoring principles](https://myadventuresincoding.wordpress.com/2010/07/03/refactoring-principles/)