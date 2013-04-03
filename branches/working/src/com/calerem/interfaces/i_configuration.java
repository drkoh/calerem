package com.calerem.interfaces;

public interface i_configuration {

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_date_format(java.lang.String)
	 */
	public void f_set_date_format(String v_new_format);

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_notification_sound(java.lang.String)
	 */
	public void f_set_notification_sound(String v_new_path);

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_language(java.lang.String)
	 */
	public void f_set_language(String v_new_language);
 
	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#f_set_skin(java.lang.String)
	 */
	public void f_set_skin(String v_new_path);

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#getV_date_format()
	 */
	public String getV_date_format();

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_interface#setV_date_format(java.lang.String)
	 */
	public void setV_date_format(String v_date_format);

	/**
	 * @return the v_notification_sound
	 */
	public String getV_notification_sound();

	/**
	 * @param v_notification_sound the v_notification_sound to set
	 */
	public void setV_notification_sound(String v_notification_sound);

	/**
	 * @return the v_language
	 */
	public String getV_language();

	/**
	 * @param v_language the v_language to set
	 */
	public void setV_language(String v_language);

	/**
	 * @return the v_skin
	 */
	public String getV_skin();

	/**
	 * @param v_skin the v_skin to set
	 */
	public void setV_skin(String v_skin);

	/**
	 * @return the v_eortologio_xml
	 */
	public String getV_eortologio_xml();

	/**
	 * @param v_eortologio_xml the v_eortologio_xml to set
	 */
	public void setV_eortologio_xml(String v_eortologio_xml);

}