package com.example.database3.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.database3.R;
import com.example.database3.model.Contact;
import com.example.database3.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    public Database( Context context) {
        super(context, Util.DATABASE_NAME,null,Util.DATABASE_VERSION);
    }

    //Creating our table method.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACT_TABLE = " CREATE TABLE "+Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY ,"
                + Util.KEY_NAME + " TEXT ,"
                + Util.KEY_PHONE_NUMBER + " TEXT ,"
                + Util.KEY_ADDRESS + " TEXT " + ")";

        db.execSQL(CREATE_CONTACT_TABLE);//Table creating

    }

    //Dropping table if exists and create again
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //Dropping table if Exists
        String DROP_TABLE = String.valueOf(R.string.drop_table);
        db.execSQL(DROP_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);//Crating table again

    }

    //CRUD --> Create Read Update Delete

    //Create Contact
    public void createContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhoneNumber());
        values.put(Util.KEY_ADDRESS,contact.getAddress());

        db.insert(Util.TABLE_NAME,null,values);
        Log.d("DBHandler", "Item Added");
        db.close();
    }


    //Read Single Contact
    public Contact readContact(int id ){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,new String[]{Util.KEY_ID,Util.KEY_NAME,Util.KEY_PHONE_NUMBER,Util.KEY_ADDRESS},
                Util.KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null);

        if(cursor !=null) cursor.moveToFirst();

        Contact contact = new Contact();
        contact.setId(Integer.parseInt(cursor.getString(0)));
        contact.setName(cursor.getString(1));
        contact.setPhoneNumber(cursor.getString(2));
        contact.setAddress(cursor.getString(3));

        return contact;
    }

    //Read All Contacts
    public List<Contact> readAllContacts(){
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM "+Util.TABLE_NAME;

        Cursor cursor = db.rawQuery(selectAll,null);

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                contact.setAddress(cursor.getString(3));

                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;

    }

    //Update Contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Util.KEY_NAME,contact.getName());
        values.put(Util.KEY_PHONE_NUMBER,contact.getPhoneNumber());
        values.put(Util.KEY_ADDRESS,contact.getAddress());

    return db.update(Util.TABLE_NAME,values,
            Util.KEY_ID +"=?",new String[]{String.valueOf(contact.getId())});
    }

    //Delete Contact
    public void deleteContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Util.TABLE_NAME,Util.KEY_ID +"=?",new String[]{String.valueOf(contact.getId())});

        db.close();
    }

    //Count total contacts
    public int countAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = " SELECT * FROM "+Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);

        return cursor.getCount();
    }
}
