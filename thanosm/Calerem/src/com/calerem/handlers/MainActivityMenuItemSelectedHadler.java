/**
 * 
 */
package com.calerem.handlers;

import android.content.Context;
import android.view.MenuItem;

import com.calerem.R;
import com.calerem.controllers.UIController;

/**
 * Handler for item selected.
 * @author DarkParadise
 */
public class MainActivityMenuItemSelectedHadler {
	
	private Context basecontext;
	
	public MainActivityMenuItemSelectedHadler(Context context)
	{
		this.basecontext = context;
	}
	
	public boolean  onOptionsItemSelected(MenuItem item)
	{
        
       switch (item.getItemId())
       {
       case R.id.MainActivityMenu:
    	   new UIController(basecontext).newConfiguration();
           return true;
       default:
           return false;
       }
	}

}
