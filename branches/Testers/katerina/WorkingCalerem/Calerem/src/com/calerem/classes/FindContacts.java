package com.calerem.classes;

import java.util.ArrayList;
import java.util.EmptyStackException;

import com.calerem.classes.Contact;

public class FindContacts {
	
	//private static contacts contact1 = new contacts ("Kalida","string","String","string",1);
	//private static contacts contact2 = new contacts ("Kostas","string","String","string",1);
	//private static contacts[] Contacts={contact1,contact2};
	
	static ArrayList<String> eortologioArrayList = new ArrayList<String>();
	static Contact[] contacts;		
	
	
	public FindContacts (){
		
	}

	public static Contact[]  searchNames(final Contact[] contacts,
			final ArrayList<String> eortologioNames) {
		final ArrayList<Contact> finalContacts = new ArrayList<Contact>();
		Contact nameDayContacts[];
		
		int contactCursor, eortologioCursor;
		try {
			if (contacts.length > 0 && eortologioNames.size() > 0) {
				for (contactCursor = 0; contactCursor < contacts.length; contactCursor++) {
					for (eortologioCursor = 0; eortologioCursor < eortologioNames.size(); eortologioCursor++) {
						if ((eortologioNames.get(eortologioCursor)).contains(contacts[contactCursor].getName())) {
							
							finalContacts.add(contacts[contactCursor]);
						}
					}
				}
				nameDayContacts = new Contact[finalContacts.size()];
				for (int namesCursor =0; namesCursor<finalContacts.size();namesCursor++) { 
					nameDayContacts[namesCursor] =finalContacts.get(namesCursor);
				}
			} else {
				throw new EmptyStackException();
			}
			return nameDayContacts;
		} catch (Exception e) {
			e.printStackTrace();
			Contact NamedayContacts[] = new Contact[finalContacts.size()];
			return NamedayContacts;
		}
				
	}
	
	
}
