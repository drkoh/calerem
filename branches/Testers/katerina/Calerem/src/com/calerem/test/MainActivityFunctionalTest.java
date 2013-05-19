package com.calerem.test;

import java.util.Calendar;
import java.util.Locale;

import com.calerem.MainActivity;
import com.calerem.R;
import com.calerem.ui.NewEvent;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

/*
 * Test if MainActivity starts NewEvent activity when calendar item clicked 
 * @author SpiMeLo
 */
public class MainActivityFunctionalTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private GridView calendarView;
	private MainActivity activity;
	
	public MainActivityFunctionalTest() {
		super(MainActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(false);
	    activity = getActivity();
		   
	    calendarView = (GridView) activity.findViewById(R.id.calendar);
	}
	
	/*
	 * Test when NewEvent been called from MainActivity,if layout is correct
	 */
	public void testNewEventLayout() throws Exception {
		 
		 ActivityMonitor monitor = getInstrumentation().addMonitor(NewEvent.class.getName(), null, false);
		 
		 TouchUtils.clickView(this, calendarView);
		 NewEvent startedActivity = (NewEvent) monitor
			        .waitForActivityWithTimeout(2000);
			    assertNotNull(startedActivity);
	     
	     Button buttonSave = (Button) startedActivity. findViewById(R.id.btnSave);
		 EditText name = (EditText) startedActivity. findViewById(R.id.etName);
		 EditText desc = (EditText) startedActivity. findViewById(R.id.etDescription);
		 DatePicker datePicker = (DatePicker) startedActivity.findViewById(R.id.datePicker);
		 Spinner spinner_type = (Spinner) startedActivity.findViewById(R.id.Spinner01);
		 
		 ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				 buttonSave);
		 ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				 name);
		 ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				 desc);
		 ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				 datePicker);
		 ViewAsserts.assertOnScreen(startedActivity.getWindow().getDecorView(),
				 spinner_type);
		 
		 assertEquals("Save", buttonSave.getText().toString());
		 assertEquals("Event Name",name.getHint().toString());
		 assertEquals("Description", desc.getHint().toString());
		 assertNotNull(datePicker.getCalendarView());
		 assertEquals("Birthday",spinner_type.getSelectedItem());
		 Calendar _calendar=Calendar.getInstance(Locale.getDefault());
		assertEquals(_calendar.get(Calendar.DAY_OF_MONTH),datePicker.getDayOfMonth()+4);
		 
		 this.sendKeys(KeyEvent.KEYCODE_BACK);
		    TouchUtils.clickView(this, calendarView);
	}
}
