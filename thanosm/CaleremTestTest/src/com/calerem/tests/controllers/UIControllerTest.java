/**
 * 
 */
package com.calerem.tests.controllers;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;

import com.calerem.MainActivity;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.controllers.Database;
import com.calerem.controllers.UIController;
import com.calerem.ui.Configuration;
import com.calerem.ui.NewEvent;
import com.calerem.ui.SendEmail;
import com.calerem.ui.ViewEvent;

/**
 * UIController Tests in JUnit 3.
 * @author DarkParadise
 */
public class UIControllerTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private ActivityMonitor activityMonitor;
	private Database db;
	private Contact contact = new Contact("Kostas","Papadopoulos","6948579123","destiny_kati@yahoo.gr",1);
	private Event event = new Event("Birthday","My Event",1366224072,contact,1,"My Description");
	
	/**
	 * Constructor needed for testing with ActivityInstrumentationTestCase2
	 */
	public UIControllerTest()
	{
		super(MainActivity.class);
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.UIController#newSendEmail(java.lang.Integer, java.lang.String, java.lang.String)}.
	 */
	public void testNewSendEmail() {
		activityMonitor = getInstrumentation().addMonitor(SendEmail.class.getName(), null, false);
		new UIController(this.getActivity()).newSendEmail((Integer) null, "", "");
		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
		assertNotNull(nextActivity);
		nextActivity.finish();
	}

	/**
	 * Test method for {@link com.calerem.controllers.UIController#newViewEvent(int)}.
	 * @throws Exception 
	 */
	public void testNewViewEvent() throws Exception {
		db = new Database(this.getActivity());
		db.factory_reset();
		db.add_contact(contact);
		db.add_event(event);
		activityMonitor = getInstrumentation().addMonitor(ViewEvent.class.getName(), null, false);
		new UIController(this.getActivity()).newViewEvent(1);
		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
		assertNotNull(nextActivity);
		nextActivity.finish();
	}

	/**
	 * Test method for {@link com.calerem.controllers.UIController#newNewEvent()}.
	 */
	public void testNewNewEvent() {
		activityMonitor = getInstrumentation().addMonitor(NewEvent.class.getName(), null, false);
		new UIController(this.getActivity()).newNewEvent(10);
		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
		assertNotNull(nextActivity);
		nextActivity.finish();
	}
	
	/**
	 * Test method for {@link com.calerem.controllers.UIController#newConfiguration()}.
	 */
	public void testNewConfiguration()
	{
		activityMonitor = getInstrumentation().addMonitor(Configuration.class.getName(), null, false);
		new UIController(this.getActivity()).newConfiguration();
		Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 2000);
		assertNotNull(nextActivity);
		nextActivity.finish();
	}
}
