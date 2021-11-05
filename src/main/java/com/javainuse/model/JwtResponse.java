package com.javainuse.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String username;
	private final int userId;

	public JwtResponse(String jwttoken, String username, int userId) {
		this.jwttoken = jwttoken;
		this.username = username;
		this.userId = userId;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getUsername() {
		return this.username;
	}

	public int getUserId() {
		return this.userId;
	}
}