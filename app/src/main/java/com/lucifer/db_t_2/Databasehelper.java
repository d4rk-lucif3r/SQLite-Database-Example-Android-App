package com.lucifer.db_t_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Databasehelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Student.db";
    public static final String TABLE_NAME="Student_table";
    public static final String Col1="ID";
    public static final String Col2="NAME";
    public static final String Col3="SURNAME";
    public static final String Col4="MARKS";


    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 1)
        ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT,"
            + " SURNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

         onCreate(db);
    }
    public boolean insertData(String name, String surname, String Marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2,name);
        contentValues.put(Col3,surname);
        contentValues.put(Col4,Marks);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

public Cursor getalldata(){
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor res =db.rawQuery("select * from "+TABLE_NAME,null);
    return res;

}
    public boolean updatedata(String Id,String name, String Surname, String Marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1,Id);
        contentValues.put(Col2,name);
        contentValues.put(Col3,Surname);
        contentValues.put(Col4,Marks);
db.update(TABLE_NAME,contentValues,"Id=?",new String[]{Id});
return true;
    }
    public Integer deletedata(String Id){

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME,"ID=?", new String[]{Id});


    }}
