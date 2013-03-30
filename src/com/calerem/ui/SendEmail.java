package com.calerem.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.calerem.R;
import com.calerem.classes.c_contact;
import com.google.gson.Gson;

public class SendEmail extends Activity implements View.OnClickListener  
{
	EditText v_person_email,v_person_message, v_person_subject;
	String v_email_address, v_text_message, v_email_subject;
	Button v_send_email;
	c_contact v_contact;
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
		v_contact = new Gson().fromJson(data,c_contact.class);
		initializeVars();
		if(v_contact != null)
		{
			if(v_contact.v_email.isEmpty())
			{
				v_person_email.setText(v_contact.v_email);
			}
		}		
		if(!data_subject.isEmpty())
		{
			v_person_subject.setText(data_subject);
		}
		if(!data_text.isEmpty())
		{
			v_person_message.setText(data_text);
		}
		v_send_email.setOnClickListener(this);
	}
	
	private void initializeVars() {
		// TODO Auto-generated method stub
		
		v_person_email = (EditText) findViewById(R.id.etEmail);
		v_person_subject = (EditText) findViewById(R.id.etSubject);
		v_person_message = (EditText) findViewById(R.id.etMessage);
		v_send_email = (Button) findViewById(R.id.bSendEmail);
	}
	
	public void onClick(View v)
	{
		Intent intent_ret = new Intent();
		intent_ret.putExtra("result_email",v_person_email.getText().toString());
		intent_ret.putExtra("result_subject",v_person_subject.getText().toString());
		intent_ret.putExtra("result_text",v_person_message.getText().toString());
		setResult(RESULT_OK,intent_ret);
		finish();
	}
}
