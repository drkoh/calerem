package com.calerem.tests.test;

import java.util.Calendar;
import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.ui.NewEvent;
import com.google.gson.Gson;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * Test if an event is saved when button clicked.Set name,description,event type and a specific date 
 * @author SpiMeLo
 */
public class NewEventTest extends ActivityInstrumentationTestCase2<NewEvent> {

	private EditText desc;
	private EditText name;
	private Button buttonSave;
	private static  NewEvent activity;
	private Spinner spinner_type;
	Contact contact1 = new Contact("Asura","Tzini","6982530379","bam@hotmail.com",1);
	Contact contact2 = new Contact("Spira","Likou","6973103987","boom@yahoo.gr",1);
	Contact v_contact[] ={contact1,contact2};
	String[]  contacts = new String[0];
	private static Gson gson = new Gson();
	private DatePicker datePicker;
	Calendar calendar;
	int YEAR=2013;
	int MONTH=8;
	int DAY_OF_MONTH=18;
	
	public NewEventTest() {
		super( NewEvent.class);
	}
	
	public void testButtonSaveIntentWithEvent()throws Throwable {
		
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
			       NewEvent.class);
			intent.setClassName("com.calerem.ui", "com.calerem.ui.NewEvent");
			intent.putExtra("Data", gson.toJson(v_contact));
			   
			setActivityIntent(intent);
			activity = getActivity();
			this.datePicker = (DatePicker)NewEventTest.activity.findViewById(R.id.datePicker);
			this.buttonSave = (Button)NewEventTest.activity. findViewById(R.id.btnSave);
			this.name = (EditText)NewEventTest.activity. findViewById(R.id.etName);
			this.desc = (EditText)NewEventTest.activity. findViewById(R.id.etDescription);
			this.spinner_type = (Spinner)NewEventTest.activity.findViewById(R.id.Spinner01);
			
			activity.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					name.setText("Lila");
					desc.setText("NEW_TEXT");
					spinner_type.setSelection(1);
					datePicker.init(YEAR, MONTH, DAY_OF_MONTH, null);
					buttonSave.performClick();
				 }
			});
			getInstrumentation().waitForIdleSync();
			assertEquals("Lila", name.getText().toString());
			assertEquals(18, datePicker.getDayOfMonth());
			assertEquals(8, datePicker.getMonth());
			assertEquals(2013, datePicker.getYear());
			assertEquals("NEW_TEXT", desc.getText().toString());
			assertEquals("Nameday",spinner_type.getSelectedItem());
			assertNotNull("Expected intent not null",intent);
	} 

}

