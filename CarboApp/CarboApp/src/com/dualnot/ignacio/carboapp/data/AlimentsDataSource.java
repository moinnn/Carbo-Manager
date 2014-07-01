package com.dualnot.ignacio.carboapp.data;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.dualnot.ignacio.carboapp.bean.Aliment;
import com.dualnot.ignacio.carboapp.data.AlimentContract.AlimentEntry;

public class AlimentsDataSource {
	
	//Database fields
	private SQLiteDatabase database;
	private MySQLiteDbHelper dbHelper;
	private String[] allColumns = {
			AlimentEntry._ID,
			AlimentEntry.COLUMN_NAME_ALIMENT_NAME,
			AlimentEntry.COLUMN_NAME_GRAMS_PER_RATION,
			AlimentEntry.COLUMN_NAME_RATIONS_IN_PICTURE
			};
	
	public AlimentsDataSource(Context context) {
		this.dbHelper = new MySQLiteDbHelper(context);
	}
	
	public void open() throws SQLException {
		this.database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		this.dbHelper.close();
	}
	
	public Aliment createAliment(String name, Integer grams_per_ration, Integer rations_in_picture){
		ContentValues values = new ContentValues();
		values.put(AlimentEntry.COLUMN_NAME_ALIMENT_NAME, name);
		values.put(AlimentEntry.COLUMN_NAME_GRAMS_PER_RATION, grams_per_ration);
		values.put(AlimentEntry.COLUMN_NAME_RATIONS_IN_PICTURE, rations_in_picture);
		long insertId = this.database.insert(AlimentEntry.TABLE_NAME, null, values);
		Cursor cursor = this.database.query(
				AlimentEntry.TABLE_NAME, 
				this.allColumns, 
				AlimentEntry._ID + " = " + insertId, 
				null, null, null, null);
		cursor.moveToFirst();
		Aliment newAliment = cursorToAliment(cursor);
		cursor.close();
		return newAliment;
	}
	
	public void deleteAliment(Aliment aliment) {
		long id = aliment.getId();
		System.out.println("Aliment deleted with id: " + id);
		database.delete(AlimentEntry.TABLE_NAME, AlimentEntry._ID + " = " + id, null);
	}
	
	public List<Aliment> getAllAliments(){
		List<Aliment> aliments = new ArrayList<Aliment>();
		Cursor cursor = this.database.query(AlimentEntry.TABLE_NAME, this.allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()){
			Aliment a = cursorToAliment(cursor);
			aliments.add(a);
			cursor.moveToNext();
		}
		cursor.close();
		return aliments;
	}
	
	private Aliment cursorToAliment(Cursor cursor){
		Aliment aliment = new Aliment();
		aliment.setId(cursor.getLong(0));
		aliment.setName(cursor.getString(1));
		aliment.setGrams_per_ration(cursor.getInt(2));
		aliment.setRations_in_picture(cursor.getInt(3));
		return aliment;
	}
	
	

}
