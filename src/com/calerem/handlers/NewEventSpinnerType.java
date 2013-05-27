/**
 * 
 */
package com.calerem.handlers;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Type Spinner handle.
 * @author DarkParadise
 */
public class NewEventSpinnerType  extends Activity implements OnItemSelectedListener {
	private String EventType;
	
	/**
	 * Sets event type based on selected item.
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) 
	{
		setEventType((String) arg0.getSelectedItem());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return EventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	private void setEventType(String eventType) {
		EventType = eventType;
	}	
}
