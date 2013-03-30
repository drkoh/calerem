package com.example.calerem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ViewNotification extends Activity{

	EditText edNotyDate,edCelebPerson,edEvType,edRemText,edDateTitle,edSettedDate,edSettedTime;
	c_event event1;
	
	public ViewNotification(c_event new_event) {
		event1.v_event_date=new_event.v_event_date;
		event1.v_event_contact=new_event.v_event_contact;
		event1.v_event_description=new_event.v_event_description;
		event1.v_event_type=new_event.v_event_type;
		event1.v_event_name=new_event.v_event_name;
		//den exei oristhei kapou settedDate - Time tis upenthumisis..!
	}
	
	@Override
	public void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_notification);
		initializeVariables();
		edCelebPerson.setText("Notification Date -" + event1.v_event_name);
		edEvType.setText(event1.v_event_type);
		edNotyDate.setText(event1.v_event_date);
		edRemText.setText(event1.v_event_description);
	}
	
	public void initializeVariables() {
		edNotyDate = (EditText) findViewById (R.id.notiDt);
		edCelebPerson = (EditText) findViewById (R.id.celebPerson);
		edEvType = (EditText) findViewById (R.id.evType);
		edRemText = (EditText) findViewById (R.id.remText);
		edDateTitle = (EditText) findViewById (R.id.dtTitle);
		edSettedDate = (EditText) findViewById (R.id.setDate);
		edSettedTime = (EditText) findViewById (R.id.setTime);
	}
}
