package com.learnJava.streams;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.List;
import java.util.Optional;

public class StreamsFindAnyFirstExample {

	
	
	// Returns the first match , doesnot iterates through all the rows
    public static Optional<Student> findAny()
    {
        return StudentDataBase.getAllStudents().stream()
                .filter(student -> student.getGpa()>=2)
                .findAny();
    }

    public static Optional<Student> findFirst(){

        return StudentDataBase.getAllStudents().stream()
                .filter(student -> student.getGpa()>=3.8)
                .findFirst();
    }

    public static void main(String[] args) {

        Optional<Student> findAnyStudent = findAny();
        Student s = findAnyStudent.isPresent() ? findAnyStudent.get(): null ;
        
        
        
        System.out.println(s);
        
        /* 
        if(findAnyStudent.isPresent()){
            System.out.println("Student is :" + findAnyStudent.get());
        }else{
            System.out.println("No Student Found");
        }
      
        
        
        Optional<Student> findFirst = findFirst();
        if(findFirst.isPresent()){
            System.out.println("Student is :" + findFirst.get());
        }else{
            System.out.println("No Student Found");
        }
        //it will make a lot of different during parallel stream
 */
    }
}
