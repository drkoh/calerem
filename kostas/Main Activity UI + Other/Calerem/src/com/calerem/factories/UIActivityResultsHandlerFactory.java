/**
 * 
 */
package com.calerem.factories;

import android.content.Context;
import android.content.Intent;

import com.calerem.handlers.NewEventResult;
import com.calerem.handlers.SendEmailResult;

/**
 * Handler for all Form results.
 * @author DarkParadise
 */
public class UIActivityResultsHandlerFactory {
	private Context basecontext;

	/**
	 * Base Constructor.
	 * @param Context context
	 */
	public UIActivityResultsHandlerFactory(Context context)
	{
		basecontext = context;
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
			new NewEventResult(basecontext, resultCode, data);
			break;
		case 2:
			new SendEmailResult(basecontext,resultCode, data);
			break;
		}
	}
}
