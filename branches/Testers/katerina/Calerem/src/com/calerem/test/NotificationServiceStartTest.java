package com.calerem.test;

import android.content.Intent;
import android.test.ServiceTestCase;
import com.calerem.NotificationService;
import com.calerem.classes.Contact;
import com.google.gson.Gson;




public class NotificationServiceStartTest extends
		ServiceTestCase<NotificationService> {
	
	private static Gson gson = new Gson();
	NotificationService service;
	
	public NotificationServiceStartTest() {
		super(NotificationService.class);
	
	}
	public void testService() {
		Contact contact1 = new Contact("Asura","Tzini","6982530379","bam@hotmail.com",1);
		Contact contact2 = new Contact("Spira","Likou","6973103987","boom@yahoo.gr",2);
		Contact[] v_contact ={contact1,contact2};
	
		Intent intent=new Intent(getSystemContext(),NotificationService.class);
		intent.setClass(getContext(), NotificationService.class);
		intent.putExtra("Data", gson.toJson(v_contact));
		
		startService(intent);
		
		try {
			Thread.sleep(35000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(getService());
	}

}

