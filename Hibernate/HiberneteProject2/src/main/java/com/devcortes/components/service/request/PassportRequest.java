package com.devcortes.components.service.request;

public class PassportRequest {
	private String code;

	public PassportRequest() {}

	public PassportRequest(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
