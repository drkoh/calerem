package com.calerem.ui;

import java.util.ArrayList;

import com.calerem.R;
import com.calerem.api.EortologioApi;
import com.calerem.classes.Contact;
import com.calerem.classes.FindContacts;
import com.google.gson.Gson;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowContactsHavingNameday extends Activity implements View.OnClickListener{

	private static Button bt1;
	private static EditText text1;
	private static Contact contacts[];
	//private static EortologioApi eort;
	static ArrayList<String> eortologioArrayList = new ArrayList<String>();
	
	private void initVars() {
		bt1 = (Button) findViewById(R.id.button1);
		text1 = (EditText) findViewById(R.id.editText1);
		eortologioArrayList = EortologioApi.parseVals();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		Bundle extras = getIntent().getExtras();
		String data = extras.getString("Data");
		contacts = new Gson().fromJson(data,Contact[].class);
		eortologioArrayList = EortologioApi.parseVals();
		initVars();
		bt1.setOnClickListener(this);
					
	}
	
	
	@Override
	public void onClick(View arg0) {
		String titleEnd=" having nameday.";
		String finalTitle = "Today is";
		String noNameday = "Today is nobody is celebrating his nameday.";
		
		/*
		 * Do this for example if there is nobody having nameday.		 
		 *  
		 * contactArray.add("Nikos Papoulias");
		 * contactArray.add("Lazaros Amanatidis");
		 * contactArray.add("Kostas Epic");
		 * contactArray.add("Ninja Troll");
		 */
		//eortologioArrayList.add(" Leo ");
		//eortologioArrayList.add(" Maria ");
		if(eortologioArrayList.isEmpty()){		
			text1.setText(noNameday);
		}
		else {
			Contact [] finalcontacts = 	FindContacts.searchNames(contacts, eortologioArrayList)	;
			for(int contactCursor=0;contactCursor<finalcontacts.length; contactCursor++) {
				finalTitle += " " + finalcontacts[contactCursor].getName() +" "+ finalcontacts[contactCursor].getLastname();
			}
			finalTitle += titleEnd;
			
			text1.setText(finalTitle);
		}
	}
	
}
