package com.dualnot.ignacio.carboapp.data;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class AlimentContract {
	
	public AlimentContract(){}
	
	public static abstract class AlimentEntry implements BaseColumns{
		public static final String TABLE_NAME = "aliment";
        public static final String COLUMN_NAME_ALIMENT_NAME = "aliment_name";
        public static final String COLUMN_NAME_GRAMS_PER_RATION = "grams_per_ration";
        public static final String COLUMN_NAME_RATIONS_IN_PICTURE = "rations_in_picture";
        
        private static final String TEXT_TYPE = " TEXT";
        private static final String INTEGER_TYPE = " INT";
        private static final String REAL_TYPE = " REAL";
        private static final String COMMA_SEP = ",";
        private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + AlimentEntry.TABLE_NAME + " (" +
            AlimentEntry._ID + " INTEGER PRIMARY KEY," +
            AlimentEntry.COLUMN_NAME_ALIMENT_NAME + TEXT_TYPE + COMMA_SEP +
            AlimentEntry.COLUMN_NAME_GRAMS_PER_RATION + INTEGER_TYPE + COMMA_SEP +
            AlimentEntry.COLUMN_NAME_RATIONS_IN_PICTURE + INTEGER_TYPE +
            " )";

        private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + AlimentEntry.TABLE_NAME;
        
        public static void onCreate(SQLiteDatabase db) {
        	Log.w(AlimentContract.class.getName(), SQL_CREATE_ENTRIES);
        	db.execSQL(SQL_CREATE_ENTRIES);
        }
        public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // This database is only a cache for online data, so its upgrade policy is
            // to simply to discard the data and start over
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        
	}
	

}
