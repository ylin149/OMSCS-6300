# Test Plan


**Author**: Team 08

## 1 Testing Strategy

### 1.1 Overall strategy

Our testing strategy will be split in two parts. We will use both manual and automated testing. Automated testing will include unit and integration tests which will be written and implemented while developing the code. When a developer adds or modifies code in the project, they are responsible for adding or fixing unit and integration tests that cover that code.</br>
Manual tests will be conducted when a feature is completed. A team member who did not work on that section of code will conduct manual tests focused on verifying use cases. Regression testing will be handled mostly with automated tests that will be run when new code is checked in. Manual regression testing focused on use cases will also be conducted before a major deliverable.


### 1.2 Test Selection

Unit and integration tests will be done using white box testing methods. The goal of these tests will be to cover the different code paths and ensure that the different possible paths work correctly. System level testing will use black box testing. Inputs and expected outputs for the tests will be selected using the use case model to ensure that all use cases are covered correctly.

### 1.3 Adequacy Criterion

Code that is difficult to verify with manual methods like the job score will be completely covered by automated tests. Unit tests will be written for the job score code that covers multiple common scenarios using white box testing methods. Integration tests will cover the parts of the code that save data to the database. System level tests will be considered adequate if the testing covers all of the use case model.

### 1.4 Bug Tracking

Bugs and enhancement requests will be tracked using a document in the repository. The bug or request will be documented and the team will decide if the request is valid. If it is valid the status of the request will be tracked in the document. When completed the status will be updated and relevant tests, added because of the request will be marked in the document.

### 1.5 Technology

Unit and integration testing will be implemented using JUnit and Espresso.

## 2 Test Cases

| #    | Purpose                              | Steps                                                        | Expected Result                                              | Result | Pass/Fail | Additional Info |
| ---- | ------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------ | --------- | --------------- |
| 1    | Save a user's job for the first time | 1. Click enter job details<br />2. Enter details in all fields<br />3. Click Save<br />4. Click enter job details | The values entered should be shown in the dialog.            | The values previously entered correctly populate the fields.       | Pass          |                 |
| 2    | Edit a user's job                    | 1. Follow steps for test 1<br />2. Change the details<br />3. Click Save<br />4. Click enter job details | The new values should be shown in the dialog.                | The edited values correctly populate the fields.       | Pass          |                 |
| 3    | Exit the job details dialog          | 1. Click enter job details<br />2. Enter details in all fields<br />3. Click Cancel<br />4. Click enter job details | The entered information should not be saved and the dialog should show the original values. | Changed values are successfully discarded and not saved.       | Pass           |                 |
| 4    | Save a job offer                     | 1. Click enter job offer<br />2. Enter details in all fields<br />3. Click Save<br /><br />4. Click return to main menu<br />5. Click compare job offers | The entered job should be in the list of job offers to compare. | Entered job is saved and listed in the job offer list.       | Pass          |  |
| 5    | Delete a job offer                   | 1. Follow steps in test 4 to save an offer<br />2. Select the offer<br />3. Select Delete | The offer should be removed from the list of offers.         | Job is deleted from the job list.       | Pass       |                 |
| 6    | Enter multiple job offers            | 1. Click enter job offer<br />2. Enter details in all fields<br />3. Click Save<br /><br />4. Click enter another offer<br /> | The dialog should clear all fields and be ready to input a new job offer. | Form is correctly cleared for the next job to be entered. | Pass           |                 |
| 7    | Compare job offers               | 1. Enter at least two job offers<br />2. Select compare job offers <br />3. Select which jobs to compare<br /><br />4. Click compare offer<br /> | Both the new offer and the current job should be displayed next to each other in a table with the details. | Job were successfully compared and their scores listed.       | Pass          |                 |
| 8    | Adjust comparison settings           | 1. Click settings<br />2. Click adjust comparison settings<br />3. Click value to be changed<br />4. Navigate back to settings screen | The newly entered values should be shown in the dialog.      | Entered values correctly populated the adjust comparison settings screen.       | Pass          |                 |
| 9    | View comparison table                | 1. Enter multiple job offers using steps from test 4<br />2. Click compare job offers | All entered jobs should be visible ranked by score with the current job clearly marked | Jobs are all listed and able to be selected | Pass       |                 |
| 10   | View job comparison                  | 1. Follow steps from test 8<br />2. Select two jobs and click view comparison | The two jobs should be displayed next to each other in a table with the details. | Pair of jobs are successfully compared with weights specified.        | Pass          |                 |
| 11   | Test score calculation               | Create unit tests with expected inputs and outputs to test correctness of score calculation. | The test should output the expected value given the inputs.  | Added test for small numbers and numbers representative of real world | Pass |                 |
| 12   | Test database saving                 | Create integration tests that save and retrieve data from the database. | The test should be able to save and retrieve the expected data | Add tests for all database operations | Pass |                 |