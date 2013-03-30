package com.calerem.classes;

public class c_message_log {

	public Integer v_date;
	public Integer v_id;
	public String v_type;
	public String v_message;
	public c_contact v_contact;
	
	public c_message_log(Integer n_date,Integer n_id,String n_type,String n_message,c_contact n_contact)
	{
		v_date = n_date;
		v_id = n_id;
		v_type = n_type;
		v_message = n_message;
		v_contact = n_contact;
	}
}
