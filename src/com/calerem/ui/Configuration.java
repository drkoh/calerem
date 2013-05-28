package com.calerem.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.calerem.R;
import com.calerem.classes.ConfigurationCalerem;
import com.calerem.handlers.ConfigurationOnClickHandler;
import com.calerem.handlers.ConfigurationSpinnerDateFormat;
import com.calerem.handlers.ConfigurationSpinnerLanguage;
import com.calerem.handlers.ConfigurationSpinnerSkin;
import com.google.gson.Gson;


/**
 * Form to view and edit Configuration.
 * @author DarkParadise
 */
public class Configuration extends Activity {
	
	private Spinner SpinnerDateFormat, SpinnerLanguage, SpinnerSkin;
	private String[] DateFormats = {"25-01-2013","01-25-2013","01-Jan-2013","Jan-25-2013"},Languages = {"English"},Skins = {"Default"};
	private String[] DateFormatsValue = {"dd-MM-yyyy","MM-dd-yyyy","dd-MMM-yyyy","MMM-dd-yyyy"};
	private Button SaveButton;
	private ConfigurationCalerem CurrentConfig;
	
	/**
	 * Base Constructor.
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		this.CurrentConfig = new Gson().fromJson(extras.getString("Data"), ConfigurationCalerem.class);
		this.initVisualObjects();
	}
	
	/**
	 * Initializes visual objects.
	 */
	private void initVisualObjects()
	{
		this.setContentView(R.layout.configuration);
		this.SpinnerDateFormat = (Spinner) this.findViewById(R.id.SpinnerDateFormat);
		this.SpinnerDateFormat.setAdapter(new ArrayAdapter<String>(Configuration.this, android.R.layout.simple_spinner_item, this.DateFormats));
		for(int i=0;i<DateFormats.length;i++)
		{
			if(this.CurrentConfig.getDate_format().equals(DateFormatsValue[i]))
			{
				this.SpinnerDateFormat.setSelection(i);
				break;
			}
		}
		ConfigurationSpinnerDateFormat SpinnerDateFormatListener = new ConfigurationSpinnerDateFormat();
		this.SpinnerDateFormat.setOnItemSelectedListener(SpinnerDateFormatListener);
		this.SpinnerLanguage = (Spinner) this.findViewById(R.id.SpinnerLanguage);
		this.SpinnerLanguage.setAdapter(new ArrayAdapter<String>(Configuration.this, android.R.layout.simple_spinner_item, this.Languages));
		this.SpinnerLanguage.setSelection(0);
		ConfigurationSpinnerLanguage SpinnerLanguageListener = new ConfigurationSpinnerLanguage();
		this.SpinnerLanguage.setOnItemSelectedListener(SpinnerLanguageListener);
		this.SpinnerSkin = (Spinner) this.findViewById(R.id.SpinnerSkin);
		this.SpinnerSkin.setAdapter(new ArrayAdapter<String>(Configuration.this, android.R.layout.simple_spinner_item, this.Skins));
		this.SpinnerSkin.setSelection(0);
		ConfigurationSpinnerSkin SpinnerSkinListener = new ConfigurationSpinnerSkin();
		this.SpinnerSkin.setOnItemSelectedListener(SpinnerSkinListener);
		this.SaveButton = (Button) this.findViewById(R.id.SaveButton);
		this.SaveButton.setOnClickListener(new ConfigurationOnClickHandler(this, CurrentConfig, SpinnerDateFormatListener, SpinnerLanguageListener, SpinnerSkinListener));
	}	
}
