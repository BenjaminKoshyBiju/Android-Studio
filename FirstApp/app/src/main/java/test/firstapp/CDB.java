package test.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import test.firstapp.CDept;

import java.util.ArrayList;
import java.util.List;

public class CDB extends SQLiteOpenHelper {
    private static final int DATABE_VERSION = 2;
    private static final String DATABASE_NAME ="DMS";
    public CDB(Context context)
    {
        super(context,DATABASE_NAME,null,DATABE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table TbDept (dno integer primary key autoincrement,dname text,dloc text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS TbDept");
        onCreate(db );

    }
    public void addDept(String dn,String dl) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("dname", dn);
            cv.put("dpass", dl);
            db.insert("TbDept", null, cv);
            db.close();
        } catch (Exception e) {
            System.out.println(e);
            Log.d("INSERT",e.toString());
        }
    }
    public String[] getOneDepartment(int dno)
    {
        String a[]=new String[2];
        try {
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.query("TbDept",new String[]{"dno","dname","dpass"},"dno"+"=?",
                    new String[]{String.valueOf(dno)},null,null,null,null);
            if(cursor!=null&&cursor.getCount()!=0)
            {
                cursor.moveToFirst();
                a[0]=cursor.getString(1);
                a[1]=cursor.getString(2);
            }
            else
            {
                a[0]="";
                a[1]="";
            }
        } catch (Exception e) {
            System.out.println(e);
            Log.d("SELECT",e.toString());
        }
        return a;
    }
    public int deleteDept(int dno)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete("TbDept", "dno=?",new String[]{String.valueOf(dno)});
    }
    public void update(int dno,String dn,String d1)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("dname",dn);
        values.put("dpass",d1);
        db.update("TbDept",values,"dno=?",new String[]{String.valueOf(dno)});
        db.close();

    }
    public List<CDept> getAllvalues()
    {
        List<CDept> recList=new ArrayList<CDept>();
        //Select all query
        String selectQuery="SELECT * FROM TbDept";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        //looping through all rows and adding to list
        if (cursor.moveToFirst())
            do{
                CDept rec= new CDept();
                rec.id=Integer.parseInt(cursor.getString(0));
                rec.dname=cursor.getString(1);
                rec.dpass=cursor.getString(2);
                recList.add(rec);
            }   while (cursor.moveToNext());
        return recList;
    }

}