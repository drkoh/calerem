package com.calerem.ui;

//import junit.framework.TestCase;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class NewEventTest extends UiAutomatorTestCase {
	
	public NewEventTest()
    {
    }

	public void testNewEventTest() throws UiObjectNotFoundException {   
	      
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
	      UiObject buttonCreateNewEvent = new UiObject(new UiSelector().text("Create new event"));
	      
	      //Ean uparxei to buttonCreateNewEvent to kanei click
	      if(buttonCreateNewEvent.exists() && buttonCreateNewEvent.isEnabled()) 
	      {
	    	  buttonCreateNewEvent.click();
	    	  
	      //TO-DO:Dokimi gia Birthday kai Nameday - Default einai to Birthday
	    	  
	      //Dimiourgite enas UiSelector gia na diavastei to pedio event name
	      UiObject textEventName = new UiObject(new UiSelector().text("Event Name"));
	      //Clickare to pedio Event name
	      textEventName.click();
	      //Vazei ena doikimastiko onoma TestName sto pedio Event Name
	      textEventName.setText("TestName");
	      
	      //Dimiourgite enas UiSelector gia na diavastei to pedio Description
	      UiObject textDescription = new UiObject(new UiSelector().text("Description"));
	      //Clickare to pedio Description
	      textDescription.click();
	      //Vazei ena doikimastiko onoma TestDescription sto pedio Description
	      textDescription.setText("TestDescription");
	      
	      //TO-DO:Dokimi pediou Hmeromhnias
	      
	      //TO-DO:Dokimi sundesis me epafi - Default einai to None
	      
	      //Dimiourgite enas UiSelector gia na diavastei to koumbi save
	      UiObject buttonSave = new UiObject(new UiSelector().text("Save"));
	      //Clickare to koumbi save
	      buttonSave.click();

	}
	      

	}
}
