package com.learnJava.functionalInterfaces;

import com.learnJava.data.Student;
import com.learnJava.data.StudentDataBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionStudentExample 
{
   
	
    static Predicate<Student> p1 = ((s) ->
		{
		return s.getGradeLevel() > 2;
		});

    
    
	
    static Function<List<Student> , Map<String, Double>>  f1 = ((studentlsts) -> 
         {

             Map<String , Double> studentGradeMap = new HashMap<>();
          
             studentlsts.forEach((student -> {

                                              if(p1.test(student))
                                              {
                                               studentGradeMap.put(student.getName(),student.getGpa());
                                              }
                                           }));

         return studentGradeMap;

    });
    
    
    //Supplier -> no input argument , but returns a value i.e List<Student> object 
    public static  Supplier<List<Student>> studentsSupplierLst = () -> 
                                                                   {
                                                                	   return StudentDataBase.getAllStudents();
                                                                   };

                                                                   
                                                               
                                                                   
    public static void main(String[] args) {

       // System.out.println(f1.apply(studentsSupplierLst.get()));
        
        
        
   
        
        
     // Same code using Stream                                                               
        
   Map<String,List<String>> studentMap = studentsSupplierLst.get()
                           .stream()
                           .filter(p1)
                           .collect(Collectors.toMap(Student::getName , Student::getActivities));

    
    System.out.println(studentMap);
    
    /*
     
     {
         Emily=[swimming, gymnastics, aerobics], 
         James=[swimming, basketball, baseball, football], 
         Dave=[swimming, gymnastics, soccer], 
         James1=[swimming, basketball, baseball, football], 
         Sophia=[swimming, dancing, football]}
     
     */
    
    }
}
