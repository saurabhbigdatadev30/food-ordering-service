package com.learnJava.functionalInterfaces;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ConsumerExample 

{
	
	  static Predicate<Student> p1 = ((s) ->
												{
												  return s.getGradeLevel() >= 3;
												});



      static Predicate<Student> p2 = (s) ->   {
	                                           return s.getGpa() >= 3.9;
										       };


    static Consumer<Student>  c1 = p ->  
                                       {
                                    	 System.out.println(p);
                                       };

                                       
    static Consumer<Student>  c2 = p -> {
    									  System.out.print(p.getName().toUpperCase() + "\n");
    									 };
    									
    									
    static Consumer<Student>  c3 = p ->    {
    										 System.out.println(p.getActivities() + "\n");
    										};
    										
    public static void printName()
    {
        List<Student> personList = StudentDataBase.getAllStudents();
            personList.forEach(c3);
    }

    public static void printNameAndActivities(){
       // System.out.println("printNameAndActivities : ");
        List<Student> personList = StudentDataBase.getAllStudents();
        personList.forEach(c2.andThen(c3));
    }

    
    // ******  Combining Functional Interface = [Consumer with Predicate] ******
    public static void printNameAndActivitiesUsingCondition()
    {
        List<Student> studentList = StudentDataBase.getAllStudents();
        
        studentList.forEach( (s) ->  
                                  {
                                    //if(s.getGradeLevel() >=3 && s.getGpa()>= 3.9)
                                     if	(p1.test(s)  && p2.test(s))
                                    	{
                                	      c2.andThen(c3).accept(s);
                                    	}
                                  } );
    }   
    
    public static void main(String[] args) 
    {

        Consumer<String> c1 = (s) ->  
                                         {
                                        	System.out.println(s.toUpperCase());
                                         };

       // c1.accept("java8");

         //  printName();
          //  printNameAndActivities();
           printNameAndActivitiesUsingCondition();


    



    }
}
