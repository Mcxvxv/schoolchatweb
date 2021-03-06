package com.wust.schoolchat.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.wust.schoolchat.controller.MyHandShakeInterceptor;


@Component

@EnableWebSocket
public class MyWebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer{
	
	@Autowired
    MyWebSocketHandler handler;
	
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

 

        //添加websocket处理器，添加握手拦截器

        webSocketHandlerRegistry.addHandler(handler, "/ws").addInterceptors(new MyHandShakeInterceptor());

 

        //添加websocket处理器，添加握手拦截器

        webSocketHandlerRegistry.addHandler(handler, "/ws/sockjs").addInterceptors(new MyHandShakeInterceptor()).withSockJS();

    }
}
