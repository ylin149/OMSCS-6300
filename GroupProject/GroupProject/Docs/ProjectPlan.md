# Project Plan

**Author**: Team 008

## 1 Introduction

The JobCompare Android app will offer the user an ability to compare multiple job offers quickly and easily. Allowing the user to set weights to different job criteria will separate JobCompare from competitors. 

## 2 Process Description

There will be two main task generalizations. First, we will have the "back end" coding where all of the classes in the UML diagram are made and all methods are created. Then we will have the "front end/gui" tasks which are based on accepting user input and generating all of the different screens we will need for the android app to work properly.

### Front end tasks

#### Create MainMenu
- Description: The main menu will be the first thing the user sees when they open the app. It will contain multipe buttons to enter job details, enter new job offers, adjust the comparison settings or compare job offers. From this menu all other menus can be accessed and thus should be one of the first things completed.
- Entrance criteria: Nothing, this will be the first file created so we only need a UI for the user to input data on.
- Exit criteria: Finish creating buttons for all actions along with a good looking interface.
#### Create JobDetailsMenu
- Description: The JobDetailsMenu will have all of the input boxes as descibed in the [requirements doc](https://docs.google.com/document/d/1iuyjpgr8nXKklR1gqKJ7CNW_Tf79dhqRZ78z38RO3Cc/edit) sectuion 2 , along with a save and exit button. These input boxes include Title, company, location, cost of living, commute time, salary, bonux, retirement benefits and leave time.
- Entrance criteria: This is also a screen so we only need user input.
- Exit criteria: Fully functioning screen where users can input data into all required fields.
#### Create ComparisonSettingsMenu
- Description: The comparison settings menu will allow a user to change the weightings on different job components. The requirements document lists commute time, salary, bonus, retirement benefits and leave time. All of these values will have input boxes that allow a user to specify an integer weight to them and that weight will be applied to all job offer comparisons.
- Entrance criteria: User input through a gui for the weightings
- Exit criteria: We can close the activitiy when the screen can capture all required user input and create an event to save the data. 
#### Create JobComparisonMenu
- Description: This menu will show all of hte job offers ranked best to worst based on the comparison settings a user has chosen. It will indicate the users current job and will only display the Title and Company of the position for a concise menu. The user will be able to select exactly two jobs to compare and that will bring up a new screen showing the two offers compared side by side. At this point they will either compare more offers, or return to the main menu.
- Entrance criteria: Along with user input, we will also need some back end support to display the details of the offer in output fields. There will be a dependency on the JobComparisonManager class.
- Exit criteria: Fully featured menu with all requiremed input, output, and buttons.
#### Assign corresponding events to buttons
- Description: This activity will tie the GUI to the back end code where the actual data storage and logic is. It will be the final task for the front-end tasks and relies on all menus already being completed. 
- Entrance criteria: All screens are done and classes are created for data to be stored.
- Exit criteria: Every button, input, and output GUI feature is tied to its correct back end counterpart. 

### Back end tasks

#### Create ComparisonSetting class
- Description: The ComparisonSetting class will contain all of the weightings from the Comparison weights menu. The user input will be the input to this task. The user input will then be applied to all job offers to score them using the algorithm found in the [requirements document](https://docs.google.com/document/d/1iuyjpgr8nXKklR1gqKJ7CNW_Tf79dhqRZ78z38RO3Cc/edit). To fully recognize the class there will also be an interface class for ComparisonSettings which we will extend for each specified type. 
- Entrance criteria: The comparisonSettingsMenu will have to be completed prior to being able to fully support this activity. The inputs to this activity are the user inputs that will be gained through the ComparrisonSettingsMenu.
- Exit criteria: When we can save the values inputed by the user to varaibles and apply all the weightings to all job offers.
#### Create Job and JobDetail classes
- Description: The Job class will contain all the attributes a job has, but no real logic behind it without any important functions. The JobDetail class will contain all of the currently inputted jobs, along with all of the methods to save, compare, add, dlete and show jobs. Between these two classes we believe the main functionality of the app when comparing jobs will be located here.
- Entrance criteria: This activity needs the GUI screen to be working to input data. 
- Exit criteria: Fully functioning ability to edit, add or delete jobs along with comparison logic.
#### Create overall JobComparisonManager and MenuController classes
- Description: The JobComparisonManager is the main class which will contain the main menu and the Menu controller. The menu controller class will render the app when it is started and contain all the other classes. The main functions that need to be completed here are the initialization of all other classes and rendering the main menu. 
- Entrance criteria: No input is needed.
- Exit criteria: A fully built and structured application which contains all needed classes to function properly.
#### Create unit tests and finalize testing
- Description: As features are being completed one of the validation engineers will test the features are complete according to the spec. When possible we will use junit tests to automate testing of specific features to ensure thea pp is always somehwat working. The final testing will be mostly done manually.
- Entrance criteria: The inputs to this activity are finished features. As a feature is finished it either needs to have a junit test written or manually tested.
- Exit criteria: All features have been tested and are working as expected.

## 3 Team

Welcome to our team! Our team will consist of four different roles some of which we share. 

The four roles are defined as such:
- Project manager: The project manager will submit the assignments and be the last one that makes sure everything looks ready for the customer. They will also set up meetings and generally manage the team's goals and keep track of progress of those goals.
- Back End Developer: A back end developer would mainly be concerned with adding logic to the events which occur when the user submits actions through the front end. This also includes making all needed classes and methods that were included in our UML Class diagram. 
- Front End Developer: The front end developer will create all of the necessary screens and input fields to make the app work properly. They will also have to communicate with the back end developers to match event types to certain buttons.
- Reviewer: A reviewer will check submitted code to make sure it is robust and extendable. Everyone on the team will need to be a reviewer at some point in the project.
- Validation Engineer: This team member will focus on writing unit tests and testing overall functionality both with automated methods and manually.

Our team consists of four members each with unique roles. Our Names are Andrew Fowler, Joseph Melius, Elsa Plaza and Andrew Ruth. We all hope we can make an amazing user experience for our customer's job search. Below you will find a table of our names and what roles we will have.

| Team Member  | Roles                                                |
|--------------|------------------------------------------------------|
|Andrew Fowler | Back end developer, validation engineer and reviewer |
|Joseph Melius | Back end developer, validation engineer and reviewer |
|Elsa Plaza    | Project manager, front end developer and reviewer    |
|Andrew Ruth   | Front end developer, validation engineer and reviewer|
