package com.learnJava.streams;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

public class StreamsSortingFindingMax 
{
	
	public static void main(String[] args) 
	{
		 System.out.println("Higest Grade" +getHighestGradeStudent().get());
		 System.out.println("******  SORTED BY GPA in Asc order****** ");
		 sortStudentsByGpa().forEach(System.out::println);
	}
	
	
	// Sorting Student by GPA
	 public static List<Student> sortStudentsByGpa()
	 {
	        return  StudentDataBase.getAllStudents().stream()
	                .sorted(Comparator.comparing(Student::getGpa))
	                .collect(toList());
	    }

	
	
	    // From the List of Students obtain the Student with highest grade
	    public static Optional<Student> getHighestGradeStudent()
	    {
	        Optional<Student> studentOptional =  StudentDataBase.getAllStudents().stream()
	                							.reduce((s1,s2)-> (s1.getGpa() > s2.getGpa()) ? s1 : s2);
	                												
	        return studentOptional;
	    }

	}


