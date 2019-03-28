package com.wust.schoolchat.bean;


public class Message {
	
	//0 代表通知所有人在线信息 
	//1代表通信信息 
	//2代表好友信息 
	//3代表返回查询信息
	private int type;
	private String from;
	private String to;
	private int resultcode;
	private Object data;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getResultcode() {
		return resultcode;
	}
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	

}
