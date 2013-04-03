package com.calerem.interfaces;

import com.calerem.classes.c_configuration;
import com.calerem.classes.c_contact;
import com.calerem.classes.c_event;
import com.calerem.classes.c_message_log;
import com.calerem.classes.c_sync_log;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public interface i_database {

	public void openDataBase() throws SQLException;

	public void close();

	// insert values in events by using ContentValues API
	public void f_add_event(c_event v_new_event);

	// delete events by id
	public void f_delete_event(Integer v_event_id);

	// update events table with query
	public void f_update_event(c_event v_new_event);

	public c_event f_get_event(int v_event_id);

	public void f_import_events(String v_export_path);

	public String f_export_events(String v_export_path);

	// Restore Original .db File.
	public void f_factory_reset();

	//Return applications events based on a period.
	public c_event[] f_return_events(Integer v_start_time, Integer v_end_time);

	//Return applications configuration object.
	public c_configuration f_read_configuration();

	//Update the configuration table with new entries.
	public void f_update_configuration(c_configuration v_new_configuration);

	//Insert new celebration, its actually an event without contact
	//Example: 28-Oct
	public void f_add_celebration(c_event v_new_cele);

	//Update a celebration
	public void f_update_celebration(c_event v_new_cele);

	//Delete Celebration
	public void f_delete_celebration(c_event v_new_cele);

	// Delete all Celebrations.
	public void f_truncate_celebrations();

	//Insert sync date in the table.
	public void f_log_sync(c_sync_log v_log);

	//Return the sync log based on how many entries the developer asked for.
	public c_sync_log[] f_read_sync_log(int limit);

	//insert a message sent to the log, so we can keep history.
	public void f_log_messages(c_message_log v_log);

	public c_message_log[] f_read_message_log(int limit);

	//Add a contact to the database.
	public void f_add_contact(c_contact v_contact);

	// update a contact.
	public void f_update_contact(c_contact v_contact);

	//Get a contact from the database.
	public c_contact f_get_contact(int v_contact_id);

	public c_contact[] f_get_contacts();

	public void onCreate(SQLiteDatabase db);

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

}