package com.example.uts_10119294_lingga.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

import com.example.uts_10119294_lingga.contract.TodoDBContract;
import com.example.uts_10119294_lingga.contract.TodoDBContract.TodoSchema;
import com.example.uts_10119294_lingga.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TodoDB.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TodoSchema.TABLE_NAME + " (" +
                    TodoSchema._ID + " INTEGER PRIMARY KEY," +
                    TodoSchema.COLUMN_NAME_TODO + " TEXT," +
                    TodoSchema.COLUMN_NAME_TANGGAL + " TEXT," +
                    TodoSchema.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    TodoSchema.COLUMN_NAME_ISDONE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TodoSchema.TABLE_NAME;
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

    public void storeTodo(Todo todo){
        ContentValues cv = new ContentValues();
        cv.put(TodoSchema.COLUMN_NAME_TODO,todo.getTodo());
        cv.put(TodoSchema.COLUMN_NAME_TANGGAL,todo.getTanggal());
        cv.put(TodoSchema.COLUMN_NAME_DESCRIPTION,todo.getDescription());
        cv.put(TodoSchema.COLUMN_NAME_ISDONE,0);
        db.insert(TodoSchema.TABLE_NAME,null,cv);
    }

    public List<Todo> getAllTodo(){
        List<Todo> todosDb = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try{
            cur = db.query(TodoSchema.TABLE_NAME, null, null, null, null, null, null, null);
            if(cur != null){
                if(cur.moveToFirst()){
                    do{
                        Todo todo = new Todo();
                        todo.setId(cur.getInt(cur.getColumnIndexOrThrow(BaseColumns._ID)));
                        todo.setTodo(cur.getString(cur.getColumnIndexOrThrow(TodoSchema.COLUMN_NAME_TODO)));
                        todo.setTanggal(cur.getString(cur.getColumnIndexOrThrow(TodoSchema.COLUMN_NAME_TANGGAL)));
                        todo.setDescription(cur.getString(cur.getColumnIndexOrThrow(TodoSchema.COLUMN_NAME_DESCRIPTION)));
                        todo.setDone(cur.getColumnIndexOrThrow(TodoSchema.COLUMN_NAME_ISDONE));
                        todosDb.add(todo);
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

    public void updateTodo(int id,Todo todo){
        ContentValues cv = new ContentValues();
        cv.put(TodoSchema.COLUMN_NAME_TODO,todo.getTodo());
        cv.put(TodoSchema.COLUMN_NAME_DESCRIPTION,todo.getDescription());
        db.update(TodoSchema.TABLE_NAME,cv,BaseColumns._ID + "= ?", new String[] {String.valueOf(id)});
    }

    public void deleteTodo(int id){
        System.out.println("deleted todo helper : "+BaseColumns._ID);
        db.delete(TodoSchema.TABLE_NAME, BaseColumns._ID + "= ?", new String[] {String.valueOf(id)});
    }
}
