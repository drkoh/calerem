package com.calerem.classes;

import android.test.RenamingDelegatingContext;

/**
 * Class to hold contact's data.
 * @author DarkParadise
 */
public class Contact
{
	private String name;
	

	private String lastname;
	private String phone;
	private String email;
	private Integer id;
	
	
	/**
	 * Base Constructor
	 * @param String name
	 * @param String lastname
	 * @param String phone
	 * @param String email
	 * @param Integer id
	 */
	public Contact(String name,String lastname,String phone,String email,Integer id)
	{
		setName(name);
		setLastname(lastname);
		setPhone(phone);
		setEmail(email);
		setId(id);
	}

	public Contact(RenamingDelegatingContext context) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
}
