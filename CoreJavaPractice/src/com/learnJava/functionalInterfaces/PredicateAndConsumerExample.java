package com.learnJava.functionalInterfaces;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateAndConsumerExample {

    static Predicate<Student> p1 = (s) -> s.getGradeLevel()>=3;

    static Predicate<Student> p2 = (s) -> s.getGpa()>=3.9;

    
     
    BiConsumer<String, List<String>> studentBiConsumer = (name, activities) ->  {
    	                                                            			  System.out.println(name + " : " + activities);
    																			};

    																			
    // OPTION [A] : - Inovke on every student 																		
    // Predicate + Consumer 
    Consumer<Student> studentConsumer = (student) -> 
    										{
    											if(p1.and(p2).test(student))
    											{
    											 studentBiConsumer.accept(student.getName(),student.getActivities());
    											}
    										};

  // OPTION [B] :- - Inovke using directly foreach
    										
    public void printNameandActivities(List<Student> studentList)
    {
        studentList.forEach(studentConsumer);
    }

    public void printNameandActivities1(List<Student> studentList)
    {
    	System.out.println("******");
        studentList.forEach((student) -> 
		{
			if(p1.and(p2).test(student))
			{
			 studentBiConsumer.accept(student.getName(),student.getActivities());
			}
		});
    }
    
    
    
    
    public static void main(String[] args) {

        List<Student> studentList = StudentDataBase.getAllStudents();

        new PredicateAndConsumerExample().printNameandActivities(studentList);
        new PredicateAndConsumerExample().printNameandActivities1(studentList);

    }
}
