package com.learnJava.functionalInterfaces;

import java.util.function.Function;

public class FunctionExample {

    static Function<String , String> f1 =  ((name) ->          {
    															return name.concat("XXXX");
    															});

    static Function<String , String> f2 =  ((name) -> {
    													 return name.concat("default");
    													});

    static Function<String , Integer> f3 =  (name) -> name.length();


 
    /*
      Understanding difference between andThen() && apply() method
      f1.andThen(f2) -> f1 is executed followed by f2
      f1.compose(f2) -> f2 is executed followed by f1
      
      	Result of andthen : java8XXXXdefault
		Result of compose : java8defaultXXXX
      
     */

    public static void main(String[] args) 
    {

      //  System.out.println("Result is : " + upperCase.apply("java8"));

        System.out.println("Result of andthen : " + f1.andThen(f2).apply("java8"));

        System.out.println("Result of compose : " + f1.compose(f2).apply("java8"));

     //   Function<String,String> abc = Function.identity();

     //   System.out.println(abc.apply("ABC"));


    }
}
