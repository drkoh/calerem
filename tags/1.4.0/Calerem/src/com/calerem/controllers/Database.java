package com.calerem.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.calerem.classes.Celebration;
import com.calerem.classes.ConfigurationCalerem;
import com.calerem.classes.Contact;
import com.calerem.classes.Event;
import com.calerem.classes.MessageLog;
import com.calerem.classes.SyncLog;

public class Database extends SQLiteOpenHelper {
	private static String db_name = "Calerem.db"; 
	private SQLiteDatabase myDataBase;
	private static Context myContext;
	private static String sqlite_path;

	/**
	 * Base constructor.
	 * @param Context context
	 * @throws IOException
	 */
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
	/**
	 * Creates the database file.
	 * @throws IOException
	 */
	private void createDataBase() throws IOException {
		SQLiteDatabase db = myContext.openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
		db.close();
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
		File file = new File(sqlite_path);
		if(file.exists())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Copies the database file from assets to the ../databases folder.
	 * @throws IOException
	 */
	private void copyDataBase() throws IOException {

		// Open your local db as the input stream
		InputStream myInput = myContext.getAssets().open(db_name);

		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(sqlite_path);

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

	/**
	 * Opens the database file.
	 * @throws SQLException
	 */
	public void openDataBase() throws SQLException {
		// Open the database
		myDataBase = SQLiteDatabase.openDatabase(sqlite_path, null,SQLiteDatabase.OPEN_READWRITE);
	}

	/**
	 * Closes the database connection.
	 * @see android.database.sqlite.SQLiteOpenHelper#close()
	 */
	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}

	/**
	 * Adds an event to the database.
	 * @param Event newevent
	 * @return long how many rows were affected.
	 */
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

	/**
	 * Deletes an event from the database based on the id.
	 * @param Integer event_id
	 * @return long how many rows were affected.
	 */
	public long delete_event(Integer event_id) {
		String id="_id";
		return myDataBase.delete("events",id + "=" + event_id, null);
	}

	/**
	 * Updates an event to the database.
	 * @param Event newevent
	 * @return long how many rows were affected.
	 */
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
	/**
	 * Returns an event from the database based on its id.
	 * @param int event_id
	 * @return Event
	 */
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
		dbCursor.close();
		return event;
	}	

	public void import_events(String export_path) {

	}

	public String export_events(String export_path) {
		return "";
	}

	/**
	 * Restores the original .db file.
	 */
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

