package com.wust.schoolchat.dao;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.wust.schoolchat.bean.User;

@Repository
public interface UserDao {
	
	@Insert("insert into s_user values(#{u_name},#{u_password},null)")
	public void registerUser(@Param("u_name")String u_name,@Param("u_password")String u_password);
	
	@Select("select * from s_user where u_name=#{u_name}")
	public User queryUserByUsername(String u_name);
	
	@Select("select * from s_user where u_name in (select f_name from s_friends where u_name=#{u_name})")
	public List<User> queryUserFriendByUsername(String u_name);
	
	@Insert("insert into s_friends values(#{u_name_1},#{u_name_2})")
	public void addfriend(@Param("u_name_1")String u_name_1,@Param("u_name_2")String u_name_2);

}
