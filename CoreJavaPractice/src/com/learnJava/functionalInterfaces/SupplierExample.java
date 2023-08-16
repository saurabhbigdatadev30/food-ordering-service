package com.learnJava.functionalInterfaces;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SupplierExample 
{

	//Supplier -> no input argument , but returns a value i.e Student object
	   
	
    public static  Supplier<Student> studentSupplier = () -> 
    {
      return  new Student("Adam",2,4.0,"male", Arrays.asList("swimming", "basketball","volleyball"));
    };

    
  //Supplier -> no input argument , but returns a value i.e List<Student> object 
    public static  Supplier<List<Student>> studentsSupplierLst = () -> 
                                                                   {
                                                                	   return StudentDataBase.getAllStudents();
                                                                   };

    public static void main(String[] args) 
    {

        List<Student> student = studentsSupplierLst.get();

        // Removing duplicate from List
        List<Student> studentUnique =  student.stream()
               .distinct()
               .collect(Collectors.toList());
   
    System.out.println(studentUnique);
    }
    
    
}
