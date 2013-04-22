package com.calerem.classes;

/**
 * Class to hold synchronization logs.
 * 
 * @author DarkParadise
 */
public class SyncLog {

	private Integer date;
	private String type;
	private Integer id;

	/**
	 * Base Constructor
	 * 
	 * @param Integer
	 *            date
	 * @param String
	 *            type
	 * @param Integer
	 *            id
	 */
	public SyncLog(Integer date, String type, Integer id) {
		setDate(date);
		setType(type);
		setId(id);
	}

	/**
	 * @return the date
	 */
	public Integer getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Integer date) {
		this.date = date;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
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
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
