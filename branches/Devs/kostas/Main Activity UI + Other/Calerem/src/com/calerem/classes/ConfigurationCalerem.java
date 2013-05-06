package com.calerem.classes;

/**
 * Class to hold configuration data.
 * @author DarkParadise
 */
public class ConfigurationCalerem {
	private String date_format;
	private String notification_sound;
	private String language;
	private String skin;
	private String eortologioURLGR;
	private String eortologioURLEN;

	/**
	 * Base Constructor
	 * @param Context context
	 * @param String date_format
	 * @param String notification_sound
	 * @param String language
	 * @param String skin
	 * @param String eortologio_xml
	 */
	public ConfigurationCalerem(String date_format,String notification_sound,String language,String skin,String eortologioURLGR,String eortologioURLEN)
	{
		setDate_format(date_format);
		setNotification_sound(notification_sound);
		setLanguage(language);
		setSkin(skin);
		setEortologioURLGR(eortologioURLGR);
		setEortologioURLEN(eortologioURLEN);
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
		this.date_format = date_format;
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
		this.notification_sound = notification_sound;
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
		this.language = language;		
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
		this.skin = skin;
	}

	/**
	 * @return the eortologioURLGR
	 */
	public String getEortologioURLGR() {
		return eortologioURLGR;
	}

	/**
	 * @param eortologioURLGR the eortologioURLGR to set
	 */
	public void setEortologioURLGR(String eortologioURLGR) {
		this.eortologioURLGR = eortologioURLGR;
	}

	/**
	 * @return the eortologioURLEN
	 */
	public String getEortologioURLEN() {
		return eortologioURLEN;
	}

	/**
	 * @param eortologioURLEN the eortologioURLEN to set
	 */
	public void setEortologioURLEN(String eortologioURLEN) {
		this.eortologioURLEN = eortologioURLEN;
	}

}


