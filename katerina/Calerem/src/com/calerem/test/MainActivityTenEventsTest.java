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
 * Test if eventButton is updated onResume when an 10 events exists
 * @author SpiMeLo
 */
public class MainActivityTenEventsTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	Database db ;
	MainActivity activity;
	
	public MainActivityTenEventsTest() {
		super(MainActivity.class);
	}
	protected void setUp() throws Exception {
		super.setUp();
		Contact contact1 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",1);
	  	final Event event1 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact1, 1,"Minimal Criminal");
	  	Contact contact2 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",2);
	  	final Event event2 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact2, 2,"Minimal Criminal");
	  	Contact contact3 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",3);
	  	final Event event3 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact3, 3,"Minimal Criminal");
	  	Contact contact4 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",4);
	  	final Event event4 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact4, 4,"Minimal Criminal");
	  	Contact contact5 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",5);
	  	final Event event5 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact5, 5,"Minimal Criminal");
	  	Contact contact6 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",6);
	  	final Event event6 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact6, 6,"Minimal Criminal");
	  	Contact contact7 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",7);
	  	final Event event7 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact7, 7,"Minimal Criminal");
	  	Contact contact8 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",8);
	  	final Event event8 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact8, 8,"Minimal Criminal");
	  	Contact contact9 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",9);
	  	final Event event9 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact9, 9,"Minimal Criminal");
	  	Contact contact10 = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",10);
	  	final Event event10 = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact10, 10,"Minimal Criminal");
	  	try {
			db = new Database(this.getActivity());
			db.factory_reset();
			db.add_contact(contact1);
			db.add_event(event1);
			db.add_contact(contact2);
			db.add_event(event2);
			db.add_contact(contact3);
			db.add_event(event3);
			db.add_contact(contact4);
			db.add_event(event4);
			db.add_contact(contact5);
			db.add_event(event5);
			db.add_contact(contact6);
			db.add_event(event6);
			db.add_contact(contact7);
			db.add_event(event7);
			db.add_contact(contact8);
			db.add_event(event8);
			db.add_contact(contact9);
			db.add_event(event9);
			db.add_contact(contact10);
			db.add_event(event10);
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
		    assertEquals("There are 10 events today.", eventsButton.getText().toString());
	} 
	
	protected void tearDown() throws Exception {
		super.tearDown();
		db = new Database(this.getActivity());
		db.factory_reset();
	}

	
}
