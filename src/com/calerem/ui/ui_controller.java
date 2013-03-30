package com.calerem.ui;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.calerem.classes.c_contact;
import com.calerem.classes.c_database;
import com.calerem.classes.c_email_API;
import com.calerem.classes.c_event;
import com.google.gson.Gson;

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
	
	public void f_new_SendEmail(Integer contact_id,String subject,String text)
	{
		Intent intent = new Intent(basecontext, SendEmail.class);
		if(contact_id != null)
		{
			c_contact v_contact = db.f_get_contact(contact_id);
			intent.putExtra("Data", gson.toJson(v_contact));
		}
		intent.putExtra("Data_subject", subject);
		intent.putExtra("Data_text", text);
		((Activity) basecontext).startActivityForResult(intent, 2);
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
		 * 2 = Send Email
		 */
		switch (requestCode)
		{
			case 1:
				if(resultCode == Activity.RESULT_OK)
				{      
					c_event v_event = gson.fromJson(data.getStringExtra("result"), c_event.class);
					db.f_add_event(v_event);
				}
				else if (resultCode == Activity.RESULT_CANCELED) 
				{    
					//Write your code on no result return 
				}
				break;
			case 2:
				if(resultCode == Activity.RESULT_OK)
				{      
					String v_email = data.getStringExtra("result_email");
					String v_subject = data.getStringExtra("result_subject");
					String v_text = data.getStringExtra("result_text");
					new c_email_API().f_send_mail(v_email, v_subject, v_text,basecontext);
				}
				else if (resultCode == Activity.RESULT_CANCELED) 
				{    
					//Write your code on no result return 
				}
				break;
		}			
	}	
}
