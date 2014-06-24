package com.dualnot.ignacio.carboapp.data;

import com.dualnot.ignacio.carboapp.data.AlimentContract.AlimentEntry;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteDbHelper extends SQLiteOpenHelper{
	
	// If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Aliments.db";
    

    public MySQLiteDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    	//db.execSQL(AlimentEntry.SQL_CREATE_ENTRIES);
    	AlimentEntry.onCreate(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
    	Log.w(MySQLiteDbHelper.class.getName(),
    	        "Upgrading database from version " + oldVersion + " to "
    	            + newVersion + ", which will destroy all old data");
    	AlimentEntry.onUpgrade(db, oldVersion, newVersion);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
