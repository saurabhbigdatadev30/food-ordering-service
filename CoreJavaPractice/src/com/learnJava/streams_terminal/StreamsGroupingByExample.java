package com.learnJava.streams_terminal;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

import java.lang.reflect.Array;

public class StreamsGroupingByExample 

{
	
	
	
	
	 public static void countOccuranceOfEachWordInString()
	    {
		 
		// Grouping by charecter -  Find the occurance of each charecter in the String 
			
		    String s1 = "AADBCABCBA";
			Map<String, Long> m1 = new HashMap<>();
			m1 = Arrays.stream(s1.split("")).collect(Collectors.groupingBy(Function.identity(), counting()));
			
			System.out.println(m1);
			
			
	    }

    public static void groupingByGender()
    {

        Map<String,List<Student>> studentMap =  StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy(Student::getGender));
       
         //Stream.of(studentMap).forEach(System.out::println);
          System.out.println(studentMap);
    }

   /*
   
   {
   female =
			[
			  Student{name='Jenny', gradeLevel=2, gpa=3.8, gender='female', activities=[swimming, gymnastics, soccer]}, 
			  Student{name='Emily', gradeLevel=3, gpa=4.0, gender='female', activities=[swimming, gymnastics, aerobics]}, 
			  Student{name='Sophia', gradeLevel=6, gpa=3.5, gender='female', activities=[swimming, dancing, football]}
			 ],

	   
   male =
          [
		    Student{name='Adam', gradeLevel=2, gpa=3.6, gender='male', activities=[swimming, basketball, volleyball]}, 
		    Student{name='Dave', gradeLevel=3, gpa=5.0, gender='male', activities=[swimming, gymnastics, soccer]}, 
		    Student{name='James', gradeLevel=4, gpa=8.9, gender='male', activities=[swimming, basketball, baseball, football]}
		   ]
		   
  }
   
   
    */
    
    

    
    public  static void customizedGroupingBy()
    {

        Map<String,List<Student>> studentMap =  StudentDataBase.getAllStudents()
                .stream()
                .collect(Collectors.groupingBy((student) ->  
                										    student.getGpa()>= 3.8 ?  "OUTSTANDING" : "AVERAGE"));

        Stream.of(studentMap).forEach(System.out::println);
    }
    
    
    /*
    
    OUTPUT :->     Map< String , List<Student> > studentMap 
    {
       AVERAGE= [
                  Student{name='Adam', gradeLevel=2, gpa=3.6, gender='male', activities=[swimming, basketball, volleyball]}, 
   			      Student{name='Sophia', gradeLevel=6, gpa=3.5, gender='female', activities=[swimming, dancing, football]}
   			    ], 
    
    
    OUTSTANDING= [
                   Student{name='Jenny', gradeLevel=2,  gpa=3.8, gender='female', activities=[swimming, gymnastics, soccer]}, 
                   Student{name='Emily', gradeLevel=3,  gpa=4.0, gender='female', activities=[swimming, gymnastics, aerobics]}, 
   			       Student{name='Dave', gradeLevel=3,   gpa=5.0, gender='male',   activities=[swimming, gymnastics, soccer]}, 
   			       Student{name='James', gradeLevel=4,  gpa=8.9, gender='male',   activities=[swimming, basketball, baseball, football]}
   			 ]
    }
          
        */
    
    
    /**
       TO DO - Group By = Grade . For each Grade ..  find the students in Category = OUTSTANDING AND Category = AVERAGE  
     */
    

    
    
    public  static void twoLevelGrouping(){

        Map<Integer,  Map<String , List<Student>>> studentMap =  StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getGradeLevel, groupingBy(student -> student.getGpa()>= 3.8 ?  "OUTSTANDING" : "AVERAGE")));

       // Stream.of(studentMap).forEach(System.out::println);
        System.out.println(studentMap);

    } 
    
   /*
      TO DO - Group By = Gender . For each gender..  find the students in Category = OUTSTANDING AND Category = AVERAGE
      
    */
    
    public  static void twoLevelGrouping_1()
    {
        Map<String,  Map<String , List<Student>>> studentMap =  StudentDataBase.getAllStudents().stream()
                		.collect(groupingBy(Student::getGender, groupingBy(student -> student.getGpa()>= 3.8 ?  "OUTSTANDING" : "AVERAGE")));

       // Stream.of(studentMap).forEach(System.out::println);
        System.out.println(studentMap);

    }
    
    
    public  static void twoLevelGrouping_XX()
    {
    	
        Function<Student , String> f1 = (student) -> student.getGpa()>= 3.8 ?  "OUTSTANDING" : "AVERAGE" ;
    		
        Map<String,  Map<String , List<Student>>> studentMap =  StudentDataBase.getAllStudents().stream()
                		.collect(groupingBy(Student::getGender, groupingBy(f1)));

       // Stream.of(studentMap).forEach(System.out::println);
        System.out.println(studentMap);

    }
    
    
    
  /*
   Map<String,  Map<String , List<Student>>> studentMap 
   
    {
 female=
    {
	   AVERAGE=
	      [
		    Student{name='Sophia', gradeLevel=6, gpa=3.5, gender='female', activities=[swimming, dancing, football]}
		  ], 
	   
	 OUTSTANDING=
	     [ 
		   Student{name='Jenny', gradeLevel=2, gpa=3.8, gender='female', activities=[swimming, gymnastics, soccer]}, 
	       Student{name='Emily', gradeLevel=3, gpa=4.0, gender='female', activities=[swimming, gymnastics, aerobics]}
		 ]
		 }, 
	   
   
   male= 
        {
		 AVERAGE= [
		           Student{name='Adam', gradeLevel=2, gpa=3.6, gender='male', activities=[swimming, basketball, volleyball]}
				   ], 
		 
		 OUTSTANDING= [
		               Student{name='Dave', gradeLevel=3, gpa=5.0, gender='male', activities=[swimming, gymnastics, soccer]}, 
					   Student{name='James', gradeLevel=4, gpa=8.9, gender='male', activities=[swimming, basketball, baseball, football]}
					   ]
		}
					   
					   
} 

     
   */
    
    
    
    
    
    

    /**
     * Grouping by Two parameters
     */
    public  static void twoLevelGrouping_2(){

        Map<String,Integer> nameNoteBooksMap = StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getName,
                        summingInt(Student::getNoteBooks)));// second argument can be of any type of collector

        System.out.println(nameNoteBooksMap);
    }

    /**
     * Grouping by Two parameters
     */
    public  static void twoLevelGrouping_3(){

        Map<String,Set<Student>> nameNoteBooksMap = StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getName,
                        toSet()));// second argument can be of any type of collector

        System.out.println(nameNoteBooksMap);
    }


    public static void threeArgumentGroupingBy()
    {
        LinkedHashMap<String,Set<Student>> studentMap = StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getName,LinkedHashMap::new,
                        toSet()));
        System.out.println(" studentMap : " + studentMap);
    }


    // For each Grade, find the student with max GPA
    public  static void calculteTopGpaStudentinEachGrade()
    
     {

        Map<Integer, Optional<Student>> studentMapOptional =  StudentDataBase.getAllStudents()
        														 .stream()
        														 .collect(groupingBy(Student::getGradeLevel , maxBy(Comparator.comparingDouble(Student::getGpa))));
        
        Stream.of(studentMapOptional).forEach(System.out::println);


        Map<Integer, Student> studentMap =  StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getGradeLevel,
                        collectingAndThen(maxBy(Comparator.comparingDouble(Student::getGpa))
                        ,Optional::get
                )));

      //  Stream.of(studentMap).forEach(System.out::println);
    }

    public  static void calculteleastGpaStudentinEachGrade(){

        Map<Integer, Optional<Student>> studentMapOptional =  StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getGradeLevel,minBy(Comparator.comparingDouble(Student::getGpa))
                ));

        Stream.of(studentMapOptional).forEach(System.out::println);


        Map<Integer, Student> studentMap =  StudentDataBase.getAllStudents().stream()
                .collect(groupingBy(Student::getGradeLevel,
                        collectingAndThen(minBy(Comparator.comparingDouble(Student::getGpa))
                                ,Optional::get
                        )));

        Stream.of(studentMap).forEach(System.out::println);
    }

    public static void main(String[] args) {

    	//countOccuranceOfEachWordInString();
        // groupingByGender();
        // customizedGroupingBy();
        //groupByGrade();
        //twoLevelGrouping();
         // twoLevelGrouping_1();
          //twoLevelGrouping_XX();
        //twoLevelGrouping_2();
        //twoLevelGrouping_3();
           calculteTopGpaStudentinEachGrade();
        //calculteleastGpaStudentinEachGrade();
       // threeArgumentGroupingBy();
    }
}