	/**
	 * Returns all events between the start and end time.
	 * @param Long start_time Epoch start.
	 * @param Long end_time Epoch end.
	 * @return Event array.
	 */
	public Event[] get_events(long start_time, long end_time) {
		String name,type,date,contact_id,description;
		name="name";
		type="type";
		date="date";
		contact_id="contact_id";
		description="description";
		String id="_id";
		Cursor dbCursor = myDataBase.query("events", null,"date >= " + start_time + " AND date <= " + end_time, null, null, null, null);
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

	/**
	 * Reads the configuration from the database.
	 * @return ConfigurationCalerem
	 */
	public ConfigurationCalerem read_configuration() {
		String date_format,sound_path,language,skin_path,eortologioURLGR,eortologioURLEN;
		date_format="date_format";
		sound_path="sound_path";
		language="language";
		skin_path="skin_path";
		eortologioURLGR="eortologiourlgr";
		eortologioURLEN="eortologiourlen";
		Cursor dbCursor = myDataBase.query("configuration", null, null, null, null, null, null);
		dbCursor.moveToFirst();
		ConfigurationCalerem configuration = new ConfigurationCalerem(
				dbCursor.getString(dbCursor.getColumnIndex(date_format)),
				dbCursor.getString(dbCursor.getColumnIndex(sound_path)),
				dbCursor.getString(dbCursor.getColumnIndex(language)),
				dbCursor.getString(dbCursor.getColumnIndex(skin_path)),
				dbCursor.getString(dbCursor.getColumnIndex(eortologioURLGR)),
				dbCursor.getString(dbCursor.getColumnIndex(eortologioURLEN))
				);
		dbCursor.close();
		return configuration;
	}

	/**
	 * Updates the apps configuration in the database.
	 * @param ConfigurationCalerem new_configuration
	 * @return long how many rows were affected.
	 */
	public long update_configuration(ConfigurationCalerem new_configuration) {
		String date_format,sound_path,language,skin_path,eortologioURLGR,eortologioURLEN;
		date_format="date_format";
		sound_path="sound_path";
		language="language";
		skin_path="skin_path";
		eortologioURLGR="eortologiourlgr";
		eortologioURLEN="eortologiourlen";
		ContentValues cv = new ContentValues();
		cv.put(date_format, new_configuration.getDate_format());
		cv.put(sound_path, new_configuration.getNotification_sound());
		cv.put(language, new_configuration.getLanguage());
		cv.put(skin_path, new_configuration.getSkin());
		cv.put(eortologioURLGR, new_configuration.getEortologioURLGR());
		cv.put(eortologioURLEN, new_configuration.getEortologioURLEN());
		long resultCode = myDataBase.update("configuration", cv, null, null);
		cv.clear();
		return resultCode;
	}

	/**
	 * Returns all celebrations for a specific date ("dd-MM")
	 * @param String date
	 * @return Celebration[]
	 */
	public Celebration[] get_celebrations(String date)
	{
		Cursor dbCursor = myDataBase.query("celebrations", null, "date = ?", new String[]{date}, null, null, null);
		Celebration[] celebrations = new Celebration[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			celebrations[i] = new Celebration(
					dbCursor.getString(dbCursor.getColumnIndex("type")),
					dbCursor.getString(dbCursor.getColumnIndex("name")),
					dbCursor.getString(dbCursor.getColumnIndex("date")),
					dbCursor.getInt(dbCursor.getColumnIndex("_id"))
					);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return celebrations;
	}
	
	/**
	 * Returns the number of rows (0 or 1 usually) if a celebration exists in the database.
	 * @param String date
	 * @param String name
	 * @return int
	 */
	public int celebration_exists(String date, String name)
	{
		Cursor dbCursor = myDataBase.query("celebrations", null, "date = ? AND name = ?", new String[]{date,name}, null, null, null);
		int count = dbCursor.getCount();
		dbCursor.close();
		return count;
	}

	/**
	 * Adds a celebration to the database.
	 * @param Event new_cele
	 * @return long how many rows were affected.
	 */
	public long add_celebration(Celebration celebration) {
		String name,type,date;
		name="name";
		type="type";
		date="date";

		ContentValues cv = new ContentValues();
		cv.put(name, celebration.getName());
		cv.put(type, celebration.getType());
		cv.put(date, celebration.getDate());
		long resultCode = myDataBase.insert("celebrations", null, cv);
		cv.clear();
		return resultCode;
	}

	/**
	 * Updates a celebration in the database.
	 * @param Event new_cele
	 * @return long how many rows were affected.
	 */
	public long update_celebration(Celebration celebration) {
		String name,type,date,id;
		name="name";
		type="type";
		date="date";
		id="_id";

		ContentValues cv = new ContentValues();
		cv.put(name, celebration.getName());
		cv.put(type, celebration.getType());
		cv.put(date, celebration.getDate());
		long resultCode = myDataBase.update("celebrations", cv, id+ "=" + celebration.getId(), null);
		cv.clear();
		return resultCode;
	}

	/**
	 * Deletes an event from the database.
	 * @param Event new_cele
	 * @return long how many rows were affected.
	 */
	public long delete_celebration(Celebration celebration) {
		String id = "_id";
		return myDataBase.delete("celebrations", id + "=" + celebration.getId(), null);
	}

	/**
	 * Deletes all celebrations from the database.
	 * @return long how many rows were affected.
	 */
	public long truncate_celebrations() {
		return myDataBase.delete("celebrations", null, null);

	}

	/**
	 * Adds a synchronization log to the database. 
	 * @param SyncLog log
	 * @return long how many rows were affected.
	 */
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

	/**
	 * Returns synchronization log.
	 * @param int limit How many rows to return.
	 * @return SyncLog array
	 */
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
					dbCursor.getLong(dbCursor.getColumnIndex(date)),
					dbCursor.getString(dbCursor.getColumnIndex(type)),
					dbCursor.getInt(dbCursor.getColumnIndex(id))
					);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return log;
	}

	/**
	 * Adds a MessageLog to the database.
	 * @param MessageLog log
	 * @return long how many rows were affected.
	 */
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

	/**
	 * Returns the logs from all messages sent. Based on limit.
	 * @param int limit How many rows to return.
	 * @return MessageLog array.
	 */
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

	/**
	 * Adds a new contact.
	 * @param Contact contact
	 * @return long how many rows were affected.
	 */
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
	/**
	 * Updates a contact to the new contact provided.
	 * @param Contact contact
	 * @return long how many rows were affected.
	 */
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

	/**
	 * Returns a contact based on the contact_id provided.
	 * @param int contact_id
	 * @return Contact
	 */
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
	/**
	 * Requests all contacts from the database.
	 * @return array of Contacts
	 */
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

	/**
	 * Called when the object is destroyed.
	 * Closes the database.
	 * @see java.lang.Object#finalize()
	 */
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
