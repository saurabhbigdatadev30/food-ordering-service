package com.learnJava.streams;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class StreamsComparatorExample 
{
	
	

    public static List<Student> sortStudentsByName()
    {

       return  StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(toList());
    }

    public static List<Student> sortStudentsByGpa()
    {

        return  StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGpa))
                .collect(toList());
    }

    public static List<Student> sortStudentsByGpaReversed(){

        return  StudentDataBase.getAllStudents().stream()
                .sorted(Comparator.comparing(Student::getGpa).reversed())
                .collect(toList());
    }
    
    
    // get Student with max GPA() -- using Stream max
    public static Optional<Student> getMaxGPAStudent()
    {

        return  StudentDataBase.getAllStudents().stream()
                .max(Comparator.comparing(Student::getGpa));
              
    }
    
    
    // get Student with max GPA() -- Using reduce()
    public static Optional<Student> getMaxGPAStudent_1()
    {

        return  StudentDataBase.getAllStudents().stream()
                .reduce((s1,s2) -> s1.getGpa() > s2.getGpa() ? s1 : s2) ;
              
    }

    public static void main(String[] args) 
    {
         System.out.println("Students sorted by NAME");
         sortStudentsByName().forEach(System.out::println);
         System.out.println("Students sorted by GPA");
         sortStudentsByGpa().forEach(System.out::println);

         System.out.println("Students sorted by GPA Higher to Lower");
         sortStudentsByGpaReversed().forEach(System.out::println);
        
         System.out.println("**** MAX GPA EMPLOYEE" +getMaxGPAStudent());
         System.out.println("**** MAX GPA EMPLOYEE by reduce functionality" +getMaxGPAStudent());

        
        
        /**
         * In JAVA 8 Using Lambda 
         */
        Comparator<Integer> comparatorLambda = (Integer  a, Integer b) -> {
        	                                                                 return a.compareTo(b);
        																	};

        System.out.println(comparatorLambda.compare(10,2));
    }
}
