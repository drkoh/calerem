/**
 * 
 */
package com.calerem.handlers;

import android.app.Activity;
import android.content.Intent;

import com.calerem.classes.Event;
import com.calerem.controllers.Database;
import com.google.gson.Gson;

/**
 * Handler for NewEvent Activity Result.
 * 
 * @author DarkParadise
 */
public class NewEventResult {
	/**
	 * Base Constructor. Reads the result event and adds it to the database.
	 * 
	 * @param Database
	 *            db
	 * @param int resultCode
	 * @param Intent
	 *            data
	 */
	public NewEventResult(Database db, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			Event v_event = new Gson().fromJson(data.getStringExtra("result"), Event.class);
			db.add_event(v_event);
		} else if (resultCode == Activity.RESULT_CANCELED) {
			// Write your code on no result return
		}
	}
}
