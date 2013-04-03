package com.calerem.classes;

import com.calerem.interfaces.i_event;


public class c_event implements i_event
{

	public String v_event_type;
	public String v_event_name;
	public Integer v_event_date;
	public c_contact v_event_contact;
	public Integer v_event_id;
	public String v_event_description;

	public c_event(String n_event_type,String n_event_name,Integer n_event_date,c_contact n_event_contact,Integer n_event_id,String n_event_description)
	{
		v_event_type = n_event_type;
		v_event_name = n_event_name;
		v_event_date = n_event_date;
		v_event_contact = n_event_contact;
		v_event_id = n_event_id;
		v_event_description = n_event_description;
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
			if (v_new_date > 0)
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
