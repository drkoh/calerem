package com.example.calerem;

import com.example.calerem.c_event;

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
	String[] types = { "No Selection", "Birthday", "Nameday" };
	DatePicker datePicker;
	EditText etName, etContact, etDesc;
	Button buttonSave;
	String eventType, eventName, eventDate, eventContact, eventDesc;
	c_event newEvent;
	c_contact contact;

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
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		etName = (EditText) findViewById(R.id.etName);
		etContact = (EditText) findViewById(R.id.etContact);
		etDesc = (EditText) findViewById(R.id.etDescription);
		buttonSave = (Button) findViewById(R.id.btnSave);
		spinner = (Spinner) findViewById(R.id.spinnerType);
	}

	@Override
	public void onClick(View v) {

		if (etName.getText().toString().trim().length() == 0) {
			etName.setError("You need to enter a name for the Event");
		} else {
			newEvent.v_event_name = etName.getText().toString();
		}

		eventDate = "" + datePicker.getDayOfMonth() + "-"
				+ datePicker.getMonth() + "-" + datePicker.getYear();

		if (etContact.getText().toString().trim().length() == 0) {
			eventContact = " ";
		} else {
			//newEvent.v_event_contact = contact.v_name(etContact.getText()); // TODO
																			// handle
																			// as
																			// object
		}

		if (etDesc.getText().toString().trim().length() == 0) {
			eventDesc = " ";
		} else {
			newEvent.v_event_description = etDesc.getText().toString();
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			Toast.makeText(getApplicationContext(),
					"You need to select Event Type", Toast.LENGTH_LONG).show();
			break;
		case 1:
			newEvent.v_event_type = types[1];
			break;
		case 2:
			newEvent.v_event_type = types[2];
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		Toast.makeText(getApplicationContext(),
				"You need to select Event Type", Toast.LENGTH_LONG).show();
	}

}
