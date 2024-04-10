package com.example.log;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

    static String DATABASE_NAME = "UserDataBase";
    public static final String TABLE_NAME = "UserTable";
    public static final String Table_Column_ID = "id";
    public static final String Table_Column_1_Name = "name";
    public static final String Table_Column_2_Email = "email";
    public static final String Table_Column_3_Password = "password";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + Table_Column_ID + " INTEGER PRIMARY KEY, "
                + Table_Column_1_Name + " VARCHAR, " + Table_Column_2_Email + " VARCHAR, " + Table_Column_3_Password + " VARCHAR)";
        database.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void viewData() {
        SQLiteDatabase database = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(Table_Column_ID));
                String name = cursor.getString(cursor.getColumnIndex(Table_Column_1_Name));
                String email = cursor.getString(cursor.getColumnIndex(Table_Column_2_Email));
                String password = cursor.getString(cursor.getColumnIndex(Table_Column_3_Password));

                Log.d("UserData", "ID: " + id + ", Name: " + name + ", Email: " + email + ", Password: " + password);
            } while (cursor.moveToNext());
        }

        cursor.close();
    }
}