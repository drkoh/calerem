/**
 * Requires a getV_event() method in NewEvent.class that returns the v_event
 */
package com.calerem.ui.test;

import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.ui.NewEvent;
import com.google.gson.Gson;
import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;


public class NewEventTest extends ActivityInstrumentationTestCase2<NewEvent> {

	private EditText desc;
	private EditText name;
	private Button buttonSave;
	private static  Activity activity;
	Contact contact1 = new Contact("Asura","Tzini","6982530379","bam@hotmail.com",1);
	Contact contact2 = new Contact("Spira","Likou","6973103987","boom@yahoo.gr",1);
	Contact v_contact[] ={contact1,contact2};
	String[]  contacts = new String[0];
	private static Gson gson = new Gson();

	public NewEventTest() {
		super( NewEvent.class);
	
	}
	/*
	 * Set name and description and test that event is not empty 
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		
		Intent intent = new Intent();
		intent.setClassName("com.calerem.ui", "com.calerem.ui.NewEvent");
		intent.putExtra("Data", gson.toJson(v_contact));
		
		setActivityIntent(intent);
		
		activity=getActivity();
	  
		this.buttonSave = (Button)NewEventTest.activity. findViewById(R.id.btnSave);
		this.name = (EditText)NewEventTest.activity. findViewById(R.id.etName);
	    this.desc = (EditText)NewEventTest.activity. findViewById(R.id.etDescription);
		
		
	}
	
	public void test()throws Throwable {
		
		  activity.finish();
		  setActivityInitialTouchMode(false);
	
		
		  activity.runOnUiThread(new Runnable() {
			  @Override
			  public void run() {
				  name.setText("Lila");
				  desc.setText("NEW_TEXT");
				  buttonSave.performClick();
				 
		  		}
		  });
		  getInstrumentation().waitForIdleSync();
		 
		  assertNotNull(NewEvent.getV_event());
		  
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
	}
}
