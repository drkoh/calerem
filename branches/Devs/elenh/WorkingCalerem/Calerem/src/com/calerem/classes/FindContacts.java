package com.calerem.classes;

import java.util.ArrayList;
import com.calerem.classes.Contact;

/**
 * The Class FindContacts. Responsible for returning the Contacts with nameday
 * on current date.
 */
public class FindContacts {

	/** The eortologio array list. */
	static ArrayList<String> eortologioArrayList = new ArrayList<String>();

	/** The contacts Array. */
	static Contact[] contacts;

	/**
	 * Instantiates a new find contacts.
	 */
	public FindContacts() {
	}

	/**
	 * Search names.
	 * 
	 * @param contacts
	 *            the array of Contacts from Android
	 * @param eortologioNames
	 *            the eortologio ArrayList with namedays
	 * @return contact[] array with Contacts having nameday
	 */
	public static Contact[] searchNames(final Contact[] contacts, final ArrayList<String> eortologioNames) {
		final ArrayList<Contact> finalContacts = new ArrayList<Contact>();
		Contact nameDayContacts[];

		int contactIndex, eortologioIndex;
		try {

			for (contactIndex = 0; contactIndex < contacts.length; contactIndex++) {
				for (eortologioIndex = 0; eortologioIndex < eortologioNames.size(); eortologioIndex++) {
					if ((eortologioNames.get(eortologioIndex)).contains(contacts[contactIndex].getName())) {

						finalContacts.add(contacts[contactIndex]);
					}
				}
			}
			nameDayContacts = new Contact[finalContacts.size()];
			for (int namesIndex = 0; namesIndex < finalContacts.size(); namesIndex++) {
				nameDayContacts[namesIndex] = finalContacts.get(namesIndex);
			}

			return nameDayContacts;
		} catch (Exception e) {
			e.printStackTrace();
			Contact NamedayContacts[] = new Contact[finalContacts.size()];
			return NamedayContacts;
		}

	}

}
