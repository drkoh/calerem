package com.example.calerem;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewEvent extends Activity {
	//Statement of variables
	Spinner spinner;
	String[] types = {"Birthday", "Nameday"};
	DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
	EditText name = (EditText) findViewById(R.id.etName);
	EditText contact = (EditText) findViewById(R.id.etContact);
	EditText desc = (EditText) findViewById(R.id.etDescription);
	Button buttonSave = (Button) findViewById(R.id.btnSave);
	
	private int year;
	private int month;
	private int day;
	String eventType, eventName, eventDate, eventContact, eventDesc;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_event);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				NewEvent.this, android.R.layout.simple_spinner_item, types);
		
	    spinner = (Spinner) findViewById(R.id.spinnerType);
	    spinner.setAdapter(adapter);
	    spinner.setSelection(0);
	    spinner.setOnItemSelectedListener((OnItemSelectedListener) this);
	    
	    buttonSave.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(spinner.getSelectedItemPosition() < 0) {
					eventType = spinner.getSelectedItem().toString();
				} else {
					Toast.makeText(getApplicationContext(), "You need to select Event Type", Toast.LENGTH_LONG).show();
				}
				
				if(name.getText().toString().trim().length() !=0) {
					eventName = name.getText().toString();
				} else {
					name.setError("You need to enter a name for the Event");
				}
				
				if(datePicker.getDayOfMonth() != 0) {
					day = datePicker.getDayOfMonth();
				} else {
					Toast.makeText(getApplicationContext(), "You need to select a Day", Toast.LENGTH_LONG).show();
				}
				
				if(datePicker.getMonth() != 0) {
					month = datePicker.getMonth();
				} else {
					Toast.makeText(getApplicationContext(), "You need to select Month", Toast.LENGTH_LONG).show();
				}
				
				if(datePicker.getYear() != 0) {
					year = datePicker.getYear();
				} else {
					Toast.makeText(getApplicationContext(), "You need to select Year", Toast.LENGTH_LONG).show();
				}
				
				StringBuilder sb = new StringBuilder()
				.append(day).append("-")
				.append(month + 1).append("-")
				.append(year).append(" ");
				eventDate = sb.toString();					
				
				if(contact.getText().toString().trim().length() !=0) {
					eventContact = contact.getText().toString();
				} else {
					eventContact = " ";
				}
				
				if(desc.getText().toString().trim().length() !=0) {
					eventDesc = desc.getText().toString();
				} else {
					eventDesc = " ";
				}	
				
			}
		});
	}

	
}
