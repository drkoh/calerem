package com.example.calerem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class c_database extends SQLiteOpenHelper {

	public SQLiteDatabase myDataBase;
	private static Context myContext;
	public static String v_sqlite_path = "/data/data/com.example.calerem/databases/calerem.db";

	public c_database(Context context) {
		super(myContext, "calerem", null, 1);
		this.myContext = context;
		myDataBase = SQLiteDatabase.openDatabase(v_sqlite_path, null,
				SQLiteDatabase.OPEN_READWRITE);
		// TODO Auto-generated constructor stub
	}

	public void f_add_event(c_event v_new_event)
	{
		ContentValues cv= new ContentValues();
		cv.put("name", v_new_event.v_event_name);
		cv.put("type", v_new_event.v_event_type);
		cv.put("date", v_new_event.v_event_date);
		cv.put("description", v_new_event.v_event_description);
		cv.put("Contact_id", v_new_event.v_event_contact.v_id);
		myDataBase.insert("events", null, cv);
	}
	
	public void f_delete_event(Integer v_event_id)
	{
		myDataBase.execSQL("DELETE FROM events WHERE id=" + v_event_id +";");
	}
	
	public void f_update_event(c_event v_new_event)
	{
		myDataBase.execSQL("UPDATE events SET name=" + v_new_event.v_event_name + ", type=" + v_new_event.v_event_type + ", date=" + v_new_event.v_event_date + ", description=" + v_new_event.v_event_description + ", Contact_id=" + v_new_event.v_event_contact.v_id + " where id=" + v_new_event.v_event_id + ";");
	}
	
	public void f_import_events(String v_export_path)
	{
		
	}
	
	public String f_export_events(String v_export_path)
	{
		return "";
	}
	
	public void f_factory_reset()
	{
		
	}
	
	public c_event f_return_events(Integer v_start_time, Integer v_end_time)
	{
		c_event event1 = null;
		return event1;
	}
	public c_configuration f_read_configuration()
	{
		c_configuration config1 = null;
		return config1;
	}
	
	public void f_update_configuration(c_configuration v_new_configuration)
	{
		
	}
	
	public void f_add_celebration(c_event v_new_cele)
	{
		
	}
	
	public void f_update_celebration(c_event v_new_cele)
	{
		
	}
	
	public void f_delete_celebration(c_event v_new_cele)
	{
		
	}
	
	public void f_truncate_celebrations(){
		
	}
	
	public void f_log_synch(String v_type, Integer v_date)
	{
		
	}
	
	public void f_read_synch_log()
	{
		
	}
	public void f_log_messages(String v_type, c_contact v_contact, String v_message)
	{
		
	}
	
	public void f_read_message_log()
	{
		
	}
	
	public void f_add_contact(c_contact v_contact)
	{
		
	}
	
	public void f_update_contact(c_contact v_contact){
		
	}
	
	protected void finalize () 
	{     //Destructor function
       myDataBase.close();
    }
		
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
