package com.oportunis.gallery;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by User on 11/26/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PhotoBook.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertData(String title, String deskrip, byte[] foto){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO " + DatabaseConstract.MyTable.TABLE_NAME + " VALUES (NULL, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, title);
        statement.bindString(2, deskrip);
        statement.bindBlob(3, foto);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void updateData(String name, String price, byte[] image, int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE " +DatabaseConstract.MyTable.TABLE_NAME+ " SET "+ DatabaseConstract.MyTable.COLUMN_NAMA +" = ?, " +DatabaseConstract.MyTable.COLUMN_DESKRIPSI+" = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, name);
        statement.bindString(2, price);
        statement.bindDouble(4, (double)id);

        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM "+ DatabaseConstract.MyTable.TABLE_NAME +" WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE " +
                DatabaseConstract.MyTable.TABLE_NAME + "(" +
                DatabaseConstract.MyTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DatabaseConstract.MyTable.COLUMN_NAMA + " TEXT NOT NULL," +
                DatabaseConstract.MyTable.COLUMN_DESKRIPSI + " TEXT NOT NULL," +
                DatabaseConstract.MyTable.COLUMN_PHOTO + " BLOB NOT NULL);";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstract.MyTable.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
