package com.lateofrederick.todoish;

import android.provider.BaseColumns;

public class TodoContract {
    private TodoContract() {}

    public static class TodoEntry implements BaseColumns {
        public static  final String TABLE_NAME = "todos";
        public static final String COLUMN_NAME_FNAME = "firstname";
        public static final String COLUMN_NAME_LNAME = "lastname";
        public static final String COLUMN_NAME_TELE = "telephone";
    }


}
