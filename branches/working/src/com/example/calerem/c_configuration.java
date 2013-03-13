package com.example.calerem;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;

public class c_configuration extends Activity{
@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
String v_date_format;
String v_notification_sound;
String v_language;
String v_skin;
String v_eortologio_xml;

public void f_set_date_format(String v_new_format)
{
	if (v_new_format.equals("DD-MM-YYYY"))
		v_date_format = v_new_format;
	else if (v_new_format.equals("MM-DD-YYYY"))
		v_date_format = v_new_format;
};

public void f_set_notification_sound(String v_new_path)
{
	File file = getBaseContext().getFileStreamPath(v_new_path);
	if(file.exists()){
		String fType = v_new_path.substring((v_new_path.lastIndexOf(".") + 1), v_new_path.length());
		if(fType=="mp3"){
			v_notification_sound = v_new_path;
		}
	}	
};

public void f_set_language(String v_new_language)
{
	
};

public void f_set_skin(String v_new_path)
{
	
};


}


