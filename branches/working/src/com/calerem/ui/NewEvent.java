package com.calerem.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.calerem.R;
import com.calerem.classes.c_contact;
import com.calerem.classes.c_event;
import com.calerem.interfaces.i_NewEvent;
import com.google.gson.Gson;

public class NewEvent extends Activity implements i_NewEvent {
	//Statement of variables
	private Spinner spinner_type,spinner_contact;
	private String[] types = {"Birthday", "Nameday"};
	private String[] contacts;
	private DatePicker datePicker;
	protected EditText name,contact,desc;
	private Button buttonSave;
	private Date date;
	protected long epoch;
	protected int contact_position;
	private c_contact contacts_obj[];
	private c_event v_event;
	protected String eventType, eventName, eventContact, eventDesc;
	
	private void initVars()
	{
		datePicker = (DatePicker) findViewById(R.id.datePicker);
		name = (EditText) findViewById(R.id.etName);
		desc = (EditText) findViewById(R.id.etDescription);
		buttonSave = (Button) findViewById(R.id.btnSave);	
	    spinner_type = (Spinner) findViewById(R.id.Spinner01);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(NewEvent.this, android.R.layout.simple_spinner_item, types);
	    spinner_type.setAdapter(adapter);
	    spinner_type.setSelection(0);	
	    spinner_contact = (Spinner) findViewById(R.id.spinner_contacts);
		adapter = new ArrayAdapter<String>(NewEvent.this, android.R.layout.simple_spinner_item, contacts);
	    spinner_contact.setAdapter(adapter);
	    spinner_contact.setSelection(0);	    
	}
	
	private class Spinner_typeActivity extends Activity implements OnItemSelectedListener
	{
		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			eventType = (String) arg0.getSelectedItem();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}
		
	}
	private class Spinner_contactActivity extends Activity implements OnItemSelectedListener
	{
		
		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			contact_position = arg0.getSelectedItemPosition();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_event);
		Bundle extras = getIntent().getExtras();
		String data = extras.getString("Data");
		contacts_obj = new Gson().fromJson(data,c_contact[].class);
		contacts = new String[contacts_obj.length+1];
		contacts[0] = "None";
		for(int i = 0;i<contacts_obj.length;i++)
		{
			contacts[i+1] = contacts_obj[i].getV_name() + " " + contacts_obj[i].getV_lastname();
		}
		this.initVars();
	    Spinner_typeActivity spinner_type_listener = new Spinner_typeActivity();
	    spinner_type.setOnItemSelectedListener(spinner_type_listener);
	    Spinner_contactActivity spinner_contact_listener = new Spinner_contactActivity();
	    spinner_contact.setOnItemSelectedListener(spinner_contact_listener);	    
	    buttonSave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try {
					SimpleDateFormat epoch_parser  = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
					date = epoch_parser.parse("" + datePicker.getYear() + "-" + (datePicker.getMonth()+1) + "-" + datePicker.getDayOfMonth());
					epoch = date.getTime() / 1000;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				if(desc.getText().toString().trim().length() !=0) {
					eventDesc = desc.getText().toString();
				} else {
					eventDesc = "";
				}
				
				if(name.getText().toString().trim().length() !=0) {
					eventName = name.getText().toString();
					if(contact_position == 0)
					{
						v_event = new c_event(eventType,eventName,(int) epoch,(c_contact) null,(Integer) null,eventDesc);
					}
					else
					{
						v_event = new c_event(eventType,eventName,(int) epoch,contacts_obj[contact_position-1],(Integer) null,eventDesc);
					}
					Intent intent_ret = new Intent();
					intent_ret.putExtra("result", new Gson().toJson(v_event));
					setResult(RESULT_OK,intent_ret);
					finish();
				} else {
					name.setError("You need to enter a name for the Event");
				}
				
			}
		});
	}

	
}
