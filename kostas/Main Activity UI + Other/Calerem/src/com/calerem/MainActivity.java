package com.calerem;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.calerem.classes.Event;
import com.calerem.controllers.Database;
import com.calerem.factories.UIActivityResultsHandlerFactory;
import com.calerem.handlers.MainActivityGridCellAdapter;
import com.calerem.handlers.MainActivityOnClickHandler;

/**
 * Main Activity of the application with the calendar as main view.
 * @author DarkParadise
 */
public class MainActivity extends Activity
{
	private Button eventsButton,currentMonth;
	private ImageView prevMonth,nextMonth;
	private GridView calendarView;
	private MainActivityGridCellAdapter adapter;
	private Calendar _calendar;
	private int month, year;
	private static final String dateTemplate = "MMMM yyyy";
	private Database db;

	/** 
	 * Works as base constructor.
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		try {
			db = new Database(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		this.initVisualObjects();
	}
	/** 
	 * When application regains focus it updates the calendar and the header.
	 * @see android.app.Activity#onResume()
	 */
	@Override
	public void onResume()
	{
		super.onResume();
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter); 
		this.updateEventsButton();
	}
	/**
	 * Calls the handler for activity results.
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		new UIActivityResultsHandlerFactory(this).onActivityResult(requestCode, resultCode, data);
	}
	/**
	 * Initializes the visual objects.
	 */
	private void initVisualObjects()
	{
		setContentView(R.layout.activity_main);
		eventsButton = (Button) this.findViewById(R.id.eventsButton);
		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(new MainActivityOnClickHandler(this, prevMonth, nextMonth, month, year));
		currentMonth = (Button) this.findViewById(R.id.currentMonth);
		currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));
		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(new MainActivityOnClickHandler(this, prevMonth, nextMonth, month, year));
		calendarView = (GridView) this.findViewById(R.id.calendar);
		adapter = new MainActivityGridCellAdapter(this, R.id.calendar_day_gridcell, month, year);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}
	/**
	 * Updates the Grid View (Days in month). Called after user clicked on next or previous month.
	 * Called by handler.
	 * @param month
	 * @param year
	 */
	public void setGridCellAdapterToDate(int month, int year)
	{
		adapter = new MainActivityGridCellAdapter(this, R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(DateFormat.format(dateTemplate, _calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}
	/**
	 * Updates the Header of the Calendar.
	 */
	private void updateEventsButton()
	{
		Event[] events = db.get_events((Calendar.getInstance().getTimeInMillis()/1000)-86399, (Calendar.getInstance().getTimeInMillis()/1000)); 
		if(events.length>0)
		{
			eventsButton.setText("There are " + events.length + " events today.");
		}
		else
		{
			eventsButton.setText("There is no event registered today.");
		}
	}
}
