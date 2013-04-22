package com.calerem.controllers;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.calerem.api.ContactsAPI;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.ui.NewEvent;
import com.calerem.ui.SendEmail;
import com.calerem.ui.ShowContactsHavingNameday;
import com.calerem.ui.ViewEvent;
import com.google.gson.Gson;

/**
 * Controller for the UI objects of our application.
 * 
 * @author DarkParadise
 */
public class UIController {
	private Database db;
	private static Gson gson = new Gson();
	private Context basecontext;

	/**
	 * Base Constructor.
	 * 
	 * @param Context
	 *            context
	 */
	public UIController(Context context) {
		basecontext = context;
		try {
			db = new Database(basecontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates a SendEmail form Activity.
	 * 
	 * @param Integer
	 *            contact_id
	 * @param String
	 *            subject
	 * @param String
	 *            text
	 */
	public void newSendEmail(Integer contact_id, String subject, String text) {
		Intent intent = new Intent(basecontext, SendEmail.class);
		if (contact_id != null) {
			Contact contact = db.get_contact(contact_id);
			intent.putExtra("Data", gson.toJson(contact));
		}
		intent.putExtra("Data_subject", subject);
		intent.putExtra("Data_text", text);
		((Activity) basecontext).startActivityForResult(intent, 2);
	}

	/**
	 * Creates a ViewEvent form Activity.
	 * 
	 * @param int v_event_id
	 */
	public void newViewEvent(int v_event_id) {
		Intent intent = new Intent(basecontext, ViewEvent.class);
		Event v_event = db.get_event(v_event_id);
		intent.putExtra("Data", gson.toJson(v_event));
		basecontext.startActivity(intent);
	}

	/**
	 * Creates a NewEvent form Activity.
	 */
	public void newNewEvent() {
		Intent intent = new Intent(basecontext, NewEvent.class);
		Contact[] contact = db.get_contacts();
		intent.putExtra("Data", gson.toJson(contact));
		((Activity) basecontext).startActivityForResult(intent, 1);
	}

	public void newShowContacts() {
		Intent intent = new Intent(basecontext, ShowContactsHavingNameday.class);
		ContactsAPI contApi = new ContactsAPI(basecontext);
		// Contact contact1 = new Contact
		// ("Eutyhis","string","String","string",1);
		// Contact contact2 = new Contact
		// ("Eutyhia","string","String","string",2);
		// Contact finalContact = new Contact
		// ("Lathos apotelesma"," "," "," ",4);
		// db.add_contact(contact1);
		// db.add_contact(contact2);
		// Contact contact[]= {contact1,contact2};

		// Eortologio eort = new Eortologio ();

		// Contact finalContacts[]={finalContact};
		// Contact []
		// finalContactsHavingNameday=FindContacts.searchNames(contact,eort.returnVal());
		Contact[] contacts = contApi.getContacts();

		intent.putExtra("Data", gson.toJson(contacts));
		basecontext.startActivity(intent);
	}

}
