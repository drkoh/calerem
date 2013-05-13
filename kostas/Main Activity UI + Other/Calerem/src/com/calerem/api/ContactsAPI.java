package com.calerem.api;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.calerem.classes.Contact;

/**
 * Connects to android contacts API.
 * @author DarkParadise
 * @version 1.0
 */
public class ContactsAPI{
	
	private Context basecontext;
	private ContentResolver cr;
	private Cursor ContactsCur;
	
	/**
	 * Base constructor that initializes some basic value.
	 * Also queries all contacts from contact API.
	 * @param Context context
	 */
	public ContactsAPI(Context context) {
		basecontext = context;
		cr = basecontext.getContentResolver();
		ContactsCur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	}
	
	/**
	 * Fetches the query from contacts API
	 * Requests additional data from other functions.
	 * @return Contact array.
	 * @see com.calerem.classes.Contact
	 */
	private Contact[] readDatabase()
	{
		Contact v_contact[] = new Contact[ContactsCur.getCount()];
		if (ContactsCur.getCount() > 0) 
		{
			int i = 0;
			while (ContactsCur.moveToNext()) 
			{
				String id = ContactsCur.getString(ContactsCur.getColumnIndex(ContactsContract.Contacts._ID));
				String first_name = "" + this.getFirstName(id);
				String last_name = "" + this.getLastName(id);
				String phone = "";
				String email = "";
				if(this.getPhoneNumbers(id).length > 0)
				{
					phone = this.getPhoneNumbers(id)[0];
				}
				if(this.getEmailAddress(id).length > 0)
				{
					email = this.getEmailAddress(id)[0];
				}
				v_contact[i] = new Contact(first_name,last_name,phone,email,(Integer) null);
				i++;
			}
		}
		this.ContactsCur.close();
		return v_contact;
	}
	
	/**
	 * Requests first name for a specific Contact.
	 * @param String id Contact ID in android database.
	 * @return String first_name
	 */
	private String getFirstName(String id)
	{
		String first_name = "";
		Cursor nameCur = cr.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?", new String[]{id}, null);
		while(nameCur.moveToNext())
		{
			first_name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
		}
		nameCur.close();
		return first_name;
	}
	
	/**
	 * Requests last name for a specific Contact.
	 * @param String id Contact ID in android database.
	 * @return String last_name
	 */
	private String getLastName(String id)
	{
		String last_name = "";
		Cursor nameCur = cr.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?", new String[]{id}, null);
		while(nameCur.moveToNext())
		{
			last_name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
		}
		nameCur.close();
		return last_name;
	}	

	/**
	 * Requests all phone numbers for a specific Contact.
	 * @param String id Contact ID in android database.
	 * @return Long phone
	 */
	private String[] getPhoneNumbers(String id)
	{
		String phone[] = new String[Integer.parseInt(ContactsCur.getString(ContactsCur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))];
		int i = 0;
		if (Integer.parseInt(ContactsCur.getString(ContactsCur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) 
		{
			Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id}, null);
 	        while (pCur.moveToNext()) 
 	        {
 	        	phone[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll("[\\D]", "");
 	        	i++;
 	        } 
 	        pCur.close();
		}
		return phone;
	}
	
	/**
	 * Requests all email addresses for a specific Contact.
	 * @param String id Contact ID in android database.
	 * @return String email
	 */
	private String[] getEmailAddress(String id)
	{
		String email[] = {""};
		int i = 0;
		Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
		while (emailCur.moveToNext()) 
		{ 
			email[i] = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
			i++;
		} 
		emailCur.close();
		return email;
	}
	
	/**
	 * Returns all contact from the API
	 * @return Contact array
	 * @see com.calerem.classes.Contact
	 */
	public Contact[] getContacts()
	{
		return this.readDatabase();
	}
}
