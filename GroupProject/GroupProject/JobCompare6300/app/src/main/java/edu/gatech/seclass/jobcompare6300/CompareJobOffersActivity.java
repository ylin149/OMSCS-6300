package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.DAL.JobDataModel;
import edu.gatech.seclass.jobcompare6300.Models.Job;

public class CompareJobOffersActivity extends AppCompatActivity {

    Job offer1 = new Job();
    Job offer2 = new Job();
    public TextView inputTitleV1;
    public TextView inputScoreV1;
    public TextView inputCompanyV1;
    public TextView inputLocationV1;
    public TextView inputCostOfLivingV1;
    public TextView inputCommuteTimeV1;
    public TextView inputSalaryV1;
    public TextView inputBonusV1;
    public TextView inputRetirementV1;
    public TextView inputLeaveV1;
    public TextView inputTitleV2;
    public TextView inputScoreV2;
    public TextView inputCompanyV2;
    public TextView inputLocationV2;
    public TextView inputCostOfLivingV2;
    public TextView inputCommuteTimeV2;
    public TextView inputSalaryV2;
    public TextView inputBonusV2;
    public TextView inputRetirementV2;
    public TextView inputLeaveV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offers_comparison_tables);
        initializeFields();
        getData();
    }

    private void getData() {
        Bundle b = getIntent().getExtras();
        JobDataModel dataModel = new JobDataModel(this);
        if (b != null) {
            int id1 = b.getInt("job1id");
            Job offerById1 = null;
            Job offerById2 = null;
            if (id1 > 0) {
                offerById1 = dataModel.Get(id1);
            }
            int id2 = b.getInt("job2id");
            if (id2 > 0) {
                offerById2 = dataModel.Get(id2);
            }
            if (offerById1 != null && offerById2 != null) {
                float scoreById1 = offerById1.Score;
                float scoreById2 = offerById2.Score;
                offer1 = (scoreById1 > scoreById2) ? offerById1 : offerById2;
                offer2 = (scoreById1 <= scoreById2) ? offerById1 : offerById2;

            }
        }
        setFields();
    }

    private void initializeFields() {
        inputTitleV1 = (TextView) findViewById(R.id.offer1Title);
        inputScoreV1 = (TextView) findViewById(R.id.offer1Score);
        inputCompanyV1 = (TextView) findViewById(R.id.offer1Company);
        inputLocationV1 = (TextView) findViewById(R.id.offer1Location);
        inputCostOfLivingV1 = (TextView) findViewById(R.id.offer1CostOfLiving);
        inputCommuteTimeV1 = (TextView) findViewById(R.id.offer1CommuteTime);
        inputSalaryV1 = (TextView) findViewById(R.id.offer1YearlySalary);
        inputBonusV1 = (TextView) findViewById(R.id.offer1YearlyBonus);
        inputRetirementV1 = (TextView) findViewById(R.id.offer1Retirement);
        inputLeaveV1 = (TextView) findViewById(R.id.offer1LeaveTime);
        inputTitleV2 = (TextView) findViewById(R.id.offer2Title);
        inputScoreV2 = (TextView) findViewById(R.id.offer2Score);
        inputCompanyV2 = (TextView) findViewById(R.id.offer2Company);
        inputLocationV2 = (TextView) findViewById(R.id.offer2Location);
        inputCostOfLivingV2 = (TextView) findViewById(R.id.offer2CostOfLiving);
        inputCommuteTimeV2 = (TextView) findViewById(R.id.offer2CommuteTime);
        inputSalaryV2 = (TextView) findViewById(R.id.offer2YearlySalary);
        inputBonusV2 = (TextView) findViewById(R.id.offer2YearlyBonus);
        inputRetirementV2 = (TextView) findViewById(R.id.offer2Retirement);
        inputLeaveV2 = (TextView) findViewById(R.id.offer2LeaveTime);
    }

    private void setFields() {
        //job1
        inputTitleV1.setText(inputTitleV1.getText() + offer1.Title);
        inputScoreV1.setText(inputScoreV1.getText() + String.format("%.2f", offer1.Score));
        inputCompanyV1.setText(inputCompanyV1.getText() + offer1.Company);
        inputLocationV1.setText(inputLocationV1.getText() + offer1.Location);
        inputCostOfLivingV1.setText(inputCostOfLivingV1.getText() + Float.toString(offer1.CostOfLiving));
        inputCommuteTimeV1.setText(inputCommuteTimeV1.getText() + Float.toString(offer1.CommuteTime));
        inputSalaryV1.setText(inputSalaryV1.getText() + Float.toString(offer1.YearlySalary));
        inputBonusV1.setText(inputBonusV1.getText() + Float.toString(offer1.YearlyBonus));
        inputRetirementV1.setText(inputRetirementV1.getText() + Float.toString(offer1.RetirementBenefits));
        inputLeaveV1.setText(inputLeaveV1.getText() + Integer.toString(offer1.LeaveTime));
        //job2
        inputTitleV2.setText(inputTitleV2.getText() + offer2.Title);
        inputScoreV2.setText(inputScoreV2.getText() + String.format("%.2f", offer2.Score));
        inputCompanyV2.setText(inputCompanyV2.getText() + offer2.Company);
        inputLocationV2.setText(inputLocationV2.getText() + offer2.Location);
        inputCostOfLivingV2.setText(inputCostOfLivingV2.getText() + Float.toString(offer2.CostOfLiving));
        inputCommuteTimeV2.setText(inputCommuteTimeV2.getText() + Float.toString(offer2.CommuteTime));
        inputSalaryV2.setText(inputSalaryV2.getText() + Float.toString(offer2.YearlySalary));
        inputBonusV2.setText(inputBonusV2.getText() + Float.toString(offer2.YearlyBonus));
        inputRetirementV2.setText(inputRetirementV2.getText() + Float.toString(offer2.RetirementBenefits));
        inputLeaveV2.setText(inputLeaveV2.getText() + Integer.toString(offer2.LeaveTime));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.comparison_menu, menu);
        return true;
    }

    public void performAnotherComparison(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), JobComparisonSelectionActivity.class);
        startActivityForResult(i, 1);
    }

    public void returnToMainMenu(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(i, 1);
    }
}