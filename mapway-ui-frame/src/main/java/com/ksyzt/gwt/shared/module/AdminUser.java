package com.ksyzt.gwt.shared.module;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class AdminUser.
 */
public class AdminUser implements Serializable {
	
	/** The user name. */
	String userName;
	
	/** The password. */
	String password;
	
	/**
	 * Gets the user name.
	 *
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Sets the password.
	 *
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
