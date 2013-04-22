package com.calerem.test;

import java.util.ArrayList;
import junit.framework.Assert;
import com.calerem.R;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import com.calerem.classes.Contact;
import com.calerem.ui.ShowContactsHavingNameday;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;



public class ShowContactsHavingNamedayTest extends
		ActivityInstrumentationTestCase2<ShowContactsHavingNameday> {

	private static  Activity activity;
	private  Button bt1;
	private  TextView tvNamedaysToday;
	private  TextView tvContactNamedays;
	private static ArrayList<String> namedaysToday;
	private static Contact contacts[];
	
	
	public ShowContactsHavingNamedayTest() {
		super(ShowContactsHavingNameday.class);
		
		}
	

	protected void setUp() throws Exception{
		 ShowContactsHavingNamedayTest.activity = this.getActivity();
		 this.bt1 = (Button)ShowContactsHavingNamedayTest.activity. findViewById(R.id.button1);
		 this.tvNamedaysToday = (TextView)ShowContactsHavingNamedayTest.activity. findViewById(R.id.tvNamedaysToday);
		 this.tvContactNamedays = (TextView)ShowContactsHavingNamedayTest.activity. findViewById(R.id.tvContactNamedays);
		 namedaysToday=null;
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
	}
	
	public void testViewsCreated() {
		 
		 assertNotNull(getActivity());
	     assertNotNull(tvContactNamedays);
	     assertNotNull(tvNamedaysToday);
	     assertNotNull(bt1);
	   
	 }
	
	public void testViewsVisible() {
	    
		  ViewAsserts.assertOnScreen(bt1.getRootView(),bt1);
		  ViewAsserts.assertOnScreen(tvContactNamedays.getRootView(),tvContactNamedays);
		  ViewAsserts.assertOnScreen(tvNamedaysToday.getRootView(),tvNamedaysToday);
		
	}
	
	public void testIfNoNameday()throws Throwable {
		String noNameday = "Δε γιορτάζει κανείς σήμερα.";
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bt1.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		
		
		Assert.assertEquals(noNameday, this.tvNamedaysToday.getText().toString());
		
	}
	
   public void testButtonClickIfNoContactNameday()throws Throwable {
		activity.finish();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		activity=this.getActivity();
		String noContactsNameday = "Δε γιορτάζει καμία από τις επαφή.";
		Contact[] finalcontacts = null;
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bt1.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		Assert.assertEquals(noContactsNameday, this.tvContactNamedays.getText().toString());
	}
   
   
 }
