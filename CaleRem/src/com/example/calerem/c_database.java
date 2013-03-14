package com.example.calerem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
		Cursor dbCursor = myDataBase.rawQuery("SELECT name, type, date, Contact_id, id, description FROM events WHERE date>" + v_start_time + " AND date<" + v_end_time + ";" , null);
		c_event event1 = null;
		while (!dbCursor.moveToNext())
        {
			event1.v_event_name = dbCursor.getString(0);
			event1.v_event_type = dbCursor.getString(1);
			event1.v_event_date = dbCursor.getInt(2);
			event1.v_event_contact.v_id = dbCursor.getInt(3);
			event1.v_event_id = dbCursor.getInt(4);
			event1.v_event_description = dbCursor.getString(5);
        }
		return event1;
	}
	public c_configuration f_read_configuration()
	{
		c_configuration config1 = null;
		Cursor dbCursor = myDataBase.rawQuery("SELECT date_format, sound_path, language, skin_path, eortologio_url FROM Configuration ;", null);
		while (!dbCursor.moveToNext())
        {
			config1.v_date_format = dbCursor.getString(0);
			config1.v_notification_sound = dbCursor.getString(1);
			config1.v_language = dbCursor.getString(2);
			config1.v_skin = dbCursor.getString(3);
			config1.v_eortologio_xml = dbCursor.getString(4);
        }
		return config1;
	}
	
	public void f_update_configuration(c_configuration v_new_configuration)
	{
		myDataBase.execSQL("UPDATE configuration SET date_format=" + v_new_configuration.v_date_format + ", sound_path=" + v_new_configuration.v_notification_sound + ", language=" + v_new_configuration.v_language + ", skin_path=" + v_new_configuration.v_skin + ", eortologio_url=" + v_new_configuration.v_eortologio_xml + " ;");
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
		ContentValues cv= new ContentValues();
		cv.put("name", v_contact.v_name);
		cv.put("lastname", v_contact.v_lastname);
		cv.put("phone", v_contact.v_phone);
		cv.put("email", v_contact.v_email);
		
		myDataBase.insert("contacts", null, cv);
	}
	
	public void f_update_contact(c_contact v_contact)
	{
		myDataBase.execSQL("UPDATE contacts SET name=" + v_contact.v_name + ", lastname=" + v_contact.v_lastname + ", phone=" + v_contact.v_phone + ", email=" + v_contact.v_email + "WHERE id=" + v_contact.v_id + " ;");
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