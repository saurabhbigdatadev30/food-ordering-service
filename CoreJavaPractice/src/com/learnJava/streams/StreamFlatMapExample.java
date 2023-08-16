package com.learnJava.streams;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamFlatMapExample 
{

	
    
    static Consumer<? super List<String>> c2 = (studentActivities) ->  
    {
 	   System.out.println(studentActivities);
    };
    
    
    //****************   Understanding Differnce between map & flatMap **************** 
    
    /*
       flatMap is also a transformation similar to map . We use flatmap() in context of stream where each element in the stream 
       represents muliple elements 
       
       Each stream element represents 
       Stream<List>
       Stream<Array> 
       
     */
    public static List<String> printStudentActivities() 
    {

    	// Stream API with map() -> returns 
    	Stream<List<String>> activitiesbyEachStudent1 = StudentDataBase.getAllStudents()     			 //  List<Students>
    													               .stream()            			//   Stream<Students>
    																   .map(Student::getActivities);   //    Stream<List<String>>
    	 
    	//activitiesbyEachStudent1.forEach(c2);
    	
    		
    	
    	// Alternate
    	Stream<List<String>> activitiesbyEachStudent2 = StudentDataBase.getAllStudents()   // List<Students>
    																	.stream()         //  Stream<Students>
    																	.map((s) -> {
    																		         return s.getActivities();
    																	             });  // Stream<List<String>>
    	
    	
    	System.out.println("**********  MAP OUTPUT   ********** ");
    	activitiesbyEachStudent1.forEach(c2);
    	
    	// For every student, we have List of activities .. We get Stream<List<String>> . But we want list of activities of All students -> is  Stream<String>  	
    	// We have Stream<List<String> , using map()
    	
    	
    	// Converting Stream<List<String>> activitiesbyEachStudent2 = Stream<String> activitiesbyEachStudentFlatten
    	
    	  List<String> getstudentActivitiesFlatten = StudentDataBase.getAllStudents()
                  .stream()
                  .map(Student::getActivities) // This returns Stream<List<String>>
                  .flatMap(List::stream) //<Stream<String>
                  .collect(toList());
    	
    		System.out.println("GET STUDENT ACTIVITIES "  + "= " +getstudentActivitiesFlatten);
    	  
    	  
        List<String> studentActivitiesFlat = StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getActivities) // This returns Stream<List<String>>
                .flatMap(List::stream) //<Stream<String>
                .distinct()
                .collect(toList());

        return studentActivitiesFlat;

    }
    
    
    

    public static List<String> printUniqueStudentActivities() {

        List<String> studentActivities = StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(toList());

        return studentActivities;

    }

    public static long getStudentActivitiesCount() {

        long totalActivities = StudentDataBase.getAllStudents()
                .stream()
                .map(Student::getActivities)
                .flatMap(List::stream)
                //.distinct()
                .count();

        return totalActivities;

    }

    public static void main(String[] args) {


		
    	System.out.println("GET STUDENT ACTIVITIES DISTINCT  " + "=" + printStudentActivities());
    	System.out.println("Student Activities : " + getStudentActivitiesCount());
    	
    	
 /*
 
   Analysing Map VS Flatmap output 
    	
    	Map Output 
    	[swimming, basketball, volleyball]
     	[swimming, gymnastics, soccer]
		[swimming, gymnastics, aerobics]
	    [swimming, gymnastics, soccer]
	    [swimming, dancing, football]
        [swimming, basketball, baseball, football]

Flatmap Output
Student Activities : [swimming, basketball, volleyball, swimming, gymnastics, soccer, swimming, gymnastics, aerobics, swimming, gymnastics, soccer, swimming, dancing, football, swimming, basketball, baseball, football]
     
 
    	 
    	 */
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

}
