/**
 * 
 */
package com.calerem.api;
import android.telephony.SmsManager;

/**
 * Connects to android SMS API.
 * @author DarkParadise
 * @version 1.0
 */
public class SMSAPI {
	/**
	 * Sends a silent SMS.
	 * @param String phone Recipient Phone
	 * @param String text SMS Message Text Maximum 160 Characters
	 * @return boolean on success
	 */
	public boolean sendSMS(String phone,String text)
	{
		if(text.length() <= 160)
		{
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(phone, null, text, null, null);
			return true;	
		}
		else
		{
			return false;
		}
	}
}
