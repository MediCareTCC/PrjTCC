package com.example.prjtcc;

import android.provider.BaseColumns;

public class AlarmContract {

    private AlarmContract(){}

    public static final class AlarmEntry implements BaseColumns {
        public static final String TABLE_NAME = "alarmList";
        public static final String COLUMN_MEDICINE = "medicine";
        public static final String COLUMN_SCHEDULE = "schedule";
        public static final String COLUMN_DOSE = "dosage";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
