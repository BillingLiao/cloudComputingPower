package com.ant.webPage.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Msg implements Serializable {
	private static final long serialVersionUID = 10058L;

	private int code;
	private Object data;
	private String message;
	
	/*public void set(String message, int CodeConstant, Object data) {
		this.code = CodeConstant;
		this.message = message;
		this.data = data;
	}
*/
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}