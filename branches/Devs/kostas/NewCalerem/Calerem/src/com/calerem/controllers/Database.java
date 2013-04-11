package com.calerem.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.calerem.classes.ConfigurationCalerem;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.classes.MessageLog;
import com.calerem.classes.SyncLog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	private static String db_name = "Calerem.db"; 
	public SQLiteDatabase myDataBase;
	private static Context myContext;
	public static String sqlite_path;

	public Database(Context context) throws IOException {
		super(myContext, "calerem", null, 1);
		Database.myContext = context;
		sqlite_path = Database.myContext.getDatabasePath(db_name).getAbsolutePath();
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
		myContext.openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
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
			checkDB = SQLiteDatabase.openDatabase(sqlite_path, null, SQLiteDatabase.OPEN_READONLY);
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
		InputStream myInput = myContext.getAssets().open(db_name);

		// Path to the just created empty db
		String outFileName = sqlite_path;

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
		myDataBase = SQLiteDatabase.openDatabase(sqlite_path, null,
				SQLiteDatabase.OPEN_READWRITE);
	}
	
	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}
	
	// insert values in events by using ContentValues API
	public long add_event(Event newevent) {
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		ContentValues cv = new ContentValues();
		cv.put(name, newevent.getEvent_name());
		cv.put(type, newevent.getEvent_type());
		cv.put(date, newevent.getEvent_date());
		if(newevent.getEvent_contact() == null)
		{
			cv.put(contact_id, (Integer) null);
		}
		else
		{
			cv.put(contact_id, newevent.getEvent_contact().getId());	
		}
		cv.put(description, newevent.getEvent_description());
		long resultCode = myDataBase.insert("events", null, cv);
		cv.clear();
		return resultCode;
	}
	
	// delete events by id
	public long delete_event(Integer event_id) {
		String id="_id";
		return myDataBase.delete("events",id + "=" + event_id, null);
	}
	
	// update events table with query
	public long update_event(Event newevent) {
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		String id="_id";
		ContentValues cv = new ContentValues();
		cv.put(name, newevent.getEvent_name());
		cv.put(type, newevent.getEvent_type());
		cv.put(date, newevent.getEvent_date());
		if(newevent.getEvent_contact() == null)
		{
			cv.put(contact_id, (Integer) null);
		}
		else
		{
			cv.put(contact_id, newevent.getEvent_contact().getId());	
		}		
		cv.put(description, newevent.getEvent_description());
		long resultCode = myDataBase.update("events", cv,id + "=" + newevent.getEvent_id(), null);
		cv.clear();
		return resultCode;
	}
	public Event get_event(int event_id)
	{
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		String id="_id";
		Cursor dbCursor = myDataBase.query("events", null,id + "=" + event_id, null, null, null,  "1");
		dbCursor.moveToFirst();
		Event event;
		Contact contact = null;
		if(!dbCursor.isNull(dbCursor.getColumnIndex(contact_id)))
		{
			contact = this.get_contact(dbCursor.getInt(dbCursor.getColumnIndex(contact_id)));
		}
		event = new Event(
				dbCursor.getString(dbCursor.getColumnIndex(type)),
				dbCursor.getString(dbCursor.getColumnIndex(name)),
				dbCursor.getInt(dbCursor.getColumnIndex(date)),
				contact,
				dbCursor.getInt(dbCursor.getColumnIndex(id)),
				dbCursor.getString(dbCursor.getColumnIndex(description))
				);
		return event;
	}	

	public void import_events(String export_path) {

	}

	public String export_events(String export_path) {
		return "";
	}

	// Restore Original .db File.
	public void factory_reset() {
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
	public Event[] return_events(Integer start_time, Integer end_time) {
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		String id="_id";
		Cursor dbCursor = myDataBase.query("events", null,"date >=" + start_time + " AND date <=" + end_time, null, null, null, null);
		Event events[] = new Event[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			Contact contact = null;
			if(!dbCursor.isNull(dbCursor.getColumnIndex(contact_id)))
			{
				contact = this.get_contact(dbCursor.getInt(dbCursor.getColumnIndex("contact_id")));
			}
			events[i] = new Event(
					dbCursor.getString(dbCursor.getColumnIndex(type)),
					dbCursor.getString(dbCursor.getColumnIndex(name)),
					dbCursor.getInt(dbCursor.getColumnIndex(date)),
					contact,
					dbCursor.getInt(dbCursor.getColumnIndex(id)),
					dbCursor.getString(dbCursor.getColumnIndex(description))
					); 
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return events;
	}
	
	//Return applications configuration object.
	public ConfigurationCalerem read_configuration() {
		String date_format,sound_path,language,skin_path,eortologio_url;
		date_format="date_format";
		sound_path="sound_path";
		language="language";
		skin_path="skin_path";
		eortologio_url="eortologio_url";
		Cursor dbCursor = myDataBase.query("configuration", null, null, null, null, null, null);
		dbCursor.moveToFirst();
		ConfigurationCalerem configuration = new ConfigurationCalerem(
				myContext,
				dbCursor.getString(dbCursor.getColumnIndex(date_format)),
				dbCursor.getString(dbCursor.getColumnIndex(sound_path)),
				dbCursor.getString(dbCursor.getColumnIndex(language)),
				dbCursor.getString(dbCursor.getColumnIndex(skin_path)),
				dbCursor.getString(dbCursor.getColumnIndex(eortologio_url))
				);
		dbCursor.close();
		return configuration;
	}
	
	//Update the configuration table with new entries.
	public long update_configuration(ConfigurationCalerem new_configuration) {
		String date_format,sound_path,language,skin_path,eortologio_url;
		date_format="date_format";
		sound_path="sound_path";
		language="language";
		skin_path="skin_path";
		eortologio_url="eortologio_url";
		ContentValues cv = new ContentValues();
		cv.put(date_format, new_configuration.getDate_format());
		cv.put(sound_path, new_configuration.getNotification_sound());
		cv.put(language, new_configuration.getLanguage());
		cv.put(skin_path, new_configuration.getSkin());
		cv.put(eortologio_url, new_configuration.getEortologio_xml());
		long resultCode = myDataBase.update("configuration", cv, null, null);
		cv.clear();
		return resultCode;
	}
	
	//Insert new celebration, its actually an event without contact
	//Example: 28-Oct
	public long add_celebration(Event new_cele) {
		String name,type,date,description;
		name="name";
		type="type";
		date="date";
		description="description";
		
		ContentValues cv = new ContentValues();
		cv.put(name, new_cele.getEvent_name());
		cv.put(type, new_cele.getEvent_type());
		cv.put(date, new_cele.getEvent_date());
		cv.put(description, new_cele.getEvent_description());
		long resultCode = myDataBase.insert("celebrations", null, cv);
		cv.clear();
		return resultCode;
	}

	//Update a celebration
	public long update_celebration(Event new_cele) {
		String name,type,date,description,id;
		name="name";
		type="type";
		date="date";
		description="description";
		id="_id";
		
		ContentValues cv = new ContentValues();
		cv.put(name, new_cele.getEvent_name());
		cv.put(type, new_cele.getEvent_type());
		cv.put(date, new_cele.getEvent_date());
		cv.put(description, new_cele.getEvent_description());
		long resultCode = myDataBase.update("celebrations", cv, id+ "=" + new_cele.getEvent_id(), null);
		cv.clear();
		return resultCode;
	}

	//Delete Celebration
	public long delete_celebration(Event new_cele) {
		String id = "_id";
		return myDataBase.delete("celebrations", id + "=" + new_cele.getEvent_id(), null);
	}
	
	// Delete all Celebrations.
	public long truncate_celebrations() {
		return myDataBase.delete("celebrations", null, null);

	}
	
	//Insert sync date in the table.
	public long log_sync(SyncLog log) {
		String type,date;
		type="type";
		date="date";
		ContentValues cv = new ContentValues();
		cv.put(type, log.getType());
		cv.put(date, log.getDate());
		long resultCode = myDataBase.insert("synchronize_log", null, cv);
		cv.clear();
		return resultCode;
	}
	
	//Return the sync log based on how many entries the developer asked for.
	public SyncLog[] read_sync_log(int limit) {
		String type,date,id;
		type="type";
		date="date";
		id="_id";
		Cursor dbCursor = myDataBase.query("synchronize_log", null, null, null, null, null, "date DESC", "" + limit);
		SyncLog log[] = new SyncLog[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			log[i] = new SyncLog(
				dbCursor.getInt(dbCursor.getColumnIndex(date)),
				dbCursor.getString(dbCursor.getColumnIndex(type)),
				dbCursor.getInt(dbCursor.getColumnIndex(id))
				);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return log;
	}

	//insert a message sent to the log, so we can keep history.
	public long log_messages(MessageLog log) {
		String type,date,contact_id,message;
		type="type";
		date="date";
		contact_id="contact_id";
		message = "message";
		ContentValues cv = new ContentValues();
		cv.put(type, log.getType());
		cv.put(date, log.getDate());
		cv.put(contact_id, log.getContact().getId());
		cv.put(message, log.getMessage());
		long resultCode = myDataBase.insert("messages", null, cv);
		cv.clear();
		return resultCode;
	}


	public MessageLog[] read_message_log(int limit) {
		String type,date,contact_id,message,id;
		type="type";
		date="date";
		contact_id="contact_id";
		message = "message";
		id="_id";
		Cursor dbCursor = myDataBase.query("messages", null, null, null, null, null, "date DESC", "" + limit);
		MessageLog log[] = new MessageLog[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			Contact contact = null;
			if(!dbCursor.isNull(dbCursor.getColumnIndex(contact_id)))
			{
				contact = this.get_contact(dbCursor.getInt(dbCursor.getColumnIndex("contact_id")));
			}
			log[i] = new MessageLog(
				dbCursor.getInt(dbCursor.getColumnIndex(date)),
				dbCursor.getInt(dbCursor.getColumnIndex(id)),
				dbCursor.getString(dbCursor.getColumnIndex(type)),
				dbCursor.getString(dbCursor.getColumnIndex(message)),
				contact
				);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return log;
	}

	//Add a contact to the database.
	public long add_contact(Contact contact) {
		String name,lastname,phone,email;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		ContentValues cv = new ContentValues();
		cv.put(name, contact.getName());
		cv.put(lastname, contact.getLastname());
		cv.put(phone, contact.getPhone());
		cv.put(email, contact.getEmail());
		long resultCode = myDataBase.insert("contacts", null, cv);
		cv.clear();
		return resultCode;
	}
	// update a contact.
	public long update_contact(Contact contact) {
		String name,lastname,phone,email,id;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		id="_id";
		ContentValues cv = new ContentValues();
		cv.put(name, contact.getName());
		cv.put(lastname, contact.getLastname());
		cv.put(phone, contact.getPhone());
		cv.put(email, contact.getEmail());
		long resultCode = myDataBase.update("contacts", cv, id + "=" + contact.getId(), null);
		cv.clear();
		return resultCode;
	}
	//Get a contact from the database.
	public Contact get_contact(int contact_id)
	{
		String name,lastname,phone,email,id;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		id="_id";
		
		Cursor dbCursor = myDataBase.query("contacts", null, id + "=" + contact_id, null, null, null, null);
		dbCursor.moveToFirst();
		Contact contact = new Contact(
				dbCursor.getString(dbCursor.getColumnIndex(name)),
				dbCursor.getString(dbCursor.getColumnIndex(lastname)),
				dbCursor.getString(dbCursor.getColumnIndex(phone)),
				dbCursor.getString(dbCursor.getColumnIndex(email)),
				dbCursor.getInt(dbCursor.getColumnIndex(id))
				);
		dbCursor.close();
		return contact;
	}
	public Contact[] get_contacts()
	{
		String name,lastname,phone,email,id;
		name="name";
		lastname = "lastname";
		phone="phone";
		email="email";
		id="_id";
		
		Cursor dbCursor = myDataBase.query("contacts", null, null, null, null, null, null);
		dbCursor.moveToFirst();
		Contact contact[] = new Contact[dbCursor.getCount()]; 
		for(int i=0;i<dbCursor.getCount();i++)
		{
			contact[i] = new Contact(
				dbCursor.getString(dbCursor.getColumnIndex(name)),
				dbCursor.getString(dbCursor.getColumnIndex(lastname)),
				dbCursor.getString(dbCursor.getColumnIndex(phone)),
				dbCursor.getString(dbCursor.getColumnIndex(email)),
				dbCursor.getInt(dbCursor.getColumnIndex(id))
				);
		}
		dbCursor.close();
		return contact;
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
