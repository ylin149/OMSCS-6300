package edu.gatech.seclass.jobcompare6300.DAL;

import android.provider.BaseColumns;

public final class SettingsContract {
    private SettingsContract() {}

    public static class SettingsEntry implements BaseColumns {
        public static final String TABLE_NAME = "settings";
        public static final String COLUMN_NAME_COMMUTE = "commuteTimeWeight";
        public static final String COLUMN_NAME_SALARY = "salaryWeight";
        public static final String COLUMN_NAME_BONUS = "bonusWeight";
        public static final String COLUMN_NAME_BENEFITS = "benefitsWeight";
        public static final String COLUMN_NAME_LEAVE = "leaveTimeWeight";
    }
}
