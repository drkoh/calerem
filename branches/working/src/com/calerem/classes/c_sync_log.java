package com.calerem.classes;

import com.calerem.interfaces.i_sync_log;

public class c_sync_log implements i_sync_log {

	public Integer v_date;
	public String v_type;
	public Integer v_id;
	
	public c_sync_log(Integer n_date, String n_type,Integer n_id)
	{
		v_date = n_date;
		v_type = n_type;
		v_id = n_id;
	}
	
}
