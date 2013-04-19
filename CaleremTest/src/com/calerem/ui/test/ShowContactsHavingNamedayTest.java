/**
 * Unsolved: ThrowsNullPointerException
 * This Test class run sucessful only without
 * @param  Bundle extras = getIntent().getExtras();
 * @param	String data = extras.getString("Data");
 * @param contacts = new Gson().fromJson(data,Contact[].class);
 * @fun getContactsHavingNameday(); from tested class
 * Not sure if it has to do with missing library in test project
 */
package com.calerem.ui.test;

import java.util.ArrayList;
import junit.framework.Assert;
import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.ui.ShowContactsHavingNameday;
import com.google.gson.Gson;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;


/**
 * Test if ShowContacsHavingNameday class works properly
 * To run on command line:
 * adb -e shell am instrument -w -e class com.calerem.ui.test.ShowContactsHavingNamedayTest 
 *              com.calerem.ui.test/android.test.InstrumentationTestRunner
 * @author SpiMeLo
 */
public class ShowContactsHavingNamedayTest extends
		ActivityInstrumentationTestCase2<ShowContactsHavingNameday>  {
	 
	 private Activity activity;
	 private ArrayList<String> contactArray;
	 private EditText text1;
	 Intent intent;
	 String noNameday = "Today nobody celebrates his nameday.";
	 String finalTitle = "Today is Nikos Papoulias having nameday.";
	
	 public ShowContactsHavingNamedayTest() {
		super(ShowContactsHavingNameday.class);
	}

	@Override
	protected void setUp(){
		 this.activity = this.getActivity();
	     this.text1 = (EditText) this.activity.findViewById(com.calerem.R.id.editText1);
	     contactArray = new ArrayList<String>();
	     //intent.setClassName("com.calerem.ui", "com.calerem.ui.ShowContactHavingNamday");
		 //intent.getIntExtra("Data",0);
		 //String data = intent.getStringExtra("Data");
		// contacts=new Gson().fromJson(data,Contact[].class);
	     
	}	

	protected void tearDown() throws Exception {
		super.tearDown();
		
	}

	/**
	 * Test if the views created
	 * @see android.test.ActivityInstrumentationTestCase2#testViewsCreated()
	 */
	 public void testViewsCreated() {
		 
		 assertNotNull(getActivity());
	     assertNotNull(text1);
	   
	 }
	
	/**
	 * Test if the views are visible on screen
	 * @see android.test.ActivityInstrumentationTestCase2#testViewsVisible()
	 */
	
	  public void testViewsVisible() {
	    
		  ViewAsserts.assertOnScreen(text1.getRootView(),text1);
	  }
	  
	/**
	 * Test if expected result appears on screen
	 * when contactArray is empty
	 * @see android.test.ActivityInstrumentationTestCase2#testContactArrayIsEmpty()
	 * @param contactArray
	 * @param text1  
	 */
	  public void testContactArrayIsEmpty() {
		     
		  Assert.assertEquals(noNameday, this.text1.getText().toString());

	  }
	  
	/**
	 * Test if expected result appears on screen
	 * when contactArray has values
	 * @param contactArray
	 * @param text
	 */
	  public void testContactArrayHasNames() { //FIXME Works only without some parametres (refer to the top)
		  contactArray.add("Nikos Papoulias");
		  Assert.assertEquals(finalTitle, this.text1.getText().toString());
	  }
	  
	  public void testException() {//XXX dont know what bad its bad
		  try {
		    	//some code
		    	fail("Thrown exception");
		    } catch (Throwable expected) {
		    	assertEquals(NullPointerException.class,ShowContactsHavingNameday.class);
		    }
	  }
	  public void testGetContactsHavingNameday() {
			fail("Not yet implemented");
		}

		public void testOnResume() {
			fail("Not yet implemented");
		}

		public void testOnPause() {
			fail("Not yet implemented");
		}

		public void testOnStop() {
			fail("Not yet implemented");
		}

		public void testOnWindowFocusChanged() {
			fail("Not yet implemented");
		}

		public void testFinish() {
			fail("Not yet implemented");
		}

	 
	 
	

}
