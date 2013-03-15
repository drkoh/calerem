package com.example.calerem;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class c_database extends SQLiteOpenHelper {

	public SQLiteDatabase myDataBase;
	private static Context myContext;
	public static String v_sqlite_path = "/Calerem/src/com/example/calerem/databases/";
	private static String v_db_name = "Calerem.db";

	public c_database(Context context) {
		//constructor
		super(myContext, "calerem", null, 1);
		this.myContext = context;
		//function that "opens" the database and enables us to read and write from and on it
		myDataBase = SQLiteDatabase.openDatabase(v_sqlite_path, null,
				SQLiteDatabase.OPEN_READWRITE);
		// TODO Auto-generated constructor stub
	}

//ANASTASIAS COPY-PASTE BEGIN

	public void createDataBase() throws IOException{
 
    	boolean dbExist = checkDataBase();
 
    	if(dbExist){
    		//do nothing - database already exist
    	}else{
 
    		//By calling this method and empty database will be created into the default system path
               //of your application so we are gonna be able to overwrite that database with our database.
        	this.getReadableDatabase();
 
        	try {
 
    			copyDataBase();
 
    		} catch (IOException e) {
 
        		throw new Error("Error copying database");
 
        	}
    	}
 
    }
 
    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){
 
    	SQLiteDatabase checkDB = null;
 
    	try{
    		String myPath = v_sqlite_path + v_db_name;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}catch(SQLiteException e){
 
    		//database does't exist yet.
 
    	}
 
    	if(checkDB != null){
 
    		checkDB.close();
 
    	}
 
    	return checkDB != null ? true : false;
    }
 
    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{
 
    	//Open your local db as the input stream
    	InputStream myInput = myContext.getAssets().open(v_db_name);
 
    	// Path to the just created empty db
    	String outFileName = v_sqlite_path + v_db_name;
 
    	//Open the empty db as the output stream
    	OutputStream myOutput = new FileOutputStream(outFileName);
 
    	//transfer bytes from the inputfile to the outputfile
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = myInput.read(buffer))>0){
    		myOutput.write(buffer, 0, length);
    	}
 
    	//Close the streams
    	myOutput.flush();
    	myOutput.close();
    	myInput.close();
 
    	}

	public void openDataBase() throws SQLException{
 
    	//Open the database
        String myPath = v_sqlite_path + v_db_name;
    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
    	}
 
    	@Override
	public synchronized void close() {
 
    	    if(myDataBase != null)
    		    myDataBase.close();
 
    	    super.close();
 
	}
 
	@Override
	public void onCreate(SQLiteDatabase db) {
 
	}
 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
 
	}

//ANASTASIAS COPY-PASTE END

	public void f_add_event(c_event v_new_event)
	{
		//insert values in events by using ContentValues var
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
		//delete events by id
		myDataBase.execSQL("DELETE FROM events WHERE id=" + v_event_id +";");
	}
	
	public void f_update_event(c_event v_new_event)
	{
		//update events table with query
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
		//create a cursor that gets the values printed by select query and import them in the c_event's object vars
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
		//create a cursor (table) that gets the values printed by the select query and enter, in every variable of the configuration's class object, these values 
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
		//update table configuration with given by object v_new_configuration entries
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
	
	public void f_truncate_celebrations()
	{
		//delete the special events from events table
		myDataBase.execSQL("DELETE FROM events WHERE Contact_id = NULL ;");
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
		//insert values in contacts by ContentValues var
		ContentValues cv= new ContentValues();
		cv.put("name", v_contact.v_name);
		cv.put("lastname", v_contact.v_lastname);
		cv.put("phone", v_contact.v_phone);
		cv.put("email", v_contact.v_email);
		
		myDataBase.insert("contacts", null, cv);
	}
	
	public void f_update_contact(c_contact v_contact)
	{
		//update contacts by using the proper query
		myDataBase.execSQL("UPDATE contacts SET name=" + v_contact.v_name + ", lastname=" + v_contact.v_lastname + ", phone=" + v_contact.v_phone + ", email=" + v_contact.v_email + "WHERE id=" + v_contact.v_id + " ;");
	}
	
	protected void finalize () 
	{     //Destructor function
       myDataBase.close();
    }



}
