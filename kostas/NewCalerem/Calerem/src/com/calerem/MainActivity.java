package com.calerem;

import com.calerem.controllers.UIController;
import com.calerem.handlers.UIActivityResult;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new UIController(this).newSendEmail((Integer) null, "", "");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		new UIActivityResult(this).onActivityResult(requestCode, resultCode, data);
	}
}
