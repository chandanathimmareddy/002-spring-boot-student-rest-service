package com.example.student.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.student.model.Address;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level=AccessLevel.PUBLIC)
public final class StudentUtil {
 private StudentUtil()
 {
	 
 };
 public static String validateAddress()
 {
	 String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
	 
	 Pattern pattern = Pattern.compile(regex);
	  
     Matcher matcher = pattern.matcher(new Address().getZipcode());
	  if(matcher.matches())
	  {
        return "valid zipcode";
	   }else
		   
	   {
       return "imvalid zipcode";
	   }
	  
	   
 }
	
}
