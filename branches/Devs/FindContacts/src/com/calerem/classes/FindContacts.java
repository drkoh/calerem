package com.calerem.classes;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class FindContacts {

	ArrayList<Integer> searchNames(final String[] contactNames,
			final String[] eortologioNames) {
		final ArrayList<Integer> contactId = new ArrayList<Integer>();

		// contactId.ensureCapacity(contactNames.length);

		int contactCursor, eortologioCursor;
		try {
			if (contactNames.length > 0 && eortologioNames.length > 0) {
				for (contactCursor = 0; contactCursor < contactNames.length; contactCursor++) {
					for (eortologioCursor = 0; eortologioCursor < eortologioNames.length; eortologioCursor++) {
						if ((contactNames[contactCursor]).equals(eortologioNames[eortologioCursor])) {
							contactId.add(contactCursor);
						}
					}
				}
			}else{
				throw new EmptyStackException();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contactId;
	}
}
