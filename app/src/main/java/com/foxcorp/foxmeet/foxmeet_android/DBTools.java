package com.foxcorp.foxmeet.foxmeet_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jake on 1/17/15.
 */
public class DBTools extends SQLiteOpenHelper{
	@Override
	public void onCreate(SQLiteDatabase db) {
		String query = "CREATE TABLE groups (name TEXT, emails TEXT)";

		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public DBTools(Context applicationContext) {
		super(applicationContext, "groups.db", null, 1);
	}

	public void deleteKey (String name) {
		this.getWritableDatabase().execSQL(String.format("DELETE FROM groups WHERE name = '%s'", name));
	}

	public void insertGroup(HashMap<String, String> queryValues) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", queryValues.get("name"));
		values.put("emails", queryValues.get("emails"));

		db.insert("groups", null, values);
		db.close();
	}

	public ArrayList<HashMap<String, String>> getGroups() {
		ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

		String query = "SELECT * FROM groups";

		SQLiteDatabase db = this.getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				HashMap<String, String> groupMap = new HashMap<>();
				groupMap.put("name", cursor.getString(0));
				groupMap.put("emails", cursor.getString(1));
				arrayList.add(groupMap);
			} while (cursor.moveToNext());
		}
		return arrayList;
	}

}
