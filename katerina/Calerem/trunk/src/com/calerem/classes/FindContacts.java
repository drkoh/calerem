package com.calerem.classes;

import java.util.ArrayList;
import java.util.EmptyStackException;

import com.calerem.classes.Contact;

public class FindContacts {
	
	static ArrayList<String> eortologioArrayList = new ArrayList<String>();
	static Contact contacts[];	
	static ArrayList <Integer> ids= new ArrayList<Integer>();
	
	public FindContacts (){
		
	}

	public static ArrayList<Integer> searchNames(final Contact[] contacts,
			final ArrayList<String> eortologioNames) {
		final ArrayList<Integer> contactId = new ArrayList<Integer>();

		// contactId.ensureCapacity(contactNames.length);

		int contactCursor, eortologioCursor;
		try {
			if (contacts.length > 0 && eortologioNames.size() > 0) {
				for (contactCursor = 0; contactCursor < contacts.length; contactCursor++) {
					for (eortologioCursor = 0; eortologioCursor < eortologioNames.size(); eortologioCursor++) {
						if ((contacts[contactCursor].getName()).equals(eortologioNames.get(eortologioCursor))) {
							contactId.add(contactCursor);
						}
					}
				}
			} else {
				throw new EmptyStackException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactId;
	}
	
	public static Contact[] parseNames (ArrayList<Integer> ids){
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
