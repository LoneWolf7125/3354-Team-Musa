package com.se.contactsmanager.Utils;

import android.test.AndroidTestCase;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;

public class DatabaseHelperTest extends AndroidTestCase
{
    private DatabaseHelper db;

    private static long mID;
    private static String mName;
    private static String mPhoneNumber;
    private static String mDevice;
    private static String mEmail;
    private static String mPhoto;

    public void testDropDB(){
        assertTrue(mContext.deleteDatabase(DatabaseHelper.DATABASE_NAME));
    }

    public void testCreateDB(){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        assertTrue(db.isOpen());
        db.close();
    }

    public void testInsertData()
    {
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        mName = "John Doe";
        mPhoneNumber = "1234567";
        mDevice = "Work";
        mEmail = "asdf@gmail.com";
        mPhoto = "none";

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL1, mName);
        contentValues.put(DatabaseHelper.COL2, mPhoneNumber);
        contentValues.put(DatabaseHelper.COL3, mDevice);
        contentValues.put(DatabaseHelper.COL4, mEmail);
        contentValues.put(DatabaseHelper.COL5, mPhoto);

        mID = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);
        assertTrue(mID != -1);
    }

    public void testIsDataCorrectInDB(){
        DatabaseHelper dbHelper = new DatabaseHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME,null,null,null,null,null,null);
        assertTrue(cursor.moveToFirst());

        int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL0);
        int dbId = cursor.getInt(idColumnIndex);

        int nameColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL1);
        String dbName = cursor.getString(nameColumnIndex);

        int phoneNumberColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL2);
        int dbPhoneNumber = cursor.getInt(phoneNumberColumnIndex);

        int deviceColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL3);
        int dbDevice = cursor.getInt(deviceColumnIndex);

        int emailColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL4);
        int dbEmail = cursor.getInt(emailColumnIndex);

        int photoColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL5);
        int dbPhoto = cursor.getInt(photoColumnIndex);

        assertEquals(mID, dbId);
        assertEquals(mName, dbName);
        assertEquals(mPhoneNumber, dbPhoneNumber);
        assertEquals(mDevice, dbDevice);
        assertEquals(mEmail, dbEmail);
        assertEquals(mPhoto, dbPhoto);
    }

}