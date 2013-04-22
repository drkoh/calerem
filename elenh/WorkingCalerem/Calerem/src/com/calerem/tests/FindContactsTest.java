package com.calerem.tests;

import java.util.ArrayList;
import com.calerem.classes.Contact;
import com.calerem.classes.FindContacts;

import junit.framework.TestCase;

/**
 * The Class FindContactsTest is testing if the results of
 * FindContacts.searchNames(final Contact[] contacts, final ArrayList<String>
 * eortologioNames) are the expected, depending on the input.
 * 
 * Also tests if the proper exceptions are thrown
 */
public class FindContactsTest extends TestCase {
	/** Test Values */
	static ArrayList<String> eortologioInput = new ArrayList<String>();
	Contact c1 = new Contact("Eleni", "Aidonidou", "6988383043", "elenaidon@gmail.com", 1);
	Contact c2 = new Contact("Agapi", "Spiridonidou", "123456789", "agapi@gmail.com", 2);
	Contact c3 = new Contact(null, null, null, null, null);
	Contact[] emptyContactInput = { c3 };
	Contact[] contactInput = { c1, c2 };
	Contact[] expected = { c1 };

	@Test
	public void testEmptyEortologioArray() {
		try {
			Contact[] result = FindContacts.searchNames(contactInput, eortologioInput);
			assertEquals(expected[0].getName(), result[0].getName());
			fail();
		} catch (IndexOutOfBoundsException ioe) {
		}
	}

	@Test
	public void testSearchNames() {
		eortologioInput.add("Eleni");
		Contact[] result = FindContacts.searchNames(contactInput, eortologioInput);
		assertEquals(expected[0].getName(), result[0].getName());
	}

}
