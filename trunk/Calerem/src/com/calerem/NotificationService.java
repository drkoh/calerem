package com.calerem;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


import com.calerem.classes.Celebration;
import com.calerem.classes.Contact;
import com.calerem.controllers.Database;
import com.calerem.controllers.SyncController;
import com.google.gson.Gson;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.app.Notification.Builder;


/**
 * Service that runs daily checking EortologioAPI and ContactsAPI and printing in a notification, currently celebrating contacts
 * @authors athaboil, Cyclothemik
 */

public class NotificationService extends Service
{
	private static final int CALEREM_ID = 1;
	Database db;


	/**
	 * Primary method called by calling the service
	 * @param Intent intent, int startid
	 */
	
	@Override
	public void onStart(Intent intent, int startid) 
	{
		
		Celebration[] cele;
		Bundle extras = intent.getExtras();
		Contact contacts[] = new Gson().fromJson(extras.getString("Data"),Contact[].class);
		
		cele = get_Eort();
		ArrayList<String> finals = findContacts(contacts,cele);
		init_notification(finals);
	
		
	//	Log.e("custom", contacts[1].getName());
	//	Log.e("custom1", cele[4].getName());
	//	Log.e("custom2",finals.get(0));
		
	}
	/**
	 * Compares contacts names with celebration names  
	 * @param contacts
	 * @param cele
	 * @return finals
	 */
	
	private ArrayList<String>  findContacts(Contact[] contacts, Celebration[] cele) {
		// TODO Auto-generated method stub
		
		ArrayList<String> finals = new ArrayList<String>();
		for(int i=0;i<contacts.length;i++)
		{
			for(int j=0;j<cele.length;j++)
			{
				if(contacts[i].getName().contains(cele[j].getName()))
				{
					if(contacts[i].getLastname().isEmpty())
					{
						finals.add(contacts[i].getName() );
					}
					else
					{
						finals.add(contacts[i].getName()+ " " + contacts[i].getLastname());
					}
					
					
				}
			}
		}
		return finals;
	}

	/**
	 * gets Data from EortologioAPI and database into a table of Celebrations
	 */
	private Celebration[] get_Eort() 
	{
		// TODO Auto-generated method stub
		Celebration[] cele;
		
			new SyncController(this).SyncEortologio();
		
		
		 try {
	    	 db = new Database(getBaseContext());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
		 cele=db.get_celebrations(sdf.format(new Date()));
		 
		 return cele;
	}
			
	/**
	 * creates a notification that is thrown through calling this method via onStart
	 * @param contacts
	 */
	private void init_notification(ArrayList<String> finals) 
	{
		
			Builder NotificationBuilder = new Notification.Builder(this);
			NotificationBuilder.setContentTitle("There are " + finals.size() + " people celebrating today");
			NotificationBuilder.setContentText(this.CompileNotificationText(finals));
			NotificationBuilder.setTicker("CaleRem Notification");
			NotificationBuilder.setWhen(System.currentTimeMillis());
			NotificationBuilder.setSmallIcon(R.drawable.icon);
			Notification notification = NotificationBuilder.build();

			NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
			notificationManager.notify(CALEREM_ID, notification);
			stopSelf();
		
	}
	/**
	 * Creates a CharSequence containing celebrating contacts names
	 * @param finals
	 * @return NotificationText
	 */
	private CharSequence CompileNotificationText(ArrayList<String> finals)
	{
		CharSequence NotificationText = "";
		if(finals.size()>0)
		{
			
			for(int i=0;i<finals.size();i++)
			{
				NotificationText = NotificationText +  finals.get(i) + ", "  ;
			}
			
		}
		else
		{
			
			NotificationText="no one is celebrating";
		}
		return NotificationText;
	}
	
	@Override
	public IBinder onBind(Intent arg0) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
