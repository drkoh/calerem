package com.calerem.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.handlers.SendEmailOnClickHandler;
import com.google.gson.Gson;

/**
 * Form that enables the user to write an email.
 * @author DarkParadise
 */
public class SendEmail extends Activity  
{
	private EditText person_email,person_message, person_subject;
	private Button send_email;
	private Contact contact;
	
	/**
	 * Prepares the form. Reads input values.
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sendemail);
		Bundle extras = getIntent().getExtras();
		String data = extras.getString("Data");
		String data_subject = extras.getString("Data_subject");
		String data_text = extras.getString("Data_text");
		contact = new Gson().fromJson(data,Contact.class);
		initializeVars();
		if(contact != null)
		{
			if(contact.getEmail().isEmpty())
			{
				person_email.setText(contact.getEmail());
			}
		}		
		if(!data_subject.isEmpty())
		{
			person_subject.setText(data_subject);
		}
		if(!data_text.isEmpty())
		{
			person_message.setText(data_text);
		}
		send_email.setOnClickListener(new SendEmailOnClickHandler(this, person_email, person_email, person_email));
	}
	
	/**
	 * Link variables to visual objects.
	 */
	private void initializeVars() {
		person_email = (EditText) findViewById(R.id.etEmail);
		person_subject = (EditText) findViewById(R.id.etSubject);
		person_message = (EditText) findViewById(R.id.etMessage);
		send_email = (Button) findViewById(R.id.bSendEmail);
	}
}
