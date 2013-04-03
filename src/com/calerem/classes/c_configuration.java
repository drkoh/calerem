package com.calerem.classes;

import java.io.File;

import com.calerem.interfaces.i_configuration;

import android.app.Activity;

public class c_configuration extends Activity implements i_configuration{
	private String v_date_format;
	private String v_notification_sound;
	private String v_language;
	private String v_skin;
	private String v_eortologio_xml;

	public c_configuration(String n_date_format,String n_notification_sound,String n_language,String n_skin,String n_eortologio_xml)
	{
		setV_date_format(n_date_format);
		setV_notification_sound(n_notification_sound);
		setV_language(n_language);
		setV_skin(n_skin);
		setV_eortologio_xml(n_eortologio_xml);
	}
	
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_date_format(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#f_set_date_format(java.lang.String)
	 */
	public void f_set_date_format(String v_new_format)
	{
		if (v_new_format.equals("DD-MM-YYYY"))
			setV_date_format(v_new_format);
		else if (v_new_format.equals("MM-DD-YYYY"))
			setV_date_format(v_new_format);
	};
	
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_notification_sound(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#f_set_notification_sound(java.lang.String)
	 */
	public void f_set_notification_sound(String v_new_path)
	{
		File file = getBaseContext().getFileStreamPath(v_new_path);
		if(file.exists()){
			String fType = v_new_path.substring((v_new_path.lastIndexOf(".") + 1), v_new_path.length());
			if(fType=="mp3"){
				setV_notification_sound(v_new_path);
			}
		}	
	};
	
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_language(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#f_set_language(java.lang.String)
	 */
	public void f_set_language(String v_new_language)
	{
		File file = getBaseContext().getFileStreamPath(v_new_language);
		if(file.exists()){
			setV_language(v_new_language);
		}	
	};	
	
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_skin(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#f_set_skin(java.lang.String)
	 */
	public void f_set_skin(String v_new_path)
	{
		File file = getBaseContext().getFileStreamPath(v_new_path);
		if(file.exists()){
			setV_skin(v_new_path);
		}
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#getV_date_format()
	 */
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#getV_date_format()
	 */
	public String getV_date_format() {
		return v_date_format;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#setV_date_format(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#setV_date_format(java.lang.String)
	 */
	public void setV_date_format(String v_date_format) {
		this.v_date_format = v_date_format;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#getV_notification_sound()
	 */
	public String getV_notification_sound() {
		return v_notification_sound;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#setV_notification_sound(java.lang.String)
	 */
	public void setV_notification_sound(String v_notification_sound) {
		this.v_notification_sound = v_notification_sound;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#getV_language()
	 */
	public String getV_language() {
		return v_language;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#setV_language(java.lang.String)
	 */
	public void setV_language(String v_language) {
		this.v_language = v_language;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#getV_skin()
	 */
	public String getV_skin() {
		return v_skin;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#setV_skin(java.lang.String)
	 */
	public void setV_skin(String v_skin) {
		this.v_skin = v_skin;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#getV_eortologio_xml()
	 */
	public String getV_eortologio_xml() {
		return v_eortologio_xml;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_configuration#setV_eortologio_xml(java.lang.String)
	 */
	public void setV_eortologio_xml(String v_eortologio_xml) {
		this.v_eortologio_xml = v_eortologio_xml;
	};
}


