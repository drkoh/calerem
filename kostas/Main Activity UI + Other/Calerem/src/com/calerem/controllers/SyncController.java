package com.calerem.controllers;

import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import android.content.Context;
import android.os.StrictMode;

import com.calerem.api.EortologioAPI;
import com.calerem.classes.Celebration;
import com.calerem.classes.SyncLog;

/**
 * Syncs contacts with various APIs.
 * @author DarkParadise
 */
public class SyncController {

	private String grURL = "http://www.eortologio.gr/rss/si_av_me_el.xml";
	private String enURL = "http://www.eortologio.gr/rss/si_av_me_en.xml";
	private Celebration celebrations[];
	private Context basecontext;
	private Database db;

	/**
	 * Base Constructor.
	 * @param Context context
	 */
	public SyncController(Context context)
	{
		basecontext = context;
		try {
			db = new Database(basecontext);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Syncs with EortologioAPI.
	 */
	public void SyncEortologio()
	{
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		EortologioAPI eortologio = new EortologioAPI(basecontext);
		try {
			eortologio.getFile(grURL, enURL);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			celebrations = eortologio.readXML();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		this.EortologioAddToDatabase();
	}
	
	/**
	 * Called by SyncEortologio.
	 * Adds celebrations into the database.
	 */
	private void EortologioAddToDatabase()
	{
		db.log_sync(new SyncLog(new Date().getTime()/1000, "Eortologio", null));
		for(int i=0; i<celebrations.length;i++)
		{
			if(db.celebration_exists(celebrations[i].getDate(), celebrations[i].getName())<1)
			{
				db.add_celebration(celebrations[i]);
			}
		}
	}
}
