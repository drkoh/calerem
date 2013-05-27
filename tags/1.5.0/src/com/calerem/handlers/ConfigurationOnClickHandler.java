package com.calerem.handlers;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.calerem.classes.ConfigurationCalerem;
import com.google.gson.Gson;

/**
 * On Click Handler for Save Button.
 * @author DarkParadise
 */
public class ConfigurationOnClickHandler implements OnClickListener {

	private ConfigurationSpinnerDateFormat SpinnerDateFormatListener;
	private ConfigurationSpinnerLanguage SpinnerLanguageListener;
	private ConfigurationSpinnerSkin SpinnerSkinListener;
	private ConfigurationCalerem current_config;
	private Activity ConfigurationActivity;
	public ConfigurationOnClickHandler(Activity ConfigurationActivity,ConfigurationCalerem current_config,ConfigurationSpinnerDateFormat SpinnerDateFormatListener,ConfigurationSpinnerLanguage SpinnerLanguageListener,ConfigurationSpinnerSkin SpinnerSkinListener)
	{
		this.SpinnerDateFormatListener = SpinnerDateFormatListener;
		this.SpinnerLanguageListener = SpinnerLanguageListener;
		this.SpinnerSkinListener = SpinnerSkinListener;
		this.current_config = current_config;
		this.ConfigurationActivity = ConfigurationActivity;
	}
	
	@Override
	public void onClick(View arg0) {
		switch(this.SpinnerDateFormatListener.getSelectedPosition())
		{
		case 0:
			this.current_config.setDate_format("dd-MM-yyyy");
			break;
		case 1:
			this.current_config.setDate_format("MM-dd-yyyy");
			break;
		case 2:
			this.current_config.setDate_format("dd-MMM-yyyy");
			break;
		case 3:
			this.current_config.setDate_format("MMM-dd-yyyy");
			break;
		}
		switch(this.SpinnerLanguageListener.getSelectedPosition())
		{
		case 0:
			this.current_config.setLanguage("");
		}
		switch(this.SpinnerSkinListener.getSelectedPosition())
		{
		case 0:
			this.current_config.setSkin("");
		}
		Intent intent_ret = new Intent();
		intent_ret.putExtra("result", new Gson().toJson(this.current_config));
		this.ConfigurationActivity.setResult(Activity.RESULT_OK, intent_ret);
		this.ConfigurationActivity.finish();
	}

}
