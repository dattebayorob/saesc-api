package com.dtb.saesc.api.model.response.impl;

import com.dtb.saesc.api.model.response.Response;

public class ResponseData implements Response{
	private Object data;
	
	public ResponseData(Object data) {
		this.data = data;
	}
	
	public static ResponseData data(Object data) {
		return new ResponseData(data);
	}

	public Object getData() {
		return data;
	}
	
	
}
