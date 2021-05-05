package com.example.student.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.model.Address;
import com.example.student.model.ServiceMessage;
import com.example.student.model.Student;
import com.example.student.model.StudentServiceResponse;

	@RestController
	@RequestMapping("/student")
	public class StudentDataController {
				
				List<Student>studentslist=new ArrayList<Student>();
				

				StudentServiceResponse serviceresponse ;
				@Autowired
				public StudentDataController(StudentServiceResponse serviceresponse) {
					super();
					this.serviceresponse = serviceresponse;
				}
				
				@PostConstruct
				public void StudenInfo()
				{   
					
					studentslist.add(new Student(new Student().getId(),"Mary Kane",new Address("Chanyton cir","Austin",75660,"USA")));
					studentslist.add(new Student(new Student().getId(),"Jack paul",new Address("Jakson hills","Austin",75660,"USA")));
				}
			    @GetMapping("/names")
				public  StudentServiceResponse studentlist()
			  {
			 
				
					StudentServiceResponse serviceresponse=new StudentServiceResponse();
					ServiceMessage serviceMessage = new ServiceMessage();
					serviceMessage.setCode("200");
					serviceMessage.setDescription("Request completed succesfully");
					serviceMessage.setType("OK");
					serviceresponse.setServicemessage(serviceMessage);
				    serviceresponse.setStudents(studentslist);
					return serviceresponse;
					
				   
				
			}
			    @GetMapping("/names/{id}")
				public StudentServiceResponse getStudentbyId(@PathVariable int id)
				{
                   
					ServiceMessage serviceMessage = new ServiceMessage();
                    Student student=studentslist.get(id);
 					serviceMessage.setCode("200");
					serviceMessage.setDescription("Request completed succesfully");
					serviceMessage.setType("OK");
					serviceresponse.setServicemessage(serviceMessage);
					serviceresponse.setStudent(student);
					return serviceresponse;
					
				}
			    
			    @PostMapping(path ="/names", consumes = "application/json", produces = "application/json")
				public StudentServiceResponse saveStudent(@Valid@RequestBody Student students, BindingResult theBindingResult)
				{   
					Student newstudent=new Student();
			    	if(theBindingResult.hasErrors())
				{
					ServiceMessage serviceMessage = new ServiceMessage("400","Request is not successfull","BAD REQUEST");
					serviceresponse.setServicemessage(serviceMessage);
					return serviceresponse;
				}else
					newstudent.setId(students.getId());
			    	newstudent.setName(students.getName());
			    	newstudent.setAddress(students.getAddress());
			    	studentslist.add(newstudent);
			    	StudentServiceResponse serviceresponse=new StudentServiceResponse();
					ServiceMessage serviceMessage = new ServiceMessage("200","Request Completed Successfully","OK");
					serviceresponse.setServicemessage(serviceMessage);
				    serviceresponse.setStudent(students);
					return serviceresponse;
		}
			    
			    @PutMapping(path ="/names/{id}", consumes = "application/json", produces = "application/json")
				public StudentServiceResponse updateStudent(@PathVariable int id ,@Valid@RequestBody Student student, BindingResult theBindingResult)
				{   
			    	Student updatestudent=studentslist.get(id);
			    	updatestudent.setName(student.getName());
			        updatestudent.setAddress(student.getAddress());
			    	if(theBindingResult.hasErrors())
					{
						ServiceMessage serviceMessage = new ServiceMessage("400","Request is not successfull","BAD REQUEST");
						serviceresponse.setServicemessage(serviceMessage);
						return serviceresponse;
					}else
					{
			    	StudentServiceResponse serviceresponse=new StudentServiceResponse();
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
			    
			    @DeleteMapping(path ="/names/{id}")
				public StudentServiceResponse deletStudent( @PathVariable int id)
				{
			    	studentslist.remove(id);
			    	StudentServiceResponse serviceresponse=new StudentServiceResponse();
					ServiceMessage serviceMessage = new ServiceMessage();
					serviceMessage.setCode("200");
					serviceMessage.setDescription("Request completed succesfully");
					serviceMessage.setType("OK");
					serviceresponse.setServicemessage(serviceMessage);
					serviceresponse.getStudents();
			        return serviceresponse;
		}

	}


	

