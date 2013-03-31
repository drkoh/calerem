package com.example.calerem;

import java.io.IOException;

import com.example.calerem.c_configuration;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class configurationActivity extends Activity {
	Spinner spn_date_format;
	Spinner spn_notification_sound;
	Spinner spn_language;
	Spinner spn_skin;
	Spinner spn_eortologio_xml;
	c_configuration configurationData = new c_configuration(null, null, null, null, null);
	c_database database;
	ArrayAdapter<String> myAdap;
	int spinnerPosition ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurationlayout);
		 
		spn_date_format = (Spinner) findViewById(R.id.spnDateFormat);
		spn_notification_sound = (Spinner) findViewById(R.id.spnNotificationsSound);
		spn_language = (Spinner) findViewById(R.id.spnLanguage);
		spn_skin = (Spinner) findViewById(R.id.spnSkin);
		spn_eortologio_xml = (Spinner) findViewById(R.id.spnEortologioXml);
		try {
			database = new c_database(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		configurationData = database.f_read_configuration();		
		
		myAdap = (ArrayAdapter<String>) spn_date_format.getAdapter();
		spinnerPosition = myAdap.getPosition(configurationData.v_date_format);
		spn_date_format.setSelection(spinnerPosition);
		
		myAdap = (ArrayAdapter<String>) spn_notification_sound.getAdapter();
		spinnerPosition = myAdap.getPosition(configurationData.v_notification_sound);
		spn_date_format.setSelection(spinnerPosition);
		
		myAdap = (ArrayAdapter<String>) spn_language.getAdapter();
		 spinnerPosition = myAdap.getPosition(configurationData.v_language);
		spn_date_format.setSelection(spinnerPosition);
		
		myAdap = (ArrayAdapter<String>) spn_skin.getAdapter();
	    spinnerPosition = myAdap.getPosition(configurationData.v_skin);
		spn_date_format.setSelection(spinnerPosition);
		
	    myAdap = (ArrayAdapter<String>) spn_eortologio_xml.getAdapter();
		spinnerPosition = myAdap.getPosition(configurationData.v_eortologio_xml);
		spn_date_format.setSelection(spinnerPosition);
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	private void clkSave(View v){
		
		
		configurationData.v_date_format = spn_date_format.getSelectedItem().toString();
		configurationData.v_notification_sound = spn_notification_sound.getSelectedItem().toString();
		configurationData.v_language = spn_language.getSelectedItem().toString();
		configurationData.v_skin = spn_skin.getSelectedItem().toString();
		configurationData.v_eortologio_xml = spn_eortologio_xml.getSelectedItem().toString();
		
		database.f_update_configuration(configurationData);
		
		
		
		
	}
	
	
	
	public void clkReset(View v){

		ArrayAdapter<String> myAdap = (ArrayAdapter<String>) spn_date_format.getAdapter();
		int spinnerPosition = myAdap.getPosition(configurationData.v_date_format);
		spn_date_format.setSelection(spinnerPosition);
		
		myAdap = (ArrayAdapter<String>) spn_notification_sound.getAdapter();
		spinnerPosition = myAdap.getPosition(configurationData.v_notification_sound);
		spn_date_format.setSelection(spinnerPosition);
		
		myAdap = (ArrayAdapter<String>) spn_language.getAdapter();
		 spinnerPosition = myAdap.getPosition(configurationData.v_language);
		spn_date_format.setSelection(spinnerPosition);
		
		myAdap = (ArrayAdapter<String>) spn_skin.getAdapter();
	    spinnerPosition = myAdap.getPosition(configurationData.v_skin);
		spn_date_format.setSelection(spinnerPosition);
		
	    myAdap = (ArrayAdapter<String>) spn_eortologio_xml.getAdapter();
		spinnerPosition = myAdap.getPosition(configurationData.v_eortologio_xml);
		spn_date_format.setSelection(spinnerPosition);
		
		
	}
	

}
