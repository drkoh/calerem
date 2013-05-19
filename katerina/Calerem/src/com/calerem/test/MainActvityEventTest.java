package com.calerem.test;

import java.util.Calendar;
import com.calerem.MainActivity;
import com.calerem.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;

/*
 * Test if eventButton is updated onResume when an event exists
 * @author SpiMeLo
 */
public class MainActvityEventTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	public MainActvityEventTest() {
		super(MainActivity.class);
	}
	
	public void testEventButton() throws Exception {
	 
		MainActivity activity=getActivity();
		final Button eventsButton = (Button) activity.findViewById(R.id.eventsButton);
		Contact contact = new Contact("Anita","Gate","6982245679","anitaforever@hotmail.com",1);
	  	final Event event = new Event("Birthday","New",Calendar.getInstance().getTimeInMillis()/1000,contact, 1,"Minimal Criminal");
 	 
	  	getActivity().runOnUiThread(new Runnable() {
	  		  @Override
		      public void run() {
		    	  Event[] events ={event};
		    	  eventsButton.setText("There are " + events.length + " events today.");
		      }
		    });
	    getInstrumentation().waitForIdleSync();
		    assertEquals("There are 1 events today.", eventsButton.getText().toString());
	} 

	
}
