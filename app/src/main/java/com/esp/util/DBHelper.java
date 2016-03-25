package com.esp.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public Context context;

	public DBHelper(Context context) {
		super(context, Config.DB_NAME, null, 33);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void execute(String statment) {
		SQLiteDatabase db = this.getWritableDatabase();
		try {
			Log.debug(this.getClass() + " :: execute() :: ", statment);
			Log.print(this.getClass() + " :: query() :: ", statment);
			db.execSQL(statment);

		} catch (Exception e) {
			Log.error(this.getClass() + " :: execute() ::", e);
			Log.print(e);
		} finally {
			db.close();
			db = null;
		}
	}

	public Cursor query(String statment) {
		Cursor cur = null;
		SQLiteDatabase db = this.getReadableDatabase();
		try {
			Log.debug(this.getClass() + " :: query() :: ", statment);
			Log.print(this.getClass() + " :: query() :: ", statment);
			cur = db.rawQuery(statment, null);
			cur.moveToPosition(-1);
		} catch (Exception e) {
			Log.error(this.getClass() + " :: query() ::", e);
			Log.print(e);
		} finally {

			db.close();
			db = null;
		}

		return cur;
	}

	public static String getDBStr(String str) {

		str = str != null ? str.replaceAll("'", "''") : null;
		str = str != null ? str.replaceAll("&#039;", "''") : null;
		str = str != null ? str.replaceAll("&amp;", "&") : null;

		return str;

	}

	public void upgrade(int level) {
		switch (level) {
		case 0:
			doUpdate1();
		case 1:
			// doUpdate2();
		case 2:
			// doUpdate3();
		case 3:
			// doUpdate4();
		}
	}

	private void doUpdate1() {
		
		this.execute("CREATE TABLE All_weather (id INTEGER PRIMARY KEY AUTOINCREMENT, image TEXT, image_heading TEXT, image_text TEXT, is_deleted INTEGER, is_main INTEGER, last_updated_on INTEGER, main_cat_id INTEGER, tbl_id INTEGER)");

		// Create Table
		// Storage.copyDB(this.context);
		// this.execute("Drop TABLE items ");

	}
}
