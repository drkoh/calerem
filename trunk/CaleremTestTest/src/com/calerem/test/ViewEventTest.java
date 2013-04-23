package com.calerem.ui;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

@Author despoina

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
	      UiObject buttonViewEvent = new UiObject(new UiSelector().text("View Existing Event"));
	      
	      //Ean uparxei to buttonViewEvent to kanei click
	      if(buttonViewEvent.exists() && buttonViewEvent.isEnabled()) 
	      {
	    	  buttonViewEvent.click();
	      }
	      
	

	}
}
