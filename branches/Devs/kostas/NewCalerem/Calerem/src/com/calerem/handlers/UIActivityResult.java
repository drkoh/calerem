/**
 * 
 */
package com.calerem.handlers;

import java.io.IOException;

import android.content.Context;
import android.content.Intent;

import com.calerem.controllers.Database;

/**
 * Handler for all Form results.
 * @author DarkParadise
 */
public class UIActivityResult {
	private Database db;
	private Context basecontext;
	
	/**
	 * Base Constructor.
	 * @param Context context
	 */
	public UIActivityResult(Context context)
	{
		basecontext = context;
		try {
			db = new Database(basecontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Uses the request code to decide which form returned the result.
	 * Check the inner comment block for all acceptable codes.
	 * @param int requestCode
	 * @param int resultCode
	 * @param Intent data
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		/* Request Codes
		 * 1 = New Event
		 * 2 = Send Email
		 */
		switch (requestCode)
		{
			case 1:
				new NewEventResult(db, resultCode, data);
				break;
			case 2:
				new SendEmailResult(basecontext,resultCode, data);
			break;
		}
	}
}
