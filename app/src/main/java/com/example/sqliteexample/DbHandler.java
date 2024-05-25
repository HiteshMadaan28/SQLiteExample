package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHandler extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userdb";
    private  static final String TABLE_User = "user_details";
    private  static final String KEY_ID = "id";
    private  static final String KEY_NAME = "name";
    private  static final String KEY_LOC = "location";
    private  static final String KEY_DESG = "designation";

    public DbHandler(Context context){
        super(context,DB_NAME,null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE= "CREATE TABLE " + TABLE_User + "("+ KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" TEXT,"+KEY_LOC+" TEXT,"+KEY_DESG+" TEXT"+")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_User);
        onCreate(db);
    }
    //For inserting user details
    public void insertUserDetails(String username, String location, String designation){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NAME,username);
        values.put(KEY_LOC,location);
        values.put(KEY_DESG,designation);
        db.insert(TABLE_User,null,values);
        db.close();
    }

    //Get user Details
    public ArrayList<HashMap<String,String>> getUser(){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<HashMap<String,String>> userlist = new ArrayList<>();
        String selectQuery="SELECT location,designation,name FROM "+TABLE_User;

        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> user = new HashMap<>();
                user.put(KEY_NAME, cursor.getString(0));
                user.put(KEY_LOC, cursor.getString(1));
                user.put(KEY_DESG, cursor.getString(2));
                userlist.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return userlist;
    }

    //For getting user details
    public ArrayList<HashMap<String,String>> getUserByUserId(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ArrayList<HashMap<String,String>> userlist = new ArrayList<>();
        String selectQuery="SELECT * FROM "+TABLE_User+" WHERE "+KEY_ID+"="+id;

        Cursor cursor=db.query(TABLE_User,new String[]{KEY_NAME,KEY_LOC,KEY_DESG},KEY_ID+"=?",new String[]{String.valueOf(KEY_ID)},null,null,null,null);
        if (cursor.moveToNext()){
            HashMap<String,String> user=new HashMap<>();
            user.put(KEY_NAME,cursor.getString(0));
            user.put(KEY_LOC,cursor.getString(1));
            user.put(KEY_DESG,cursor.getString(2));
            userlist.add(user);
        }
        return userlist;
    }

    //For deleting value from table using Key_id
    public void deleteUserDetails(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_User,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

    //updating value of table using Key_id
    public int updateUserDetails(String loction,String designation,int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_LOC,loction);
        values.put(KEY_DESG,designation);
        int count=db.update(TABLE_User,values,KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
        return count;
    }


}
