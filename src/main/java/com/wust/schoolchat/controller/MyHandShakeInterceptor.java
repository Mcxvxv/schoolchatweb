package com.wust.schoolchat.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;


public class MyHandShakeInterceptor implements HandshakeInterceptor {

	@Override
	public void afterHandshake(ServerHttpRequest arg0, ServerHttpResponse arg1, WebSocketHandler arg2, Exception arg3) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse arg1, WebSocketHandler arg2,
			Map<String, Object> attributes) throws Exception {
		// TODO Auto-generated method stub
		

        if (serverHttpRequest instanceof ServletServerHttpRequest) {

            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) serverHttpRequest;

            HttpSession session = servletRequest.getServletRequest().getSession(false);
//            System.out.println("session_id:"+session.getId());
            System.out.println("Websocket:用户[ID:" + (String)session.getAttribute("SESSION_USERNAME") + "]已经建立连接");
            if(session!=null) {
            	// 标记用户
                String userName = (String) session.getAttribute("SESSION_USERNAME");  
                //这边获得登录时设置的唯一用户标识                
                if (userName == null) {                      
                	userName = "未知" + session.getId();                  
                }                  
                attributes.put("WEBSOCKET_USERNAME", userName);
            }
            

        }
        return true;

	}

}
