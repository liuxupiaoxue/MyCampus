package com.android.liuxu.mycampus.model;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String username;
	private String password;
	private int role;
	private String nickname;
	private String phoneNumber;
	private String email;
	
	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", nickname=" + nickname + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	public User(){

	}

	public User(int id, String username, String password, int role, String nickname, String phoneNumber,
			String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	

}
