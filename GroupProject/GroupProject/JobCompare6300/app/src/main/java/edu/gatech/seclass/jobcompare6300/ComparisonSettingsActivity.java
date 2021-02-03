package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.gatech.seclass.jobcompare6300.DAL.SettingsDataModel;
import edu.gatech.seclass.jobcompare6300.Models.ComparisonSettings;

public class ComparisonSettingsActivity extends AppCompatActivity {

    private Button btnSetSettings;
    private SharedPreferences prefStore;
    private TextView textSettingsDisplay;
    private SettingsDataModel settingsDataModel;
    private ComparisonSettings comparisonSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_comparison_settings_launcher);
        comparisonSettings = new ComparisonSettings();
        settingsDataModel = new SettingsDataModel(this);
        btnSetSettings = (Button) findViewById(R.id.btnSetSettings);
        prefStore = PreferenceManager.getDefaultSharedPreferences(this);
        textSettingsDisplay = (TextView) findViewById(R.id.textViewSettings);
        addSettingsClick();

    }

    @Override
    protected void onResume() {
        super.onResume();
        saveSettingsStoreIntoDB();
        displayComparisonSettings();
    }

    private void addSettingsClick() {
        btnSetSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            displayComparisonSettings();
        }
    }

    private void saveSettingsStoreIntoDB() {
        comparisonSettings.CommuteTimeWeight = Integer.valueOf(prefStore.getString("prefCommute", "1"));
        comparisonSettings.YearlySalaryWeight = Integer.valueOf(prefStore.getString("prefYearlySalary", "1"));
        comparisonSettings.YearlyBonusWeight = Integer.valueOf(prefStore.getString("prefYearlyBonus", "1"));
        comparisonSettings.BenefitsWeight = Integer.valueOf(prefStore.getString("prefRetBenefit", "1"));
        comparisonSettings.LeaveTimeWeight = Integer.valueOf(prefStore.getString("prefLeave", "1"));
        settingsDataModel.Update(comparisonSettings);
    }

    private void displayComparisonSettings() {
        comparisonSettings = settingsDataModel.Get();
        StringBuilder sbSettings = new StringBuilder();
        sbSettings.append("Commute Time: " + comparisonSettings.CommuteTimeWeight);
        sbSettings.append("\nYearly Salary:" + comparisonSettings.YearlySalaryWeight);
        sbSettings.append("\nYearly Bonus: " + comparisonSettings.YearlyBonusWeight);
        sbSettings.append("\nRetirement benefits: " + comparisonSettings.BenefitsWeight);
        sbSettings.append("\nLeave time: " + comparisonSettings.LeaveTimeWeight);
        textSettingsDisplay.setText(sbSettings);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.comparison_settings_menu, menu);
        return true;
    }

    public void returnToMainMenu(MenuItem item) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(i, 1);
    }
}