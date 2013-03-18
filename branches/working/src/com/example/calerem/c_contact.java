package com.example.calerem;

import android.app.Activity;
import android.os.Bundle;



public class c_contact extends Activity
{
	public String v_name;
	public String v_lastname;
	public int v_phone;
	public String v_email;
	public Integer v_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	public c_contact(String n_name,String n_lastname,int n_phone,String n_email,int n_id)
	{
		v_name = n_name;
		v_lastname = n_lastname;
		v_phone = n_phone;
		v_email = n_email;
		v_id = n_id;
	}
}
