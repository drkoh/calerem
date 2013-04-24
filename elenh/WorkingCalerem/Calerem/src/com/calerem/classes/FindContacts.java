package com.calerem.classes;

import java.util.ArrayList;
import com.calerem.classes.Contact;

/**
 * The Class FindContacts. Responsible for returning the Contacts with nameday
 * on current date.
 * 
 * @author Aidonidou Eleni
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
	 * Checks if eortologio ArrayList contains the first name of each of the
	 * contacts in the Contact Array
	 * 
	 * @param contacts
	 *            the array of Contacts from Android
	 * @param eortologioNames
	 *            the eortologio ArrayList with namedays
	 * @return contact[] array with Contacts having nameday
	 */
	public static Contact[] searchNames(final Contact[] contacts, final ArrayList<String> eortologioNames) {
		final ArrayList<Contact> finalContacts = new ArrayList<Contact>();
		Contact[] namedayContacts;

		int contactIndex, eortologioIndex;
		try {

			for (contactIndex = 0; contactIndex < contacts.length; contactIndex++) {
				for (eortologioIndex = 0; eortologioIndex < eortologioNames.size(); eortologioIndex++) {
					if ((eortologioNames.get(eortologioIndex)).contains(contacts[contactIndex].getName())) {

						finalContacts.add(contacts[contactIndex]);
					}
				}
			}
			namedayContacts = new Contact[finalContacts.size()];
			for (int namesIndex = 0; namesIndex < finalContacts.size(); namesIndex++) {
				namedayContacts[namesIndex] = finalContacts.get(namesIndex);
			}

			return namedayContacts;
		} catch (Exception e) {
			e.printStackTrace();
			namedayContacts = new Contact[finalContacts.size()];
			return namedayContacts;
		}

	}

}