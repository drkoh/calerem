/**
 * 
 */
package com.calerem.handlers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.calerem.api.EmailAPI;

/**
 * Handler for SendEmail Activity Result.
 * @author DarkParadise
 */
public class SendEmailResult {
	/**
	 * Base Constructor.
	 * Connects to the EmailAPI to send the email.
	 * @param Context basecontext
	 * @param int resultCode
	 * @param Intent data
	 */
	public SendEmailResult(Context basecontext,int resultCode, Intent data)
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
