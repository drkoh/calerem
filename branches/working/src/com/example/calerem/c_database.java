package com.example.calerem;

import java.io.BufferedOutputStream;
import java.io.File;
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
import android.util.Log;

public class c_database extends SQLiteOpenHelper {

	private static final String EXPORT_FILE_NAME = "/sdcard/export.xml";
	public SQLiteDatabase myDataBase;
	private static Context myContext;
	public static String v_sqlite_path = "/Calerem/src/com/example/calerem/databases/";
	private static String v_db_name = "Calerem.db";
	private Exporter _exporter;
	public String path = v_sqlite_path + v_db_name;

	public c_database(Context context) {
		// constructor
		super(myContext, "calerem", null, 1);
		this.myContext = context;
		// function that "opens" the database and enables us to read and write
		// from and on it
		myDataBase = SQLiteDatabase.openDatabase(path, null,
				SQLiteDatabase.OPEN_READWRITE);
		// TODO Auto-generated constructor stub
		myContext = myContext;
		myDataBase = myDataBase;
		try {
			// create a file on the sdcard to export the
			// database contents to
			File myFile = new File(EXPORT_FILE_NAME);
			myFile.createNewFile();

			FileOutputStream fOut = new FileOutputStream(myFile);
			BufferedOutputStream bos = new BufferedOutputStream(fOut);

			_exporter = new Exporter(bos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ANASTASIAS COPY-PASTE BEGIN

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if (dbExist) {
			// do nothing - database already exist
		} else {

			// By calling this method and empty database will be created into
			// the default system path
			// of your application so we are gonna be able to overwrite that
			// database with our database.
			this.getReadableDatabase();

			try {

				copyDataBase();

			} catch (IOException e) {

				throw new Error("Error copying database");

			}
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
			String myPath = v_sqlite_path + v_db_name;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {

			// database does't exist yet.

		}

		if (checkDB != null) {

			checkDB.close();

		}

		return checkDB != null ? true : false;
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
		String outFileName = v_sqlite_path + v_db_name;

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
		String myPath = v_sqlite_path + v_db_name;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);

	}

	@Override
	public synchronized void close() {

		if (myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// ANASTASIAS COPY-PASTE END

	public void f_add_event(c_event v_new_event) {
		// insert values in events by using ContentValues var
		ContentValues cv = new ContentValues();
		cv.put("name", v_new_event.v_event_name);
		cv.put("type", v_new_event.v_event_type);
		cv.put("date", v_new_event.v_event_date);
		cv.put("description", v_new_event.v_event_description);
		cv.put("contact_id", v_new_event.v_event_contact.v_id);
		myDataBase.insert("events", null, cv);
	}

	public void f_delete_event(Integer v_event_id) {
		// delete events by id
		myDataBase.execSQL("DELETE FROM events WHERE _id=" + v_event_id + ";");
	}

	public void f_update_event(c_event v_new_event) {
		// update events table with query
		myDataBase.execSQL("UPDATE events SET name=" + v_new_event.v_event_name
				+ ", type=" + v_new_event.v_event_type + ", date="
				+ v_new_event.v_event_date + ", description="
				+ v_new_event.v_event_description + ", contact_id="
				+ v_new_event.v_event_contact.v_id + " where _id="
				+ v_new_event.v_event_id + ";");
	}

	public void f_import_events(String v_export_path) {

	}

	public String f_export_events(String v_export_path) {
		return "";
	}

	public void f_factory_reset() {
		// delete all the entries from all the tables by using queries
		myDataBase.execSQL("DELETE * FROM events");
		myDataBase.execSQL("DELETE * FROM configuration");
		myDataBase.execSQL("DELETE * FROM contacts");
		myDataBase.execSQL("DELETE * FROM ");
		myDataBase.execSQL("DELETE * FROM events");
		myDataBase.execSQL("DELETE * FROM events");
		myDataBase.execSQL("DELETE * FROM events");

	}

	public c_event f_return_events(Integer v_start_time, Integer v_end_time) {
		// create a cursor that gets the values printed by select query and
		// import them in the c_event's object vars
		Cursor dbCursor = myDataBase.rawQuery(
				"SELECT name, type, date, contact_id, _id, description FROM events WHERE date>"
						+ v_start_time + " AND date<" + v_end_time + ";", null);
		c_event event1 = new c_event();

		while (!dbCursor.moveToNext()) {
			event1.v_event_name = dbCursor.getString(0);
			event1.v_event_type = dbCursor.getString(1);
			event1.v_event_date = dbCursor.getInt(2);
			event1.v_event_contact.v_id = dbCursor.getInt(3);
			event1.v_event_id = dbCursor.getInt(4);
			event1.v_event_description = dbCursor.getString(5);
		}
		return event1;
	}

	public c_configuration f_read_configuration() {
		// create a cursor (table) that gets the values printed by the select
		// query and enter, in every variable of the configuration's class
		// object, these values
		c_configuration config1 = new c_configuration();
		Cursor dbCursor = myDataBase
				.rawQuery(
						"SELECT date_format, sound_path, language, skin_path, eortologio_url FROM Configuration ;",
						null);
		while (!dbCursor.moveToNext()) {
			config1.v_date_format = dbCursor.getString(0);
			config1.v_notification_sound = dbCursor.getString(1);
			config1.v_language = dbCursor.getString(2);
			config1.v_skin = dbCursor.getString(3);
			config1.v_eortologio_xml = dbCursor.getString(4);
		}
		return config1;
	}

	public void f_update_configuration(c_configuration v_new_configuration) {
		// update table configuration with given by object v_new_configuration
		// entries
		myDataBase.execSQL("UPDATE configuration SET date_format="
				+ v_new_configuration.v_date_format + ", sound_path="
				+ v_new_configuration.v_notification_sound + ", language="
				+ v_new_configuration.v_language + ", skin_path="
				+ v_new_configuration.v_skin + ", eortologio_url="
				+ v_new_configuration.v_eortologio_xml + " ;");
	}

	public void f_add_celebration(c_event v_new_cele) {
		// insert values in events by using ContentValues var
		ContentValues cv = new ContentValues();
		cv.put("name", v_new_cele.v_event_name);
		cv.put("type", v_new_cele.v_event_type);
		cv.put("date", v_new_cele.v_event_date);
		cv.put("description", v_new_cele.v_event_description);
		cv.put("contact_id", "");
		myDataBase.insert("events", null, cv);
	}

	public void f_update_celebration(c_event v_new_cele) {
		myDataBase.execSQL("UPDATE events SET name=" + v_new_cele.v_event_name
				+ ", type=" + v_new_cele.v_event_type + ", date="
				+ v_new_cele.v_event_date + ", description="
				+ v_new_cele.v_event_description + ", contact_id=" + ""
				+ " where _id=" + v_new_cele.v_event_id + ";");
	}

	public void f_delete_celebration(c_event v_new_cele) {
		myDataBase.execSQL("DELETE FROM events WHERE _id="
				+ v_new_cele.v_event_id + ";");
	}

	public void f_truncate_celebrations() {
		// delete the special events from events table
		myDataBase.execSQL("DELETE * FROM celebrations ;");

	}

	public void f_log_synch(String v_type, Integer v_date) {
		myDataBase.execSQL("UPDATE synchronize_log SET type=" + v_type
				+ ", date=" + v_date + ";");
	}

	public void f_read_synch_log() {
		myDataBase
				.execSQL("SELECT * FROM synchronize_log WHERE _id=(SELECT MAX(_id) from synchronize_log) ;");
	}

	public void f_log_messages(Integer v_date, String v_type,
			c_contact v_contact, String v_message) {
		myDataBase.execSQL("UPDATE message_log SET type=" + v_type + ", date="
				+ v_date + ", contact_id=" + v_contact.v_id + ", message="
				+ v_message + ";");
	}

	public void f_read_message_log() {
		myDataBase.execSQL("SELECT * FROM message_log ;");
	}

	public void f_add_contact(c_contact v_contact) {
		// insert values in contacts by ContentValues var
		ContentValues cv = new ContentValues();
		cv.put("name", v_contact.v_name);
		cv.put("lastname", v_contact.v_lastname);
		cv.put("phone", v_contact.v_phone);
		cv.put("email", v_contact.v_email);

		myDataBase.insert("contacts", null, cv);
	}

	public void f_update_contact(c_contact v_contact) {
		// update contacts by using the proper query
		myDataBase.execSQL("UPDATE contacts SET name=" + v_contact.v_name
				+ ", lastname=" + v_contact.v_lastname + ", phone="
				+ v_contact.v_phone + ", email=" + v_contact.v_email
				+ "WHERE _id=" + v_contact.v_id + " ;");
	}

	protected void finalize() { // Destructor function
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
					EXPORT_FILE_NAME, Context.MODE_WORLD_READABLE)));
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
				"celebration", "synchronize_log", "message_log" };
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

}
