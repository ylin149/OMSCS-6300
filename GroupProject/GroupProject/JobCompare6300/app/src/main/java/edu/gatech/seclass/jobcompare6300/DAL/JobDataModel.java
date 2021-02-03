package edu.gatech.seclass.jobcompare6300.DAL;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.gatech.seclass.jobcompare6300.Models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.Models.Job;

public class JobDataModel {
    private JobDbHelper dbHelper;
    public static final String DATABASE_NAME = "JobComparison.db";

    public JobDataModel(Context context){
        dbHelper = new JobDbHelper(context,DATABASE_NAME);
    }

    public JobDataModel(Context context, String databaseName){
        dbHelper = new JobDbHelper(context,databaseName);
    }

    public boolean Add(Job job){
        boolean result = false;

        job.Score = CalculateScore(job);

        if(job.id == 0){
            if(job.IsCurrentJob && GetCurrentJobId() > 0){
                job.id = GetCurrentJobId();
                result = dbHelper.updateJob(job);
            }
            else{
                result = dbHelper.insertJob(job);
            }
        }

        return result;
    }

    public boolean Update(Job job){
        boolean result = false;

        job.Score = CalculateScore(job);

        if(job.id > 0){
            if(!(job.IsCurrentJob && GetCurrentJobId() != job.id)){
                result = dbHelper.updateJob(job);
            }
        }

        return result;
    }

    public Job Get(Integer id){
        return dbHelper.getJob(id);
    }

    public Job GetCurrentJob(){
        Job job = Get(GetCurrentJobId());
        job.IsCurrentJob = true;
        return job;
    }

    public ArrayList<Job> GetAll(){
        ArrayList<Job> result = dbHelper.getAllJobs();
        Collections.sort(result, new Comparator<Job>() {
            @Override
            public int compare(Job t1, Job t2) {
                return (int)((t2.Score - t1.Score));
            }
        });
        return result;
    }

    public boolean Delete(Integer id){
        boolean result = false;
        int idDeleted = dbHelper.deleteJob(id);
        if(idDeleted > 0){
            result = true;
        }
        return result;
    }

    public void UpdateAllScores(){
        ArrayList<Job> jobs = GetAll();

        for(int i = 0; i < jobs.size(); i++){
            UpdateJobScore(jobs.get(i));
        }
    }

    private Integer GetCurrentJobId(){
        int currentJobId = -1;

        ArrayList<Job> jobs = GetAll();
        for(int i = 0; i < jobs.size(); i++) {
            if(jobs.get(i).IsCurrentJob){
                currentJobId = jobs.get(i).id;
            }
        }

        return currentJobId;
    }

    private float CalculateScore(Job job) {
        ComparisonSettings weights = dbHelper.getSettings();
        float AYS = job.YearlySalary * (100/ job.CostOfLiving);
        int totalWeight = weights.CommuteTimeWeight + weights.YearlySalaryWeight + weights.YearlyBonusWeight + weights.BenefitsWeight + weights.LeaveTimeWeight;
        float score = AYS * weights.YearlySalaryWeight / totalWeight;
        score += job.YearlyBonus * (100/ job.CostOfLiving) * weights.YearlyBonusWeight / totalWeight;
        score += job.RetirementBenefits * AYS * weights.BenefitsWeight / totalWeight;
        score += (job.LeaveTime * AYS /260) * weights.LeaveTimeWeight / totalWeight;
        score -= (job.CommuteTime * AYS/8) * weights.CommuteTimeWeight / totalWeight;

        return score;
    }

    private void UpdateJobScore(Job job){
        job.Score = CalculateScore(job);
        Update(job);
    }
}
