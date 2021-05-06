package com.example.student.constants;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level=AccessLevel.PUBLIC)

public final class StudentConstants {
	
	public static String SUCESS_RESPONSE_CODE = "200";
	public static String BAD_REQUEST_RESPONSE_CODE = "400";
	public static String SUCESS_RESPONSE_TYPE = "OK";
	public static String BAD_REQUEST_RESPONSE_TYPE = "BAD REQUEST";
	public static String SUCESS_RESPONSE_DESCRIPTION = "Request Completed Successfully";
	public static String BAD_REQUEST_RESPONSE_DESCRIPTION = "Request is unsuccessfull";
	public static String CREATED_RESPONSE_TYPE = "CREATED";
	public static String CREATED_RESPONSE_CODE = "201";
	public static String CREATED_RESPONSE_DESCRIPTION = "Created Successfully";



}
