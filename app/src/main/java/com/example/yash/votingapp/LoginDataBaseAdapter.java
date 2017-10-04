package com.example.yash.votingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Yash on 06-09-2017.
 */

class LoginDataBaseAdapter {

    static final String DATABASE_NAME = "votersystem.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 3;

    static final String CREATE_LOGIN_TABLE = "create table "+"LOGIN"+
            "( " +"ID integer primary key autoincrement,"+ "PASSWORD  text,"+"REPASSWORD text,"+ "SECURITYHINT text) ";

    static final String CREATE_ADMIN_TABLE = "create table "+"ADMIN"+
            "( " +"ID integer primary key autoincrement,"+ "FIRSTNAME  text,"+"LASTNAME text,"+"PARTY text,"+ "SYMBOL text) ";

    static final String CREATE_REPORT_TABLE = "create table "+"REPORT"+"(" +"ID integer primary key autoincrement,"+ "FULLNAME text,"+"PARTY text"+")";
    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;
    private Cursor mCursor;

    public  LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String password,String repassword,String securityhint)
    {
        ContentValues newValues = new ContentValues();
        newValues.put("PASSWORD", password);
        newValues.put("REPASSWORD",repassword);
        newValues.put("SECURITYHINT",securityhint);

        db.insert("LOGIN", null, newValues);
    }

    public int deleteEntry(String password)
    {
        String where="PASSWORD=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{password}) ;
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String password)
    {
        Cursor cursor=db.query("LOGIN", null, " PASSWORD=?", new String[]{password}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String repassword= cursor.getString(cursor.getColumnIndex("REPASSWORD"));
        cursor.close();
        return repassword;
    }

    public String getAllTags(String a) {


        Cursor c = db.rawQuery("SELECT * FROM " + "LOGIN" + " where SECURITYHINT = '" +a + "'" , null);
        String str = null;
        if (c.moveToFirst()) {
            do {
                str = c.getString(c.getColumnIndex("PASSWORD"));
            } while (c.moveToNext());
        }
        return str;
    }


    public void  updateEntry(String password,String repassword)
    {
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("PASSWORD", password);
        updatedValues.put("REPASSWORD",repassword);
        updatedValues.put("SECURITYHINT",repassword);

        String where="USERNAME = ?";
        db.update("LOGIN",updatedValues, where, new String[]{password});
    }



    public HashMap<String, String> getAnimalInfo(String id) {
        HashMap<String, String> wordList = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM LOGIN where SECURITYHINT='"+id+"'";
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                wordList.put("PASSWORD", cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return wordList;
    }



    public void insertAdminEntry(String firstName,String lastName,String party,String symbol  )
    {
        ContentValues newValues = new ContentValues();
        newValues.put("FIRSTNAME", firstName);
        newValues.put("LASTNAME",lastName);
        newValues.put("PARTY",party);
        newValues.put("SYMBOL", symbol);

        db.insert("ADMIN", null, newValues);
    }

    public void insertReportEntry(String firstName,String party  )
    {
        ContentValues newValues1 = new ContentValues();
        newValues1.put("FULLNAME", firstName);
        newValues1.put("PARTY",party);

        db.insert("REPORT", null, newValues1);
    }



    /**
     * getting all todos
     * */
    public List<String> getAllFirstName() {

        List<String> todos = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + "ADMIN";

        Log.e("admin", selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                todos.add(c.getString(1));

            } while (c.moveToNext());
        }

        return todos;
    }

    public List<String> getAllPartyName() {

        List<String> todos = new ArrayList<String>();
        String selectQuery = "select  "+"PARTY"+" From " + "ADMIN" ;
        //String selectQuery = "SELECT  * FROM " + "ADMIN";

        Log.e("admin", selectQuery);

        Cursor curs = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (curs.moveToFirst()) {
            do {
                todos.add(curs.getString(0));

            } while (curs.moveToNext());
        }

        return todos;
    }

    public List<String> getAllLastName() {

        List<String> todos = new ArrayList<String>();
        String selectQuery = "select  "+"LASTNAME"+" From " + "ADMIN" ;
        //String selectQuery = "SELECT  * FROM " + "ADMIN";

        Log.e("admin", selectQuery);

        Cursor curs = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (curs.moveToFirst()) {
            do {
                todos.add(curs.getString(0));

            } while (curs.moveToNext());
        }

        return todos;
    }


    public String fetchMyRowid(String column_name)
    {
        String query = "select  "+column_name+" From " + "ADMIN";

        mCursor =db.rawQuery(query, null);
        StringBuffer buf = new StringBuffer();
        while(mCursor.moveToNext()) {

            buf.append(mCursor.getString(mCursor.getColumnIndex(column_name))+",");

        }

        return buf.toString();
    }


    public int getAllPartyNameCount(String partyName) {

        List<String> todos = new ArrayList<String>();
        //SELECT * FROM Report where party="NCP"
        String selectQuery = "SELECT * FROM REPORT WHERE PARTY="+"\""+partyName+"\"";
        // String selectQuery = "SELECT * FROM " + "ADMIN";

        Log.e("admin", selectQuery);

        Cursor curs = db.rawQuery(selectQuery, null);

		/*// looping through all rows and adding to list
		if (curs.moveToFirst()) {
			do {
				todos.add(curs.getString(0));

			} while (curs.moveToNext());
		}*/

        return curs.getCount();
    }
}
