package com.calerem.ui;

import java.util.ArrayList;
import com.calerem.AutoStart;
import com.calerem.R;
import com.calerem.api.EortologioApi;
import com.calerem.classes.Contact;
import com.calerem.classes.FindContacts;
import com.google.gson.Gson;
import android.app.Activity;
<<<<<<< .mine
import android.content.Context;
import android.content.Intent;
=======
>>>>>>> .r152
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

<<<<<<< .mine
=======
public class ShowContactsHavingNameday extends Activity {
>>>>>>> .r152

<<<<<<< .mine
public class ShowContactsHavingNameday extends Activity implements View.OnClickListener {

	private static Button bt1;
=======
	private static ArrayList<String> contactArray;
>>>>>>> .r152
	private static TextView tvNamedaysToday;
	private static TextView tvContactNamedays;
	private static Contact contacts[];
	private static ArrayList<String> namedaysToday;
	private static ArrayList<String> eortologioNames;
	private static EortologioApi eortologioApi;
	private Context basecontext;
	
<<<<<<< .mine
	private void initVars() {
		bt1 = (Button) findViewById(R.id.button1);
		tvNamedaysToday = (TextView) findViewById(R.id.tvNamedaysToday);
		tvContactNamedays = (TextView) findViewById(R.id.tvContactNamedays);
		namedaysToday = new ArrayList<String>();
		eortologioNames = new ArrayList<String>();
		eortologioApi = new EortologioApi();

	}

=======
>>>>>>> .r152
	@Override
<<<<<<< .mine
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
=======
	protected void onCreate(Bundle savedInstanceState)
	{	super.onCreate(savedInstanceState);
		
>>>>>>> .r152
		setContentView(R.layout.notification);
		text1 = (EditText) findViewById(R.id.editText1);
		Bundle extras = getIntent().getExtras();
		basecontext = this.getBaseContext();
		String data = extras.getString("Data");
		contacts = new Gson().fromJson(data, Contact[].class);
		contactArray = new ArrayList<String>();
		new XmlToDoc().execute();
<<<<<<< .mine
		bt1.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		String finalTitle = "Σήμερα γιορτάζουν οι: ";
		String noNameday = "Δε γιορτάζει κανείς σήμερα.";
		String noContactsNameday = "Δε γιορτάζει καμία από τις επαφή.";

		if (namedaysToday.isEmpty()) {
			tvNamedaysToday.setText(noNameday);
		} else {
			Contact[] finalcontacts = FindContacts.searchNames(contacts, namedaysToday);
			if (finalcontacts.length == 0) {
				tvContactNamedays.setText(noContactsNameday);
			} else {
				for (int contactCursor = 0; contactCursor < finalcontacts.length; contactCursor++) {
					finalTitle += " " + finalcontacts[contactCursor].getName() + " " + finalcontacts[contactCursor].getLastname();
				}
				tvContactNamedays.setText(finalTitle);
			}
		}
	}

	/**
	 * The Class XmlToDoc.
	 */
	private class XmlToDoc extends AsyncTask<Void, Void, Void> {
=======
		String titleEnd=" having nameday.";
		String finalTitle = "Today is";
		String noNameday = "Today nobody celebrates his nameday.";
	 	getContactsHavingNameday();
>>>>>>> .r152
		
<<<<<<< .mine
		@Override
		protected Void doInBackground(Void... arg0) {
			namedaysToday = eortologioApi.returnVal();
			eortologioNames = eortologioApi.returnGreekVal();
			return null;
=======
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
>>>>>>> .r152
		}
<<<<<<< .mine

		@Override
		protected void onCancelled() {
			super.onCancelled();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
	
		@Override
		protected void onPostExecute(Void result) {
			String str = "";
			ArrayList<CharSequence> temp = new ArrayList<CharSequence>();
			for (int i = 0; i < eortologioNames.size(); i++)
=======
		
		else {
			String nn =	"";
			for (int i = 0; i < contactArray.size(); i++)
>>>>>>> .r152
			{
				str += eortologioNames.get(i);
			}
<<<<<<< .mine
			for(int i = 0; i < namedaysToday.size();i++)
=======
		
			for (int contactCursor=0; contactCursor < contactArray.size(); contactCursor++)
>>>>>>> .r152
			{
				temp.add(namedaysToday.get(i));
			}
<<<<<<< .mine
			tvNamedaysToday.setText(str);
			Intent intent = new Intent();
			intent.putExtra("Eortologio", temp);
			new AutoStart().onReceive(basecontext, intent);
			super.onPostExecute(result);
=======
			finalTitle += titleEnd;
			
			text1.setText(finalTitle);
		}	
	} //end of onCreate()
	
	public void getContactsHavingNameday () {
		for (int contactCursor=0; contactCursor<contacts.length; contactCursor++) {
			contactArray.add(contacts[contactCursor].getName()+" "+contacts[contactCursor].getLastname()+" ");
>>>>>>> .r152
		}
<<<<<<< .mine
		
		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
=======
	}
	
	/*
	 * 
	
		public void f_throw_notification(ArrayList<String> abc)
		{
			finish();
>>>>>>> .r152
		}

	}
}
