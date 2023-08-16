package com.learnJava.streams_terminal;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class StreamsMappingExample {

    public static void main(String[] args) 
    
    {
    	
    	Set<String> namesSetPrevious = StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getName)
                .collect(toSet()); 
    	
    	 System.out.println("namesList : " + namesSetPrevious);	
    	

        Set<String> namesSet = StudentDataBase.getAllStudents()
                .stream()
                .collect(mapping(Student::getName,toSet())); // this avoids the additional map intermediate operation.

        System.out.println("namesSet : " + namesSet);

        List<String> namesList = StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.mapping(Student::getName,toList())); // this avoids the additional map intermediate operation.

        System.out.println("namesList : " + namesList);

    }

}
