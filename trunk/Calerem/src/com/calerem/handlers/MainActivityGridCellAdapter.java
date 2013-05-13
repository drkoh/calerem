/**
 * 
 */
package com.calerem.handlers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.calerem.R;
import com.calerem.classes.Event;
import com.calerem.controllers.Database;

/**
 * Grid Cell Adapter for Main Activity.
 * @author DarkParadise
 */
public class MainActivityGridCellAdapter extends BaseAdapter
{
	private final Context _context;
	private final List<String> list;
	private static final int DAY_OFFSET = 1;
	private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	private int daysInMonth;
	private int currentDayOfMonth;
	private int currentWeekDay;
	private Button gridcell;
	private TextView num_events_per_day;
	Database db;
	private final HashMap<String, Integer> eventsPerMonthMap;
	/**
	 * Base Constructor.
	 * @param Context context
	 * @param int textViewResourceId
	 * @param int month
	 * @param int year
	 */
	public MainActivityGridCellAdapter(Context context, int textViewResourceId, int month, int year)
	{
		super();
		this._context = context;
		try {
			db = new Database(this._context);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.list = new ArrayList<String>();
		Calendar calendar = Calendar.getInstance();
		setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
		setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
		printMonth(month, year);
		eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
	}
	/**
	 * Gets month based on the id.
	 * @param int i
	 * @return
	 */
	private String getMonthAsString(int i)
	{
		return months[i];
	}
	/**
	 * Gets the number of days a month has based on the id.
	 * @param int i
	 * @return
	 */
	private int getNumberOfDaysOfMonth(int i)
	{
		return daysOfMonth[i];
	}
	/**
	 * Gets an item from the list (contains days + formatting).
	 * @see android.widget.Adapter#getItem(int)
	 */
	public String getItem(int position)
	{
		return list.get(position);
	}
	/**
	 * Gets the list's size.
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return list.size();
	}
	/**
	 * Calculates the variables needed to set the buttons (next-previous month).
	 * Also decides which days belong in this month and their status (event or not).
	 * @param int mm
	 * @param int yy
	 */
	private void printMonth(int mm, int yy)
	{
		int trailingSpaces = 0;
		int daysInPrevMonth = 0;
		int prevMonth = 0;
		int prevYear = 0;
		int nextMonth = 0;
		int nextYear = 0;
		int currentMonth = mm - 1;
		daysInMonth = getNumberOfDaysOfMonth(currentMonth);
		GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
		if (currentMonth == 11)
		{
			prevMonth = currentMonth - 1;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
			nextMonth = 0;
			prevYear = yy;
			nextYear = yy + 1;
		}
		else if (currentMonth == 0)
		{
			prevMonth = 11;
			prevYear = yy - 1;
			nextYear = yy;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
			nextMonth = 1;
		}
		else
		{
			prevMonth = currentMonth - 1;
			nextMonth = currentMonth + 1;
			nextYear = yy;
			prevYear = yy;
			daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
		}
		int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		trailingSpaces = currentWeekDay;
		if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1)
		{
			++daysInMonth;
		}
		for (int i = 0; i < trailingSpaces; i++)
		{
			list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
		}
		for (int i = 1; i <= daysInMonth; i++)
		{	
			if ((i == getCurrentDayOfMonth() && currentMonth == Calendar.getInstance().get(Calendar.MONTH)))
			{
				list.add(String.valueOf(i) + "-BLACK" + "-" + getMonthAsString(currentMonth) + "-" + yy);
			}			
			else
			{
				list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
			}
		}
		for (int i = 0; i < list.size() % 7; i++)
		{
			list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
		}
	}
	/**
	 * Reads the hash map for the number of events per month and returns the map.
	 * @param int year
	 * @param int month
	 * @return map;
	 */
	private HashMap<String, Integer> findNumberOfEventsPerMonth(int year, int month)
	{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		return map;
	}
	/**
	 * Returns the position. (Not used).
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	/**
	 * Returns the view of the grid cell. Sets colors for events, amount and anything else needed.
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View row = convertView;
		if (row == null)
		{
			LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.calendar_day_gridcell, parent, false);
		}
		// Get a reference to the Day gridcell
		gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
		gridcell.setOnClickListener(new MainActivityGridCellAdapterOnClickListener(this._context));
		String[] day_color = list.get(position).split("-");
		String theday = day_color[0];
		String themonth = day_color[2];
		String theyear = day_color[3];
		if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null))
		{
			if (eventsPerMonthMap.containsKey(theday))
			{
				num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);
				Integer numEvents = eventsPerMonthMap.get(theday);
				num_events_per_day.setText(numEvents.toString());
			}
		}
		// Set the Day GridCell
		gridcell.setText(theday);
		long epoch = 0;
		try 
		{
			SimpleDateFormat epoch_parser  = new SimpleDateFormat("dd-MMMMMMM-yyyy",Locale.ENGLISH);
			Date date = epoch_parser.parse(theday + "-" + themonth + "-" + theyear);
			epoch = date.getTime() / 1000;
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		Event[] events = db.get_events(epoch,epoch);
		gridcell.setTag(R.id.GRIDCELL_Date,epoch);
		gridcell.setTag(R.id.GRIDCELL_Events, events);
		if (day_color[1].equals("GREY"))
		{
			gridcell.setTextColor(Color.GRAY);
			gridcell.setClickable(false);
		}
		else if (day_color[1].equals("WHITE"))
		{
			gridcell.setTextColor(Color.WHITE);
			if(events.length>0)
			{
				gridcell.setBackgroundColor(Color.GREEN);
			}
		}
		else if (day_color[1].equals("BLACK"))
		{
			gridcell.setTextColor(this._context.getResources().getColor(R.color.static_text_color));
			if(events.length>0)
			{
				gridcell.setBackgroundColor(Color.GREEN);
			}
		}
		return row;
	}
	/**
	 * @return the currentDayOfMonth
	 */
	public int getCurrentDayOfMonth() {
		return currentDayOfMonth;
	}
	/**
	 * @param currentDayOfMonth the currentDayOfMonth to set
	 */
	public void setCurrentDayOfMonth(int currentDayOfMonth) {
		this.currentDayOfMonth = currentDayOfMonth;
	}
	/**
	 * @return the currentWeekDay
	 */
	public int getCurrentWeekDay() {
		return currentWeekDay;
	}
	/**
	 * @param currentWeekDay the currentWeekDay to set
	 */
	public void setCurrentWeekDay(int currentWeekDay) {
		this.currentWeekDay = currentWeekDay;
	}
}
