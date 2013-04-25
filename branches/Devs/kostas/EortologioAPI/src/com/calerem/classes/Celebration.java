/**
 * 
 */
package com.calerem.classes;

/**
 * Class to hold Celebrations.
 * @author DarkParadise
 */
public class Celebration {
	
	private String type;
	private String name;
	private String date;
	
	/**
	 * Base Constructor.
	 * @param String type
	 * @param String name
	 * @param String date
	 */
	public Celebration(String type, String name, String date)
	{
		this.setType(type);
		this.setName(name);
		this.setDate(date);
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
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

}
