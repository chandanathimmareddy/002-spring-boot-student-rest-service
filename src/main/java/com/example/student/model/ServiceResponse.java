package com.example.student.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServiceResponse{
	
	@JsonProperty("message")
	ServiceMessage servicemessage;
	

}
