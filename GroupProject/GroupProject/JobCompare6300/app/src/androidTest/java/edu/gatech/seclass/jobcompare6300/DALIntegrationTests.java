package edu.gatech.seclass.jobcompare6300;
import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.DAL.JobContract;
import edu.gatech.seclass.jobcompare6300.DAL.JobDataModel;
import edu.gatech.seclass.jobcompare6300.DAL.SettingsContract;
import edu.gatech.seclass.jobcompare6300.DAL.SettingsDataModel;
import edu.gatech.seclass.jobcompare6300.Models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.Models.Job;

@RunWith(AndroidJUnit4.class)
public class DALIntegrationTests {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testJobSave(){
        Context context = mActivityRule.getActivity().getBaseContext();
        context.deleteDatabase("TestDataBase.db");
        JobDataModel dataModel = new JobDataModel(context,"TestDataBase.db");
        Job job = new Job();
        job.Title = "TEST";
        job.Company = "Comp";
        job.Location = "loc";
        job.CostOfLiving = 1;
        job.CommuteTime = 2;
        job.YearlySalary= 3;
        job.YearlyBonus = 4;
        job.RetirementBenefits = 5;
        job.LeaveTime = 6;

        SettingsDataModel settingModel = new SettingsDataModel(context,"TestDataBase.db");
        ComparisonSettings settings = new ComparisonSettings();
        settings.CommuteTimeWeight = 1;
        settings.YearlySalaryWeight = 2;
        settings.YearlyBonusWeight = 3;
        settings.BenefitsWeight = 4;
        settings.LeaveTimeWeight = 5;
        boolean settingsSuccess = settingModel.Update(settings);

        boolean success = dataModel.Add(job);
        ArrayList<Job> jobs = dataModel.GetAll();
        Assert.assertEquals(1, jobs.size());
        Assert.assertEquals("TEST",jobs.get(0).Title);
        Assert.assertEquals("Comp",jobs.get(0).Company );
        Assert.assertEquals("loc",jobs.get(0).Location);
        Assert.assertEquals(1,jobs.get(0).CostOfLiving,0);
        Assert.assertEquals(2,jobs.get(0).CommuteTime,0);
        Assert.assertEquals(3,jobs.get(0).YearlySalary,0);
        Assert.assertEquals(4,jobs.get(0).YearlyBonus,0);
        Assert.assertEquals(5,jobs.get(0).RetirementBenefits,0);
        Assert.assertEquals(6,jobs.get(0).LeaveTime);

        //Score
        float score = job.YearlySalary* (100/job.CostOfLiving) * settings.YearlySalaryWeight / 15;
        score = score + job.YearlyBonus * (100/job.CostOfLiving) * settings.YearlyBonusWeight / 15;
        score = score + job.RetirementBenefits * job.YearlySalary* (100/job.CostOfLiving) * settings.BenefitsWeight / 15;
        score = score + (job.LeaveTime * job.YearlySalary* (100/job.CostOfLiving) /260) * settings.LeaveTimeWeight / 15;
        score = score - (job.CommuteTime * job.YearlySalary* (100/job.CostOfLiving)/8) * settings.CommuteTimeWeight / 15;
        Assert.assertEquals(score,jobs.get(0).Score,1);

    }

    @Test
    public void testJobSaveCurrent(){
        Context context = mActivityRule.getActivity().getBaseContext();
        context.deleteDatabase("TestDataBase.db");
        JobDataModel dataModel = new JobDataModel(context,"TestDataBase.db");
        Job job = new Job();
        job.Title = "TESTC";
        job.IsCurrentJob = true;
        Job job2 = new Job();
        job2.Title = "TEST2";
        dataModel.Add(job2);

        boolean success = dataModel.Add(job);
        Job currentJob = dataModel.GetCurrentJob();
        Assert.assertEquals(currentJob.IsCurrentJob,true);
        Assert.assertEquals(job.Title,currentJob.Title);
    }

