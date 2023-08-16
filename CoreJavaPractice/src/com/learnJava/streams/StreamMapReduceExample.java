package com.learnJava.streams;

import com.learnJava.data.StudentDataBase;

import java.util.Optional;
import java.util.function.Predicate;

import com.learnJava.data.Student;
public class StreamMapReduceExample 
{

	 static Predicate<Student> studentGPA = (student) -> 
     { 
      return  student.getGpa()>=3.9;
      };
	
	private static  Optional<Integer>  noOfBooks() 
	{
	
		return StudentDataBase.getAllStudents().stream()     // Stream<Student>
				       .filter(studentGPA)  				 // Stream<Student>
		               .map(Student::getNoteBooks)           // Stream<Integer>
		              // .reduce(0,(x,y) -> x +y);
		                 .reduce(Integer::sum);
		
	}
	

	
	public static void main(String[] args) 
	{
		
		System.out.println(noOfBooks().isPresent() ? noOfBooks().get() : 0);
		
	 /*
		if (noOfBooks().isPresent()) {
		System.out.println("Book Count is " + noOfBooks().get());
		}
		*/
	}
}
