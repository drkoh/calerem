package com.calerem.ui;

import java.util.ArrayList;

import com.calerem.AutoStart;
import com.calerem.R;
import com.calerem.api.EortologioApi;
import com.calerem.classes.Contact;
import com.calerem.classes.FindContacts;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ShowContactsHavingNameday extends Activity implements View.OnClickListener {

	private static Button bt1;
	private static TextView tvNamedaysToday;
	private static TextView tvContactNamedays;
	private static Contact contacts[];
	private static ArrayList<String> namedaysToday;
	private static ArrayList<String> eortologioNames;
	private static EortologioApi eortologioApi;
	private Context basecontext;
	
	private void initVars() {
		bt1 = (Button) findViewById(R.id.button1);
		tvNamedaysToday = (TextView) findViewById(R.id.tvNamedaysToday);
		tvContactNamedays = (TextView) findViewById(R.id.tvContactNamedays);
		namedaysToday = new ArrayList<String>();
		eortologioNames = new ArrayList<String>();
		eortologioApi = new EortologioApi();

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		Bundle extras = getIntent().getExtras();
		basecontext = this.getBaseContext();
		String data = extras.getString("Data");
		contacts = new Gson().fromJson(data, Contact[].class);
		initVars();
		new XmlToDoc().execute();
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
		
		@Override
		protected Void doInBackground(Void... arg0) {
			namedaysToday = eortologioApi.returnVal();
			eortologioNames = eortologioApi.returnGreekVal();
			return null;
		}

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
			{
				str += eortologioNames.get(i);
			}
			for(int i = 0; i < namedaysToday.size();i++)
			{
				temp.add(namedaysToday.get(i));
			}
			tvNamedaysToday.setText(str);
			Intent intent = new Intent();
			intent.putExtra("Eortologio", temp);
			new AutoStart().onReceive(basecontext, intent);
			super.onPostExecute(result);
		}
		
		@Override
		protected void onProgressUpdate(Void... values) {
			super.onProgressUpdate(values);
		}

	}
}
