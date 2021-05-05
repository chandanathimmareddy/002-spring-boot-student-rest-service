package com.example.student.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceMessage {
	
	String type;
	
	String code;
	
	String description;
	

	public ServiceMessage() {
		super();
	}


	public ServiceMessage(String type, String code, String description) {
		super();
		this.type = type;
		this.code = code;
		this.description = description;
	}
	
	

}
