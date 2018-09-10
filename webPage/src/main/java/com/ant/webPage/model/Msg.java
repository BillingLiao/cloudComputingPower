package com.ant.webPage.model;

import org.springframework.stereotype.Component;

@Component
public class Msg {
	private int code;
	private Object data;
	private String message;
	
	public void set(String message, int CodeConstant, Object data) {
		this.code = CodeConstant;
		this.message = message;
		this.data = data;
	}
}