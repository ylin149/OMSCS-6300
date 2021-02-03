# UML Design Description #

## 1. ##
When the app is started, the user is presented with the main menu, which allows the user to (1) enter current job details, (2) enter job offers, (3) adjust the comparison settings, or (4) compare job offers (disabled if no job offers were entered yet1).
------------------------------

For this requirement, a menu will be provided to the user for them to select the mode they want to run. Depending of the mode selected, the user will be redirected to the appropriate interface. If the user chooses to enter their current job, or a job offer, they will be taken to the job entry interface. Users can also choose to modify the comparison settings or compare job offers. This is shown in the UML under the "Menu" class (which I wasn't quite sure how to diagram). The user will be redirected to the other interfaces to utilize one of the needed class. 

## 2. ##
When choosing to enter current job details, a user will:
	a. Be shown a user interface to enter (if it is the first time) or edit all of the details of their current job, which consist of:
		i. Title
		ii. Company
		iii. Location (entered as city and state)
		iv. Overall cost of living in the location (expressed as an index )
		v. Commute time (round-trip and measured in hours or fraction thereof)
		vi. Yearly salary
		vii. Yearly bonus
		viii. Retirement benefits (as percentage matched)
		ix. Leave time (vacation days and holiday and/or sick leave, as a single
			overall number of days)
	b. Be able to either save the job details or cancel and exit without saving, returning in both cases to the main menu.
------------------------------

When entering job details, the user will populate the class attributes with the appropriate values. The field requirements are fulfilled in the following way; Title is represented by 'Title: String', Company is represented by 'Company: String', Location is represented by 'Location: String', Overall Cost of Living is represented by 'CoL Index: Integer', Commute Time is represented by 'Commute Time: Hours', Yearly Salary is represented by 'Yearly Salary: Money', Yearly Bonus is represented by 'Yearly Bonus: Money', Retirement Benefits is represented by 'Retirement Benefits: Float', and Leave Time is represented by 'Leave Time: Integer'. You can also execute the following operations; confirmJob() to submit the form and set your current job, confirmOffer() to submit you a job offer, returnToMenu() to return to the main options, and compareOffers() to go to the screen to calculate ranking and compare offers.

## 3. ##
When choosing to enter job offers, a user will:

	a. Be shown a user interface to enter all of the details of the offer, which are the same ones listed above for the current job.
	b. Be able to either save the job offer details or cancel.
	c. Be able to (1) enter another offer, (2) return to the main menu, or (3) compare the offer with the current job details (if present).	
------------------------------
This will used the same interface as requirement 2, but will require pressing the gui element to execute confirmOffer(), instead of the one that would confirmJob().

## 4. ##
When adjusting the comparison settings, the user can assign integer weights to:
	a. Commute time
	b. Yearly salary
	c. Yearly bonus
	d. Retirement benefits
	e. Leave time
If no weights are assigned, all factors are considered equal.
------------------------------

The ComparissonWeights class will contain all the storage locations for setting up all the weight values. Commute time will be stored in 'CTWeight: Integer', Yearly Salary in 'YSWeight: Integer', Yearly Bonus in 'YBWeight: Integer', Retirement Benefits in 'RBWeight: Integer', Leave Time in LTWeight: Integer'. The user will also be able to confirm the weights or cancel them by returning to the menu.


## 5. ##
When choosing to compare job offers, a user will:
	a. Be shown a list of job offers, displayed as Title and Company, ranked from best to worst (see below for details), and including the current job (if present), clearly indicated.
	b. Select two jobs to compare and trigger the comparison.
	c. Be shown a table comparing the two jobs, displaying, for each job:
		i. Title
		ii. Company
		iii. Location
		iv. Commute time
		v. Yearly salary adjusted for cost of living
		vi. Yearly bonus adjusted for cost of living
		vii. Retirement benefits (as percentage matched)
		viii. Leave time
	d. Be offered to perform another comparison or go back to the main menu.
------------------------------
The JobComparison class will not have any data stored in it, but will have several calculated fields, including: Ranking which will be 'Ranking: Float', Yearly Salary Adjusted which will be 'AYS: Money', Yearly Bonus Adjusted which will be 'AYB: Money', and Retirement Benefits percentage which will be 'RBP: Float'. 

## 6. ##
When ranking jobs, a job’s score is computed as the weighted sum of:
AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)
where:
AYS = yearly salary adjusted for cost of living
AYB = yearly bonus adjusted for cost of living
RBP = retirement benefits percentage
LT = leave time
CT = commute time
The rationale for the CT subformula is:
	a. value of an employee hour = (AYS / 260) / 8
	b. commute hours per year = CT * 260
	c. therefore commute-time cost = CT * 260 * (AYS / 260) / 8 = CT * AYS / 8
For example, if the weights are 2 for the yearly salary, 2 for the retirement benefits, and 1 for all other factors, the score would be computed as: 
2/7 * AYS + 1/7 * AYB + 2/7 * (RBP * AYS) + 1/7 * (LT * AYS / 260) - 1/7 (CT * AYS / 8)
------------------------------
These requirements will be fulfilled in the JobComparison class, with the calculations taking place when the calculateRanking() method is called. 


## 7. ##
The user interface must be intuitive and responsive.
------------------------------
While this is not explicitly shown in the diagram, this will be worked on as for the project deliverable.

## 8. ##
The performance of the app should be such that users do not experience any considerable lag between their actions and the response of the app.
------------------------------
While this is not explicitly shown in the diagram, this will be worked on as for the project deliverable.

## 9. ##
For simplicity, you may assume there is a single system running the app (no communication or saving between devices is necessary).
------------------------------
While this is not explicitly shown in the diagram, this will be worked on as for the project deliverable.
