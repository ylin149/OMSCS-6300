package edu.gatech.seclass.jobcompare6300.DAL;

import android.provider.BaseColumns;

public final class JobContract {
    private JobContract() {}

    public static class JobEntry implements BaseColumns {
        public static final String TABLE_NAME = "jobs";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_COMPANY = "company";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_COSTOFLIVING = "costOfLiving";
        public static final String COLUMN_NAME_COMMUTETIME = "commuteTime";
        public static final String COLUMN_NAME_SALARY = "salary";
        public static final String COLUMN_NAME_BONUS = "bonus";
        public static final String COLUMN_NAME_BENEFITS = "benefits";
        public static final String COLUMN_NAME_LEAVETIME = "leaveTime";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_CURRENT = "isCurrentJob";

    }
}
