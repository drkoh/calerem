/**
 * 
 */
package com.calerem.handlers;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/**
 * On Click Handler.
 * @author DarkParadise
 */
public class MainActivityOnClickHandler  implements OnClickListener {

	private ImageView prevMonth,nextMonth;
	private static int month,year;
	private Activity MainActivity;
	public MainActivityOnClickHandler(Activity MainActivity,ImageView prevMonth,ImageView nextMonth,int month,int year)
	{
		this.prevMonth = prevMonth;
		this.nextMonth = nextMonth;
		MainActivityOnClickHandler.month = month;
		MainActivityOnClickHandler.year = year;
		this.MainActivity = MainActivity;
	}
	/** 
	 * When called it updates the main activity's view with new month and grid view.
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View arg0) {
		if (arg0 == prevMonth)
		{
			if (month <= 1)
			{
				month = 12;
				year--;
			}
			else
			{
				month--;
			}
			((com.calerem.MainActivity) this.MainActivity).setGridCellAdapterToDate(month, year);
		}
		else if (arg0 == nextMonth)
		{
			if (month > 11)
			{
				month = 1;
				year++;
			}
			else
			{
				month++;
			}
			((com.calerem.MainActivity) this.MainActivity).setGridCellAdapterToDate(month, year);
		}
	}

}
