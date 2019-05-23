package com.recyclerview.example;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {


    public static final int DbVersion = 2;
    public static final String DbName = "Test.db";

    public static final String productTable = "ProductTable";
    public static final String productName = "ProductName";
    public static final String productId = "ProductId";
    public static final String isFavorite = "isFavorite";



    public static final String PersonTable = "PersonTable";
    public static final String PersonName = "PersonName";
    public static final String PersonId = "PersonId";



    public DbHelper(Context context) {
        super(context, DbName, null, DbVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQlCategoryTable = "create table "
                + productTable
                + " ("
                + productId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + productName + " TEXT NOT NULL,"
                + isFavorite + " INTEGER NOT NULL"
                + " )";

        String SQlCategoryTable2 = "create table "
                + PersonTable
                + " ("
                + PersonId + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PersonName + " TEXT NOT NULL"

                + ")";

        db.execSQL(SQlCategoryTable);
        db.execSQL(SQlCategoryTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertProduct(String productNameString, int isFavoriteInt) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(productName, productNameString);
        values.put(isFavorite, isFavoriteInt);

        long result = db.insert(productTable, null, values);
        Log.d("productIdDB", "" + result);
        db.close();

        return result;


    }
    public long insertPerson(String  Name  ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PersonName, Name);


        long result = db.insert( PersonTable, null, values);

        db.close();

        return result;


    }
    public void updateFavorite(int id, int favorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(isFavorite, favorite);

        db.update(productTable, values, productId + " = ?", new String[]{id + ""});
    }


    public void deletProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(productTable, productId + " = ? ", new String[]{id + ""});
    }


    public void deletPerson(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete( PersonTable,  PersonId + " = ? ", new String[]{id + ""});
    }
}
