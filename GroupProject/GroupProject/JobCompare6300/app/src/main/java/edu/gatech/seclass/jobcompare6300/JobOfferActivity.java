package edu.gatech.seclass.jobcompare6300;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.DAL.JobDataModel;
import edu.gatech.seclass.jobcompare6300.Models.Job;

public class JobOfferActivity  extends AppCompatActivity {
    public EditText inputTitleV ;
    public EditText inputCompanyV;
    public EditText inputLocationV ;
    public EditText inputCostOfLivingV;
    public EditText inputCommuteTimeV;
    public EditText inputSalaryV;
    public EditText inputBonusV;
    public EditText inputRetirementV;
    public EditText inputLeaveV;
    Job Job = new Job();
    JobDataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offer_details);

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
        dataModel = new JobDataModel(this);

        //POPULATE Job for edit
        Job = new Job();

        Bundle b = getIntent().getExtras();
        if(b!= null){
            int id = b.getInt("id");
            if(id > 0){
                Job = dataModel.Get(id);
            }
        }

        viewCurrentJob();
    }

    protected void viewCurrentJob(){
        //view
        inputTitleV.setText(Job.Title);
        inputCompanyV.setText(Job.Company);
        inputLocationV.setText(Job.Location);
        inputCostOfLivingV.setText(String.valueOf(Job.CostOfLiving));
        inputCommuteTimeV.setText(String.valueOf(Job.CommuteTime));
        inputSalaryV.setText(String.valueOf(Job.YearlySalary));
        inputBonusV.setText(String.valueOf(Job.YearlyBonus));
        inputRetirementV.setText(String.valueOf(Job.RetirementBenefits));
        inputLeaveV.setText(String.valueOf(Job.LeaveTime));
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
            Job.Title = inputTitleV.getText().toString();
            Job.Company = inputCompanyV.getText().toString();
            Job.Location = inputLocationV.getText().toString();
            Job.CostOfLiving = Float.parseFloat(inputCostOfLivingV.getText().toString());
            Job.CommuteTime = Float.parseFloat(inputCommuteTimeV.getText().toString());
            Job.YearlySalary = Float.parseFloat(inputSalaryV.getText().toString());
            Job.YearlyBonus = Float.parseFloat(inputBonusV.getText().toString());
            Job.RetirementBenefits = Float.parseFloat(inputRetirementV.getText().toString());
            Job.LeaveTime = Integer.parseInt(inputLeaveV.getText().toString());
            boolean result = false;
            if (Job.id == 0) {
                result = dataModel.Add(Job);
            } else {
                result = dataModel.Update(Job);
            }
            //TODO Show error based on result
            finish();
        }
    }

    public void returnToMainMenu(View view) {
        finish();
    }
}
