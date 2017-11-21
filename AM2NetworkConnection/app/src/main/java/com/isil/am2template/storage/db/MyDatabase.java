package com.isil.am2template.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {


	public static final int DATABASE_VERSION = 1;

	public static final String DATABASE_NAME = "BDEvents";

	public static final String TABLE_LOCATIONS = "tb_locations";
	public static final String TABLE_CART = "tb_cart";

	//Columnas de la Tabla Notes
	public static final String KEY_ID = "id";
	public static final String KEY_COD = "code";
	public static final String KEY_NAME = "name";
	public static final String KEY_DESC = "desc";
	public static final String KEY_SIZE = "size";
	public static final String CART_ID = "cartId";
	public static final String CART_COD = "code";
	public static final String CART_NAME = "cartName";
	public static final String CART_SIZE = "cartSize";

	public MyDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql= "CREATE TABLE " + TABLE_LOCATIONS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + KEY_COD + " TEXT," + KEY_NAME + " TEXT,"
				+ KEY_DESC + " TEXT,"
				+ KEY_SIZE + " TEXT" + ")";
		String sql2= "CREATE TABLE " + TABLE_CART + "("
				+ CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + CART_COD + " TEXT," + CART_NAME + " TEXT,"
				+ CART_SIZE + " TEXT" + ")";
		db.execSQL(sql);
		db.execSQL(sql2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		String sql= "DROP TABLE IF EXISTS " + TABLE_LOCATIONS;
		String sql2= "DROP TABLE IF EXISTS " + TABLE_CART;
		db.execSQL(sql);
		db.execSQL(sql2);
	}

}
