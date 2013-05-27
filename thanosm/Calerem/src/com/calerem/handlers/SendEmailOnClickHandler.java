/**
 * 
 */
package com.calerem.handlers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * On Click handler for SendEmail.
 * @author DarkParadise
 */
public class SendEmailOnClickHandler implements OnClickListener {
	EditText person_email,person_message, person_subject;
	Activity SendEmail;

	/**
	 * Base Constructor.
	 * @param Activity activity
	 * @param EditText person_email
	 * @param EditText person_message
	 * @param EditText person_subject
	 */
	public SendEmailOnClickHandler(Activity activity,EditText person_email,EditText person_message,EditText person_subject)
	{
		this.person_email = person_email;
		this.person_message = person_message;
		this.person_subject = person_subject;
		this.SendEmail = activity;
	}
	
	/**
	 * When called returns variables to the controller.
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View arg0) {
		Intent intent_ret = new Intent();
		intent_ret.putExtra("result_email",person_email.getText().toString());
		intent_ret.putExtra("result_subject",person_subject.getText().toString());
		intent_ret.putExtra("result_text",person_message.getText().toString());
		this.SendEmail.setResult(Activity.RESULT_OK,intent_ret);
		this.SendEmail.finish();
	}
}
