package sample.op.eastblue.dawnisland.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {

	
	private String fullName;
	
	private Date lastLogin;

	public User() {}
	
	public User(String fullName) {
		this.fullName = fullName;
		this.lastLogin = new Date();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
