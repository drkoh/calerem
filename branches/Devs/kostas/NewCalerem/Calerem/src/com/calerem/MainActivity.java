package com.calerem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

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
