package com.example.thenotification;


import java.util.ArrayList;


import android.app.Activity;
import android.app.PendingIntent;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import  com.example.thenotification.mock_contact_class;





public class c_throw_notifications extends Activity  implements OnClickListener {
	
	Intent intent;
	NotificationManager nm;
	PendingIntent pi;
	CharSequence names;
	Button bt1;
	mock_contact_class a123;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.notification);
		bt1 = (Button) findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		
		intent = new Intent(getApplicationContext(),c_throw_notifications.class); 
		pi = PendingIntent.getActivity(getApplicationContext(), 0, intent , 0);
		nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		
		
	}
	
/*	public CharSequence sendNamesToNotification(ArrayList<String> celeList)
	{
		String nn =	"";
		for (int i = 0; i < celeList.size(); i++)
		{
			nn = nn + celeList.get(i) + " " ;
		}
		CharSequence nn1 = nn;

		return nn1;
	}
	*/

	public void onClick(View v){
		
		
		
		Notification n = new Notification();
		
		n.tickerText="HAPPY NAME-DAY";
		n.icon= R.drawable.turtle;
		n.number=12345;
		
		
	//	names = sendNamesToNotification(a123.getNamesForNotification(contacts));
		
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("Nikos Papoulias");
		temp.add("Lazaros Amanatidis");
		temp.add("Kostas Epic");
		temp.add("Ninja Troll");
		
		String nn =	"";
		for (int i = 0; i < temp.size(); i++)
		{
			nn = nn + temp.get(i) + " " ;
		}
		CharSequence nn1 = nn;
	
		
		n.setLatestEventInfo(getApplicationContext(), n.tickerText, nn1, pi);
		
		nm.notify(n.number, n);
		
		//nm.cancel(n.number);
		
 
		 
		
	}
	


	public void f_throw_notification(ArrayList<String> abc)
	{
	
		finish();
		
		
	};

}



