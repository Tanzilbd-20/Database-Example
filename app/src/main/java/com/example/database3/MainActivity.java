package com.example.database3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.database3.data.Database;
import com.example.database3.model.Contact;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Database db = new Database(this);

        //Counts all the contacts
        int countContacts = db.countAllContacts();
        Log.d("Count_Contacts ", "Total Contacts : "+countContacts);


        //Creating new Contacts
        Contact tanzil = new Contact();
        tanzil.setName("Tanzil");
        tanzil.setPhoneNumber("01127273755");
        tanzil.setAddress("Kota Laksamana");
        Contact shipan = new Contact("Shipan","3462542","Melaka Raya");
        Contact shaheen = new Contact("Shaheen","364573467","Melaka Raya 25");
        Contact arif = new Contact("Arif","456456","Melaka Raya 23");
        Contact durjoy = new Contact("Durjoy","3984782684","Brahmanbaria");
        Contact topu = new Contact("Topu","734856376","Dubai");
        Contact tanvir = new Contact("Tanvir","934578678","Kuwait");
        Contact riaz = new Contact("Riaz","+880183467363","College Para");

        //Adding contacts to our database.
     /*   db.createContact(tanzil);
        db.createContact(shipan);
        db.createContact(shaheen);
        db.createContact(arif);
        db.createContact(durjoy);
        db.createContact(topu);
        db.createContact(tanvir);
        db.createContact(riaz);*/


        //Delete Contact
      Contact deleteContact = db.readContact(9);

     // db.deleteContact(deleteContact);


      //UpdateContact
      Contact updateContact = db.readContact(8);
    /*  updateContact.setName("Riaz Choudury");
      db.updateContact(updateContact);*/



        //Read all contacts.
        List<Contact> contactList = db.readAllContacts();
        for(Contact contact:contactList){
            Log.d("Information : ", "ID : "+contact.getId());
            Log.d("Information : ", "Name : "+contact.getName());
            Log.d("Information : ", "Phone Number : "+contact.getPhoneNumber());
            Log.d("Information : ", "Address : "+contact.getAddress());
        }



    }
}