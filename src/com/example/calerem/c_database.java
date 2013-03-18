package com.example.calerem;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
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
import android.os.Environment;
import android.util.Log;

public class c_database extends SQLiteOpenHelper {

	private static final String EXPORT_FILE_NAME = Environment.getExternalStorageDirectory().getPath() + "/CaleRem/export.xml";
	private static String v_db_name = "Calerem.db"; 
	public SQLiteDatabase myDataBase;
	private static Context myContext;
	public static String v_sqlite_path;
	private Exporter _exporter;

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
		ContentValues cv = new ContentValues();
		cv.put("name", v_new_event.v_event_name);
		cv.put("type", v_new_event.v_event_type);
		cv.put("date", v_new_event.v_event_date);
		if(v_new_event.v_event_contact == null)
		{
			cv.put("contact_id", (Integer) null);
		}
		else
		{
			cv.put("contact_id", v_new_event.v_event_contact.v_id);	
		}
		cv.put("description", v_new_event.v_event_description);
		myDataBase.insert("events", null, cv);
		cv.clear();
	}
	
	// delete events by id
	public void f_delete_event(Integer v_event_id) {
		myDataBase.delete("events", "_id=" + v_event_id, null);
	}
	
	// update events table with query
	public void f_update_event(c_event v_new_event) {
		ContentValues cv = new ContentValues();
		cv.put("name", v_new_event.v_event_name);
		cv.put("type", v_new_event.v_event_type);
		cv.put("date", v_new_event.v_event_date);
		if(v_new_event.v_event_contact == null)
		{
			cv.put("contact_id", (Integer) null);
		}
		else
		{
			cv.put("contact_id", v_new_event.v_event_contact.v_id);	
		}		
		cv.put("description", v_new_event.v_event_description);
		myDataBase.update("events", cv, "_id=" + v_new_event.v_event_id, null);
		cv.clear();
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Return applications events based on a period.
	public c_event[] f_return_events(Integer v_start_time, Integer v_end_time) {
		Cursor dbCursor = myDataBase.query("events", null, "date>=" + v_start_time + " AND date<=" + v_end_time, null, null, null, null);
		c_event v_events[] = new c_event[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			c_contact v_contact = (c_contact) null;
			if(!dbCursor.isNull(dbCursor.getColumnIndex("contact_id")))
			{
				v_contact = this.f_get_contact(dbCursor.getInt(dbCursor.getColumnIndex("contact_id")));
			}
			v_events[i] = new c_event(
					dbCursor.getString(dbCursor.getColumnIndex("type")),
					dbCursor.getString(dbCursor.getColumnIndex("name")),
					dbCursor.getInt(dbCursor.getColumnIndex("date")),
					v_contact,
					dbCursor.getInt(dbCursor.getColumnIndex("_id")),
					dbCursor.getString(dbCursor.getColumnIndex("description"))
					); 
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return v_events;
	}
	
	//Return applications configuration object.
	public c_configuration f_read_configuration() {
		Cursor dbCursor = myDataBase.query("configuration", null, null, null, null, null, null);
		dbCursor.moveToFirst();
		c_configuration v_configuration = new c_configuration(
				dbCursor.getString(dbCursor.getColumnIndex("date_format")),
				dbCursor.getString(dbCursor.getColumnIndex("sound_path")),
				dbCursor.getString(dbCursor.getColumnIndex("language")),
				dbCursor.getString(dbCursor.getColumnIndex("skin_path")),
				dbCursor.getString(dbCursor.getColumnIndex("eortologio_url"))
				);
		dbCursor.close();
		return v_configuration;
	}
	
	//Update the configuration table with new entries.
	public void f_update_configuration(c_configuration v_new_configuration) {
		ContentValues cv = new ContentValues();
		cv.put("date_format", v_new_configuration.v_date_format);
		cv.put("sound_path", v_new_configuration.v_notification_sound);
		cv.put("language", v_new_configuration.v_notification_sound);
		cv.put("skin_path", v_new_configuration.v_skin);
		cv.put("eortologio_url", v_new_configuration.v_eortologio_xml);
		myDataBase.update("configuration", cv, null, null);
		cv.clear();
	}
	
	//Insert new celebration, its actually an event without contact
	//Example: 28-Oct
	public void f_add_celebration(c_event v_new_cele) {
		ContentValues cv = new ContentValues();
		cv.put("name", v_new_cele.v_event_name);
		cv.put("type", v_new_cele.v_event_type);
		cv.put("date", v_new_cele.v_event_date);
		cv.put("description", v_new_cele.v_event_description);
		myDataBase.insert("celebrations", null, cv);
		cv.clear();
	}

	//Update a celebration
	public void f_update_celebration(c_event v_new_cele) {
		ContentValues cv = new ContentValues();
		cv.put("name", v_new_cele.v_event_name);
		cv.put("type", v_new_cele.v_event_type);
		cv.put("date", v_new_cele.v_event_date);
		cv.put("description", v_new_cele.v_event_description);
		myDataBase.update("celebrations", cv, "_id=" + v_new_cele.v_event_id, null);
		cv.clear();
	}

	//Delete Celebration
	public void f_delete_celebration(c_event v_new_cele) {
		myDataBase.delete("celebrations", "_id=" + v_new_cele.v_event_id, null);
	}
	
	// Delete all Celebrations.
	public void f_truncate_celebrations() {
		myDataBase.delete("celebrations", null, null);

	}
	
	//Insert sync date in the table.
	public void f_log_sync(c_sync_log v_log) {
		ContentValues cv = new ContentValues();
		cv.put("type", v_log.v_type);
		cv.put("date", v_log.v_date);
		myDataBase.insert("synchronize_log", null, cv);
		cv.clear();
	}
	
	//Return the sync log based on how many entries the developer asked for.
	public c_sync_log[] f_read_sync_log(int limit) {
		Cursor dbCursor = myDataBase.query("synchronize_log", null, null, null, null, null, "date DESC", "" + limit);
		c_sync_log v_log[] = new c_sync_log[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			v_log[i] = new c_sync_log(
				dbCursor.getInt(dbCursor.getColumnIndex("date")),
				dbCursor.getString(dbCursor.getColumnIndex("type")),
				dbCursor.getInt(dbCursor.getColumnIndex("_id"))
				);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return v_log;
	}

	//insert a message sent to the log, so we can keep history.
	public void f_log_messages(c_message_log v_log) {
		ContentValues cv = new ContentValues();
		cv.put("type", v_log.v_type);
		cv.put("date", v_log.v_date);
		cv.put("contact_id", v_log.v_contact.v_id);
		cv.put("message", v_log.v_message);
		myDataBase.insert("message_log", null, cv);
		cv.clear();
	}


	public c_message_log[] f_read_message_log(int limit) {
		Cursor dbCursor = myDataBase.query("message_log", null, null, null, null, null, "date DESC", "" + limit);
		c_message_log v_log[] = new c_message_log[dbCursor.getCount()];
		dbCursor.moveToFirst();
		for(int i=0;i<dbCursor.getCount();i++)
		{
			c_contact v_contact = (c_contact) null;
			if(!dbCursor.isNull(dbCursor.getColumnIndex("contact_id")))
			{
				v_contact = this.f_get_contact(dbCursor.getInt(dbCursor.getColumnIndex("contact_id")));
			}
			v_log[i] = new c_message_log(
				dbCursor.getInt(dbCursor.getColumnIndex("date")),
				dbCursor.getInt(dbCursor.getColumnIndex("_id")),
				dbCursor.getString(dbCursor.getColumnIndex("type")),
				dbCursor.getString(dbCursor.getColumnIndex("message")),
				v_contact
				);
			dbCursor.moveToNext();
		}
		dbCursor.close();
		return v_log;
	}

	//Add a contact to the database.
	public void f_add_contact(c_contact v_contact) {
		ContentValues cv = new ContentValues();
		cv.put("name", v_contact.v_name);
		cv.put("lastname", v_contact.v_lastname);
		cv.put("phone", v_contact.v_phone);
		cv.put("email", v_contact.v_email);
		myDataBase.insert("contacts", null, cv);
		cv.clear();
	}
	// update a contact.
	public void f_update_contact(c_contact v_contact) {
		ContentValues cv = new ContentValues();
		cv.put("name", v_contact.v_name);
		cv.put("lastname", v_contact.v_lastname);
		cv.put("phone", v_contact.v_phone);
		cv.put("email", v_contact.v_email);
		myDataBase.update("cotnacts", cv, "_id=" + v_contact.v_id, null);
		cv.clear();
	}
	//Get a contact from the database.
	public c_contact f_get_contact(int v_contact_id)
	{
		Cursor dbCursor = myDataBase.query("contacts", null, "_id=" + v_contact_id, null, null, null, null);
		dbCursor.moveToFirst();
		c_contact v_contact = new c_contact(
				dbCursor.getString(dbCursor.getColumnIndex("name")),
				dbCursor.getString(dbCursor.getColumnIndex("lastname")),
				dbCursor.getInt(dbCursor.getColumnIndex("phone")),
				dbCursor.getString(dbCursor.getColumnIndex("email")),
				dbCursor.getInt(dbCursor.getColumnIndex("_id"))
				);
		dbCursor.close();
		return v_contact;
	}
	
	// Destructor function
	protected void finalize() { 
		myDataBase.close();
	}

	// functions and classes used for import export data

	class Exporter {
		private static final String CLOSING_WITH_TICK = "'>";
		private static final String START_DB = "<export-database name='";
		private static final String END_DB = "</export-database>";
		private static final String START_TABLE = "<table name='";
		private static final String END_TABLE = "</table>";
		private static final String START_ROW = "<row>";
		private static final String END_ROW = "</row>";
		private static final String START_COL = "<col name='";
		private static final String END_COL = "</col>";

		private BufferedOutputStream _bos;

		public Exporter() throws FileNotFoundException {
			this(new BufferedOutputStream(myContext.openFileOutput(
					EXPORT_FILE_NAME, Context.MODE_PRIVATE)));
		}

		public Exporter(BufferedOutputStream bos) {
			_bos = bos;
		}

		public void close() throws IOException {
			if (_bos != null) {
				_bos.close();
			}
		}

		public void startDbExport(String dbName) throws IOException {
			String stg = START_DB + dbName + CLOSING_WITH_TICK;
			_bos.write(stg.getBytes());
		}

		public void endDbExport() throws IOException {
			_bos.write(END_DB.getBytes());
		}

		public void startTable(String tableName) throws IOException {
			String stg = START_TABLE + tableName + CLOSING_WITH_TICK;
			_bos.write(stg.getBytes());
		}

		public void endTable() throws IOException {
			_bos.write(END_TABLE.getBytes());
		}

		public void startRow() throws IOException {
			_bos.write(START_ROW.getBytes());
		}

		public void endRow() throws IOException {
			_bos.write(END_ROW.getBytes());
		}

		public void addColumn(String name, String val) throws IOException {
			String stg = START_COL + name + CLOSING_WITH_TICK + val + END_COL;
			_bos.write(stg.getBytes());
		}
	}

	public void exportData() {
		log("Exporting Data");
		String tables[] = { "events", "contacts", "messages", "configuration",
				"celebration", "synchronize_log", "message_log" };
		try {
			for (int i = 0; i <= 6; i++) {
				_exporter.startDbExport(myDataBase.getPath());
				String tableName = tables[i];
				// get the tables out of the given sqlite database
				String sql = "SELECT * FROM sqlite_master";

				Cursor cur = myDataBase.rawQuery(sql, new String[0]);
				Log.d("db", "show tables, cur size " + cur.getCount());
				cur.moveToFirst();

				while (cur.getPosition() < cur.getCount()) {
					tableName = cur.getString(cur.getColumnIndex("name"));
					log("table name " + tableName);

					// don't process these two tables since they are used
					// for metadata
					if (!tableName.equals("android_metadata")
							&& !tableName.equals("sqlite_sequence")) {
						exportTable(tableName);
					}

					cur.moveToNext();
				}
				_exporter.endDbExport();
				_exporter.close();
				i++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void exportTable(String tableName) throws IOException {
		String tables[] = { "events", "contacts", "messages", "configuration",
				"celebrations", "synchronize_log", "message_log" };
		for (int i = 0; i <= 6; i++) {

			_exporter.startTable(tableName);
			tableName = tables[i];
			// get everything from the table
			String sql = "select * from " + tableName;
			Cursor cur = myDataBase.rawQuery(sql, new String[0]);
			int numcols = cur.getColumnCount();

			log("Start exporting table " + tableName);

			// // logging
			// for( int idx = 0; idx < numcols; idx++ )
			// {
			// log( "column " + cur.getColumnName(idx) );
			// }

			cur.moveToFirst();

			// move through the table, creating rows
			// and adding each column with name and value
			// to the row
			while (cur.getPosition() < cur.getCount()) {
				_exporter.startRow();
				String name;
				String val;
				for (int idx = 0; idx < numcols; idx++) {
					name = cur.getColumnName(idx);
					val = cur.getString(idx);
					log("col '" + name + "' -- val '" + val + "'");

					_exporter.addColumn(name, val);
				}

				_exporter.endRow();
				cur.moveToNext();
			}

			cur.close();
			i++;
			_exporter.endTable();
		}

	}

	private void log(String msg) {
		Log.d("DatabaseAssistant", msg);
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
