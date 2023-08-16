package com.learnJava.functionalInterfaces;

public class MyFunctionalInterfaceImplementation implements MyFunctionalInterface
{


	// lambda way of overriding abstract 
	MyFunctionalInterface  f1 = (i,j) -> 
	                               {
	                            	   return 23;
	                               };

	@Override
	public int testMethod(int i, int j) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

	

}
  

