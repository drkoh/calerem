package com.example.thenotification;

import java.util.ArrayList;

import com.example.thenotification.c_contact;




public class mock_contact_class 
{

	public ArrayList<String> getNamesForNotification(ArrayList<c_contact> contacts)
	{
		ArrayList<String> temp = new ArrayList<String>();

		temp.add("Nikos Papoulias");
		temp.add("Lazaros Amanatidis");
		temp.add("Kostas Epic");
		temp.add("Ninja Troll");
		
		for (int i = 0; i < temp.size(); i++)
		{
			temp.add(contacts.get(i).getV_name() + " " + contacts.get(i).getV_lastname());
		}

		return temp;
	}

}
