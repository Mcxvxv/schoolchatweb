package com.wust.schoolchat.service;

import java.util.List;

import com.wust.schoolchat.bean.User;

public interface UserService {
	public void insertUser(String u_name,String u_password);
	public User queryUserByName(String u_name);
	public List<User> queryUserFriendsByUsername(String u_name);
	public void addfriend(String u_name_1,String u_name_2);
}
