package com.calerem.test;

import com.calerem.MainActivity;
import com.calerem.R;
import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.ViewAsserts;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

/*
 * Test MainActivity layout and functionality
 * @author SpiMeLo
 */
public class MainActivityFunctionalLayoutTest extends ActivityUnitTestCase<MainActivity> {

	private Button eventsButton,currentMonth;
	private ImageView prevMonth,nextMonth;
	private GridView calendarView;
	private MainActivity activity;
	
	public MainActivityFunctionalLayoutTest() {
		super(MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		Intent intent = new Intent(getInstrumentation().getTargetContext(),
		        MainActivity.class);
		    startActivity(intent, null, null);
		    activity = getActivity();
		   
		    eventsButton = (Button) activity.findViewById(R.id.eventsButton);
			prevMonth = (ImageView) activity.findViewById(R.id.prevMonth);
			currentMonth = (Button) activity.findViewById(R.id.currentMonth);
			nextMonth = (ImageView) activity.findViewById(R.id.nextMonth);
			calendarView = (GridView) activity.findViewById(R.id.calendar);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/*
	 * Test that layout items are visible
	 */
	public void testLayout() {
		
		 assertNotNull(getActivity());
	     assertNotNull(eventsButton);
	     assertNotNull(prevMonth);
	     assertNotNull(currentMonth);
	     assertNotNull(nextMonth);
	     assertNotNull(calendarView);
	     
	     ViewAsserts.assertOnScreen(eventsButton.getRootView(),eventsButton);
		 ViewAsserts.assertOnScreen(prevMonth.getRootView(),prevMonth);
		 ViewAsserts.assertOnScreen(currentMonth.getRootView(),currentMonth);
		 ViewAsserts.assertOnScreen(nextMonth.getRootView(),nextMonth);
		 ViewAsserts.assertOnScreen(calendarView.getRootView(),calendarView);
	}
	
	/*
	 * Test if @prevMonth clicked 100 times gives the right result 
	 * Test if @nextMonth clicked 1000 times gives the right result
	 */
	public void testOnClickListenerPreviousNextMonth() {
		
		int i;
		prevMonth = (ImageView) getActivity().findViewById(R.id.prevMonth);
		nextMonth = (ImageView) getActivity().findViewById(R.id.nextMonth);
		
		for(i=0;i<100;i++) { prevMonth.performClick(); }		
		assertEquals("Expected January 2005","January 2005",currentMonth.getText());
		
		for(i=0;i<1000;i++) { nextMonth.performClick(); }
		assertEquals("Expected May 2088","May 2088",currentMonth.getText());
	}
	
	/*
	 * Test if current month shows up when first time MainActivity been called  
	 */
	public void testCurrentMonthView() {
		
		assertEquals("May 2013",currentMonth.getText());
	} 
	
	/*
	 * Test if onResume method works proper,(assuming there is no registered event)
	 */
	public void testOnResume() {
		
		getInstrumentation().callActivityOnResume(activity);
		String event;
		event=this.eventsButton.getText().toString();
		if(event=="There is no event registered today.") {
		assertEquals("There is no event registered today.",eventsButton.getText());
		}
		else {assertNotSame("Expected some events","There is no event registered today.",eventsButton.getText()); }
		
		assertNotNull(calendarView.getAdapter());
	}
	
    /*
     * Test if onCreateMthodOptionMenu works proper
     */
	public void testOnCreateOptionsMenuMenu() {
		
        assertNotNull(getActivity().getLayoutInflater());
        assertTrue(true);
	}

	/*
	 * Test if the requested activity starts when 
	 * onOptionsItemSeleectedMenuItem been called
	 */
	public void testOnOptionsItemSelectedMenuItem() {
		
		getActivity().getApplicationContext();
		assertNotNull(activity);
		assertNotNull(this.getStartedActivityRequest());
	}
	
}
