package com.example.calerem;

import android.app.Activity;
import android.os.Bundle;



public class c_event extends Activity
{

	public String v_event_type;
	public String v_event_name;
	public int v_event_date;
	public c_contact v_event_contact;
	public int v_event_id;
	public String v_event_description;
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}



	public void f_change_event_type(String v_new_type) 
		{
			v_event_type = v_new_type;
		}

	public void f_change_event_name(String v_new_name) 
		{
			v_event_name = v_new_name;
		}

	public void f_change_event_date(Integer v_new_date) 
	  	{
			if (v_event_date > 0)
			{
				v_event_date = v_new_date; 
			}
	  	}
	
	public void f_change_event_contact(c_contact v_new_contact) 
	  	{
			v_event_contact = v_new_contact;
	  	}

	public void f_change_event_description(String v_new_description) 
		{
			v_event_description = v_new_description;
	  	}

	
}
