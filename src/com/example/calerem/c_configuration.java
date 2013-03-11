package com.example.calerem;

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
	
};

public void f_set_language(String v_new_language)
{
	
};

public void f_set_skin(String v_new_path)
{
	
};


}


