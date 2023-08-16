package com.learnJava.streams;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;



/*
 
 Collection vs Stream :- 
 
 (1) Stream API does not have methods to add, remove or update the underlying collection
 (2) In collection, we can access elements in random order , i.e list.get(4) , in stream access is in sequential order.
 (3) Collection is eagerly constructed , streams is lazily constructed 
       Unless a terminal operation is invoked, the stream doesn t starts is processing
       
 (4) Iterating over the Stream is possible only once       
 
 
 
 
 */
public class StreamsExample {

    public static void main(String[] args) {

        Predicate<Student> gradePredicate = ((student) -> 
                                                       {
                                                    	   return student.getGradeLevel()>=3;
                                                       });
        
        Predicate<Student> gpaPredicate = student -> student.getGradeLevel()>=3.9;



        Map<String,List<String>> studentMap = StudentDataBase.getAllStudents().stream()
                . filter(gradePredicate) 
                . filter(gpaPredicate) 
                . collect(Collectors.toMap(Student::getName ,Student::getActivities ));

        System.out.println("studentMap  : " + studentMap);
        
        System.out.println("******************************");

        List<String> namesList = StudentDataBase.getAllStudents().
                stream() // Stream<Student>
                .peek((student -> {
                    System.out.println(student.getName());
                }))
                 .filter(gradePredicate) 
                 .filter(gpaPredicate) 
                 .map(Student::getName) //<Stream<List<Activites>>
                 .peek(System.out::println)
                 .distinct() // removes duplicates
                 .collect(Collectors.toList()); //collects it to a list.

        System.out.println("namesList  : " + namesList);


    }
}
