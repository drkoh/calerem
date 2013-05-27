import java.util.Date;


public class FacebookObject {
	String email = null;
	String name = null;
	Date birthday = new Date();
	
	public FacebookObject(String email, String name, Date birthday) {
		super();
		this.email = email;
		this.name = name;
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
