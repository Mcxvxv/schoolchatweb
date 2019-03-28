package com.wust.schoolchat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;

import com.wust.schoolchat.bean.Message;
import com.wust.schoolchat.bean.User;
import com.wust.schoolchat.serviceImp.UserServiceImp;
import com.wust.schoolchat.util.MyWebSocketHandler;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/User")
public class UserController {
	
	@Autowired
    MyWebSocketHandler handler;
	
	@Autowired
	UserServiceImp userservice;

	
	@RequestMapping("/register")
	public @ResponseBody Message Register(@Param("u_name")String u_name, @Param("u_password")String u_password) {
		System.out.println("u_name:"+u_name+" u_password:"+u_password);
		userservice.insertUser(u_name, u_password);
		Message message=new Message();
		message.setFrom("server");
		message.setTo(u_name);
		message.setType(3);
		message.setResultcode(0);
		Map<String,String> map=new HashMap<>();
		map.put("register", "success");
		message.setData(map);
		return message;
	}
	
	@RequestMapping("/login")
	public @ResponseBody Message login(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("u_name");
		String password = request.getParameter("u_password");
		System.out.println("u_name"+username);
		System.out.println("u_password"+password);
		User user=userservice.queryUserByName(username);
		Message message=new Message();
		message.setFrom("server");
		message.setTo(username);
		message.setType(3);
		Map<String, String> map=new HashMap<>();
		if(password.trim().equals(user.getU_password())) {
			System.out.println(username + "登录");		
			HttpSession session = request.getSession();	
			System.out.println("session_id:"+session.getId());
			session.setAttribute("SESSION_USERNAME", username);	
			System.out.println("the name in session is :"+session.getAttribute("SESSION_USERNAME"));
			map.put("login", "success");
		}else {
			map.put("login", "failed");
		}
		message.setData(map);
		return message;
	}
	
	@RequestMapping("/anotherlogin")
	public String anotherlogin(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("u_name");		
		System.out.println(username + "登录");		
		HttpSession session = request.getSession();	
		System.out.println("session_id:"+session.getId());
		session.setAttribute("SESSION_USERNAME", username);	
		System.out.println("the name in session is :"+session.getAttribute("SESSION_USERNAME"));
		Message message=new Message();
		message.setFrom("server");
		message.setTo(username);
		message.setType(3);
		Map<String, String> map=new HashMap<>();
		map.put("login", "success");
		message.setData(map);
		return "websocket";
	}
	
	@RequestMapping("/test")
	public @ResponseBody Message test(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();	
		System.out.println("session_id:"+session.getId());
		Message message=new Message();
		message.setFrom("server");
		message.setTo("client");
		Map<String, String> map=new HashMap<>();
		map.put("session_id", session.getId());
		message.setData(map);
		return message;
	}
	
	@RequestMapping("/queryfriends")
	public @ResponseBody Message queryfriends(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("u_name");	
		List<User> list = userservice.queryUserFriendsByUsername(username);
		Message message=new Message();
		message.setType(2);
		message.setFrom("server");
		message.setTo("client");
		message.setData(list);
		message.setResultcode(0);
		return message;
	}
	
	@RequestMapping("/queryUserByName")
	public @ResponseBody Message queryUserByName(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("u_name");	
		User user = userservice.queryUserByName(username);
		Message message=new Message();
		message.setType(3);
		message.setFrom("server");
		message.setTo("client");
		message.setData(user);
		message.setResultcode(0);
		return message;
	}
	
	@RequestMapping("/queryOnlineUser")
	public void queryOnlineUser() {
		handler.sendOnlineUserToAllUser();
	}
	
	@RequestMapping("/addfriend")
	public @ResponseBody Message addfriend(HttpServletRequest request,HttpServletResponse response) {
		String username_1 = request.getParameter("u_name_1");	
		String username_2 = request.getParameter("u_name_2");	
		userservice.addfriend(username_1, username_2);
		userservice.addfriend(username_2, username_1);
		Message message=new Message();
		message.setType(3);
		message.setFrom("server");
		message.setTo("client");
		message.setResultcode(0);
		Map<String,String> map=new HashMap<>();
		map.put("addfriend", "success");
		message.setData(map);
		return message;
	}
	
	
	@RequestMapping("/checkisOnline")
	public @ResponseBody Message checkisOnline(HttpServletRequest request,HttpServletResponse response) {
		String username = request.getParameter("u_name");	
		int isonline=0;
		for (WebSocketSession user : MyWebSocketHandler.getUsers()) {
			String onlineusername = (String) user.getAttributes().get("WEBSOCKET_USERNAME");
			if(onlineusername.equals(username)) {
				isonline=1;
				break;
			}
		}
		Message message=new Message();
		message.setType(3);
		message.setFrom("server");
		message.setTo("client");
		message.setResultcode(0);
		Map<String,Integer> map=new HashMap<>();
		map.put("isonline", isonline);
		message.setData(map);
		return message;
	}
	
	

}
