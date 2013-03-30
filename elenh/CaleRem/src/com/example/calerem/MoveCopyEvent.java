package com.example.calerem;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MoveCopyEvent extends Activity implements OnClickListener,
		OnItemSelectedListener {

	TextView tvName, tvType, tvDate, tvDesc, tvInfo, tvConName, tvConLast;
	Button btnGo;
	Calendar calendar;
	
	Spinner spnAction;
	String action[] = { "No Action", "Copy", "Move" };
	
	
	c_event event1;
	boolean move;

	public MoveCopyEvent(c_event new_event) {
		event1.v_event_name = new_event.v_event_name;
		event1.v_event_type = new_event.v_event_type;
		event1.v_event_date = new_event.v_event_date;
		event1.v_event_contact = new_event.v_event_contact;
		event1.v_event_description = new_event.v_event_description;

		event1.v_event_date = "20-12-2013";
		event1.v_event_description = "Happy Birthday?";
		event1.v_event_name = "First Event";
		event1.v_event_type = "Birthday";

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move_copy_event);
		initializeVars();
		checkContact(event1);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MoveCopyEvent.this,
				android.R.layout.simple_spinner_item, action);
		spnAction.setAdapter(adapter);
		spnAction.setSelection(0);
		spnAction.setOnItemSelectedListener(this);

		btnGo.setOnClickListener(this);
	}

	private void checkContact(c_event event) {
		// TODO Auto-generated method stub
		if (event.v_event_contact == null) {
			tvInfo.setVisibility(EditText.INVISIBLE);
			tvConName.setVisibility(EditText.INVISIBLE);
			tvConLast.setVisibility(TextView.INVISIBLE);
		} else {
			// else print info in these objects
			tvConName.setText(event.v_event_contact.v_name);
			tvConLast.setText(event.v_event_contact.v_lastname);
		}
	}

	private void initializeVars() {
		// TODO Auto-generated method stub
		tvName = (TextView) findViewById(R.id.tvName);
		tvType = (TextView) findViewById(R.id.tvType);
		tvDate = (TextView) findViewById(R.id.tvDate);
		tvDesc = (TextView) findViewById(R.id.tvDesc);
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		tvConName = (TextView) findViewById(R.id.tvContactName);
		tvConLast = (TextView) findViewById(R.id.tvContactLastname);
		spnAction = (Spinner) findViewById(R.id.spnSelect);
		btnGo = (Button) findViewById(R.id.btnGo);
		move = false;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (move == false){
			
			
		}else{
			
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spnAction.getSelectedItemPosition();
		switch (position) {
		case 0:
			Toast.makeText(getApplicationContext(),
					"You need to select Action", Toast.LENGTH_LONG).show();
			btnGo.setText("@string/btnGo");
			break;
		case 1:
			move = false;
			btnGo.setText(action[1]);
			break;
		case 2:
			move = true;
			btnGo.setText(action[2]);
			break;
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		Toast.makeText(getApplicationContext(),
				"You need to select Action", Toast.LENGTH_LONG).show();
	}

}
