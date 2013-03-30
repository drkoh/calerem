package com.example.calerem;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewEvent extends Activity implements OnClickListener,
		OnItemSelectedListener {
	// Statement of variables
	Spinner spinner;
	String[] types = { "Birthday", "Nameday" };
	DatePicker datePicker;
	EditText name, contact, desc;
	Button buttonSave;
	String eventType, eventName, eventDate, eventContact, eventDesc;
	private int year;
	private int month;
	private int day;
	c_event newEvent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_event);
		initializeVars();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewEvent.this,
				android.R.layout.simple_spinner_item, types);
		spinner.setAdapter(adapter);
		spinner.setSelection(0);
		spinner.setOnItemSelectedListener(this);

		buttonSave.setOnClickListener(this);

	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		name = (EditText) findViewById(R.id.etName);
		contact = (EditText) findViewById(R.id.etContact);
		desc = (EditText) findViewById(R.id.etDescription);
		buttonSave = (Button) findViewById(R.id.btnSave);
		spinner = (Spinner) findViewById(R.id.spinnerType);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (name.getText().toString().trim().length() == 0) {
			name.setError("You need to enter a name for the Event");
		} else {
			eventName = name.getText().toString();
		}

		StringBuilder sb = new StringBuilder().append(day).append("-")
				.append(month + 1).append("-").append(year).append(" ");
		eventDate = sb.toString();

		if (contact.getText().toString().trim().length() == 0) {
			eventContact = " ";
		} else {
			eventContact = contact.getText().toString();
		}

		if (desc.getText().toString().trim().length() == 0) {
			eventDesc = " ";
		} else {
			eventDesc = desc.getText().toString();
		}

		/*
		 * newEvent.f_change_event_type(eventType);
		 * newEvent.f_change_event_name(eventName);
		 * newEvent.f_change_event_date(eventDate); //TODO event date is a String not an integer
		 * newEvent.f_change_event_contact(eventContact); //TODO Contact is A Contact obj not String
		 * 
		 * //newEvent.c_event(eventType,eventName,eventDate,eventContact,"1",eventDesc);
		 */
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			eventType = types[0];
			break;
		case 1:
			eventType = types[1];
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),
				"You need to select Event Type", Toast.LENGTH_LONG).show();
	}

}
