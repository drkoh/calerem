package com.calerem.classes;

import com.calerem.interfaces.i_contact;

public class c_contact implements i_contact
{
	private String v_name;
	private String v_lastname;
	private Integer v_phone;
	private String v_email;
	private Integer v_id;
	
	public c_contact(String n_name,String n_lastname,int n_phone,String n_email,int n_id)
	{
		setV_name(n_name);
		setV_lastname(n_lastname);
		setV_phone(n_phone);
		setV_email(n_email);
		setV_id(n_id);
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#getV_name()
	 */
	public String getV_name() {
		return v_name;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#setV_name(java.lang.String)
	 */
	public void setV_name(String v_name) {
		this.v_name = v_name;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#getV_lastname()
	 */
	public String getV_lastname() {
		return v_lastname;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#setV_lastname(java.lang.String)
	 */
	public void setV_lastname(String v_lastname) {
		this.v_lastname = v_lastname;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#getV_phone()
	 */
	public Integer getV_phone() {
		return v_phone;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#setV_phone(java.lang.Integer)
	 */
	public void setV_phone(Integer v_phone) {
		this.v_phone = v_phone;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#getV_email()
	 */
	public String getV_email() {
		return v_email;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#setV_email(java.lang.String)
	 */
	public void setV_email(String v_email) {
		this.v_email = v_email;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#getV_id()
	 */
	public Integer getV_id() {
		return v_id;
	}

	/* (non-Javadoc)
	 * @see com.calerem.classes.i_contact#setV_id(java.lang.Integer)
	 */
	public void setV_id(Integer v_id) {
		this.v_id = v_id;
	}
}
