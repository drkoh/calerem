/**
 * 
 */
package com.calerem.handlers;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Contact Spinner handle.
 * @author DarkParadise
 */
public class NewEventSpinnerContact extends Activity implements OnItemSelectedListener {
	private int ContactPosition;
	
	/**
	 * Sets contact position based on item selected.
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		setContactPosition(arg0.getSelectedItemPosition());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @return the contactPosition
	 */
	public int getContactPosition() {
		return ContactPosition;
	}

	/**
	 * @param contactPosition the contactPosition to set
	 */
	private void setContactPosition(int contactPosition) {
		ContactPosition = contactPosition;
	}
}
