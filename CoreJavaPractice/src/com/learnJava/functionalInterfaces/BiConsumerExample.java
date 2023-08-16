package com.learnJava.functionalInterfaces;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BiConsumerExample
{
 
	 //Supplier -> no input argument , but returns a value i.e List<Student> object 
    public static  Supplier<List<Student>> studentsSupplier = () -> 
                                                                   {
                                                                	   
                                                                	   return StudentDataBase.getAllStudents();
                                                                   };
	

    public static void nameAndActivities()
    {

        BiConsumer<String , List<String>> studentBiConsumer = ((name , activities) -> 
                                                                                    {
                                                            	                      System.out.println(name + ":-" + activities);
                                                                                    });

        
        
        
                                                                       
        
        List<Student> students = studentsSupplier.get();
        
        students.forEach( (s) ->  { 
        	                         studentBiConsumer.accept(s.getName() , s.getActivities());
                                   });
        
        
     }

    
    /*
     OUTPUT :->
             Adam:-[swimming, basketball, volleyball]
	         Jenny:-[swimming, gymnastics, soccer]
	         Emily:-[swimming, gymnastics, aerobics]
     
     */
    
    
    public static void main(String[] args) 
    {

		/*
		 * BiConsumer<String, String> biConsumer = (a,b) -> { System.out.println(" a : "
		 * + a + " b : " + b ); };
		 * 
		 * 
		 * 
		 * 
		 * BiConsumer<Integer, Integer> multiply = (a,b) -> {
		 * System.out.println("Multiplication : " + (a * b)); };
		 * 
		 * 
		 * BiConsumer<Integer, Integer> addition = (a,b) -> {
		 * System.out.println("Addition : " + (a + b)); };
		 * 
		 * 
		 * BiConsumer<Integer, Integer> division = (a,b) -> {
		 * System.out.println("Division : " + (a / b)); };
		 * 
		 * // biConsumer.accept("java7" , "java8");
		 * 
		 * multiply.andThen(addition).andThen(division).accept(10,5);
		 */
		 

        nameAndActivities();

    }
}
