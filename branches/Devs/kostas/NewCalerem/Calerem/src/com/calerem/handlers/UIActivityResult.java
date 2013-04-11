/**
 * 
 */
package com.calerem.handlers;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.calerem.api.EmailAPI;
import com.calerem.classes.Event;
import com.calerem.controllers.Database;
import com.google.gson.Gson;

/**
 * @author DarkParadise
 *
 */
public class UIActivityResult {
	private Database db;
	private Context basecontext;
	private Gson gson;
	
	public UIActivityResult(Context context)
	{
		basecontext = context;
		try {
			db = new Database(basecontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gson = new Gson();
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
				this.NewEventResult(resultCode, data);
				break;
			case 2:
				this.NewSendEmail(resultCode, data);
			break;
		}
	}
	private void NewEventResult(int resultCode, Intent data)
	{
		if(resultCode == Activity.RESULT_OK)
		{      
			Event v_event = gson.fromJson(data.getStringExtra("result"), Event.class);
			db.add_event(v_event);
		}
		else if (resultCode == Activity.RESULT_CANCELED) 
		{    
			//Write your code on no result return 
		}		
	}
	private void NewSendEmail(int resultCode, Intent data)
	{
		if(resultCode == Activity.RESULT_OK)
		{      
			String v_email = data.getStringExtra("result_email");
			String v_subject = data.getStringExtra("result_subject");
			String v_text = data.getStringExtra("result_text");
			new EmailAPI().sendMail(v_email, v_subject, v_text,basecontext);
		}
		else if (resultCode == Activity.RESULT_CANCELED) 
		{    
			//Write your code on no result return 
		}		
	}

}
