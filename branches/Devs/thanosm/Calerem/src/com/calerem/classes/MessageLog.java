package com.calerem.classes;

/**
 * Class to hold message logs.
 * @author DarkParadise
 */
public class MessageLog {
	private long date;
	private Integer id;
	private String type;
	private String message;
	private Contact contact;
	
	/**
	 * Base Constructor
	 * @param Integer date
	 * @param Integer id
	 * @param String type
	 * @param String message
	 * @param Contact contact
	 */
	public MessageLog(long l,Integer id,String type,String message,Contact contact)
	{
		setDate(l);
		setId(id);
		setType(type);
		setMessage(message);
		setContact(contact);
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param l the date to set
	 */
	public void setDate(long l) {
		this.date = l;
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
