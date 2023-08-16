package com.learnJava.streams_terminal;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamsMinByMaxByExample
{

    public static Optional<Student> minBy()
    {
        Optional<Student> studentOptional = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.minBy(Comparator.comparing(Student::getGpa)));

        return studentOptional;
    }

    public static Optional<Student> maxBy()
    {
        Optional<Student> studentOptional = StudentDataBase.getAllStudents().stream()
                .collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)));

        return studentOptional;
    }
    
    
    
    

            public static  Student  maxByMultipleStudents()
            {

                Optional<Student> studentOptional =   StudentDataBase.getAllStudents()
                								     .stream()
                                                     .collect(Collectors.maxBy(Comparator.comparing(Student::getGpa)));
                
                Student maxStudent = studentOptional.isPresent() ? studentOptional.get() : null ;
              
                return maxStudent;
            }


    public static void main(String[] args) {

        System.out.println(minBy());

        System.out.println(maxBy());

       // System.out.println(maxByMultipleStudents());
    }
}
