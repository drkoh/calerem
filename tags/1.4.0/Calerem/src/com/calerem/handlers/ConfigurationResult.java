package com.calerem.handlers;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.calerem.classes.ConfigurationCalerem;
import com.calerem.controllers.Database;
import com.google.gson.Gson;

/**
 * Handler for NewEvent Activity Result.
 * @author DarkParadise
 */
public class ConfigurationResult {
	private Database db;
	/**
	 * Base Constructor.
	 * Writes new configuration to the database.
	 * @param Context basecontext;
	 * @param int resultCode
	 * @param Intent data
	 */
	public ConfigurationResult(Context basecontext,int resultCode, Intent data)
	{
		try {
			db = new Database(basecontext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultCode == Activity.RESULT_OK)
		{      
			db.update_configuration(new Gson().fromJson(data.getExtras().getString("result"), ConfigurationCalerem.class));
		}
		else if (resultCode == Activity.RESULT_CANCELED) 
		{    
			//Write your code on no result return 
		}
	}
}
