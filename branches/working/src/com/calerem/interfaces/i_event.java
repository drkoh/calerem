package com.calerem.interfaces;

import com.calerem.classes.c_contact;

public interface i_event {

	public String v_event_type=null;
	public String v_event_name=null;
	public Integer v_event_date=null;
	public c_contact v_event_contact=null;
	public Integer v_event_id=null;
	public String v_event_description=null;

	public void f_change_event_type(String v_new_type);

	public void f_change_event_name(String v_new_name);

	public void f_change_event_date(Integer v_new_date);
	
	public void f_change_event_contact(c_contact v_new_contact);

	public void f_change_event_description(String v_new_description);

}
