package com.calerem.classes;

import java.util.ArrayList;
import java.util.EmptyStackException;
import com.calerem.classes.Contact;

public class FindContacts {
	
	public FindContacts (){
		
	}

	public ArrayList<Integer> searchNames(final Contact[] contacts,
			final ArrayList<String> eortologioNames) {
		final ArrayList<Integer> contactId = new ArrayList<Integer>();

		// contactId.ensureCapacity(contactNames.length);

		int contactCursor, eortologioCursor;
		try {
			if (contacts.length > 0 && eortologioNames.size() > 0) {
				for (contactCursor = 0; contactCursor < contacts.length; contactCursor++) {
					for (eortologioCursor = 0; eortologioCursor < eortologioNames.size(); eortologioCursor++) {
						if ((contacts[contactCursor].getName())
								.equals(eortologioNames.get(eortologioCursor))) {
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
}
