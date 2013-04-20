package com.calerem;

import java.util.ArrayList;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.IBinder;

import com.calerem.NotificationService;
import com.calerem.R;
import com.calerem.classes.Contact;
import com.google.gson.Gson;


public class NotificationService extends Service {
	private static final int HELLO_ID = 1;



	@SuppressWarnings({ "deprecation" })
	@Override
	public void onStart(Intent intent, int startid) {


		Bundle extras = intent.getExtras();
		Contact contacts[] = new Gson().fromJson(extras.getString("Data"),Contact[].class);
		Context context =NotificationService.this
				.getApplicationContext();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(NOTIFICATION_SERVICE);

		Notification notification = new Notification();
		notification.icon=R.drawable.icon;
		notification.tickerText="CaleRem Notification";
		notification.when=System.currentTimeMillis();


		CharSequence contentTitle ="";
		CharSequence contentText ="" ;
		Integer miden = 0;

		ArrayList<String> names = new ArrayList<String>();
		for(int i=0;i<contacts.length;i++)
		{
			names.add(contacts[i].getName() + " " + contacts[i].getLastname());
		}

		if(!miden.equals(names.size()))
		{

			try{
				contentTitle = names.size() + " person(s) celebrating their nameday";
				for(int i=0; i<names.size(); i++ )
				{
					contentText= contentText + names.get(i) + " " ;
				}
			}catch (Exception e){

				e.printStackTrace();
			}
		}
		else{
			contentTitle="no person celebrating his/her nameday";
			contentText="no person celebrating";
		}



		Intent notificationIntent = new Intent(context,MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,notificationIntent,0);
		//notification.flags |= Notification.FLAG_AUTO_CANCEL;

		notification.setLatestEventInfo(context, contentTitle,contentText, contentIntent);
		notificationManager.notify(HELLO_ID, notification);

		stopSelf();
	}




	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


}
