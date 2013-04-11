package com.calerem.api;

import android.content.Context;
import android.content.Intent;

/**
 * Enables the application to send emails.
 * @author DarkParadise
 */
public class EmailAPI
{
	/**
	 * Sends an email.
	 * @param mail_address
	 * @param mail_subject
	 * @param mail_text
	 * @param basecontext
	 */
	public void sendMail(String mail_address, String mail_subject, String mail_text,Context basecontext)
	{
		Intent email_intent = new Intent (android.content.Intent.ACTION_SEND);
		email_intent.setType("text/plain");
		String[] Emails = { mail_address };
		email_intent.putExtra(android.content.Intent.EXTRA_EMAIL, Emails);
		email_intent.putExtra(android.content.Intent.EXTRA_SUBJECT, mail_subject);
		email_intent.putExtra(android.content.Intent.EXTRA_TEXT, mail_text);
		basecontext.startActivity(email_intent);
	}
}
