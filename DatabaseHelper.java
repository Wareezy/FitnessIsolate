package com.example.fitnessisolate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="InventorySystem.db";
    public static final String USER_TABLE_NAME="Equipment_Table";


    public static final String COLUMN_1="equipmentID";
    public static final String COLUMN_2="equipmentNAME";
    public static final String COLUMN_3="equipmentQuantity";
    public static final String COLUMN_4="equipmentPrice";
    public static final String COLUMN_5="equipmentBrand";
    public static final String COLUMN_6="equipmentSupplier";





    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+USER_TABLE_NAME+"(equipmentID INTEGER PRIMARY KEY AUTOINCREMENT,equipmentNAME TEXT,equipmentQuantity INTEGER,equipmentPrice INTEGER,equipmentBrand TEXT,equipmentSupplier TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ USER_TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String equipmentname,int equipmentquantity,int equipmentprice, String equipmentbrand, String equipmentsupplier) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_2, equipmentname);
        contentValues.put(COLUMN_3, equipmentquantity);
        contentValues.put(COLUMN_4,equipmentprice);
        contentValues.put(COLUMN_5, equipmentbrand);
        contentValues.put(COLUMN_6, equipmentsupplier);


        long result = db.insert(USER_TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res;
        res = db.rawQuery("select * from " + USER_TABLE_NAME, null);


        return res;
    }

    public boolean updateAccounts(String equipmentname,int equipmentquantity,int equipmentprice, String equipmentbrand, String equipmentsupplier){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLUMN_2, equipmentname);
        contentValues.put(COLUMN_3, equipmentquantity);
        contentValues.put(COLUMN_4,equipmentprice);
        contentValues.put(COLUMN_5, equipmentbrand);
        contentValues.put(COLUMN_6, equipmentsupplier);

        db.update(USER_TABLE_NAME ,contentValues,"equipmentName = ?",new String[]{equipmentname});
        return true;
    }
    public  Integer deleteData(String equipmentname){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete(USER_TABLE_NAME,"equipmentName = ?",new String[] {equipmentname});
    }


    public Cursor searchData(String searchEquipID,SQLiteDatabase sqLiteDatabase) {

        String[] projections = {COLUMN_1, COLUMN_2, COLUMN_3, COLUMN_4, COLUMN_5, COLUMN_6};
        String selection = COLUMN_1 + " LIKE ? ";
        String[] selection_args = {searchEquipID};
        Cursor res = sqLiteDatabase.query(USER_TABLE_NAME, projections, selection, selection_args, null, null, null);
        return res;
    }

}
