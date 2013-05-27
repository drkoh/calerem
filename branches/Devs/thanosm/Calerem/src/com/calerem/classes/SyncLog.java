package com.calerem.classes;

/**
 * Class to hold synchronization logs.
 * @author DarkParadise
 */
public class SyncLog {

	private long date;
	private String type;
	private Integer id;
	
	/**
	 * Base Constructor
	 * @param long date
	 * @param String type
	 * @param Integer id
	 */
	public SyncLog(long date, String type,Integer id)
	{
		setDate(date);
		setType(type);
		setId(id);
	}

	/**
	 * @return the date
	 */
	public long getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(long date) {
		this.date = date;
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
