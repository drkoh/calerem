package com.calerem.test;

import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.ui.NewEvent;
import com.google.gson.Gson;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

/*
 * Test if the right message shows up when event name 
 * is empty and button save clicked
 * @author SpiMeLo
 */
public class NewEventNoNameTest extends ActivityInstrumentationTestCase2<NewEvent> {

	private EditText desc;
	private EditText name;
	private Button buttonSave;
	private static  NewEvent activity;
	Contact contact1 = new Contact("Asura","Tzini","6982530379","bam@hotmail.com",1);
	Contact contact2 = new Contact("Spira","Likou","6973103987","boom@yahoo.gr",1);
	Contact v_contact[] ={contact1,contact2};
	String[]  contacts = new String[0];
	private static Gson gson = new Gson();

	public NewEventNoNameTest() {
		super( NewEvent.class);
	}
	
	public void testInsertNoNameGetError()throws Throwable {
	
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		       NewEvent.class);
		    intent.setClassName("com.calerem.ui", "com.calerem.ui.NewEvent");
		    intent.putExtra("Data", gson.toJson(v_contact));
		   
		    setActivityIntent(intent);
		    activity = getActivity();
		    this.buttonSave = (Button)NewEventNoNameTest.activity. findViewById(R.id.btnSave);
		    this.name = (EditText)NewEventNoNameTest.activity. findViewById(R.id.etName);
		    this.desc = (EditText)NewEventNoNameTest.activity. findViewById(R.id.etDescription);
	
		    activity.runOnUiThread(new Runnable() {
		    	@Override
		    	public void run() {
			  
		    		desc.setText("NEW_TEXT");
		    		buttonSave.performClick();
		    	}
		    });
		    getInstrumentation().waitForIdleSync();
		    assertNotNull("You need to enter a name for the Event",name.getText().toString());
	 } 
}
