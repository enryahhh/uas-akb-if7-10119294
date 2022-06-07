package com.example.uts_10119294_lingga.contract;

import android.provider.BaseColumns;
/*
 * NIM : 10119294
 * NAMA : Lingga Juliansyah
 * Kelas : IF-7
 * */
public final class NotesDBContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private NotesDBContract() {}

        /* Inner class that defines the table contents */
        public static class NoteSchema implements BaseColumns {
            public static final String TABLE_NAME = "todos";
            public static final String COLUMN_NAME_JUDUL = "judul";
            public static final String COLUMN_NAME_TANGGAL = "tanggal";
            public static final String COLUMN_NAME_KATEGORI = "kategori";
            public static final String COLUMN_NAME_ISI = "isi";
        }
    }

