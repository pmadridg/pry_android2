package com.isil.am2template.storage.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.isil.am2template.model.entity.Cart;
import com.isil.am2template.model.entity.NoteEntity;
import com.isil.am2template.model.entity.Place;

import java.util.ArrayList;
import java.util.List;

public class CRUDOperations {

	private MyDatabase helper;
	public CRUDOperations(SQLiteOpenHelper _helper) {
		super();
		// TODO Auto-generated constructor stub
		helper =(MyDatabase)_helper;
	}

	public void addPlace(Place place)
	{
		SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
		ContentValues values = new ContentValues();

        values.put(MyDatabase.KEY_COD, place.getId());
		values.put(MyDatabase.KEY_NAME, place.getName());
		values.put(MyDatabase.KEY_DESC, place.getDescription());
		values.put(MyDatabase.KEY_SIZE, place.getSize());

		db.insert(MyDatabase.TABLE_LOCATIONS, null, values);
		db.close();
	}

    public void addCart(Cart cart)
    {
        SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
        ContentValues values = new ContentValues();
        values.put(MyDatabase.CART_COD, cart .getId());
        values.put(MyDatabase.CART_NAME, cart.getTitle());
        values.put(MyDatabase.CART_SIZE, cart.getQty());

        db.insert(MyDatabase.TABLE_CART, null, values);
        db.close();
    }

	public Place getPlace(int id)
	{
		SQLiteDatabase db = helper.getReadableDatabase(); //modo lectura
		Cursor cursor = db.query(MyDatabase.TABLE_LOCATIONS,
				new String[]{MyDatabase.KEY_ID, MyDatabase.KEY_NAME,
						MyDatabase.KEY_DESC, MyDatabase.KEY_SIZE},
				MyDatabase.KEY_ID + "=?",
				new String[]{String.valueOf(id)}, null, null, null);
		if(cursor!=null)
		{
			cursor.moveToFirst();
		}
		String nid = cursor.getString(0);
		String name = cursor.getString(1);
		String desc = cursor.getString(2);
		String size = cursor.getString(3);

		Place place= new Place(
				nid, name, desc,size);
		db.close();
		return place;
	}

	public List<Place> getAllPlaces()
	{
		List<Place> lst =new ArrayList<Place>();
		String sql= "SELECT  * FROM " + MyDatabase.TABLE_LOCATIONS;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.moveToFirst())
		{
			do
			{
				Place contact =new Place();
				//contact.setId(cursor.getString(0));
                contact.setId(cursor.getString(1));
				contact.setName(cursor.getString(2));
				contact.setDescription(cursor.getString(3));
				contact.setSize(cursor.getString(4));

				lst.add(contact);
			}while(cursor.moveToNext());
		}
		db.close();
		return lst;
	}

    public List<Cart> getAllCart()
    {
        List<Cart> lst =new ArrayList<Cart>();
        String sql= "SELECT  * FROM " + MyDatabase.TABLE_CART;
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst())
        {
            do
            {
                Cart contact =new Cart();
                //contact.setId(cursor.getString(0));
                contact.setId(cursor.getString(1));
                contact.setTitle(cursor.getString(2));
                contact.setQty(cursor.getString(3));


                lst.add(contact);
            }while(cursor.moveToNext());
        }
        db.close();
        return lst;
    }

	public int getNoteCount()
	{
		String sql= "SELECT * FROM "+MyDatabase.TABLE_LOCATIONS;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		int count=cursor.getCount();
		cursor.close();
		db.close();
		return count;
	}

	//--------------------------------------------
	public int updatePlace(Place place)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(MyDatabase.KEY_NAME, place.getName());
		values.put(MyDatabase.KEY_DESC, place.getDescription());
		values.put(MyDatabase.KEY_SIZE, place.getSize());

		int row =db.update(MyDatabase.TABLE_LOCATIONS,
				values,
				MyDatabase.KEY_ID+"=?",
				new String[]{String.valueOf(place.getId())});
		db.close();

		return row;
	}

	//--------------------------------------------
	public int deletePlace(Place place)
	{
		SQLiteDatabase db = helper.getWritableDatabase();
		int row= db.delete(MyDatabase.TABLE_LOCATIONS,
				MyDatabase.KEY_ID+"=?",
				new String[]{String.valueOf(place.getId())});
		db.close();
		return row;
	}

	public long getNoteCountWithStatement(){
		String sql= "select count(*) from "+MyDatabase.TABLE_LOCATIONS;
		SQLiteDatabase db = helper.getReadableDatabase();
		SQLiteStatement s = db.compileStatement(sql);
		long count = s.simpleQueryForLong();
		db.close();

		return count;
	}


	private void clearTable(String table){
		String clearDBQuery = "DELETE FROM "+table;
		SQLiteDatabase db = helper.getWritableDatabase(); //modo escritura
		db.execSQL(clearDBQuery);
		db.close();
	}

	public void clearDb(){
		clearTable(MyDatabase.TABLE_LOCATIONS);
        //clearTable(MyDatabase.TABLE_CART);
		//clearTable(MyDatabase.TABLE_USER);
	}
}
