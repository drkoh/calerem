/**
 * 
 */
package com.calerem.handlers;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * Date Format Spinner Event Handler.
 * @author DarkParadise
 */
public class ConfigurationSpinnerDateFormat implements OnItemSelectedListener {
	
	private int SelectedPosition = -1;

	/**
	 * Set position based on selection.
	 * @see android.widget.AdapterView.OnItemSelectedListener#onItemSelected(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
		this.setSelectedPosition(arg0.getSelectedItemPosition());
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @return the selectedPosition
	 */
	public int getSelectedPosition() {
		return SelectedPosition;
	}

	/**
	 * @param selectedPosition the selectedPosition to set
	 */
	public void setSelectedPosition(int selectedPosition) {
		SelectedPosition = selectedPosition;
	}

}
