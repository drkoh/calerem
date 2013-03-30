package com.calerem.ui;

import java.io.IOException;

import com.calerem.classes.c_contact;
import com.calerem.classes.c_database;
import com.calerem.classes.c_event;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ui_controller{
	private c_database db;
	private static Gson gson = new Gson();
	private Context basecontext;

	public ui_controller(Context context)
	{
		basecontext = context;
		try {
			db = new c_database(basecontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void f_new_ViewEvent(int v_event_id)
	{
		Intent intent = new Intent(basecontext, ViewEvent.class);
		c_event v_event = db.f_get_event(v_event_id);
		intent.putExtra("Data", gson.toJson(v_event));
		basecontext.startActivity(intent);
	}
	public void f_new_NewEvent()
	{
		Intent intent = new Intent(basecontext, NewEvent.class);
		c_contact v_contact[] = db.f_get_contacts();
		intent.putExtra("Data", gson.toJson(v_contact));
		((Activity) basecontext).startActivityForResult(intent, 1);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		/* Request Codes
		 * 1 = New Event
		 */
		if (requestCode == 1) {
		     if(resultCode == Activity.RESULT_OK){      
		         c_event v_event = gson.fromJson(data.getStringExtra("result"), c_event.class);
		         db.f_add_event(v_event);
		     }
		     if (resultCode == Activity.RESULT_CANCELED) {    
		         //Write your code on no result return 
		     }
		  }			
	}	
}
