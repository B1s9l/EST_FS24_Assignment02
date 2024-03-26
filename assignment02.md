# Effective Software Testing Lab (Assignment 2)


Deliverable: One single zip file containing all the folders that you find attached to this assignment, augmented with the tests you will write for each problem, as described below. Additionally, a `Documentation.md` file per exercise where you document your decisions, testing strategy, choices, and any assumptions made. Furthermore, you should create an Assets folder for each exercise which contains screenshots of your test results such as coverage reports and logs of your tests running successfully. 
- Deadline: April 22, 2024, at 18:00 (Zurich, CH, time).

## Forming Groups
The assignment should be submitted by the same group to form for assignment 01.

Carry out the assignment **with your group only**. You are allowed to discuss solutions with other groups, but each group should come up with its own solution. A strict plagiarism policy will be applied to all the artifacts submitted for evaluation.

## Exercises
This assignment consists of eight exercises. Each exercise has its own folder and contains a problem definition and the corresponding solution in Java. Your task is to test the solutions, according to the instructions given in [the Testing section below](#testing).

The IntelliJ IDEA is strongly recommended


###### Structure of an Exercise
Every exercise has its own folder and contains a problem definition, a `README.md` file which explains the exercise, and contains a `src` folder, which contains at least two files,
`ExerciseName.java` and `ExerciseNameTest.java`. The `ExerciseName.java` file contains the solution which you have to test, and the `ExerciseNameTest.java` file is the file where you have to write your tests. If your test reveals any bugs, fix them by modifying `ExerciseName.java`. In that folder, there is an empty `pom.xml` file as well that you have to write your Maven configuration. To work on an exercise, you have to import this `pom.xml` file into your preferred IDE. Your task is to test the solutions, according to the instructions given in [the Testing section below](#testing).

## Solving an Exercise
You have to write your tests inside the `ExerciseNameTest.java` file inside the `src` folder and your Maven configuration into pom.xml in the root directory.  You have to create an `Assets` folder for the screenshots of coverage reports and a  `log.txt` file that shows your tests run successfully.  Finally, you have to create a `Documentation.md` file in the root folder where you document your testing decisions and report the requested coverage (a single `Documentation.md` should contain the documentation for all the exercises).

###### Requirements
Java 11 is used for all the exercises. There is no guarantee things will work with other versions. Use JUnit 5 for implementing your test suite.

## Overview
This assignment covers **Chapter 3: Structural Testing and Code Coverage**, introduces **Chapter 4: Designing Contracts**, and **Chapter 5: Property-Based Testing**. You will apply the concepts learned to design and implement tests for given exercises. 

## Testing 
You are asked to perform effective and systematic software testing for each exercise's solution. Specifically:


### Task 1: Code Coverage

In this task, you are required to achieve the highest possible (ideally, 100\%) line coverage. for the provided Java solutions. Utilize the [JaCoCo] plugin to analyze and generate coverage reports. 


### Task 2: Designing Contracts
- For the provided Java solutions, design and document contracts including pre-conditions, post-conditions, and invariants.
- Implement the contracts in Java solution code and write tests to verify that the contracts are enforced at runtime. Define appropriate pre-conditions, post-conditions, and invariants for each provided Java solution. Incorporate the designed contracts into the source codes. For invariants, ensure they are checked at the start and end of each public method or after any state-changing operation.


### Task 3: Testing Contracts

Develop a suite of JUnit tests specifically aimed at verifying that the contracts are correctly enforced. This should include tests that:
- Validate normal operation when pre-conditions are met.
- Confirm that appropriate exceptions or errors are thrown when pre-conditions are violated.
- Ensure post-conditions hold after the execution of functions under various conditions.
- Verify that invariants are maintained throughout the software module's lifecycle, especially after state changes.



### Task 4: Property-Based Testing
- Use **property-based testing** techniques to derive tests for the provided Java solutions.
- Identify properties that should hold true for any inputs and document your rationale.
- Use a property-based testing framework to automate the testing process. 
- hint: Add jqwik framework to Your pom.xml

## Grading Criteria
Each assignment will be graded with either 0 (fail) or 1 (pass). For an assignment to score 1, it must: 
1. Provide clear evidence of effort.
**and**
2. Provide a clear `Documentation.md` file 
**and** 
3. Provide code that fulfils the requirements of the assignment.

Please note that the lab assignments are propaedeutic to proper preparation for the final exam, i.e., the lab work should be considered as important not only for the bonus points but also for preparing the final exam.

## Note:
Using Generative AI tools in the assignments is allowed, given that you deliver an appendix in the `Documentation.md` file with all the prompts you used.
