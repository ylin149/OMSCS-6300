package edu.gatech.seclass.jobcompare6300.DAL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.Models.ComparisonSettings;
import edu.gatech.seclass.jobcompare6300.Models.Job;

public class JobDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static String DATABASE_NAME = "JobComparison.db";

    public JobDbHelper(Context context, String databaseName){
        super(context, databaseName, null, DATABASE_VERSION);
        DATABASE_NAME = databaseName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GetCreateJobQuery());
        db.execSQL(GetCreateSettingsQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE jobs");
        db.execSQL("DROP TABLE settings");
        onCreate(db);
    }

    private String GetCreateJobQuery(){
        String result = "create table " + JobContract.JobEntry.TABLE_NAME + " (";
        result += "id integer primary key";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_TITLE + " text";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_COMPANY + " text";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_LOCATION + " text";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_COSTOFLIVING + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_COMMUTETIME + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_SALARY + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_BONUS + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_BENEFITS + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_LEAVETIME + " integer";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_SCORE + " real";
        result += ", " + JobContract.JobEntry.COLUMN_NAME_CURRENT + " integer";
        result += ")";
        return result;
    }

    public boolean insertJob(Job job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_TITLE, job.Title);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COMPANY, job.Company);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LOCATION, job.Location);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COSTOFLIVING, job.CostOfLiving);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COMMUTETIME, job.CommuteTime);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_SALARY, job.YearlySalary);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_BONUS, job.YearlyBonus);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_BENEFITS, job.RetirementBenefits);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LEAVETIME, job.LeaveTime);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_SCORE, job.Score);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_CURRENT, job.IsCurrentJob);
        db.insert(JobContract.JobEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateJob(Job job){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_TITLE, job.Title);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COMPANY, job.Company);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LOCATION, job.Location);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COSTOFLIVING, job.CostOfLiving);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_COMMUTETIME, job.CommuteTime);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_SALARY, job.YearlySalary);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_BONUS, job.YearlyBonus);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_BENEFITS, job.RetirementBenefits);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_LEAVETIME, job.LeaveTime);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_SCORE, job.Score);
        contentValues.put(JobContract.JobEntry.COLUMN_NAME_CURRENT, job.IsCurrentJob);
        db.update(JobContract.JobEntry.TABLE_NAME, contentValues, "id = ?",new String[] { Integer.toString(job.id) });
        return true;
    }

    public Job getJob(Integer id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + JobContract.JobEntry.TABLE_NAME + " where id="+id+"", null );
        cursor.moveToFirst();

        Job result = new Job();
        if(cursor.isAfterLast() == false){
            result = SetJobProperties(cursor);
        }

        return result;
    }

    public ArrayList<Job> getAllJobs(){
        ArrayList<Job> result = new ArrayList<Job>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + JobContract.JobEntry.TABLE_NAME, null );
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            Job job = SetJobProperties(cursor);
            result.add(job);
            cursor.moveToNext();
        }
        return result;
    }

    public Integer deleteJob(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(JobContract.JobEntry.TABLE_NAME,"id = ? ",new String[] { Integer.toString(id) });
    }

    private Job SetJobProperties(Cursor cursor){
        Job result = new Job();
        result.id  = cursor.getInt(cursor.getColumnIndex("id"));
        result.Title = cursor.getString(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_TITLE));
        result.Company = cursor.getString(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_COMPANY));
        result.Location = cursor.getString(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_LOCATION));
        result.CostOfLiving = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_COSTOFLIVING));
        result.CommuteTime = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_COMMUTETIME));
        result.YearlySalary= cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_SALARY));
        result.YearlyBonus = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_BONUS));
        result.RetirementBenefits = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_BENEFITS));
        result.LeaveTime = cursor.getInt(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_LEAVETIME));
        result.Score = cursor.getFloat(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_SCORE));
        if(cursor.getInt(cursor.getColumnIndex(JobContract.JobEntry.COLUMN_NAME_CURRENT)) >= 1){
            result.IsCurrentJob = true;
        }
        return result;
    }

    private String GetCreateSettingsQuery(){
        String result = "create table " + SettingsContract.SettingsEntry.TABLE_NAME + " (";
        result += "id integer primary key";
        result += ", " + SettingsContract.SettingsEntry.COLUMN_NAME_COMMUTE+ " integer";
        result += ", " + SettingsContract.SettingsEntry.COLUMN_NAME_SALARY+ " integer";
        result += ", " + SettingsContract.SettingsEntry.COLUMN_NAME_BONUS+ " integer";
        result += ", " + SettingsContract.SettingsEntry.COLUMN_NAME_BENEFITS+ " integer";
        result += ", " + SettingsContract.SettingsEntry.COLUMN_NAME_LEAVE+ " integer";
        result += ")";
        return result;
    }

    public boolean insertSettings(ComparisonSettings settings){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_COMMUTE, settings.CommuteTimeWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_SALARY, settings.YearlySalaryWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_BONUS, settings.YearlyBonusWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_BENEFITS, settings.BenefitsWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_LEAVE, settings.LeaveTimeWeight);
        db.insert(SettingsContract.SettingsEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateSettings(ComparisonSettings settings){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_COMMUTE, settings.CommuteTimeWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_SALARY, settings.YearlySalaryWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_BONUS, settings.YearlyBonusWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_BENEFITS, settings.BenefitsWeight);
        contentValues.put(SettingsContract.SettingsEntry.COLUMN_NAME_LEAVE, settings.LeaveTimeWeight);
        db.update(SettingsContract.SettingsEntry.TABLE_NAME, contentValues, "id = ?",new String[] { Integer.toString(1) });
        return true;
    }

    public ComparisonSettings getSettings(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + SettingsContract.SettingsEntry.TABLE_NAME + " where id="+1+"", null );
        cursor.moveToFirst();

        ComparisonSettings result = new ComparisonSettings();
        if(cursor.isAfterLast() == false){
            result.CommuteTimeWeight = cursor.getInt(cursor.getColumnIndex(SettingsContract.SettingsEntry.COLUMN_NAME_COMMUTE));
            result.YearlySalaryWeight = cursor.getInt(cursor.getColumnIndex(SettingsContract.SettingsEntry.COLUMN_NAME_SALARY));
            result.YearlyBonusWeight = cursor.getInt(cursor.getColumnIndex(SettingsContract.SettingsEntry.COLUMN_NAME_BONUS));
            result.BenefitsWeight = cursor.getInt(cursor.getColumnIndex(SettingsContract.SettingsEntry.COLUMN_NAME_BENEFITS));
            result.LeaveTimeWeight = cursor.getInt(cursor.getColumnIndex(SettingsContract.SettingsEntry.COLUMN_NAME_LEAVE));
            result.id = cursor.getInt(cursor.getColumnIndex("id"));
        }

        return result;
    }
}
