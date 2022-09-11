package com.example.regform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CDB extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "DMS";
    public CDB(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL("create table TbUser (id integer primary key autoincrement,name text not null,uname text unique not null,pass text not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

        db.execSQL("DROP TABLE IF EXISTS TbUser");
        onCreate(db);

    }

    public void addUser(String n, String un,String p)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("name",n);
            cv.put("uname",un);
            cv.put("pass",p);
            db.insert("TbUser",null,cv);
            db.close();
        }
        catch(Exception exception)
        {
            System.out.println(exception);
            Log.d("INSERT",exception.toString());
        }
    }

    public String[] getOneUser(String uname)
    {
        String[] a = new String[3];
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query("TbUser",new String[]{"id","name","uname","pass"},"uname" + "=?", new String[]{uname},null,null,null,null);
            if(cursor != null && cursor.getCount() != 0)
            {
                cursor.moveToFirst();
                a[0] = cursor.getString(1);
                a[1] = cursor.getString(2);
                a[2] = cursor.getString(3);
            }
            else
            {
                a[0] = "";
                a[1] = "";
                a[2] = "";
            }
        }catch (Exception exception)
        {
            System.out.println(exception);
            Log.d("SELECT",exception.toString());
        }
        return a;
    }
    public int deleteUser(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("TbUser","id=?",
                new String[]{String.valueOf(id)});
    }
    public void update(String un,String p)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("pass",p);
        db.update("TbUser",values,"uname = ?",new String[]{un});
        db.close();
    }
    public List<CUser> getAllvalues()
    {
        List<CUser> recList = new ArrayList<CUser>();
        String selectquery = "SELECT * from TbUser";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectquery,null);
        if(cursor.moveToFirst())
        {
            do
            {
                CUser rec = new CUser();
                rec.id = Integer.parseInt(cursor.getString(0));
                rec.name = cursor.getString(1);
                rec.uname =  cursor.getString(2);
                rec.pass =  cursor.getString(3);
                recList.add(rec);
            }while(cursor.moveToNext());
        }
        return recList;
    }
}

