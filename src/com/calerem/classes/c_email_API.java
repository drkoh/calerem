package com.calerem.classes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class c_email_API extends Activity
{
	public void f_send_mail(String v_mail_address, String v_mail_subject, String v_mail_text,Context basecontext)
	{
		Intent v_email_intent = new Intent (android.content.Intent.ACTION_SEND);
		v_email_intent.setType("text/plain");
		String[] Emails = { v_mail_address };
		v_email_intent.putExtra(android.content.Intent.EXTRA_EMAIL, Emails);
		v_email_intent.putExtra(android.content.Intent.EXTRA_SUBJECT, v_mail_subject);
		v_email_intent.putExtra(android.content.Intent.EXTRA_TEXT, v_mail_text);
		basecontext.startActivity(v_email_intent);
	}
}
