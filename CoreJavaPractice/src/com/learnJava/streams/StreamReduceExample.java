package com.learnJava.streams;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.toList;

public class StreamReduceExample 
{

/*
 
 	Understanding reduce() function:-> Terminal operation. Used to reduce the contents of the stream into a single value
 	reduce(a,b) 
 	a = accumulator value from the operation 
 	b = next value from the stream

 */
	
    public static int performMultiplication(List<Integer> integerList)
    {
        return integerList.stream()
        				  .reduce(6, (a,b) -> a*b);

    }

    public static Optional<Integer> performMultiplicationWithNoInitialValue(List<Integer> integerList)
    {
        return integerList.stream()
                		  .reduce( (a,b) -> a*b); // performs multiplication for each element in the stream and returns a new result fo.
    }

    // Combining map & reduce() operation
    public static String combineStudentNames()
    {
        return StudentDataBase.getAllStudents().stream()
                .map(Student::getName)
                .distinct()
                .reduce("",(a,b) -> a.concat(b));  
    }

   
    // From the List of Students obtain the Student with highest grade
    public static Optional<Student> getHighestGradeStudent()
    {
        Optional<Student> studentOptional =  StudentDataBase.getAllStudents().stream()
                							.reduce((s1,s2)-> (s1.getGpa() > s2.getGpa()) ? s1 : s2);
                												
        return studentOptional;
    }




    public static void main(String[] args) {

        List<Integer> integerList = Arrays.asList(1,3,5,7);
        

        System.out.println("Result is : " + performMultiplication(integerList));
        
        
        Optional<Integer> result = performMultiplicationWithNoInitialValue(integerList);

        if(result.isPresent()){
            System.out.println("Result is : " +result.get());
        }else{
            System.out.println("Result is : " +0);
        }

        // reduce			
       // System.out.println(getHighestGradeStudent().get());
        
        // map() + reduce()
        System.out.println(combineStudentNames());
        
    }
}
