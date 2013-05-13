package com.calerem.handlers;

import com.calerem.R;
import com.calerem.classes.Event;
import com.calerem.controllers.UIController;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Button On Click Handler.
 * @author DarkParadise
 */
public class MainActivityGridCellAdapterOnClickListener implements OnClickListener {

	private Context basecontext;
	/**
	 * Base Constructor.
	 * @param Context context
	 */
	public MainActivityGridCellAdapterOnClickListener(Context context)
	{
		this.basecontext = context;
	}
	/**
	 * Handles the on click event. Pops an AlertDialog and asks the user what to do (controller style).
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View arg0) {
		long epoch = (Long) arg0.getTag(R.id.GRIDCELL_Date);
		Event events[] = (Event[]) arg0.getTag(R.id.GRIDCELL_Events);
		if(events.length>0)
		{
			final CharSequence[] items = new CharSequence[events.length+1];
			items[0]= "New Event";
			for(int i=1;i<=events.length;i++)
			{
				items[i] = "View Event:" + events[i-1].getEvent_name();
			}
			AlertDialog.Builder builder = new AlertDialog.Builder(this.basecontext);
			builder.setTitle("Select Action:");
			builder.setItems(items,  new MainActivityGridCellAdapterDialogOnClickListener(this.basecontext,epoch,events));
			AlertDialog alert = builder.create();
			alert.show();
		}
		else
		{
			new UIController(this.basecontext).newNewEvent(epoch);
		}
	}

}
