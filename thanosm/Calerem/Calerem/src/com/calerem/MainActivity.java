package com.calerem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.calerem.controllers.UIController;
import com.calerem.factories.UIActivityResultsHandlerFactory;

/**
 * Main form of the application.
 * @author DarkParadise
 */
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button buttonNewEvent = (Button) findViewById(R.id.btNewEvent);
		buttonNewEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//MainActivity.this.setContentView(R.layout.new_event);
				new UIController(MainActivity.this).newNewEvent();
			}
		});
		
		final Button buttonViewEvent = (Button) findViewById(R.id.btViewEvent);
		buttonViewEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new UIController(MainActivity.this).newViewEvent(1);
			}
		});
		
		final Button buttonSendEmail = (Button) findViewById(R.id.btSendEmail);
		buttonSendEmail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new UIController(MainActivity.this).newSendEmail(null,"","");
			}
		});
		
		final Button buttonShowContacts = (Button) findViewById (R.id.btShowContactsHavingNameday);
		buttonShowContacts.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new UIController(MainActivity.this).newShowContacts();
			}
		});
		new AutoStart().onReceive(this, getIntent());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/** 
	 * All activities results come here, so they are passed to the handler for processing.
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		new UIActivityResultsHandlerFactory(this).onActivityResult(requestCode, resultCode, data);
	}
}
