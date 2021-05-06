package com.example.student.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Address {
	
	String street;
	String city;
    String country;
     @NotNull
     @Size(min=2,message="zipcode should have atleast 2 integers")
    String zipcode;
	
    public Address() {
	}
	
       public Address(String street, String city,String zipcode, String country) {
		super();
		this.street = street;
		this.city = city;
		this.zipcode=zipcode;
		this.country=country;
	}
	

}
