package com.wust.schoolchat.bean;

import org.springframework.stereotype.Component;

public class User {
	private String u_name;
	private String u_password;
	private String u_avatar;
	
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getU_password() {
		return u_password;
	}
	public void setU_password(String u_password) {
		this.u_password = u_password;
	}
	public String getU_avatar() {
		return u_avatar;
	}
	public void setU_avatar(String u_avatar) {
		this.u_avatar = u_avatar;
	}
	


}
