package com.uniphore.dto;

import java.util.List;

public class OutputDto {

	private String status;
	private List<String> area;
	private String object_id;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getArea() {
		return area;
	}
	public void setArea(List<String> area) {
		this.area = area;
	}
	public String getObject_id() {
		return object_id;
	}
	public void setObject_id(String object_id) {
		this.object_id = object_id;
	}
	
	
}