    @Test
    public void testDeleteJob(){
        Context context = mActivityRule.getActivity().getBaseContext();
        context.deleteDatabase("TestDataBase.db");
        JobDataModel dataModel = new JobDataModel(context,"TestDataBase.db");
        Job job = new Job();
        job.Title = "TEST";

        boolean success = dataModel.Add(job);
        ArrayList<Job> jobs1 = dataModel.GetAll();
        dataModel.Delete(jobs1.get(0).id);
        ArrayList<Job> jobs2 = dataModel.GetAll();

        Assert.assertEquals(jobs1.size(),1);
        Assert.assertEquals(jobs2.size(),0);
    }

    @Test
    public void testJobGet(){
        Context context = mActivityRule.getActivity().getBaseContext();
        context.deleteDatabase("TestDataBase.db");
        JobDataModel dataModel = new JobDataModel(context,"TestDataBase.db");
        Job job = new Job();
        job.Title = "TEST";
        boolean success = dataModel.Add(job);
        Job job2 = new Job();
        job2.Title = "TEST2";
        dataModel.Add(job2);

        ArrayList<Job> jobs = dataModel.GetAll();
        Job getJob = dataModel.Get(jobs.get(0).id);

        Assert.assertEquals(jobs.size(),2);
        Assert.assertEquals(job.Title,getJob.Title);
    }

    @Test
    public void testJobScore(){
        Context context = mActivityRule.getActivity().getBaseContext();
        context.deleteDatabase("TestDataBase.db");
        SettingsDataModel dataModel = new SettingsDataModel(context,"TestDataBase.db");
        ComparisonSettings settings = new ComparisonSettings();
        settings.CommuteTimeWeight = 1;
        settings.YearlySalaryWeight = 2;
        settings.YearlyBonusWeight = 1;
        settings.BenefitsWeight = 2;
        settings.LeaveTimeWeight = 1;

        dataModel.Update(settings);
        JobDataModel jobDataModel = new JobDataModel(context,"TestDataBase.db");
        Job job = new Job();
        job.Title = "TEST";
        job.Company = "Comp";
        job.Location = "loc";
        job.CostOfLiving = 110;
        job.CommuteTime = 2;
        job.YearlySalary= 100000;
        job.YearlyBonus = 20000;
        job.RetirementBenefits = (float).05;
        job.LeaveTime = 10;
        boolean success = jobDataModel.Add(job);
        ArrayList<Job> jobs = jobDataModel.GetAll();

        Assert.assertEquals(27122.9,jobs.get(0).Score,2);
    }

    @Test
    public void testSettingsSave(){
        Context context = mActivityRule.getActivity().getBaseContext();
        context.deleteDatabase("TestDataBase.db");
        SettingsDataModel dataModel = new SettingsDataModel(context,"TestDataBase.db");
        ComparisonSettings settings = new ComparisonSettings();
        settings.CommuteTimeWeight = 1;
        settings.YearlySalaryWeight = 2;
        settings.YearlyBonusWeight = 3;
        settings.BenefitsWeight = 4;
        settings.LeaveTimeWeight = 5;

        boolean success = dataModel.Update(settings);
        ComparisonSettings getSettings = dataModel.Get();
        Assert.assertEquals(getSettings.CommuteTimeWeight,1);
        Assert.assertEquals(getSettings.YearlySalaryWeight,2);
        Assert.assertEquals(getSettings.YearlyBonusWeight,3);
        Assert.assertEquals(getSettings.BenefitsWeight,4);
        Assert.assertEquals(getSettings.LeaveTimeWeight,5);
    }

    @Test
    public void testSettingsGetInitial() {
        Context context = mActivityRule.getActivity().getBaseContext();
        context.deleteDatabase("TestDataBase.db");
        SettingsDataModel dataModel = new SettingsDataModel(context, "TestDataBase.db");
        ComparisonSettings getSettings = dataModel.Get();
        Assert.assertEquals(getSettings.CommuteTimeWeight,1);
        Assert.assertEquals(getSettings.YearlySalaryWeight,1);
        Assert.assertEquals(getSettings.YearlyBonusWeight,1);
        Assert.assertEquals(getSettings.BenefitsWeight,1);
        Assert.assertEquals(getSettings.LeaveTimeWeight,1);
    }
}
