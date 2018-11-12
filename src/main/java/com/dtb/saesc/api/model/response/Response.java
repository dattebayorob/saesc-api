package com.dtb.saesc.api.model.response;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private Object data;
	private List<String> errors;
	public Response() {
		// TODO Auto-generated constructor stub
	}
	public Response(Object data) {
		this.data = data;
	}
	public static Response data(Object data) {
		return new Response(data);
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public List<String> getErrors() {
		if(this.errors==null) {
			this.errors = new ArrayList<>();
		}
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
}
