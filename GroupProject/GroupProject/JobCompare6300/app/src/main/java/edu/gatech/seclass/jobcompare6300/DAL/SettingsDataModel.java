package edu.gatech.seclass.jobcompare6300.DAL;

import android.content.Context;

import edu.gatech.seclass.jobcompare6300.Models.ComparisonSettings;

public class SettingsDataModel {
    private JobDbHelper dbHelper;
    private JobDataModel jobDataModel;
    public static final String DATABASE_NAME = "JobComparison.db";

    public SettingsDataModel(Context context){
        dbHelper = new JobDbHelper(context,DATABASE_NAME);
        jobDataModel = new JobDataModel(context);
    }

    public SettingsDataModel(Context context, String databaseName){
        dbHelper = new JobDbHelper(context,databaseName);
        jobDataModel = new JobDataModel(context);
    }

    public boolean Update(ComparisonSettings settings){
        boolean result;
        ComparisonSettings currentSettings = dbHelper.getSettings();
        if(currentSettings.id > 0){
            settings.id = currentSettings.id;
            result = dbHelper.updateSettings(settings);
        }
        else{
            result = dbHelper.insertSettings(settings);
        }
        if(result){
            jobDataModel.UpdateAllScores();
        }
        return result;
    }

    public ComparisonSettings Get(){
        return dbHelper.getSettings();
    }
}
