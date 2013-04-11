package com.calerem.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.handlers.NewEventSpinnerContact;
import com.calerem.handlers.NewEventSpinnerType;
import com.google.gson.Gson;

/**
 * Form to create a new event.
 * @author DarkParadise
 */
public class NewEvent extends Activity implements View.OnClickListener {
	private String eventName, eventDesc;
	private String[] types = {"Birthday", "Nameday"},contacts;
	private long epoch;
	private Date date;
	private Contact contacts_obj[];
	private Event v_event;	
	private Spinner spinner_type,spinner_contact;
	private DatePicker datePicker;
	private EditText name,desc;
	private Button buttonSave;
	private NewEventSpinnerType spinner_type_listener;
	private NewEventSpinnerContact spinner_contact_listener;
	
	/**
	 * Link variables to visual objects.
	 */
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
		spinner_type_listener = new NewEventSpinnerType();
	    spinner_contact_listener = new NewEventSpinnerContact();
	    spinner_type.setOnItemSelectedListener(spinner_type_listener);
	    spinner_contact.setOnItemSelectedListener(spinner_contact_listener);	    
	    buttonSave.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_event);
		Bundle extras = getIntent().getExtras();
		String data = extras.getString("Data");
		contacts_obj = new Gson().fromJson(data,Contact[].class);
		contacts = new String[contacts_obj.length+1];
		contacts[0] = "None";
		for(int i = 0;i<contacts_obj.length;i++)
		{
			contacts[i+1] = contacts_obj[i].getName() + " " + contacts_obj[i].getLastname();
		}
		this.initVars();
	}

	@Override
	public void onClick(View v) {
		try 
		{
			SimpleDateFormat epoch_parser  = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
			date = epoch_parser.parse("" + datePicker.getYear() + "-" + (datePicker.getMonth()+1) + "-" + datePicker.getDayOfMonth());
			epoch = date.getTime() / 1000;
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
		
		if(desc.getText().toString().trim().length() !=0) 
		{
			eventDesc = desc.getText().toString();
		} 
		else 
		{
			eventDesc = "";
		}
		
		if(name.getText().toString().trim().length() !=0) 
		{
			eventName = name.getText().toString();
			if(spinner_contact_listener.getContactPosition() == 0)
			{
				v_event = new Event(spinner_type_listener.getEventType(),eventName,(int) epoch,(Contact) null,(Integer) null,eventDesc);
			}
			else
			{
				v_event = new Event(spinner_type_listener.getEventType(),eventName,(int) epoch,contacts_obj[spinner_contact_listener.getContactPosition()-1],(Integer) null,eventDesc);
			}
			Intent intent_ret = new Intent();
			intent_ret.putExtra("result", new Gson().toJson(v_event));
			setResult(RESULT_OK,intent_ret);
			finish();
		} 
		else 
		{
			name.setError("You need to enter a name for the Event");
		}
	}

	
}
