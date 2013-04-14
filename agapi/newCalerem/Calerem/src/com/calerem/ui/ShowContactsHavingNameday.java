package com.calerem.ui;

import java.util.ArrayList;

import com.calerem.R;
import com.calerem.classes.Contact;
import com.calerem.controllers.NotificationController;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowContactsHavingNameday extends Activity implements View.OnClickListener{

	static ArrayList<String> contactArray;
	static Intent intent;
	static NotificationManager nm;
	static PendingIntent pi;
	static CharSequence names;
	static Button bt1;
	static EditText text1;
	
	private void initVars() {
		contactArray = new ArrayList<String>();
		intent = new Intent();
		names = "";
		bt1 = (Button) findViewById(R.id.button1);
		text1 = (EditText) findViewById(R.id.editText1);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		initVars();
		
		bt1.setOnClickListener(this);
		
		intent = new Intent(getApplicationContext(),ShowContactsHavingNameday.class); 
		pi = PendingIntent.getActivity(getApplicationContext(), 0, intent , 0);
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
			
	}
	
	
	@Override
	public void onClick(View arg0) {
		Notification n = new Notification();
		n.tickerText="HAPPY NAME-DAY";
		//n.icon= R.drawable.turtle;
		n.number=12345;
		String titleEnd=" having nameday.";
		String finalTitle = "Today is";
		String noNameday = "Today is nobody having nameday.";
		getContactsHavingNameday();
		
		/*
		 * Do this for example if there is nobody having nameday.		 *
		 *  
		 * contactArray.add("Nikos Papoulias");
		 * contactArray.add("Lazaros Amanatidis");
		 * contactArray.add("Kostas Epic");
		 * contactArray.add("Ninja Troll");
		 */
		
		if(contactArray.isEmpty()){
			text1.setText(noNameday);
		}
		else {
			String nn =	"";
			for (int i = 0; i < contactArray.size(); i++)
			{
				nn = nn + contactArray.get(i) + "," ;
			}
			CharSequence nn1 = nn;
			
			n.setLatestEventInfo(getApplicationContext(), n.tickerText, nn1, pi);
			nm.notify(n.number, n);
			for (int contactCursor=0; contactCursor < contactArray.size(); contactCursor++)
			{
				finalTitle += " " + contactArray.get(contactCursor); 
			}
			finalTitle += titleEnd;
			
			text1.setText(finalTitle);
		}	
	}
	
	public void getContactsHavingNameday () {
		NotificationController nc = new NotificationController ();
		Contact contacts[];
		contacts = nc.parseIds();
		for (int contactCursor=0; contactCursor<contacts.length; contactCursor++) {
			contactArray.add(contacts[contactCursor].getName()+" "+contacts[contactCursor].getLastname()+" ");
		}
	}
	
	public void f_throw_notification(ArrayList<String> abc)
	{
		finish();
	}

}
