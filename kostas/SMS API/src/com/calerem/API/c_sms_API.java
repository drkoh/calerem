/**
 * 
 */
package com.calerem.API;
import android.telephony.SmsManager;

/**
 * Connects to android SMS API.
 * @author DarkParadise
 * @version 1.0
 */
public class c_sms_API {
	/**
	 * Sends a silent SMS.
	 * @param v_phone Recipient Phone
	 * @param v_text SMS Message Text Maximum 160 Characters
	 * @return boolean on success
	 */
	public boolean f_SendSMS(String v_phone,String v_text)
	{
		if(v_text.length() <= 160)
		{
			SmsManager sms = SmsManager.getDefault();
			sms.sendTextMessage(v_phone, null, v_text, null, null);
			return true;	
		}
		else
		{
			return false;
		}
	}
}
