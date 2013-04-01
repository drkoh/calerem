package com.calerem.classes;

import java.io.File;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;





public class c_configuration extends Activity
{
	
	public static String v_date_format;
	public static String v_notification_sound;
	public static String v_language;
	public static String v_skin;
	public static String v_eortologio_xml;
	
	Bundle activity = null;

	public c_configuration(String n_date_format,String n_notification_sound,String n_language,String n_skin,String n_eortologio_xml)
	{
		c_configuration.v_date_format = n_date_format;
		c_configuration.v_notification_sound = n_notification_sound;
		c_configuration.v_language = n_language;
		c_configuration.v_skin = n_skin;
		c_configuration.v_eortologio_xml = n_eortologio_xml;
	}

	

	public c_configuration() {
		/*c_configuration.v_date_format = "DD-MM-YYYY";
		c_configuration.v_notification_sound="A";
		c_configuration.v_language="A";
		c_configuration.v_skin="A";
		c_configuration.v_eortologio_xml="A";*/
		super.onCreate(activity);
	}
	
	public void startAct(View obj)
	{
		Intent b = new Intent(obj.getContext(),Activity.class);
		super.startActivity(b);
	}
	


	public static void f_set_date_format(String v_new_format)
	{
		if (v_new_format.equals("DD-MM-YYYY"))
			c_configuration.v_date_format = v_new_format;
		else if (v_new_format.equals("MM-DD-YYYY"))
			c_configuration.v_date_format = v_new_format;
	};
	
	public void f_set_notification_sound(String v_new_path)
	{
		File file = getBaseContext().getFileStreamPath(v_new_path);
		if(file.exists()){
			String fType = v_new_path.substring((v_new_path.lastIndexOf(".") + 1), v_new_path.length());
			if(fType=="mp3"){
				c_configuration.v_notification_sound = v_new_path;
			}
		}	
	};
	
	public void f_set_language(String v_new_language)
	{
		File file = getBaseContext().getFileStreamPath(v_new_language);
		if(file.exists()){
			c_configuration.v_language = v_new_language;
		}	
	};	
	
	public void f_set_skin(String v_new_path)
	{
		File file = getBaseContext().getFileStreamPath(v_new_path);
		if(file.exists()){
			c_configuration.v_skin = v_new_path;
		}
	}

	
}


