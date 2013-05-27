package com.calerem.classes;

/**
 * Class to hold event's data.
 * @author DarkParadise
 */
public class Event
{
	private String event_type;
	private String event_name;
	private long event_date;
	private Contact event_contact;
	private Integer event_id;
	private String event_description;

	/**
	 * Base Constructor
	 * @param String event_type
	 * @param String event_name
	 * @param Integer event_date
	 * @param Contact event_contact
	 * @param Integer event_id
	 * @param String event_description
	 */
	public Event(String event_type,String event_name,long event_date,Contact event_contact,Integer event_id,String event_description)
	{
		setEvent_type(event_type);
		setEvent_name(event_name);
		setEvent_date(event_date);
		setEvent_contact(event_contact);
		setEvent_id(event_id);
		setEvent_description(event_description);
	}
	
	/**
	 * @return the event_type
	 */
	public String getEvent_type() {
		return event_type;
	}

	/**
	 * @param event_type the event_type to set
	 */
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	/**
	 * @return the event_name
	 */
	public String getEvent_name() {
		return event_name;
	}

	/**
	 * @param event_name the event_name to set
	 */
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	/**
	 * @return the event_date
	 */
	public long getEvent_date() {
		return event_date;
	}

	/**
	 * @param event_date2 the event_date to set
	 */
	public void setEvent_date(long event_date2) {
		if (event_date2 > 0)
		{
			this.event_date = event_date2;
		}
	}

	/**
	 * @return the event_contact
	 */
	public Contact getEvent_contact() {
		return event_contact;
	}

	/**
	 * @param event_contact the event_contact to set
	 */
	public void setEvent_contact(Contact event_contact) {
		this.event_contact = event_contact;
	}

	/**
	 * @return the event_id
	 */
	public Integer getEvent_id() {
		return event_id;
	}

	/**
	 * @param event_id the event_id to set
	 */
	public void setEvent_id(Integer event_id) {
		this.event_id = event_id;
	}

	/**
	 * @return the event_description
	 */
	public String getEvent_description() {
		return event_description;
	}

	/**
	 * @param event_description the event_description to set
	 */
	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}
}
