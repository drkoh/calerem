/**
 * 
 */
package com.calerem.tests.api;

import java.util.ArrayList;
import java.util.Random;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.test.ActivityInstrumentationTestCase2;

import com.calerem.MainActivity;
import com.calerem.api.ContactsAPI;
import com.calerem.classes.Contact;

/**
 * ConactsAPI Test in JUnit 3.
 * @author DarkParadise
 */
public class ContactsAPITest extends ActivityInstrumentationTestCase2<MainActivity> {

	private ContactsAPI api;
	/**
	 * Base Constructor.
	 */
	public ContactsAPITest()
	{
		super(MainActivity.class);
	}

	/**
	 * Erase all contacts before test.
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	public void setUp()
	{
		ContentResolver cr = this.getActivity().getBaseContext().getContentResolver();
		Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
		while (cur.moveToNext()) 
		{
			try
			{
				String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
				Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
				cr.delete(uri, null, null);
			}
			catch(Exception e)
			{
				e.getStackTrace();
			}
		}
		cur.close();
		this.api = new ContactsAPI(this.getInstrumentation().getContext());
	}

	/**
	 * Helper function for the test, adds a contact to the phone when called.
	 * @param String FirstName
	 * @param String LastName
	 * @param String MobileNumber
	 * @param String email
	 */
	private void AddContact(String FirstName, String LastName, String[] MobileNumber, String[] email)
	{
		ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

		Builder builder = ContentProviderOperation.newInsert(RawContacts.CONTENT_URI);
		builder.withValue(RawContacts.ACCOUNT_TYPE, null);
		builder.withValue(RawContacts.ACCOUNT_NAME, null);
		ops.add(builder.build());

		// Name + Last Name
		builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
		builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0);
		builder.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
		builder.withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME, LastName);
		builder.withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, FirstName);
		ops.add(builder.build());

		// EMail
		for(int i=0;i<email.length;i++)
		{
			builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
			builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0);
			builder.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE);
			builder.withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email[i]);
			builder.withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME);
			ops.add(builder.build());
		}

		// Number
		for(int i=0;i<MobileNumber.length;i++)
		{
			builder = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
			builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0);
			builder.withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
			builder.withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber[i]);
			builder.withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME);
			ops.add(builder.build());
		}

		// Asking the Contact provider to create a new contact                 
		try 
		{
			this.getActivity().getBaseContext().getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Shut down the activity to prepare for next test.
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown()
	{
		super.getActivity().finish();
	}

	/**
	 * Tests normal environment usage.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContacts() {
		String[] Phones = {"6941234567"};
		String[] Emails = {"myemail@mydomain.com"};
		this.AddContact("TestName", "TestLastName", Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length>0)
		{
			assertEquals(contacts[0].getEmail(),"myemail@mydomain.com");	
		}
		else
		{
			fail("It should return a contact.");
		}
	}

	/**
	 * Test without any contact in the phone.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsNoContact()
	{
		Contact[] contacts = this.api.getContacts();
		if(contacts.length==0)
		{
			assertTrue(true);	
		}
		else
		{
			fail("Shouldnt return a contact.");
		}
	}

	/**
	 * Test with random contacts count.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsRandomContactsNumber()
	{
		int RandomContactsCount = new Random(System.currentTimeMillis()).nextInt(100);
		String[] Phones = {"6941234567"};
		String[] Emails = {"myemail@mydomain.com"};
		for(int i=0;i<RandomContactsCount;i++)
		{
			Emails[0] = "myemail" + i + "@mydomain.com";
			this.AddContact("TestName" + i,"TestLastName" + i, Phones, Emails);
		}
		Contact[] contacts = this.api.getContacts();
		if(contacts.length==RandomContactsCount)
		{
			assertTrue(true);	
		}
		else
		{
			fail("Wrong count returned;");
		}
	}

	/**
	 * Test without name and last name.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsContactWithoutDisplay()
	{
		String[] Phones = {"6941234567"};
		String[] Emails = {"myemail@mydomain.com"};
		this.AddContact(null, null, Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length>0)
		{
			assertEquals(contacts[0].getName().isEmpty(),contacts[0].getLastname().isEmpty());	
		}
		else
		{
			fail("Name and last name didnt match, they should both be empty strings.");
		}
	}

	/**
	 * Test without LastName.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsContactWithoutLastName()
	{
		String[] Phones = {"6941234567"};
		String[] Emails = {"myemail@mydomain.com"};
		this.AddContact("TestName", null, Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length>0)
		{
			assertTrue(contacts[0].getLastname().isEmpty());
		}
		else
		{
			fail("Last name should be an empty string.");
		}
	}

	/**
	 * Test without Name.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsContactWithoutName()
	{
		String[] Phones = {"6941234567"};
		String[] Emails = {"myemail@mydomain.com"};
		this.AddContact(null, "TestLastName", Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length>0)
		{
			assertTrue(contacts[0].getName().isEmpty());
		}
		else
		{
			fail("Name should be an empty string.");
		}
	}

	/**
	 * Test without phone.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */	
	public void testGetContactsContactWithoutPhone()
	{
		String[] Phones = new String[0];
		String[] Emails = {"myemail@mydomain.com"};
		this.AddContact("TestName",  "TestLastName", Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length>0)
		{
			assertTrue(contacts[0].getPhone().isEmpty());	
		}
		else
		{
			fail("Phone is not an empty string.");
		}
	}

	/**
	 * Test without email.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsContactWithoutEmail()
	{
		String[] Phones = {"6941234567"};
		String Emails[] = new String[0];
		this.AddContact("TestName", "TestLastName", Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length>0)
		{
			assertTrue(contacts[0].getEmail().isEmpty());
		}
		else
		{
			fail("Email is not an empty string.");
		}
	}

	/**
	 * Test with wrong context.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testContactAPIWithEmptyContext()
	{
		this.api = new ContactsAPI(null);
		Contact[] contacts = this.api.getContacts();
		assertNull(contacts);
	}
	
	/**
	 * Test with contacts that have random phone count.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsRandomPhoneNumberCount()
	{
		int RandomPhoneNumberCount = new Random(System.currentTimeMillis()).nextInt(10);
		String[] Phones = new String[RandomPhoneNumberCount];
		for(int i=0;i<RandomPhoneNumberCount;i++)
		{
			Long PhoneBuilder = (long) new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE);
			Phones[i] = "" + PhoneBuilder;
		}
		String[] Emails = {"myemail@mydomain.com"};
		this.AddContact("TestName", "TestLastName", Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length > 0)
		{
			assertEquals(contacts[0].getPhone(),Phones[0]);	
		}
		else
		{
			fail("Phones for first contact didnt match.");
		}
	}
	
	/**
	 * Test with contacts that have random email count.
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContactsRandomEmailCount()
	{
		int RandomEmailCount = new Random(System.currentTimeMillis()).nextInt(10);
		String[] Emails = new String[RandomEmailCount];
		for(int i=0;i<RandomEmailCount;i++)
		{
			Long PhoneBuilder = (long) new Random(System.currentTimeMillis()).nextInt(Integer.MAX_VALUE);
			Emails[i] = "myemail" + PhoneBuilder + "@mydomain.com";
		}
		String[] Phones = {"6941234567"};
		this.AddContact("TestName", "TestLastName", Phones, Emails);
		Contact[] contacts = this.api.getContacts();
		if(contacts.length > 0)
		{
			assertEquals(contacts[0].getPhone(),Phones[0]);	
		}
		else
		{
			fail("Phones for first contact didnt match.");
		}
	}	
}
