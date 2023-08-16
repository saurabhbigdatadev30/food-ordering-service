package com.learnJava.functionalInterfaces;

public class FunctionExample1
{
	  public static String appendDefault(String input) 
	  {
	  
	    String s =  FunctionExample.f1.apply(input); 
	    System.out.println(s);
	    return s;
	    }
	  
	  public static void main(String[] args) {
		  appendDefault("AAAA");
	  }
	 

}
