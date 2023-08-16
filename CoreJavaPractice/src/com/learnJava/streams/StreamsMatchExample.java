package com.learnJava.streams;

import java.util.function.Predicate;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

public class StreamsMatchExample 
{
	static Predicate<Student> studentGPAPredicate = (student) -> 
     { 
       return  student.getGpa()>=3.9;
     };

    
     
     public static boolean allMatch()
      {
        boolean result = StudentDataBase.getAllStudents().stream()
               // .allMatch(student -> student.getGpa()>=3.9);
                 .allMatch(studentGPAPredicate);

        return result;
    }

    public static boolean anyMatch(){

        boolean result = StudentDataBase.getAllStudents().stream()
               // .anyMatch(student -> student.getGpa()>=3.9);
        		 .anyMatch(studentGPAPredicate);

        return result;
    }

    public static boolean noneMatch(){

        boolean result = StudentDataBase.getAllStudents().stream()
                			.noneMatch(studentGPAPredicate);

        return result;
    }

    public static void main(String[] args) {

        System.out.println("Result of allMatch : " + allMatch());
        System.out.println("Result of anyMatch : " + anyMatch());
        System.out.println("Result of noneMatch : " + noneMatch());
    }
}
