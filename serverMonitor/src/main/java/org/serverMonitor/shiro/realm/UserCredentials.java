package org.serverMonitor.shiro.realm;

import java.io.Serializable;

public class UserCredentials implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9179048965372924453L;
	private String name;
	private String password;
    private String salt;
	
    public UserCredentials(String name, String password, String salt) {
		super();
		this.name = name;
		this.password = password;
		this.salt = salt;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

}
