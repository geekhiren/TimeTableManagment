package com.example.prottm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Hyran on 4/10/2018.
 */

public class DBHelper extends SQLiteAssetHelper {

int version = 1;
String database = "database.db";

public DBHelper(Context context){
    super(context, "database.db", null,1);
}

public void insertData(String tname, int tcontact)
{
    SQLiteDatabase db = getWritableDatabase();
    String str;

    str= "insert into temp(tname, tcontact) values('"+tname+"',"+tcontact+")";
    db.execSQL(str);

}
public Cursor getAllData(){
    SQLiteDatabase db = getReadableDatabase();
    String str;

    str= "select * from temp";

    Cursor cursor = db.rawQuery(str,null);
    cursor.moveToFirst();
    return  cursor;
}

public Cursor getData(int tid)
{
    SQLiteDatabase db = getReadableDatabase();
    String str;

    str= "select * from temp where tid=?";

    Cursor cursor = db.rawQuery(str, new String[]{String.valueOf(tid)});
 cursor.moveToFirst();
 //return cursor.getString("password");
    return  cursor;

}

public  void deleteData(int tid)
{
    SQLiteDatabase db = getWritableDatabase();
    String str;

    str= "DELETE from temp where tid="+tid;
    db.execSQL(str);
}
public void updateData(int tcontact, int tid){
    SQLiteDatabase db = getWritableDatabase();
    String str;

    str= "update temp set tcontact="+tcontact+" where tid="+tid;
    db.execSQL(str);


}
    public void stuinsertData(int enrollment,String name,String email,int mobile,String password)
    {
        SQLiteDatabase db = getWritableDatabase();
        String str;

        str= "insert into stu_data(enrollment,name,email,mobile,password) values("+enrollment+", '"+name+"','"+email+"',"+mobile+",'"+password+"')";
        db.execSQL(str);


    }
    public Cursor getAllStuData(){
        SQLiteDatabase db = getReadableDatabase();
        String str;

        str= "select * from stu_data";

        Cursor cursor = db.rawQuery(str,null);
        cursor.moveToFirst();
        return  cursor;
    }
}
