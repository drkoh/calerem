package com.calerem.classes;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class FindContacts {

	ArrayList<Integer> searchNames(final ArrayList<Contact> contactNames,
			final ArrayList<String> eortologioNames) {
		final ArrayList<Integer> contactId = new ArrayList<Integer>();

		// contactId.ensureCapacity(contactNames.length);

		int contactCursor, eortologioCursor;
		try {
			if (contactNames.size() > 0 && eortologioNames.size() > 0) {
				for (contactCursor = 0; contactCursor < contactNames.size(); contactCursor++) {
					for (eortologioCursor = 0; eortologioCursor < eortologioNames.size(); eortologioCursor++) {
						if ((contactNames.get(contactCursor).name)
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
