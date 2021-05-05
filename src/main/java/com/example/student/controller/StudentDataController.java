package com.example.student.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.constants.StudentConstants;
import com.example.student.model.Address;
import com.example.student.model.ServiceMessage;
import com.example.student.model.Student;
import com.example.student.model.StudentServiceResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentDataController {

	// custom logger for this class
	//private Logger logger = org.slf4j.LoggerFactory.getLogger(StudentDataController.class);
	
	List<Student> studentslist = new ArrayList<Student>();

	StudentServiceResponse serviceResponse;

	@Autowired
	public StudentDataController(StudentServiceResponse serviceresponse) {
		super();
		this.serviceResponse = serviceresponse;
	}

	@PostConstruct
	public void StudenInfo() {

		studentslist.add(
				new Student(new Student().getId(), "Mary Kane", new Address("Chanyton cir", "Austin", 75660, "USA")));
		studentslist.add(
				new Student(new Student().getId(), "Jack paul", new Address("Jakson hills", "Austin", 75660, "USA")));
	}

	@GetMapping("/names")
	public ResponseEntity<StudentServiceResponse> studentlist() {
		log.info("Entering studentlist");
		StudentServiceResponse serviceresponse = new StudentServiceResponse();
		HttpStatus httpStatus = HttpStatus.OK;
		ServiceMessage serviceMessage = new ServiceMessage();

		serviceMessage.setCode(StudentConstants.SUCCESS_RESPONSE_CODE);
		serviceMessage.setDescription("Request completed succesfully");
		serviceMessage.setType("OK");

		serviceresponse.setServicemessage(serviceMessage);
		serviceresponse.setStudents(studentslist);

		log.info("Exiting studentlist");
		return new ResponseEntity<StudentServiceResponse>(serviceresponse, httpStatus);

	}

	@GetMapping("/names/{id}")
	public StudentServiceResponse getStudentbyId(@PathVariable String id) {

		ServiceMessage serviceMessage = new ServiceMessage();
		Student student = studentslist.get(0);
		serviceMessage.setCode(StudentConstants.SUCCESS_RESPONSE_CODE);
		serviceMessage.setDescription("Request completed succesfully");
		serviceMessage.setType("OK");
		serviceResponse.setServicemessage(serviceMessage);
		serviceResponse.setStudent(student);
		return serviceResponse;

	}

	@PostMapping(path = "/names", consumes = "application/json", produces = "application/json")
	public ResponseEntity<StudentServiceResponse> saveStudent(@Valid @RequestBody Student students,
			BindingResult theBindingResult) {
		Student student = new Student();
		
		HttpStatus httpStatus = HttpStatus.CREATED;

		if (theBindingResult.hasErrors()) {
			log.error("Invalid request");
			httpStatus = HttpStatus.BAD_REQUEST;
			ServiceMessage serviceMessage = new ServiceMessage("400", "Request is not successfull", "BAD REQUEST");
			serviceResponse.setServicemessage(serviceMessage);
			return new ResponseEntity<StudentServiceResponse>(serviceResponse, httpStatus);
		} else {
			student.setId(students.getId());
			student.setName(students.getName());
			student.setAddress(students.getAddress());
			studentslist.add(student);
			StudentServiceResponse serviceresponse = new StudentServiceResponse();
			ServiceMessage serviceMessage = new ServiceMessage("200", "Request Completed Successfully", "OK");
			serviceresponse.setServicemessage(serviceMessage);
			serviceresponse.setStudent(students);
		}
		return new ResponseEntity<StudentServiceResponse>(serviceResponse, httpStatus);
	}

	@PutMapping(path = "/names/{id}", consumes = "application/json", produces = "application/json")
	public StudentServiceResponse updateStudent(@PathVariable int id, @Valid @RequestBody Student student,
			BindingResult theBindingResult) {
		Student updatestudent = studentslist.get(id);
		updatestudent.setName(student.getName());
		updatestudent.setAddress(student.getAddress());
		
		if (theBindingResult.hasErrors()) {
			ServiceMessage serviceMessage = new ServiceMessage("400", "Request is not successfull", "BAD REQUEST");
			serviceResponse.setServicemessage(serviceMessage);
			return serviceResponse;
		} else {
			StudentServiceResponse serviceresponse = new StudentServiceResponse();
			ServiceMessage serviceMessage = new ServiceMessage();
			studentslist.add(updatestudent);
			serviceMessage.setCode("200");
			serviceMessage.setDescription("Request completed succesfully");
			serviceMessage.setType("OK");
			serviceresponse.setServicemessage(serviceMessage);
			serviceresponse.setStudent(updatestudent);
			return serviceresponse;
		}
	}

	@DeleteMapping(path = "/names/{id}")
	public StudentServiceResponse deletStudent(@PathVariable int id) {
		studentslist.remove(id);
		StudentServiceResponse serviceresponse = new StudentServiceResponse();
		ServiceMessage serviceMessage = new ServiceMessage();
		serviceMessage.setCode("200");
		serviceMessage.setDescription("Request completed succesfully");
		serviceMessage.setType("OK");
		serviceresponse.setServicemessage(serviceMessage);
		serviceresponse.getStudents();
		return serviceresponse;
	}

}
