package br.com.contmatic.api.model;

import java.io.Serializable;

public class RequestLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7029580550962679689L;

	private String username;
	
	private String password;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
