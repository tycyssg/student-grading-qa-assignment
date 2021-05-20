## student-grading-qa-assignment

### Estimate story points strategy
#### Since this project is not something which will take a long time, the effort or man hours point could not be implemented. I decided that the simplest task, which is the first taks in the below table to represent 1 point and all other tasks points to be relative to that one. Fibonacci sequence: 1,2,3,5,8 will be used as a sequence when story points are determened.
---

ID | Task | Justification | Story Points 
--- |--- | --- | ---
1 | **As a developer I want to create the CONSTANTS class** | **So that duplication of code can be avoided** | `1`
2 | **As a developer I want to create the class models** | **So that different actions can be performed** | `2`
3 | **As a developer I want to create the custom exception classes** | **So that I can use them for all kind of validations** | `2`
4 | **As a developer I want to create the method which create a new Rubric** | **So that application flow can start** | `1`
5 | **As a developer I want to create the methods which gets a all existing Rubrics and get a single Rubric by name** | **N/A** | `2`
6 | **As a developer I want to create the method that add a criteria to a Rubric** | **So that Rubric criteria can be populated** | `1`
7 | **As a developer I want to create the method that add a student grade to a Rubric** | **So that Rubric student scores can be populated** | `1`
8 | **As a developer I want to create the method that get the grade of a Rubric for a specific Student** | **So that the user can get the grade of a student** | `2`
9 | **As a developer I want to create the methods that get the Min and Max grade of a Rubric** | **So that the user can check these details** | `5`
10 | **As a developer I want to create the method that get standard deviation grade of a Rubric** | **So that the user can check these details** | `3`
11 | **As a developer I want to create the methods that get the Min and Max score from a Student grade** | **So that the user can check these details** | `3`
12 | **As a developer I want to create the methods that get the Standard Deviation score from a Student grade** | **So that the user can check these details** | `2`
13 | **As a developer I want to create the method that get avg grade of a Rubric** | **So that the user can check these details** | `2`
14 | **As a developer I want to create the class that which run all the tasks** | **N/A** | `2`

### Test-Driven development
---
TDD is a software creation methodology in which test cases are created to define and verify what the code can do. To put it another way, test cases for each feature are developed and checked first, and if the test fails, new code is written to pass the test, resulting in code that is simple and bug-free.

It will start from a very simple test version and the next step is to write code just to pass the test. When the written code make the test to pass, the test is improved then the code is improved as well just to pass the latest version of the test. This process keep repeating until the full feature was implemented.

#### The diagram bellow shows the flow in TDD.
![tdd](https://user-images.githubusercontent.com/28993633/118946033-efb26b80-b94d-11eb-8985-5ae116f85df1.png)

##### Following the [COMMIT](https://github.com/tycyssg/student-grading-qa-assignment/commit/d23a8f32d3723c98838d04f83e37e36831aaba45) it can be observed the following development flow.

* Criteria must not be created with name null.
* In the method test addRubricWithNameNull(); I throw the Exception class; Then I assert a random message.
* In the method public void addRubric(); I implement an IF statement to check if the name is null, if that is true I throw the exeption.
* I run the test, the test will fail because I do not know the right message returned by the Exception.class.
* I modify the Message with the proper one in order to make the test to pass.
* I refactor the Test method to catch a custom exception such as RequiredException.class and Asserting with a constant custom message.
* I refactor the addRubric() method to throw the same Exception with the same custom message.
* I run the test again, It pass...moving to the next functionality.

##### All other tests which are testing the same method was written in the same flow as the above one. All of the tests reflect potential side effects of the method; for example, doing a test to ensure that the object was applied to the List is irrelevant since the logic is part of Java and was almost certainly checked already. It is advised that the test be written only for the developer-created business logic.
