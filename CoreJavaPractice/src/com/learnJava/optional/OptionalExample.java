package com.learnJava.optional;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.Optional;

public class OptionalExample {

    public static String getStudentName()
    {

        Student student = StudentDataBase.studentSupplier1.get();
      //  Student student = null;
        
        if(student!=null)
        {
            return  student.getName();
        }else{
            return  null;
        }
    }

    public static Optional<Student> getStudentNameOptional(){

        Optional<Student> student = Optional.ofNullable(StudentDataBase.studentSupplier1.get());
        //Optional<Student> student = Optional.ofNullable(null);

        if(student.isPresent()){
            return  student; 
        }
        return Optional.empty();
    }

    public static void main(String[] args) 
    {
    	
    	//System.out.println(getStudentName().length());

    
        if(getStudentNameOptional().isPresent())
        {
        	
            System.out.println(getStudentNameOptional().get().getName().length());
        }
        
        else
        {
            System.out.println("Name is returned as empty.");
        }


    }
}
