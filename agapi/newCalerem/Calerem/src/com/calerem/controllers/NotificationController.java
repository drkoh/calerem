package com.calerem.controllers;

import java.util.ArrayList;

import android.app.Activity;


import com.calerem.api.ContactsAPI;
import com.calerem.api.EortologioApi;
import com.calerem.classes.Contact;
import com.calerem.classes.FindContacts;

public class NotificationController extends Activity {

	static ArrayList<String> eortologioArrayList = new ArrayList<String>();
	static Contact contacts[];	
	static ArrayList <Integer> ids= new ArrayList<Integer>();
	
	public void initVars () {
		EortologioApi eort = new EortologioApi ();
		ContactsAPI contApi = null;
		eortologioArrayList = new ArrayList<String>();
		FindContacts fc = new FindContacts() ;
		ids = new ArrayList<Integer>();
		eortologioArrayList = eort.returnVal();
		contacts = contApi.getContacts();
		ids=fc.searchNames(contacts,eortologioArrayList);
	}
	
	
	public Contact[] parseIds (){
		Contact nameDayContacts[] = new Contact[ids.size()]; 
		
		 for (int namesCursor =0; namesCursor<ids.size();namesCursor++) {
			 for (int contactCursor = 0; contactCursor<contacts.length;contactCursor++) {
				 if(ids.get(namesCursor)==contacts[contactCursor].getId()) {
					 nameDayContacts[namesCursor] = new Contact (contacts[contactCursor].getName(),
							 contacts[contactCursor].getLastname(),contacts[contactCursor].getPhone(),
							 contacts[contactCursor].getEmail(),contacts[contactCursor].getId());
				 }
			 }	
		}
		return nameDayContacts;
	}
}
