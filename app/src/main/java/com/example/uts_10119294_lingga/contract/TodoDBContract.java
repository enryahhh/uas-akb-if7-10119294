package com.example.uts_10119294_lingga.contract;

import android.provider.BaseColumns;

public final class TodoDBContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private TodoDBContract() {}

        /* Inner class that defines the table contents */
        public static class TodoSchema implements BaseColumns {
            public static final String TABLE_NAME = "todos";
            public static final String COLUMN_NAME_TODO = "todo";
            public static final String COLUMN_NAME_TANGGAL = "tanggal";
            public static final String COLUMN_NAME_DESCRIPTION = "description";
            public static final String COLUMN_NAME_ISDONE = "isdone";
        }
    }

