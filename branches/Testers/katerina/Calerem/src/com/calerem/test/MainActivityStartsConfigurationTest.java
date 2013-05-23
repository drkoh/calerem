package com.calerem.test;
//Not finished
import com.calerem.MainActivity;
import com.calerem.R;
import com.calerem.ui.Configuration;
import android.app.Instrumentation.ActivityMonitor;
import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;

public class MainActivityStartsConfigurationTest extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private MainActivity activity;
	
	public MainActivityStartsConfigurationTest() {
		super(MainActivity.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(false);
	    activity = getActivity();
	}
	public void testMenuButtonPressed() throws Exception {
		 
		 ActivityMonitor monitor = getInstrumentation().addMonitor(Configuration.class.getName(), null, false);
		 getInstrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_MENU);
		 getInstrumentation().invokeMenuActionSync(activity, R.id.MainActivityMenu, 0);
		
		 assertEquals(true, getInstrumentation().checkMonitorHit(monitor, 1));
	}
}

