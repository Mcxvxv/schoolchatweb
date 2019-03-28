package com.wust.schoolchat.serviceImp;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wust.schoolchat.bean.User;
import com.wust.schoolchat.dao.UserDao;
import com.wust.schoolchat.service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	UserDao userdao;

	@Override
	public void insertUser(String u_name, String u_password) {
		// TODO Auto-generated method stub
		userdao.registerUser(u_name, u_password);
	}
	
	@Override
	public User queryUserByName(String u_name) {
		// TODO Auto-generated method stub
		User user=userdao.queryUserByUsername(u_name);
		return user;
	}
	
	@Override
	public List<User> queryUserFriendsByUsername(String u_name) {
		// TODO Auto-generated method stub
		List<User> list=userdao.queryUserFriendByUsername(u_name);
		return list;
	}
	
	@Override
	public void addfriend(String u_name_1, String u_name_2) {
		// TODO Auto-generated method stub
		userdao.addfriend(u_name_1, u_name_2);
	}

}
