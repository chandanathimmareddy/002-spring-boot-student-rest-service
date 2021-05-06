package com.example.student.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
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

import com.example.student.Service.StudentService;
import com.example.student.constants.StudentConstants;
import com.example.student.model.Address;
import com.example.student.model.ServiceMessage;
import com.example.student.model.Student;
import com.example.student.model.StudentServiceResponse;
import com.example.student.utils.StudentUtil;

import ch.qos.logback.classic.Logger;

	@RestController
	@RequestMapping("/student")
	public class StudentDataController {
				
			  List<Student>studentslist=new ArrayList<Student>();
			    Logger logger = (Logger) LoggerFactory.getLogger(StudentDataController.class);

				StudentService studentService;
				StudentServiceResponse serviceResponse ;
				@Autowired
				public StudentDataController(StudentServiceResponse serviceResponse,StudentService studentService) {
					super();
					this.serviceResponse = serviceResponse;
					this.studentService =studentService;
				}
				
				@PostConstruct
				public void StudenInfo()
				{   
					
					studentslist.add(new Student(new Student().getId(),"Mary Kane",new Address("Chanyton cir","Austin","75660","USA")));
					studentslist.add(new Student(new Student().getId(),"Jack paul",new Address("Jakson hills","Austin","75660","USA")));
				}
			    @GetMapping("/names")
				public ResponseEntity<StudentServiceResponse>studentlist()
			  {
                    logger.info("Student List");

					ServiceMessage serviceMessage = new ServiceMessage();
					serviceMessage.setCode(StudentConstants.SUCESS_RESPONSE_CODE);
					serviceMessage.setDescription(StudentConstants.SUCESS_RESPONSE_DESCRIPTION);
					serviceMessage.setType(StudentConstants.SUCESS_RESPONSE_TYPE);
					Collections.sort(studentslist, studentService);
					serviceResponse.setServicemessage(serviceMessage);
				    serviceResponse.setStudents(studentslist);
					return new ResponseEntity<StudentServiceResponse>(serviceResponse, HttpStatus.OK);
					
				   
				
			}
			    @GetMapping("/names/{id}")
				public ResponseEntity<StudentServiceResponse> getStudentbyId(@PathVariable int id)
				{
					ServiceMessage serviceMessage = new ServiceMessage();
                    Student student=studentslist.get(id);
 					serviceMessage.setCode(StudentConstants.SUCESS_RESPONSE_CODE);
					serviceMessage.setDescription(StudentConstants.BAD_REQUEST_RESPONSE_DESCRIPTION);
					serviceMessage.setType(StudentConstants.SUCESS_RESPONSE_TYPE);
					serviceResponse.setServicemessage(serviceMessage);
					serviceResponse.setStudent(student);
					return new ResponseEntity<StudentServiceResponse>(serviceResponse,  HttpStatus.OK);
					
				}
			    
			    @PostMapping(path ="/names", consumes = "application/json", produces = "application/json")
				public ResponseEntity<StudentServiceResponse> saveStudent(@Valid@RequestBody Student students,BindingResult theBindingResult)
				{   
					Student newstudent=new Student();

			    	if(theBindingResult.hasErrors())
				{   
			    	logger.error("Error");
                    ServiceMessage serviceMessage = new ServiceMessage(StudentConstants.BAD_REQUEST_RESPONSE_CODE,StudentConstants.BAD_REQUEST_RESPONSE_DESCRIPTION,StudentConstants.BAD_REQUEST_RESPONSE_TYPE);
                    serviceResponse.setServicemessage(serviceMessage);
					return new ResponseEntity<StudentServiceResponse>(serviceResponse,HttpStatus.BAD_REQUEST);
				}else
				{    

					newstudent.setId(students.getId());
			    	newstudent.setName(students.getName());
			    	newstudent.setAddress(students.getAddress());
			    	studentslist.add(newstudent);
					ServiceMessage serviceMessage = new ServiceMessage(StudentConstants.CREATED_RESPONSE_CODE,StudentConstants.CREATED_RESPONSE_DESCRIPTION,StudentConstants.CREATED_RESPONSE_TYPE);
					serviceResponse.setServicemessage(serviceMessage);
				    serviceResponse.setStudent(students);
					return new ResponseEntity<StudentServiceResponse>(serviceResponse,HttpStatus.CREATED);
				}
		}
			    
			    @PutMapping(path ="/names/{id}", consumes = "application/json", produces = "application/json")
				public ResponseEntity<StudentServiceResponse> updateStudent(@PathVariable int id ,@Valid@RequestBody Student student, BindingResult theBindingResult)
				{   
			    	Student updatestudent=studentslist.get(id);
			    	updatestudent.setName(student.getName());
			        updatestudent.setAddress(student.getAddress());
			    	if(theBindingResult.hasErrors())
					{   
				    	logger.error("Error");

						ServiceMessage serviceMessage = new ServiceMessage(StudentConstants.BAD_REQUEST_RESPONSE_CODE,StudentConstants.BAD_REQUEST_RESPONSE_DESCRIPTION,StudentConstants.BAD_REQUEST_RESPONSE_TYPE);
						serviceResponse.setServicemessage(serviceMessage);
						return new ResponseEntity<StudentServiceResponse>(serviceResponse,HttpStatus.BAD_REQUEST);
					}else
					{
					ServiceMessage serviceMessage = new ServiceMessage();
			    	studentslist.add(updatestudent);
                     serviceMessage.setCode(StudentConstants.SUCESS_RESPONSE_CODE);
					serviceMessage.setDescription(StudentConstants.SUCESS_RESPONSE_DESCRIPTION);
					serviceMessage.setType(StudentConstants.SUCESS_RESPONSE_TYPE);
					serviceResponse.setServicemessage(serviceMessage);
				    serviceResponse.setStudent(updatestudent);
					return new ResponseEntity<StudentServiceResponse>(serviceResponse,HttpStatus.OK);
		}
				}
			    
			    @DeleteMapping(path ="/names/{id}")
				public ResponseEntity<StudentServiceResponse> deletStudent( @PathVariable int id)
				{
			    	studentslist.remove(id);
					ServiceMessage serviceMessage = new ServiceMessage();
					serviceMessage.setCode(StudentConstants.SUCESS_RESPONSE_CODE);
					serviceMessage.setDescription(StudentConstants.SUCESS_RESPONSE_DESCRIPTION);
					serviceMessage.setType(StudentConstants.SUCESS_RESPONSE_TYPE);
					serviceResponse.setServicemessage(serviceMessage);
					serviceResponse.getStudents();
			        return new ResponseEntity<StudentServiceResponse>(serviceResponse,HttpStatus.OK);
		}

	}


	

