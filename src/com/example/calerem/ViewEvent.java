package com.example.calerem;

import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ViewEvent extends Activity {
	//Statement of variables
	TextView title,name,last,phone,mail;
	EditText edName, edType, edDate, edDescription, edNameC, edLastC, edPhone, edMail;
	c_event event1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_event);
		initVars();
		Bundle extras = getIntent().getExtras();
		String data = extras.getString("Data");
		event1 = new Gson().fromJson(data,c_event.class);
		checkContact();
		//print event's info in proper visual objects
		edName.setText(event1.v_event_name);
		edType.setText(event1.v_event_type);
		edDate.setText(""+event1.v_event_date);
		edDescription.setText(event1.v_event_description);
	}

	public void initVars(){
		//link vars to visual objects via id method
		edName= (EditText) findViewById (R.id.etName);
		edType= (EditText) findViewById (R.id.etType);
		edDate= (EditText) findViewById (R.id.etDt);
		edDescription = (EditText) findViewById (R.id.etDescription);
		edNameC= (EditText) findViewById (R.id.etNameC);
		edLastC= (EditText) findViewById (R.id.etLast);
		edPhone= (EditText) findViewById (R.id.etPhn);
		edMail= (EditText) findViewById (R.id.etMail);
		title= (TextView) findViewById (R.id.tvContact);
		name= (TextView) findViewById (R.id.tvNameC);
		last= (TextView) findViewById (R.id.tvLast);
		phone= (TextView) findViewById (R.id.tvPhone);
		mail= (TextView) findViewById (R.id.tvMail);
	}
	public void checkContact(){
		//if the event isn't connected to a contract make the visual objects, that exist for printing contact's info, invisible
		if(event1.v_event_contact == null){
			edNameC.setVisibility(EditText.INVISIBLE);
			edLastC.setVisibility(EditText.INVISIBLE);
			edPhone.setVisibility(EditText.INVISIBLE);
			edMail.setVisibility(EditText.INVISIBLE);
			title.setVisibility(TextView.INVISIBLE);
			name.setVisibility(TextView.INVISIBLE);
			last.setVisibility(TextView.INVISIBLE);
			phone.setVisibility(TextView.INVISIBLE);
			mail.setVisibility(TextView.INVISIBLE);
		}
		else{
			//else print info in these objects
			edNameC.setText(event1.v_event_contact.v_name);
			edLastC.setText(event1.v_event_contact.v_lastname);
			edPhone.setText(""+event1.v_event_contact.v_phone);
			edMail.setText(event1.v_event_contact.v_email);
		}
		
	}
	
}
