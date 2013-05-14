package com.calerem.ui;

//import junit.framework.TestCase;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class ViewEventTest extends UiAutomatorTestCase {
	
	public ViewEventTest()
    {
    }
	
	public void testViewEvent() throws UiObjectNotFoundException {   
	      
	      // Apo to menu pataei to home gia na ksekinisei
	      getUiDevice().pressHome();
	      
	      //Dimiourgite enas UiSelector gia na vrei to all apps screen
	      UiObject allAppsButton = new UiObject(new UiSelector()
	         .description("Apps"));
	      
	      // anoigei to all apps screen
	      allAppsButton.clickAndWaitForNewWindow();
	      
	      //Dimiourgite enas UiSelector gia na vrei to apps screen opou vriskete to calerem
	      UiObject appsTab = new UiObject(new UiSelector()
	         .text("Apps"));
	      
	      // anoigei tin kartela apps
	      appsTab.click();

	      //Dimiourgite enas UiScrollable gia na kanei scroll mexri na vrethei to calerem
	      UiScrollable appViews = new UiScrollable(new UiSelector()
	         .scrollable(true));
	      
	      // thetei to scroll orizontia opou einai oi efarmoges
	      appViews.setAsHorizontalList();
	      
	      //Dimiourgite enas UiSelector gia na anoiksei to calerem
	      UiObject Calerem = appViews.getChildByText(new UiSelector()
	         .className(android.widget.TextView.class.getName()), 
	         "Calerem");
	      Calerem.clickAndWaitForNewWindow();
	      
	      // Epivevaiwnei oti anoikse to Calerem
	      UiObject CaleremValidation = new UiObject(new UiSelector()
	         .packageName("com.calerem"));
	      assertTrue("Unable to detect Calerem",CaleremValidation.exists());  
	      //
	      UiObject buttonViewEvent = new UiObject(new UiSelector().text("View existing event"));
	      
	      //Ean uparxei to buttonViewEvent to kanei click
	      if(buttonViewEvent.exists() && buttonViewEvent.isEnabled()) 
	      {
	    	  buttonViewEvent.click();
	      }
	      
	      //Dhmiourgoume ena UISelector gia me periexomeno TestName pou prosthesame apo thn NewEventTest
	      UiObject textNameCheck = new UiObject(new UiSelector().index(1));
	      //Dhmiourgoume ena UISelector gia me periexomeno TestDescription pou prosthesame apo thn NewEventTest
	      UiObject textDescriptionCheck = new UiObject(new UiSelector().index(7));
	      
	      //TO-DO:Type
	      //TO-DO:Date
	      
	      //Ean auta ta duo vrethoun tote egine epitixhs h eisagwgh kai h ViewEvent ta emfanizei swsta
	      
	      if(textNameCheck.exists() && textDescriptionCheck.exists()) 
	      {
	    	  String test1 = textNameCheck.getText();
	    	  String test2 = textDescriptionCheck.getText();
	    	  if(test1=="TestName" && test2=="TestDescription")
	    	  {	
	    		 assertTrue(true); 
	    	  }
	    	  else
	    	  {
	    		  fail();
	    	  }
	    	  
	      }
	      
	      
	      
	

	}
}
