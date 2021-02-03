# Job Comparison Application Design Document
## Requirements
* When the app is started, the user is presented with the main menu,
which allows the user to
(1) enter current job details,
(2) enter job offers,
(3) adjust the comparison settings,
or (4) compare job offers (disabled if no job offers were entered yet).
 - [x] To represent this requirement in the class diagram, I created the JobComparisonManager, MenuAction, Menu
 classes that build and render the menu dynamically. 
I chose to dynamically build the menu,
in case in the future we need more menu items,
we can simply create a new class that would implement a
MenuAction class without having to make several changes in the JobComparisonManager class.
* When choosing to enter current job details, a user will:
Be shown a user interface to enter (if it is the first time)
or edit all of the details of their current job, which consist of:
- [x] To realize this requirement, I added the CurrentJobOfferDetailsAction class, which inherits from MenuAction.
This class is responsible for saving the current job, editing the job by using the Job object. Also, since this class inherits from \
MenuAction, it will also be responsible for rendering the interface detailed in the requirement.
  1. Title: This field was included in the Job class
  2. Company : This field was included in the Job class
  3. Location (entered as city and state): This field was included in the Job class
  4. Overall cost of living in the location (expressed as an index): This field was included in the Job class
  5. Commute time (round-trip and measured in hours or fraction thereof): This field was included in the Job class
  6. Yearly salary: This field was included in the Job class
  7. Yearly bonus: This field was included in the Job class
  8. Retirement benefits (as percentage matched): This field was included in the Job class
  9. Leave time (vacation days and holiday and/or sick leave, as a single overall number of days): This field was included in the Job class
  10. Be able to either save the job details or cancel and exit
   without saving, returning in both cases to the main menu.
  - [x] In terms of saving the job details, I added the method save to the class CurrentJobOfferDetailsAction, which saves the current job offer
  - [x] Regarding the canceling or exiting, I added the cancelOrExitAction method in the MenuAction class to cover this requirement,
   as well as the returnToMainMenuAction method in the MenuAction
* When choosing to enter job offers, a user will:
  1. Be shown a user interface to enter all of the details of the offer,
   which are the same ones listed above for the current job.
  - [x] Since this class inherits from MenuAction, it will be responsible
   for rendering the interface detailed in the requirement.
  2. Be able to either save the job offer details or cancel.
  - [x] I added the CurrentJobOfferDetailsAction class, which inherits from MenuAction.
               This class is responsible for saving job offer by calling the saveJobOffer method
  -[x] The canceling action is implemented in the MenuAction class as cancelOrExit
  3. Be able to (1) enter another offer,
   (2) return to the main menu, or
   (3) compare the offer with the current job details (if present).
  -[x] Add a new job offers can be accomplished by calling the method: addJobOffer,
   return to the main menu was implemented in MenuAction in two method, cancelOrExit and returnToMainMenuAction functions
  -[x] The compare functionality will be implemented in the method: compareJobOffer, which uses the Comparison class
* When adjusting the comparison settings, the user can assign integer weights to:
  1. Commute time: Implemented using CommuteTime class
  2. Yearly salary: Implemented using YearlySalary class
  3. Yearly bonus: Implemented using YearlyBonus
  4. Retirement benefits: Implemented using RetirementBenefits class
  5. Leave time: Implemented using LeaveTime class
  6. If no weights are assigned, all factors are considered equal.
  - [x] In terms of comparison settings, I decided to implement the ComparisonSettings interface,
   which will be implemented by each concrete comparison settings class detailed above.
  - [x] I also created ComparisonSettingsAction class,
   which encapsulate the ComparisonSettings and also render the comparison settings screen
  - [x] Each of the weights for the comparison settings are stored in their corresponding setting.
   By default, these weights will be 1, to cover the requirement that all factors will be considered equal.
* When choosing to compare job offers, a user will:
  1. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst
   (see below for details), and including the current job (if present), clearly indicated.
     -[x] showJobOfferSelectableList method in the class JobOffersDetailsAction, displays the list detailed above.
     showJobOfferSelectableList uses the list of job offers and the current job contained in the JobOffersDetailsAction class
  2. Select two jobs to compare and trigger the comparison.
      - [x] The list shown by showJobOfferSelectableList is selectable, so the user will be able to select the list of jobs he/she wants to compare
  3. Be shown a table comparing the two jobs, displaying, for each job:
     1. Title: This field is in each job offer
     2. Company : This field is in each job offer
     3. Location : This field is in each job offer
     4. Commute time : This field is in each job offer
      5. Yearly salary adjusted for cost of living: This field is calculated by using the index costOfLiving index in each offer
      6. Yearly bonus adjusted for cost of living: This field is calculated by using the index costOfLiving index in each offer
      7. Retirement benefits (as percentage matched) : This field is in each job offer
      8. Leave time : This field is in each job offer
     - [x] The class JobOffersDetailsAction uses the comparison class and then the comparison class
          uses the ComparisonTable to display the table described in this requirement.
  4. Be offered to perform another comparison or go back to the main menu.
  -[x] This option is offered by performOtherComparison method
    in the CompareJobOffersAction class
    and returnToMainMenuAction in the MenuAction class
      1. When ranking jobs, a job’s score is computed as the weighted sum of: AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)
           where:
            1. AYS = yearly salary adjusted for cost of living
            2. AYB = yearly bonus adjusted for cost of living
            3. RBP = retirement benefits percentage
            4. LT = leave time
            5. CT = commute time
            6. The rationale for the CT subformula is: value of an employee hour = (AYS / 260) / 8 commute hours per year = CT * 260, therefore commute-time cost = CT * 260 * (AYS / 260) / 8 = CT * AYS / 8
            7. For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors,
           the score would be computed as:
           2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)
          -[x] This requirement is implemented in the method rankJobOffers that belongs to the Comparison,
        which uses the algorithm detailed by the requirement.
   - [x] The list of offers that are used for the calculation is represented by a parameter in the rankJobOffers function
   - [x] The Job that might be used for the calculation is represented by a parameter in the rankJobOffers function
   - [x] The Comparison settings that are used for the calculation and are represented by a parameter in the rankJobOffers function
* The note "To be precise,
this functionality will be enabled if there are either
(1) at least two job offers, in case there is no current job, or
(2) at least one job offer, in case there is a current job."
  - [x] The requirement detailed in the note is represented in the diagram 
  by associations relationships from the Comparison class to JobOffer class and also to Job class.

- [x] The next three requirements are not represented in the class diagram, since these are functionalities will be implicitly added as I implement the Application
  * The user interface must be intuitive and responsive.
  * The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.
  * For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).