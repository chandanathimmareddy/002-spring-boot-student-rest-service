package com.example.student.model;


import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Student {
	
    String id=UUID.randomUUID().toString();
    @NotBlank
    @NotNull
    @Size(min=2,message="Name should have atleast 2 characters")
    String name;
	Address address;
	
public Student() {}
public Student(String id,String name,Address address)
{
	this.id=id;
	this.name=name;
	this.address=address;
}
 
	

}
