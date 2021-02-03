package edu.gatech.seclass.jobcompare6300.Models;

public class Job {
    public int id;
    public String Title = "";
    public String Company = "";
    public String Location = "";
    public float CostOfLiving = (float) 0.0;
    public float CommuteTime = (float) 0.0;
    public float YearlySalary = (float) 0.0;
    public float YearlyBonus = (float) 0.0;
    public float RetirementBenefits = (float) 0.0;
    public int LeaveTime = 0;
    public float Score = (float) 0.0;
    public boolean IsCurrentJob;
}
