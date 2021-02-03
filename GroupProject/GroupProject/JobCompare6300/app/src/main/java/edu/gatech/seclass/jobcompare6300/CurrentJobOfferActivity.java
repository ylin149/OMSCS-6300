package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.DAL.JobDataModel;
import edu.gatech.seclass.jobcompare6300.Models.Job;

public class CurrentJobOfferActivity extends AppCompatActivity {
    //public because who cares.
    public EditText inputTitleV ;
    public EditText inputCompanyV;
    public EditText inputLocationV ;
    public EditText inputCostOfLivingV;
    public EditText inputCommuteTimeV;
    public EditText inputSalaryV;
    public EditText inputBonusV;
    public EditText inputRetirementV;
    public EditText inputLeaveV;
    Job CurrentJob = new Job();
    JobDataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job_details);

        //Take input and save it to a new job object?
        inputTitleV = (EditText) findViewById(R.id.editTitle);
        inputCompanyV = (EditText) findViewById(R.id.editCompany);
        inputLocationV = (EditText) findViewById(R.id.editLocation);
        inputCostOfLivingV = (EditText) findViewById(R.id.editCostOfLiving);
        inputCommuteTimeV = (EditText) findViewById(R.id.editCommuteTime);
        inputSalaryV = (EditText) findViewById(R.id.editYearlySalary);
        inputBonusV = (EditText) findViewById(R.id.editYearlyBonus);
        inputRetirementV = (EditText) findViewById(R.id.editRetirementBenefits);
        inputLeaveV = (EditText) findViewById(R.id.editLeaveTime);

        //POPULATE CurrentJob
        dataModel = new JobDataModel(this);
        CurrentJob = dataModel.GetCurrentJob();
        viewCurrentJob();
    }

    protected void viewCurrentJob(){
        //view
        inputTitleV.setText(CurrentJob.Title);
        inputCompanyV.setText(CurrentJob.Company);
        inputLocationV.setText(CurrentJob.Location);
        inputCostOfLivingV.setText(String.valueOf(CurrentJob.CostOfLiving));
        inputCommuteTimeV.setText(String.valueOf(CurrentJob.CommuteTime));
        inputSalaryV.setText(String.valueOf(CurrentJob.YearlySalary));
        inputBonusV.setText(String.valueOf(CurrentJob.YearlyBonus));
        inputRetirementV.setText(String.valueOf(CurrentJob.RetirementBenefits));
        inputLeaveV.setText(String.valueOf(CurrentJob.LeaveTime));
    }

    public void saveCurrentJob(View view){

        boolean titleTest = true;
        boolean companyTest = true;
        boolean locationTest = true;
        boolean colTest = true;
        boolean comTest = true;
        boolean ysTest = true;
        boolean ybTest = true;
        boolean rbTest = true;
        boolean ltTest = true;

        if(inputTitleV.getText().toString().equals("")){
            titleTest = false;
            inputTitleV.setError("Required Value");
        }
        if(inputCompanyV.getText().toString().equals("")){
            companyTest = false;
            inputCompanyV.setError("Required Value");
        }
        if(inputLocationV.getText().toString().equals("")){
            locationTest = false;
            inputLocationV.setError("Required Value");
        }
        if(inputCostOfLivingV.getText().toString().equals("")){
            colTest = false;
            inputCostOfLivingV.setError("Required Value");
        }
        if(inputCommuteTimeV.getText().toString().equals("")){
            comTest = false;
            inputCommuteTimeV.setError("Required Value");
        }
        if(inputSalaryV.getText().toString().equals("")){
            ysTest = false;
            inputSalaryV.setError("Required Value");
        }
        if(inputBonusV.getText().toString().equals("")){
            ybTest = false;
            inputBonusV.setError("Required Value");
        }
        if(inputRetirementV.getText().toString().equals("")){
            rbTest = false;
            inputRetirementV.setError("Required Value");
        }
        if(inputLeaveV.getText().toString().equals("")){
            ltTest = false;
            inputLeaveV.setError("Required Value");
        }

        if(titleTest && companyTest && locationTest && colTest && comTest  && ysTest  && ybTest  && rbTest && ltTest) {
            //save
            CurrentJob.Title = inputTitleV.getText().toString();
            CurrentJob.Company = inputCompanyV.getText().toString();
            CurrentJob.Location = inputLocationV.getText().toString();
            CurrentJob.CostOfLiving = Float.parseFloat(inputCostOfLivingV.getText().toString());
            CurrentJob.CommuteTime = Float.parseFloat(inputCommuteTimeV.getText().toString());
            CurrentJob.YearlySalary = Float.parseFloat(inputSalaryV.getText().toString());
            CurrentJob.YearlyBonus = Float.parseFloat(inputBonusV.getText().toString());
            CurrentJob.RetirementBenefits = Float.parseFloat(inputRetirementV.getText().toString());
            CurrentJob.LeaveTime = Integer.parseInt(inputLeaveV.getText().toString());
            CurrentJob.IsCurrentJob = true;
            boolean result = false;
            if (CurrentJob.id == 0) {
                result = dataModel.Add(CurrentJob);
            } else {
                result = dataModel.Update(CurrentJob);
            }
            //TODO Show error based on result
            finish();
        }
    }

    public void returnToMainMenu(View view) {
        finish();
    }
}