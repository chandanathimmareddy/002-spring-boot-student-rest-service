package com.example.student.Service;

import java.util.Comparator;

import org.springframework.stereotype.Service;

import com.example.student.model.Student;

@Service
public class StudentService extends Student implements Comparator<Student> {
	
	public int compare(Student s1,Student s2)
	{
		return (int) (s1.getName().compareTo(s2.getName()));
	}

}
