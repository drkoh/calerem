/**
 * 
 */
package com.calerem.tests.api;

import java.util.ArrayList;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.test.ActivityInstrumentationTestCase2;

import com.calerem.MainActivity;
import com.calerem.api.ContactsAPI;
import com.calerem.classes.Contact;

/**
 * ConactsAPI Test in JUnit 3.
 * @author DarkParadise
 */
public class ContactsAPITest extends ActivityInstrumentationTestCase2<MainActivity> {

	public ContactsAPITest()
	{
		super(MainActivity.class);
	}

	/**
	 * Sets up the initial environment so tests can be run.
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		 String DisplayName = "Kostas Papadopoulos";
		 String MobileNumber = "6948579123";
		 String emailID = "destiny@yahoo.gr";
		 ArrayList < ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();
		 ops.add(ContentProviderOperation.newInsert(
		 ContactsContract.RawContacts.CONTENT_URI)
		     .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
		     .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
		     .build());

		 //------------------------------------------------------ Names
		 if (DisplayName != null) 
		 {
		     ops.add(ContentProviderOperation.newInsert(
		     ContactsContract.Data.CONTENT_URI)
		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
		         .withValue(ContactsContract.Data.MIMETYPE,
		     ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
		         .withValue(
		     ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
		     DisplayName).build());
		 }

		 //------------------------------------------------------ Mobile Number                     
		 if (MobileNumber != null) {
		     ops.add(ContentProviderOperation.
		     newInsert(ContactsContract.Data.CONTENT_URI)
		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
		         .withValue(ContactsContract.Data.MIMETYPE,
		     ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
		         .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
		         .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
		     ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
		         .build());
		 }

		 //------------------------------------------------------ Email
		 if (emailID != null) {
		     ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
		         .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
		         .withValue(ContactsContract.Data.MIMETYPE,
		     ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
		         .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
		         .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
		         .build());
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
	 * Reset environment for next test.
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	protected void tearDown()
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
	}

	/**
	 * Test method for {@link com.calerem.api.ContactsAPI#getContacts()}.
	 */
	public void testGetContacts() {
		Contact[] contacts = new ContactsAPI(this.getActivity().getBaseContext()).getContacts();
		if(contacts.length>0)
		{
			assertEquals(contacts[0].getEmail(),"destiny@yahoo.gr");	
		}
		else
		{
			fail("Couldnt read contacts.");
		}
	}

}
