package com.learnJava.streams;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.function.Predicate;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

public class StreamsFilterExample 
{

    static Predicate<Student> studentGPA = (student) -> 
         { 
           return  student.getGpa()>=3.9;
        };
        
        static Predicate<Student> studentGender = (student) -> 
        { 
          return  student.getGender().equals("male");
         };
        
        
    public static List<Student> filteredStudents()
    {
    	
        List<Student> filteredStudentList = StudentDataBase.getAllStudents()
                 .stream()
               // .filter(student -> student.getGpa()>=3.9)
                .filter(studentGender)
               // .filter(student -> student.getGender().equals("female"))
                .filter(studentGPA)
                .collect(toList());

        return filteredStudentList;
    }

    public static void main(String[] args) {

        System.out.println("Filtered Students : " + filteredStudents());

    }
}
