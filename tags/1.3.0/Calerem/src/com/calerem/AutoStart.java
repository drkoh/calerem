package com.calerem;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.calerem.api.ContactsAPI;
import com.calerem.classes.Contact;
import com.google.gson.Gson;

public class AutoStart extends BroadcastReceiver{
	private Contact contact[];
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Bundle extras = arg1.getExtras();
		Intent intent = new Intent(arg0,NotificationService.class);
		contact = null;
		
		contact = new ContactsAPI(arg0.getApplicationContext()).getContacts();
	
			intent.putExtra("Data", new Gson().toJson(contact));
			intent.putExtra("Eortologio", extras.getCharSequenceArrayList("Eortologio"));
			
        	arg0.startService(intent); //3ekinaei tin NotificationService
	}
}
