package com.calerem.ui;

import java.util.ArrayList;
import com.calerem.R;
import com.calerem.classes.Contact;
import com.google.gson.Gson;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ShowContactsHavingNameday extends Activity {

	private static ArrayList<String> contactArray;
	private static EditText text1;
	private Contact contacts[];
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{	super.onCreate(savedInstanceState);
		
		setContentView(R.layout.notification);
		text1 = (EditText) findViewById(R.id.editText1);
		Bundle extras = getIntent().getExtras();
		String data = extras.getString("Data");
		contacts = new Gson().fromJson(data,Contact[].class);
		contactArray = new ArrayList<String>();
		
		String titleEnd=" having nameday.";
		String finalTitle = "Today is";
		String noNameday = "Today nobody celebrates his nameday.";
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
		
			for (int contactCursor=0; contactCursor < contactArray.size(); contactCursor++)
			{
				finalTitle += " " + contactArray.get(contactCursor); 
			}
			finalTitle += titleEnd;
			
			text1.setText(finalTitle);
		}	
	} //end of onCreate()
	
	public void getContactsHavingNameday () {
		for (int contactCursor=0; contactCursor<contacts.length; contactCursor++) {
			contactArray.add(contacts[contactCursor].getName()+" "+contacts[contactCursor].getLastname()+" ");
		}
	}
	
	/*
	 * 
	
		public void f_throw_notification(ArrayList<String> abc)
		{
			finish();
		}
	*/

}
