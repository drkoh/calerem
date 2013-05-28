/**
 * 
 */
package com.calerem.handlers;

import android.content.Context;
import android.content.DialogInterface;

import com.calerem.classes.Event;
import com.calerem.controllers.UIController;

/**
 * Dialog On Click Handler.
 * @author DarkParadise
 */
public class MainActivityGridCellAdapterDialogOnClickListener implements DialogInterface.OnClickListener {

	private Context basecontext;
	private Long epoch;
	private Event events[];
	/**
	 * Base constructor with parameters to handle events.
	 * @param Context context
	 * @param Long epoch
	 */
	public MainActivityGridCellAdapterDialogOnClickListener(Context context,Long epoch,Event events[])
	{
		basecontext = context;
		this.epoch = epoch;
		this.events = events;
	}
	/**
	 * Handles on click events.
	 * @see android.content.DialogInterface.OnClickListener#onClick(android.content.DialogInterface, int)
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
        if(which == 0)
        {
        	new UIController(this.basecontext).newNewEvent(epoch);
        }
        else
        {
        	new UIController(this.basecontext).newViewEvent(events[which-1].getEvent_id());
        }
	}

}
