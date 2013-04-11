package com.calerem.classes;

import java.io.File;

import android.app.Activity;
import android.content.Context;

/**
 * Class to hold configuration data.
 * @author DarkParadise
 */
public class ConfigurationCalerem extends Activity{
	private String date_format;
	private String notification_sound;
	private String language;
	private String skin;
	private String eortologio_xml;
	private Context basecontext;

	/**
	 * Base Constructor
	 * @param context
	 * @param date_format
	 * @param notification_sound
	 * @param language
	 * @param skin
	 * @param eortologio_xml
	 */
	public ConfigurationCalerem(Context context,String date_format,String notification_sound,String language,String skin,String eortologio_xml)
	{
		setBasecontext(context);
		setDate_format(date_format);
		setNotification_sound(notification_sound);
		setLanguage(language);
		setSkin(skin);
		setEortologio_xml(eortologio_xml);
	}
	
	/**
	 * @return the date_format
	 */
	public String getDate_format() {
		return date_format;
	}

	/**
	 * @param date_format the date_format to set
	 */
	public void setDate_format(String date_format) {
		if (date_format.equals("DD-MM-YYYY") || date_format.equals("MM-DD-YYYY"))
		{
			this.date_format = date_format;
		}
	}

	/**
	 * @return the notification_sound
	 */
	public String getNotification_sound() {
		return notification_sound;
	}

	/**
	 * @param notification_sound the notification_sound to set
	 */
	public void setNotification_sound(String notification_sound) {
		File file = this.getBasecontext().getFileStreamPath(notification_sound);
		if(file.exists())
		{
			String fType = notification_sound.substring((notification_sound.lastIndexOf(".") + 1), notification_sound.length());
			if(fType == "mp3" || fType == "ogg")
			{
				this.notification_sound = notification_sound;
			}
		}	
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		File file = this.getBasecontext().getFileStreamPath(language);
		if(file.exists())
		{
			this.language = language;
		}
	}

	/**
	 * @return the skin
	 */
	public String getSkin() {
		return skin;
	}

	/**
	 * @param skin the skin to set
	 */
	public void setSkin(String skin) {
		File file = this.getBasecontext().getFileStreamPath(skin);
		if(file.exists())
		{
			this.skin = skin;
		}
	}

	/**
	 * @return the eortologio_xml
	 */
	public String getEortologio_xml() {
		return eortologio_xml;
	}

	/**
	 * @param eortologio_xml the eortologio_xml to set
	 */
	public void setEortologio_xml(String eortologio_xml) {
		this.eortologio_xml = eortologio_xml;
	}

	/**
	 * @return the basecontext
	 */
	private Context getBasecontext() {
		return basecontext;
	}

	/**
	 * @param basecontext the basecontext to set
	 */
	private void setBasecontext(Context basecontext) {
		this.basecontext = basecontext;
	};
}


