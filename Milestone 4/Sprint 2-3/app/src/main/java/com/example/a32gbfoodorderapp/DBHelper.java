package com.example.a32gbfoodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Orders.DB";
    private static final String TBL_NAME = "order_table";

    private static final String INT_TYPE =  " INTEGER";
    private static final String TEXT_TYPE =  " TEXT";

    private static final String COL_1 = "ID";
    private static final String COL_2 = "ORDER_NAME";
    private static final String COL_3 = "QUANTITY";
    private static final String COL_4 = "TOTAL";



    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

        db.execSQL("CREATE TABLE " + TBL_NAME + " ("+
                COL_1 + INT_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                COL_2 + TEXT_TYPE + " NOT NULL, " +
                COL_3 + TEXT_TYPE + " NOT NULL, " +
                COL_4 + TEXT_TYPE + " NOT NULL"
                + ");");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public boolean insertData(String orderItem, String quantity, String total){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, orderItem);
        contentValues.put(COL_3, quantity);
        contentValues.put(COL_4, total);

        long result = db.insert(TBL_NAME,null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllOrders(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * FROM " + TBL_NAME, null);
        return cursor;

    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();

        return(db.delete(TBL_NAME, "ID = ?", new String [] {id} ));

    }
}
