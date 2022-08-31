package com.devcortes.components.service.request;

public class PhoneFeatureRequest {
	private String ram;
	
	private String hdd;
	
	private Integer mainCamera;
	
	private Integer frontalCamera;
	
	

	public PhoneFeatureRequest() {}

	public PhoneFeatureRequest(String ram, String hdd, Integer mainCamera, Integer frontalCamera) {
		this.ram = ram;
		this.hdd = hdd;
		this.mainCamera = mainCamera;
		this.frontalCamera = frontalCamera;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getHdd() {
		return hdd;
	}

	public void setHdd(String hdd) {
		this.hdd = hdd;
	}

	public Integer getMainCamera() {
		return mainCamera;
	}

	public void setMainCamera(Integer mainCamera) {
		this.mainCamera = mainCamera;
	}

	public Integer getFrontalCamera() {
		return frontalCamera;
	}

	public void setFrontalCamera(Integer frontalCamera) {
		this.frontalCamera = frontalCamera;
	}
	
	
	
}
