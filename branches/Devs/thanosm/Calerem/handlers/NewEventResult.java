/**
 * 
 */
package com.calerem.handlers;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.calerem.classes.Event;
import com.calerem.controllers.Database;
import com.google.gson.Gson;

/**
 * Handler for NewEvent Activity Result.
 * @author DarkParadise
 */
public class NewEventResult {
	private Database db;
	/**
	 * Base Constructor.
	 * Reads the result event and adds it to the database.
	 * @param Context basecontext;
	 * @param int resultCode
	 * @param Intent data
	 */
	public NewEventResult(Context basecontext,int resultCode, Intent data)
	{
		try {
			db = new Database(basecontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultCode == Activity.RESULT_OK)
		{      
			Event v_event = new Gson().fromJson(data.getStringExtra("result"), Event.class);
			db.add_event(v_event);
		}
		else if (resultCode == Activity.RESULT_CANCELED) 
		{    
			//Write your code on no result return 
		}		
	}
}
