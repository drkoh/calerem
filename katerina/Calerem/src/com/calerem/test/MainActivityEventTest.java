package com.calerem.test;

import java.io.IOException;
import java.util.Calendar;
import com.calerem.MainActivity;
import com.calerem.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.controllers.Database;

/*
 * Test if eventButton is updated onResume when an event exists
 * @author SpiMeLo
 */
public class MainActivityEventTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	Database db ;
	MainActivity activity;
	
	public MainActivityEventTest() {
		super(MainActivity.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
		Contact contact = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",1);
	  	final Event event = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact, 1,"Minimal Criminal");
		try {
			db = new Database(this.getActivity());
			db.factory_reset();
			db.add_contact(contact);
			db.add_event(event);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public void testEventButton() throws Exception {
	 
		activity=getActivity();
		final Button eventsButton = (Button) activity.findViewById(R.id.eventsButton);
		
		getActivity().runOnUiThread(new Runnable() {
	  		  @Override
		      public void run() {
		    	  Event[] events =db.get_events((Calendar.getInstance().getTimeInMillis()/1000)-86399, (Calendar.getInstance().getTimeInMillis()/1000)); ;
		    	  eventsButton.setText("There are " + events.length + " events today.");
		      }
		    });
	    getInstrumentation().waitForIdleSync();
		    assertEquals("There are 1 events today.", eventsButton.getText().toString());
	} 
	
	protected void tearDown() throws Exception {
		super.tearDown();
		db = new Database(this.getActivity());
		db.factory_reset();
	}

	
}
