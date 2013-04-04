package com.calerem.API;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.calerem.classes.c_contact;

/**
 * Connects to android contacts API.
 * @author DarkParadise
 * @version 1.0
 */
public class c_contacts_API{
	
	private Context basecontext;
	private ContentResolver cr;
	private Cursor ContactsCur;
	
	/**
	 * Base constructor that initializes some basic value.
	 * Also queries all contacts from contact API.
	 * @param context
	 */
	public c_contacts_API(Context context) {
		basecontext = context;
		cr = basecontext.getContentResolver();
		ContactsCur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
	}
	
	/**
	 * Fetches the query from contacts API
	 * Requests additional data from other functions.
	 * @return c_contact[]
	 * @see com.calerem.classes.c_contact
	 */
	private c_contact[] f_read_database()
	{
		c_contact v_contact[] = new c_contact[ContactsCur.getCount()];
		if (ContactsCur.getCount() > 0) 
		{
			int i = 0;
			while (ContactsCur.moveToNext()) 
			{
				String id = ContactsCur.getString(ContactsCur.getColumnIndex(ContactsContract.Contacts._ID));
				String first_name = this.f_get_first_name(id);
				String last_name = this.f_get_last_name(id);
				long phone = this.f_get_phone_numbers(id)[0];
				String email = this.f_get_email_address(id)[0];
				v_contact[i] = new c_contact(first_name,last_name,phone,email,(Integer) null);
				i++;
			}
		}
		return v_contact;
	}
	
	/**
	 * Requests first name for a specific Contact.
	 * @param id Contact ID in android database.
	 * @return String first_name
	 */
	private String f_get_first_name(String id)
	{
		String first_name = "";
		Cursor nameCur = cr.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?", new String[]{id}, null);
		while(nameCur.moveToNext())
		{
			first_name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
		}
		return first_name;
	}
	
	/**
	 * Requests last name for a specific Contact.
	 * @param id Contact ID in android database.
	 * @return String last_name
	 */
	private String f_get_last_name(String id)
	{
		String last_name = "";
		Cursor nameCur = cr.query(ContactsContract.Data.CONTENT_URI, null, ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID + " = ?", new String[]{id}, null);
		while(nameCur.moveToNext())
		{
			last_name = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
		}
		return last_name;
	}	

	/**
	 * Requests all phone numbers for a specific Contact.
	 * @param id Contact ID in android database.
	 * @return Long phone
	 */
	private long[] f_get_phone_numbers(String id)
	{
		long phone[] = new long[Integer.parseInt(ContactsCur.getString(ContactsCur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))];
		int i = 0;
		if (Integer.parseInt(ContactsCur.getString(ContactsCur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) 
		{
			Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", new String[]{id}, null);
 	        while (pCur.moveToNext()) 
 	        {
 	        	String temp = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll("[\\D]", "");
 	        	phone[i] = Long.parseLong(temp);
 	        	i++;
 	        } 
 	        pCur.close();
		}
		return phone;
	}
	
	/**
	 * Requests all email addresses for a specific Contact.
	 * @param id Contact ID in android database.
	 * @return String email
	 */
	private String[] f_get_email_address(String id)
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
	 * @return c_contact[]
	 * @see com.calerem.classes.c_contact
	 */
	public c_contact[] f_get_contacts()
	{
		return this.f_read_database();
	}
}
