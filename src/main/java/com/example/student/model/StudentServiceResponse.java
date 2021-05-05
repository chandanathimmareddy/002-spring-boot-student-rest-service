package com.example.student.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonPropertyOrder({"message","students"})
@JsonInclude(Include.NON_NULL)
@Component
public class StudentServiceResponse extends ServiceResponse{
	
	@JsonProperty("student")
	Student student;
    
	@JsonProperty("students")
	List<Student>students=new ArrayList<Student>();


}
