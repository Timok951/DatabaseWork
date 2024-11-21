package com.example.databasework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "book_db";
    private static final int SCHEMA = 1;


    static final String TABLE_NAME = "books";
    public static final String COLUMN_ID = "id_book";
    public static final String COLUMN_NAME = "book_name";
    public static final String COLUMN_AUTHOR = "book_author";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    public Cursor getBookById(int bookId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = {String.valueOf(bookId)};
        return db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_AUTHOR + " TEXT)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
    public long addBook(String bookName, String bookAuthor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, bookName);
        values.put(COLUMN_AUTHOR, bookAuthor);

        // Исправленный вызов insert() с корректным синтаксисом
        long result = db.insert(TABLE_NAME, null, values);

        db.close();
        return result;
    }
    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, null,  null, null,  null, null,  null);
    }

    public int deleteBookById(long bookId) {
        SQLiteDatabase db = this.getWritableDatabase();

        String whereClause = COLUMN_ID + " = ?";

        String[] whereArgs = {String.valueOf(bookId)};

        int result = db.delete(TABLE_NAME, whereClause, whereArgs);


        db.close();

        return result;
    }



}
