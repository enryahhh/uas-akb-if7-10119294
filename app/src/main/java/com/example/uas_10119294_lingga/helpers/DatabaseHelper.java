package com.example.uas_10119294_lingga.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.uas_10119294_lingga.contract.NotesDBContract.NoteSchema;
import com.example.uas_10119294_lingga.models.Note;

import java.util.ArrayList;
import java.util.List;

/*
 * NIM : 10119294
 * NAMA : Lingga Juliansyah
 * Kelas : IF-7
 * */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NotesDB.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NoteSchema.TABLE_NAME + " (" +
                    NoteSchema._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    NoteSchema.COLUMN_NAME_JUDUL + " TEXT," +
                    NoteSchema.COLUMN_NAME_TANGGAL + " TEXT," +
                    NoteSchema.COLUMN_NAME_KATEGORI + " TEXT," +
                    NoteSchema.COLUMN_NAME_ISI + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NoteSchema.TABLE_NAME;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old, int newVer) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void openDb(){
        db = this.getWritableDatabase();
    }

    public void storeNote(Note note){
        ContentValues cv = new ContentValues();
        cv.put(NoteSchema.COLUMN_NAME_JUDUL, note.getJudul());
        cv.put(NoteSchema.COLUMN_NAME_TANGGAL, note.getTanggal());
        cv.put(NoteSchema.COLUMN_NAME_KATEGORI, note.getIsi());
        cv.put(NoteSchema.COLUMN_NAME_ISI,note.getKategori());
        db.insert(NoteSchema.TABLE_NAME,null,cv);
    }

    public List<Note> getAllNote(){
        List<Note> todosDb = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(NoteSchema.TABLE_NAME, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        Note note = new Note();
                        note.setId(cur.getInt(cur.getColumnIndexOrThrow(BaseColumns._ID)));
                        note.setJudul(cur.getString(cur.getColumnIndexOrThrow(NoteSchema.COLUMN_NAME_JUDUL)));
                        note.setTanggal(cur.getString(cur.getColumnIndexOrThrow(NoteSchema.COLUMN_NAME_TANGGAL)));
                        note.setKategori(cur.getString(cur.getColumnIndexOrThrow(NoteSchema.COLUMN_NAME_KATEGORI)));
                        note.setIsi(cur.getString(cur.getColumnIndexOrThrow(NoteSchema.COLUMN_NAME_ISI)));
                        todosDb.add(note);
                    }
                    while(cur.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            assert cur != null;
            cur.close();
        }
        return todosDb;
    }

    public void updateNote(int id, Note note){
        ContentValues cv = new ContentValues();
        cv.put(NoteSchema.COLUMN_NAME_JUDUL, note.getJudul());
        cv.put(NoteSchema.COLUMN_NAME_TANGGAL, note.getTanggal());
        cv.put(NoteSchema.COLUMN_NAME_KATEGORI, note.getKategori());
        cv.put(NoteSchema.COLUMN_NAME_ISI, note.getIsi());
        db.update(NoteSchema.TABLE_NAME,cv,BaseColumns._ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteNote(int id){
        System.out.println("deleted todo helper : "+BaseColumns._ID);
        db.delete(NoteSchema.TABLE_NAME, BaseColumns._ID + "= ?", new String[] {String.valueOf(id)});
    }
}
