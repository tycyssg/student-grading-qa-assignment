## student-grading-qa-assignment

### Estimate story points strategy
---
Since this project is not something that will take a long time, effort or man-hours point could not be implemented. I decided that the simplest task, which is the first task in the below table to represent 1 point and all other tasks points to be relative to that one. Fibonacci sequence: 1,2,3,5,8 will be used as a sequence when story points are determined.


#### Backlog
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

#### At the end of each Sprint all the estimation points of the completed tasks are added up, and the total of these points is called velocity. The current sprint velocity is 29 since all the tasks have been completed. In case some tasks are not completed (agile level of completion are 0% or 100%, so even a task is 90% is still considered 0% complete) are not included in the velocity. Assuming the last 2 tasks from the above table will not be completed in this sprint, the sprint velocity will be 25.
#### The velocity determined in the first sprint will dictate the amount of work the Team can tackle during the next sprint. So for the next sprints the team will now exactly what tasks to include to be sure they complete everything. Sometimes extra things will need to be considered when the tasks are selected, for example the priority, level of difficulty vs team members experience, new requests from the client, etc.

### Test-Driven development
---
TDD is a software creation methodology in which test cases are created to define and verify what the code can do. To put it another way, test cases for each feature are developed and checked first, and if the test fails, new code is written to pass the test, resulting in code that is simple and bug-free.

It will start from a very simple test version, and the next step is to write code just to pass the test. When the written code make the test to pass, the test is improved then the code is improved as well just to pass the latest version of the test. This process keep repeating until the full feature was implemented.

#### The diagram below shows the flow in TDD.
![tdd](https://user-images.githubusercontent.com/28993633/118946033-efb26b80-b94d-11eb-8985-5ae116f85df1.png)

#### The following [commit](https://github.com/tycyssg/student-grading-qa-assignment/commit/d23a8f32d3723c98838d04f83e37e36831aaba45) shows the above described development flow.

* Criteria must not be created with name null.
* In the method test addRubricWithNameNull(); I throw the Exception class; Then I assert a random message.
* In the method public void addRubric(); I implement an IF statement to check if the name is null, if that is true I throw the exception.
* I run the test, the test will fail because I do not know the right message returned by the Exception.class.
* I modify the Message with the proper one in order to make the test to pass.
* I refactor the Test method to catch a custom exception such as RequiredException. Class and Asserting with a constant custom message.
* I refactor the addRubric() method to throw the same Exception with the same custom message.
* I run the test again, It passes...moving to the next functionality.

##### All other tests which are testing the same method was written in the same flow as the above one. All the tests reflect potential side effects of the method; for example, doing a test to ensure that the object was applied to the List is irrelevant since the logic is part of Java and was almost certainly checked already. It is advised that the test be written only for the developer-created business logic.

### Test coverage metric
---
#### The tool used is the Coverage tool from Intellij IDE. It simply runs the test class with the Coverage tool option as it can be seen in the below picture.
![1](https://user-images.githubusercontent.com/28993633/118960684-7457b680-b95b-11eb-9395-da56765dcc23.png)

#### The coverage tool analyse the tests and at the end it presents some statistics about how much the tests cover the project.
![3](https://user-images.githubusercontent.com/28993633/118961158-ea5c1d80-b95b-11eb-8957-2cab3bcbd120.png)

#### The way how it works is as follows: In all the classes from the project on the right side we will see 2 colors along the lines of the code. The green color represent that portion of the code is covered, and the red one is the one which more tests need to be created.
![2](https://user-images.githubusercontent.com/28993633/118961496-48890080-b95c-11eb-89a8-db2ca87418b7.png)

#### The above picture shows that the method has test covered for the first part, and the second part where the Criteria are added into the list is not covered in tests. (The red part in this example is wrong, since I do not override the add method from the List, it should ignore that)

### Team version control
---
#### Each of the branch created in this project represents a task from the Backlog table. Every branch represents a new feature, and each one includes code that represents the feature's business logic as well as tests that validate all the business code. All the feature branches have been checked out from the dev branch which represent the starting point.
#### When the development process is finish, a pull request is made. All the code is being verified and reviews will be added if is the case; then the issues will be fixed, the fixes will be verified, if everything is right the pull request will be approved and then merged into the dev branch.

### Code Reviews
---
#### All the code reviewers should follow this guide when they review pull requests. Before starting make sure the new branch was checked out from the dev branch and pull request it shows that the current branch will be merged into dev.
#### To perform a proper review please ask yourself the following questions:

* Are there any obvious logic errors in the code?
* Looking at the requirements, are all cases fully implemented?
* Does the new code fix the problem?
* Is the code legible, can it be written in a more simplified way?
* Are there tests that cover all the corner cases from the new code?
* Is there any unused code that may break the application?
* Is the naming of classes or methods relevant and correctly spelled?
* Are all the lint style respected? (Code properly formatted, Ordered Imports, Unused Imports deleted)


#### The following [Pull Request](https://github.com/tycyssg/student-grading-qa-assignment/pull/11) shows how the code-review should be performed.
