package com.calerem.test;

import android.test.ActivityUnitTestCase;
import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.ui.NewEvent;
import com.google.gson.Gson;
import android.content.Intent;
import android.widget.Button;

/*
 * Test that intent is null when button clicked and no event inserted
 * @author SpiMeLo
 */
public class NewEventSaveNullEventTest extends ActivityUnitTestCase<NewEvent> {

	private NewEvent activity;
	Contact contact1 = new Contact("Asura","Tzini","6982530379","bam@hotmail.com",1);
	Contact contact2 = new Contact("Spira","Likou","6973103987","boom@yahoo.gr",1);
	Contact v_contact[] ={contact1,contact2};
	private static Gson gson = new Gson();
	
	public NewEventSaveNullEventTest() {
		super( NewEvent.class);
	
	}
	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    Intent intent = new Intent(getInstrumentation().getTargetContext(),
	       NewEvent.class);
	    intent.setClassName("com.calerem.ui", "com.calerem.ui.NewEvent");
	    intent.putExtra("Data", gson.toJson(v_contact));
	   
	    startActivity(intent, null, null);
	    activity = getActivity();
	   
	}
	
	public void testIntentWithNoEvent() {
		 Button buttonSave = (Button) activity.findViewById(R.id.btnSave);
		 getActivity().onClick(buttonSave);
		 
		 Intent triggeredIntent = getStartedActivityIntent();
		    assertNull("Expected intent null", triggeredIntent);
	} 
}

