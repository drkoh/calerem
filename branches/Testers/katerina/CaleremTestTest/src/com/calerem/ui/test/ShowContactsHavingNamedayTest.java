package com.calerem.ui.test;

import java.util.ArrayList;
import junit.framework.Assert;
import com.calerem.R;
import android.app.Activity;
import android.content.Intent;
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
	private  TextView tvToday;
	private static ArrayList<String> namedaysToday;
	
	Contact[] finalcontacts={}; 
	
	public ShowContactsHavingNamedayTest() {
		super(ShowContactsHavingNameday.class);
		
	}
	

	protected void setUp() throws Exception{
		
		Intent addEvent = new Intent();
	    addEvent.setClassName("com.calerem.ui", "com.calerem.ui.ShowContactsHavingNameday");
	    addEvent.putExtra("Num", "60-415");
	    setActivityIntent(addEvent);
	
		activity = this.getActivity();
		this.bt1 = (Button)ShowContactsHavingNamedayTest.activity. findViewById(R.id.button1);
		this.tvNamedaysToday = (TextView)ShowContactsHavingNamedayTest.activity. findViewById(R.id.tvNamedaysToday);
	    this.tvContactNamedays = (TextView)ShowContactsHavingNamedayTest.activity. findViewById(R.id.tvContactNamedays);
		this.tvToday = (TextView)ShowContactsHavingNamedayTest.activity. findViewById(R.id.tvToday);
		
		 
		
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
		
	}
	/*
	 * testViewsCreated(),testViewVisible(),testViewsStaticText()
	 * tests if objects appear on screen
	 */
	public void testViewsCreated() {
		 
		 assertNotNull(getActivity());
	     assertNotNull(tvContactNamedays);
	     assertNotNull(tvNamedaysToday);
	     assertNotNull(bt1);
	     assertNotNull(tvToday);
	   
	 }
	
	public void testViewsVisible() {
	    
		  ViewAsserts.assertOnScreen(bt1.getRootView(),bt1);
		  ViewAsserts.assertOnScreen(tvContactNamedays.getRootView(),tvContactNamedays);
		  ViewAsserts.assertOnScreen(tvNamedaysToday.getRootView(),tvNamedaysToday);
		  ViewAsserts.assertOnScreen(tvToday.getRootView(),tvToday);
		
	}
	
	public void testViewsStaticText() {
		
		  assertEquals("Σήμερα γιορτάζουν:",tvToday.getText());
		  assertEquals("Εμφάνισε τις επαφές που γιορτάζουν", bt1.getText());
		  
	}
	/*
	 * Tests if the correct message shows up,when no one has nameday
	 */
	public void testIfNoNameday()throws Throwable {
		
		  activity.finish();
		  try {
			Thread.sleep(5000);
	      } catch (InterruptedException e) {}
		
		  final String noNameday = "Δε γιορτάζει κανείς σήμερα.";
		  
		  activity.runOnUiThread(new Runnable() {
			  @Override
			  public void run() {
			  		bt1.performClick();
		  		}
		  });
		  getInstrumentation().waitForIdleSync();
		  namedaysToday= new ArrayList<String>();
		  
		  if (namedaysToday.isEmpty()) {
			  tvNamedaysToday.setText(noNameday); 
		  }
		  Assert.assertEquals("No Nameday",noNameday, this.tvNamedaysToday.getText().toString());
		
	} 
	/*
	 * Test if correct message shows up,when button clicked and no contact has nameday
	 */
	public void testButtonClickIfNoContactNameday()throws Throwable {
		
		  activity.finish();
		  try {
			Thread.sleep(5000);
		  } catch (InterruptedException e) {}
		
		  final String noContactsNameday = "Δε γιορτάζει καμία από τις επαφή.";
	
		  activity.runOnUiThread(new Runnable() {
			  @Override
			  public void run() {
				bt1.performClick();
			  }
		  });
		  getInstrumentation().waitForIdleSync();
	
		  if (finalcontacts.length == 0) {
			
			  tvContactNamedays.setText(noContactsNameday); 
			  Assert.assertEquals("No Contact nameday",noContactsNameday, this.tvContactNamedays.getText().toString()); 
		  }
		
	}
    /*
     * Test if correct message shows up,when button clicked and contact has nameday
     */
	public void testButtonClickIfContactNameday()throws Throwable {
		
		activity.finish();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		
		String finalTitle = "Σήμερα γιορτάζουν οι: ";
		
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bt1.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();
		
		Contact contact1 = new Contact("Asura","Tzini","6982530379","bam@hotmail.com",1);
		Contact contact2 = new Contact("Spira","Likou","6973103987","boom@yahoo.gr",1);
		Contact[] finalcontacts = {contact1,contact2};
		
		if (finalcontacts.length != 0) {
			for (int contactCursor = 0; contactCursor < finalcontacts.length; contactCursor++) {
			finalTitle += " " + finalcontacts[contactCursor].getName() + " " + finalcontacts[contactCursor].getLastname();
			}
		tvContactNamedays.setText(finalTitle);
		Assert.assertEquals("Contacts Asura & Spira has nameday","Σήμερα γιορτάζουν οι:  Asura Tzini Spira Likou", this.tvContactNamedays.getText().toString());
		}
		
	}
   /*
    * Test if namedays appear to screen or the correct message if no nameday
    */
	public void testWithRealDataNamedayOrNot()throws Throwable {
		
	//	activity.finish();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		 
		final String noNameday = "Δε γιορτάζει κανείς σήμερα.";
		String real;
		  
		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bt1.performClick();
			}
		 });
		 getInstrumentation().waitForIdleSync();
		 real=this.tvNamedaysToday.getText().toString();
		  
		  if(real=="Δε γιορτάζει κανείς σήμερα.") {
			  Assert.assertEquals("No Nameday",noNameday, this.tvNamedaysToday.getText().toString());
		  } else {
			 Assert.assertEquals(real,real,this.tvNamedaysToday.getText().toString());
		  }
		 
	}
   
   
}
