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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MoveCopyEvent extends Activity implements OnClickListener,
		OnItemSelectedListener {

	TextView tvName, tvType, tvDate, tvDesc, tvInfo, tvConName, tvConLast;
	TextView tvNewDate;
	Button btnGo;
	Calendar calendar;
	DatePicker datePicker;
	String[] date;
	int day, month, year;
	
	Spinner spnAction;
	String action[] = { "Copy", "Move" };
	
	
	c_event event1, event2;

	public MoveCopyEvent(c_event new_event) {
/*		event1.v_event_name = new_event.v_event_name;
		event1.v_event_type = new_event.v_event_type;
		event1.v_event_date = new_event.v_event_date;
		event1.v_event_contact = new_event.v_event_contact;
		event1.v_event_description = new_event.v_event_description;
*/
		event1.v_event_date = "20-12-2013";
		event1.v_event_description = "Happy Birthday?";
		event1.v_event_name = "First Event";
		event1.v_event_type = "Birthday";
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.move_copy_event);
		initializeVars();
		datePicker.init(year, (month - 1), day, null);
		checkContact(event1);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(MoveCopyEvent.this,
				android.R.layout.simple_spinner_item, action);
		spnAction.setAdapter(adapter);
		spnAction.setSelection(0);
		spnAction.setOnItemSelectedListener(this);

		btnGo.setOnClickListener(this);
	}

	private void checkContact(c_event event) {
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
		tvName = (TextView) findViewById(R.id.tvName);
		tvType = (TextView) findViewById(R.id.tvType);
		tvDate = (TextView) findViewById(R.id.tvDate);
		tvDesc = (TextView) findViewById(R.id.tvDesc);
		tvInfo = (TextView) findViewById(R.id.tvInfo);
		tvConName = (TextView) findViewById(R.id.tvContactName);
		tvConLast = (TextView) findViewById(R.id.tvContactLastname);
		spnAction = (Spinner) findViewById(R.id.spnSelect);
		btnGo = (Button) findViewById(R.id.btnGo);
		date = event1.v_event_date.split("-");
		day = Integer.valueOf(date[0].trim());
		month = Integer.valueOf(date[1].trim());
		year = Integer.valueOf(date[2].trim());
		
		tvNewDate = (TextView)findViewById(R.id.tvNewDate);
	}

	@Override
	public void onClick(View arg0) {
		//COPY EVENT TO NEW DATE
		event2.v_event_date = ("" + datePicker.getDayOfMonth() + "-"
				+ (datePicker.getMonth() + 1) + "-" + datePicker.getYear());
		event2.v_event_name = event1.v_event_name;
		event2.v_event_type = event1.v_event_type;
		event2.v_event_contact = event1.v_event_contact;
		event2.v_event_description = event1.v_event_description;
		tvDate.setText(event2.v_event_date);
		//TODO ADD event2 to database
		if (btnGo.getText().equals(action[1])){ //MOVE
			//TODO DELETE event1 from database
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		int position = spnAction.getSelectedItemPosition();
		switch (position) {
		case 0:
			btnGo.setText(action[0]);
			break;
		case 1:
			btnGo.setText(action[1]);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}

}
