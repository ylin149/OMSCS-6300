# Design Description
### 1. When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet).  

This is not represented in the design as it is GUI implementation. The operations listed in this point would be listed in a view that then opens other views.

### 2. When choosing to enter current job details, a user will: <ol><li>Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:<ol> <li>Title</li><li>Company</li><li> Location (entered as city and state)</li><li>Overall cost of living in the location (expressed as an index)</li><li> Commute time (round-trip and measured in hours or fraction thereof)</li><li> Yearly salary</li><li>Yearly bonus</li><li>Retirement benefits (as percentage matched)</li><li>Leave time (vacation days and holiday and/or sick leave, as a single overall number of days)</ol> <li>Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.</li></ol>

This behavior is handled by the job class in the UML diagram. The class contains all of the attributes listed in the description. The IsCurrentJob attribute in the class will be set to true to indicate that the job entered is the one the user currently has.
</br>The save operation in the class will persist the data entered by the user and the cancel operation will exit without saving.

### 3. When choosing to enter job offers, a user will: <ol><li>Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job. </li><li>Be able to either save the job offer details or cancel. </li><li>Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).

Parts 1 and 2 of this requirement are also handled by the job class. Because all of the attributes are the same the same class can be used. Jobs entered through this user operation will have the IsCurrentJob attribute set to false.
</br>Save and Cancel will work the same way as the previous requirement.
</br>Part 3 of this requirement is handled by the GUI and is not shown in the UML. For the comparison the GUI will show the data from the Job class for the job that has IsCurrentJob = true.

### 4. When adjusting the comparison settings, the user can assign integer weights to:<ol><li>Commute time</li><li>Yearly salary</li><li>Yearly bonus</li><li>Retirement benefits</li><li>Leave time</li></ol>If no weights are assigned, all factors are considered equal.

This requirement is handled by the ComparisonSettings class in the UML Diagram. Each of the weight attributes are contained in the class. The default value of the attributes is set to 1.
</br>The save operation in the class will persist the data entered by the user and the cancel operation will exit without saving.

### 5. When choosing to compare job offers, a user will: <ol><li>Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.</li><li>Select two jobs to compare and trigger the comparison.</li><li>Be shown a table comparing the two jobs, displaying, for each job:<ol><li>Title</li><li>Company</li><li>Location</li><li>Commute time</li><li>Yearly salary adjusted for cost of living</li><li>Yearly bonus adjusted for cost of living</li><li>Retirement benefits (as percentage matched)</li><li>Leave time</li></ol><li>Be offered to perform another comparison or go back to the main menu.</li>

Most of this requirement is handled by GUI implementation. The job ranking is handled by the GUI using the Score attribute on the Job class.
</br>Getting the scores to do the comparison is handled by the Job class with the CalculateScore operation. This operation uses the weights from the ComparisonSettings class and performs the calculation in the requirements to compute a score that is stored in the Job class.
</br> The options to go back to the menu or perform another comparison are handled by the GUI.

### 6. When ranking jobs, a job’s score is computed as the weighted sum of: </br>AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)...

This computation is done by the CalculateScore operation in the Job class and is stored in the Score attribute in the Job class.

### 7. The user interface must be intuitive and responsive.

This is not represented in the design and is handled within the GUI implementation.

### 8. The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.

This is not represented in the design and is handled within the GUI implementation.

### 9. For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).

This requirment is represented by the simple nature of the UML design. Having a single system removes the need for any communication classes or operations.