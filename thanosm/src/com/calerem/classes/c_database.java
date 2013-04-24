package com.calerem.classes;

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
//	private static final String EXPORT_FILE_NAME = Environment.getExternalStorageDirectory().getPath() + "/CaleRem/export.xml";
	private static String v_db_name = "Calerem.db"; 
	public SQLiteDatabase myDataBase;
	private static Context myContext;
	public static String v_sqlite_path;
//	private Exporter _exporter;

	public c_database(Context context) throws IOException {
		super(myContext, "calerem", null, 1);
		c_database.myContext = context;
		v_sqlite_path = c_database.myContext.getDatabasePath(v_db_name).getAbsolutePath();
		if(this.checkDataBase())
		{
			this.openDataBase();
		}
		else
		{
			this.createDataBase();
			this.openDataBase();
		}
	}
	private void createDataBase() throws IOException {
		myContext.openOrCreateDatabase(v_db_name, Context.MODE_PRIVATE, null);
		try {
				copyDataBase();
			} 
		catch (IOException e) 
			{
				throw new Error("Error copying database");
			}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each
	 * time you open the application.
	 * 
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase() {

		SQLiteDatabase checkDB = null;

		try {
			checkDB = SQLiteDatabase.openDatabase(v_sqlite_path, null, SQLiteDatabase.OPEN_READONLY);
		} catch (SQLiteException e) {}
		if (checkDB != null) {
			checkDB.close();
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Copies your database from your local assets-folder to the just created
	 * empty database in the system folder, from where it can be accessed and
	 * handled. This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(v_db_name);

		// Path to the just created empty db
		String outFileName = v_sqlite_path;

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() throws SQLException {
		// Open the database
		myDataBase = SQLiteDatabase.openDatabase(v_sqlite_path, null,
				SQLiteDatabase.OPEN_READWRITE);
	}
	
	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}
	
	// insert values in events by using ContentValues API
	public void f_add_event(c_event v_new_event) {
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		ContentValues cv = new ContentValues();
		cv.put(name, v_new_event.v_event_name);
		cv.put(type, v_new_event.v_event_type);
		cv.put(date, v_new_event.v_event_date);
		if(v_new_event.v_event_contact == null)
		{
			cv.put(contact_id, (Integer) null);
		}
		else
		{
			cv.put(contact_id, v_new_event.v_event_contact.v_id);	
		}
		cv.put(description, v_new_event.v_event_description);
		myDataBase.insert("events", null, cv);
		cv.clear();
	}
	
	// delete events by id
	public void f_delete_event(Integer v_event_id) {
		String id="_id";
		myDataBase.delete("events",id + "=" + v_event_id, null);
	}
	
	// update events table with query
	public void f_update_event(c_event v_new_event) {
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		String id="_id";
		ContentValues cv = new ContentValues();
		cv.put(name, v_new_event.v_event_name);
		cv.put(type, v_new_event.v_event_type);
		cv.put(date, v_new_event.v_event_date);
		if(v_new_event.v_event_contact == null)
		{
			cv.put(contact_id, (Integer) null);
		}
		else
		{
			cv.put(contact_id, v_new_event.v_event_contact.v_id);	
		}		
		cv.put(description, v_new_event.v_event_description);
		myDataBase.update("events", cv,id + "=" + v_new_event.v_event_id, null);
		cv.clear();
	}
	public c_event f_get_event(int v_event_id)
	{
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		String id="_id";
		Cursor dbCursor = myDataBase.query("events", null,id + "=" + v_event_id, null, null, null,  "1");
		dbCursor.moveToFirst();
		c_event v_event;
		c_contact v_contact = null;
		if(!dbCursor.isNull(dbCursor.getColumnIndex(contact_id)))
		{
			v_contact = this.f_get_contact(dbCursor.getInt(dbCursor.getColumnIndex(contact_id)));
		}
		v_event = new c_event(
				dbCursor.getString(dbCursor.getColumnIndex(type)),
				dbCursor.getString(dbCursor.getColumnIndex(name)),
				dbCursor.getInt(dbCursor.getColumnIndex(date)),
				v_contact,
				dbCursor.getInt(dbCursor.getColumnIndex(id)),
				dbCursor.getString(dbCursor.getColumnIndex(description))
				);
		return v_event;
	}	

	public void f_import_events(String v_export_path) {

	}

	public String f_export_events(String v_export_path) {
		return "";
	}

	// Restore Original .db File.
	public void f_factory_reset() {
		this.close();
		try {
			this.createDataBase();
			this.openDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Return applications events based on a period.
	public c_event[] f_return_events(Integer v_start_time, Integer v_end_time) {
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		String id="_id";
		Cursor dbCursor = myDataBase.query("events", null,"date >=" + v_start_time + " AND date <=" + v_end_time, null, null, null, null);
		c_event v_events[] = new c_event[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			c_contact v_contact = null;
			if(!dbCursor.isNull(dbCursor.getColumnIndex(contact_id)))
			{
				v_contact = this.f_get_contact(dbCursor.getInt(dbCursor.getColumnIndex("contact_id")));
			}
			v_events[i] = new c_event(
					dbCursor.getString(dbCursor.getColumnIndex(type)),
					dbCursor.getString(dbCursor.getColumnIndex(name)),
					dbCursor.getInt(dbCursor.getColumnIndex(date)),
					v_contact,
					dbCursor.getInt(dbCursor.getColumnIndex(id)),
					dbCursor.getString(dbCursor.getColumnIndex(description))
					); 
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return v_events;
	}
	
	//Return applications configuration object.
	public c_configuration f_read_configuration() {
		String date_format,sound_path,language,skin_path,eortologio_url;
		date_format="date_format";
		sound_path="sound_path";
		language="language";
		skin_path="skin_path";
		eortologio_url="eortologio_url";
		Cursor dbCursor = myDataBase.query("configuration", null, null, null, null, null, null);
		dbCursor.moveToFirst();
		c_configuration v_configuration = new c_configuration(
				dbCursor.getString(dbCursor.getColumnIndex(date_format)),
				dbCursor.getString(dbCursor.getColumnIndex(sound_path)),
				dbCursor.getString(dbCursor.getColumnIndex(language)),
				dbCursor.getString(dbCursor.getColumnIndex(skin_path)),
				dbCursor.getString(dbCursor.getColumnIndex(eortologio_url))
				);
		dbCursor.close();
		return v_configuration;
	}
	
	//Update the configuration table with new entries.
	public void f_update_configuration(c_configuration v_new_configuration) {
		String date_format,sound_path,language,skin_path,eortologio_url;
		date_format="date_format";
		sound_path="sound_path";
		language="language";
		skin_path="skin_path";
		eortologio_url="eortologio_url";
		ContentValues cv = new ContentValues();
		cv.put(date_format, v_new_configuration.v_date_format);
		cv.put(sound_path, v_new_configuration.v_notification_sound);
		cv.put(language, v_new_configuration.v_language);
		cv.put(skin_path, v_new_configuration.v_skin);
		cv.put(eortologio_url, v_new_configuration.v_eortologio_xml);
		myDataBase.update("configuration", cv, null, null);
		cv.clear();
	}
	
	//Insert new celebration, its actually an event without contact
	//Example: 28-Oct
	public void f_add_celebration(c_event v_new_cele) {
		String name,type,date,description;
		name="name";
		type="type";
		date="date";
		description="description";
		
		ContentValues cv = new ContentValues();
		cv.put(name, v_new_cele.v_event_name);
		cv.put(type, v_new_cele.v_event_type);
		cv.put(date, v_new_cele.v_event_date);
		cv.put(description, v_new_cele.v_event_description);
		myDataBase.insert("celebrations", null, cv);
		cv.clear();
	}

	//Update a celebration
	public void f_update_celebration(c_event v_new_cele) {
		String name,type,date,description,id;
		name="name";
		type="type";
		date="date";
		description="description";
		id="_id";
		
		ContentValues cv = new ContentValues();
		cv.put(name, v_new_cele.v_event_name);
		cv.put(type, v_new_cele.v_event_type);
		cv.put(date, v_new_cele.v_event_date);
		cv.put(description, v_new_cele.v_event_description);
		myDataBase.update("celebrations", cv, id+ "=" + v_new_cele.v_event_id, null);
		cv.clear();
	}

	//Delete Celebration
	public void f_delete_celebration(c_event v_new_cele) {
		String id = "_id";
		myDataBase.delete("celebrations", id + "=" + v_new_cele.v_event_id, null);
	}
	
	// Delete all Celebrations.
	public void f_truncate_celebrations() {
		myDataBase.delete("celebrations", null, null);

	}
	
	//Insert sync date in the table.
	public void f_log_sync(c_sync_log v_log) {
		String type,date;
		type="type";
		date="date";
		ContentValues cv = new ContentValues();
		cv.put(type, v_log.v_type);
		cv.put(date, v_log.v_date);
		myDataBase.insert("synchronize_log", null, cv);
		cv.clear();
	}
	
	//Return the sync log based on how many entries the developer asked for.
	public c_sync_log[] f_read_sync_log(int limit) {
		String type,date,id;
		type="type";
		date="date";
		id="_id";
		Cursor dbCursor = myDataBase.query("synchronize_log", null, null, null, null, null, "date DESC", "" + limit);
		c_sync_log v_log[] = new c_sync_log[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			v_log[i] = new c_sync_log(
				dbCursor.getInt(dbCursor.getColumnIndex(date)),
				dbCursor.getString(dbCursor.getColumnIndex(type)),
				dbCursor.getInt(dbCursor.getColumnIndex(id))
				);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return v_log;
	}

	//insert a message sent to the log, so we can keep history.
	public void f_log_messages(c_message_log v_log) {
		String type,date,contact_id,message;
		type="type";
		date="date";
		contact_id="contact_id";
		message = "message";
		
		ContentValues cv = new ContentValues();
		cv.put(type, v_log.v_type);
		cv.put(date, v_log.v_date);
		cv.put(contact_id, v_log.v_contact.v_id);
		cv.put(message, v_log.v_message);
		myDataBase.insert("messages", null, cv);
		cv.clear();
	}


	public c_message_log[] f_read_message_log(int limit) {
		String type,date,contact_id,message,id;
		type="type";
		date="date";
		contact_id="contact_id";
		message = "message";
		id="_id";
		Cursor dbCursor = myDataBase.query("messages", null, null, null, null, null, "date DESC", "" + limit);
		c_message_log v_log[] = new c_message_log[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			c_contact v_contact = null;
			if(!dbCursor.isNull(dbCursor.getColumnIndex(contact_id)))
			{
				v_contact = this.f_get_contact(dbCursor.getInt(dbCursor.getColumnIndex("contact_id")));
			}
			v_log[i] = new c_message_log(
				dbCursor.getInt(dbCursor.getColumnIndex(date)),
				dbCursor.getInt(dbCursor.getColumnIndex(id)),
				dbCursor.getString(dbCursor.getColumnIndex(type)),
				dbCursor.getString(dbCursor.getColumnIndex(message)),
				v_contact
				);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return v_log;
	}

	//Add a contact to the database.
	public void f_add_contact(c_contact v_contact) {
		String name,lastname,phone,email;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		ContentValues cv = new ContentValues();
		cv.put(name, v_contact.v_name);
		cv.put(lastname, v_contact.v_lastname);
		cv.put(phone, v_contact.v_phone);
		cv.put(email, v_contact.v_email);
		myDataBase.insert("contacts", null, cv);
		cv.clear();
	}
	// update a contact.
	public void f_update_contact(c_contact v_contact) {
		String name,lastname,phone,email,id;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		id="_id";
		
		ContentValues cv = new ContentValues();
		cv.put(name, v_contact.v_name);
		cv.put(lastname, v_contact.v_lastname);
		cv.put(phone, v_contact.v_phone);
		cv.put(email, v_contact.v_email);
		myDataBase.update("contacts", cv, id + "=" + v_contact.v_id, null);
		cv.clear();
	}
	//Get a contact from the database.
	public c_contact f_get_contact(int v_contact_id)
	{
		String name,lastname,phone,email,id;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		id="_id";
		
		Cursor dbCursor = myDataBase.query("contacts", null, id + "=" + v_contact_id, null, null, null, null);
		dbCursor.moveToFirst();
		c_contact v_contact = new c_contact(
				dbCursor.getString(dbCursor.getColumnIndex(name)),
				dbCursor.getString(dbCursor.getColumnIndex(lastname)),
				dbCursor.getLong(dbCursor.getColumnIndex(phone)),
				dbCursor.getString(dbCursor.getColumnIndex(email)),
				dbCursor.getInt(dbCursor.getColumnIndex(id))
				);
		dbCursor.close();
		return v_contact;
	}
	public c_contact[] f_get_contacts()
	{
		String name,lastname,phone,email,id;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		id="_id";
		
		Cursor dbCursor = myDataBase.query("contacts", null, null, null, null, null, null);
		dbCursor.moveToFirst();
		c_contact v_contact[] = new c_contact[dbCursor.getCount()]; 
		for(int i=0;i<dbCursor.getCount();i++)
		{
			v_contact[i] = new c_contact(
				dbCursor.getString(dbCursor.getColumnIndex(name)),
				dbCursor.getString(dbCursor.getColumnIndex(lastname)),
				dbCursor.getLong(dbCursor.getColumnIndex(phone)),
				dbCursor.getString(dbCursor.getColumnIndex(email)),
				dbCursor.getInt(dbCursor.getColumnIndex(id))
				);
		}
		dbCursor.close();
		return v_contact;
	}
	
	// Destructor function
	@Override
	protected void finalize() { 
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